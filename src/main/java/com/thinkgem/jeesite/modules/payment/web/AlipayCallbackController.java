package com.thinkgem.jeesite.modules.payment.web;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.thinkgem.jeesite.modules.callback.constants.AlipayTradeStatus;
import com.thinkgem.jeesite.modules.pay.strategy.AlipayStrategy;
import com.thinkgem.jeesite.modules.payment.alipay.AlipayNotifyParam;
import com.thinkgem.jeesite.modules.payment.alipay.PrintResult;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;
import com.thinkgem.jeesite.modules.score.service.ScoreService;
import com.thinkgem.jeesite.modules.storage.service.MinioTemplate;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import io.minio.messages.Bucket;

@Controller
public class AlipayCallbackController {
    private static Logger logger = LoggerFactory.getLogger(AlipayCallbackController.class);


    private ExecutorService executorService = Executors.newFixedThreadPool(20);

	@Autowired
	private TraderecordService traderecordService;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private MinioTemplate minioTemplate;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private SystemService systemService;
	@RequestMapping("ok")
	@ResponseBody
	public String send(HttpServletRequest request) {
		try {
			rabbitTemplate.convertAndSend("hello word");
			List<Bucket> list = minioTemplate.getAllBuckets();
			for(Bucket b:list) {
				System.out.println(b.name());
			}
			String bucketName = "greathiit-print-result";
			minioTemplate.createBucket(bucketName);
			minioTemplate.putObject(bucketName, "3.png", new FileInputStream(new File("D:/3.png")));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "ok";
	}
    /**
     * <pre>
     * 第一步:验证签名,签名通过后进行第二步
     * 第二步:按一下步骤进行验证
         * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
         * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
         * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
         * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
         * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * </pre>
     * 
     * @param params
     * @return
     */
    @RequestMapping("alipay_callback")
    @ResponseBody
    public String callback(HttpServletRequest request) {
        Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        String paramsJson = JSON.toJSONString(params);
        logger.info("支付宝回调，{}", paramsJson);
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayStrategy.ALIPAY_PUBLIC_KEY,
            		AlipayStrategy.CHARSET, AlipayStrategy.SIGNTYPE);
            if (signVerified) {
                logger.info("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                this.check(params);
                // 另起线程处理业务
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        AlipayNotifyParam param = buildAlipayNotifyParam(params);
                        String trade_status = param.getTradeStatus();
                        // 支付成功
                        if (trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS)
                                || trade_status.equals(AlipayTradeStatus.TRADE_FINISHED)) {
                            // 处理支付成功逻辑
                        	
                            try {
                            	
                                String outTradeNo = params.get("out_trade_no");
                                Traderecord traderecord = traderecordService.get(outTradeNo);
                                if(traderecord.getStatus().equals("00")) {
                                	 traderecord.setStatus("20");
                             		 traderecord.setPayTime(new Date());
                                     traderecordService.save(traderecord);
                                     User user =  systemService.getUser(traderecord.getUser().getId());
                                    
                                     List<File> list = scoreService.write(user);
                                     PrintResult printResult = new PrintResult();
                                     printResult.setName(user.getName());
                                     printResult.setStudentNumber(user.getNo());
                                     List<String> imgs = new ArrayList<String>();
                                     for(File file:list) {
                                    	 String filename = file.getName();
                                         int index = filename.lastIndexOf(".");
                                    	 FileInputStream stream = new FileInputStream(file);
                                    	 String md5 = DigestUtils.md5Hex(stream);
                                         String ext = filename.substring(index + 1);
                                         String objectName = md5.concat(".").concat(ext);
                                         String bucketName = "greathiit-print-result";
                                         minioTemplate.putObject(bucketName, objectName, stream);
                                         //http://wangwei.com:9000/greathiit-print-result/3.png
                                         String url = "http://img7.it.greathiit.com/greathiit-print-result/".concat(objectName);
                                         imgs.add(url);
                                     }
                                     printResult.setImg(imgs);
                                     rabbitTemplate.convertAndSend(printResult);
                                }
                                /*
                                    // 处理业务逻辑。。。
                                    myService.process(param);
                                */                      

                            } catch (Exception e) {
                                logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
                            }
                        } else {
                            logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",trade_status,paramsJson);
                        }
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return "success";
            } else {
                logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                return "failure";
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            return "failure";
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }

    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = JSON.toJSONString(params);
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }

    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * 
     * @param params
     * @throws AlipayApiException
     */
    private void check(Map<String, String> params) throws AlipayApiException {      
        String outTradeNo = params.get("out_trade_no");

        Traderecord traderecord = traderecordService.get(outTradeNo);
        if (traderecord == null) {
            throw new AlipayApiException("out_trade_no错误");
        }
        
        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();       
        if (total_amount != traderecord.getPayAmount()) {
            throw new AlipayApiException("error total_amount");
        }
        
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        /*
        Order order = getOrderByOutTradeNo(outTradeNo); // 这个方法自己实现
        
        if (order == null) {
            throw new AlipayApiException("out_trade_no错误");
        }       
		*/
        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        /*
        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();       
        if (total_amount != order.getPayPrice().longValue()) {
            throw new AlipayApiException("error total_amount");
        }
        */

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AlipayStrategy.APP_ID)) {
            throw new AlipayApiException("app_id不一致");
        }
    }

}