package com.thinkgem.jeesite.modules.pay.strategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alipay.api.AlipayApiException;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.callback.result.WechatResult;
import com.thinkgem.jeesite.modules.pay.PayStrategy;
import com.thinkgem.jeesite.modules.pay.enums.PayType;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;
import com.thinkgem.jeesite.wxpay.sdk.WXPay;
import com.thinkgem.jeesite.wxpay.sdk.WXPayConfigImpl;

public class WeChatStrategy extends TradeStrategy implements PayStrategy {
	private static Logger logger = LoggerFactory.getLogger(WeChatStrategy.class);
	private final static String WECHAT_PAY_SUCCESS = "SUCCESS";
	
    private WXPay wxpay;
    private WXPayConfigImpl config;
    private String notifyUrl;
    private PropertiesLoader propertiesLoader = null;
    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    public WeChatStrategy() {
    	propertiesLoader = new PropertiesLoader("jeesite.properties");
    	notifyUrl = propertiesLoader.getProperty("wechat.notifyUrl");
    	try {
			config = WXPayConfigImpl.getInstance();
			wxpay = new WXPay(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
	@Override
	public String generatePayParams(PayType payType, Map<String, Object> params) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("body",params.get("body").toString());
        data.put("out_trade_no", params.get("out_trade_no").toString());
        data.put("fee_type", "CNY");
        BigDecimal amount = new BigDecimal(params.get("total_fee").toString()).multiply(new BigDecimal(100));
        data.put("total_fee", String.valueOf(amount));
        data.put("spbill_create_ip", params.get("spbill_create_ip").toString());
        data.put("notify_url", notifyUrl);
        data.put("trade_type", "NATIVE");
        data.put("product_id", "12");
        
        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
	      
		return null;
	}
	//{transaction_id=4307324245220180613165742436491, nonce_str=e942de733151445d92e3546bf457ae69, bank_type=CMC, openid=sandboxopenid, sign=876F229DE7E44157818B6B471FCB677C, err_code=SUCCESS, err_code_des=SUCCESS, return_msg=OK, fee_type=CNY, mch_id=1458126002, cash_fee=379, device_info=, out_trade_no=1528880258091, cash_fee_type=CNY, total_fee=379, appid=wx5b0885bb488038f6, settlement_total_fee=379, trade_type=NATIVE, result_code=SUCCESS, time_end=20180613165742, attach=sandbox_attach, is_subscribe=Y, return_code=SUCCESS}
	@Override
	public String callbackParams(PayType payType, Map<String, String> params) {
		String return_code = params.get("return_code");
		if(!StringUtils.isEmpty(return_code)&&return_code.equals(WECHAT_PAY_SUCCESS)) {
			try {
				if(wxpay.isPayResultNotifySignatureValid(params)) {
					this.check(params);
					executorService.execute(new Runnable() {

						@Override
						public void run() {
							Gson gson = new Gson();
						  	String resultJson = gson.toJson(params);
						  	WechatResult wechatResult = gson.fromJson(resultJson, WechatResult.class);
			                logger.info("支付宝回调签名认证成功");
			                if (wechatResult.getResult_code().equals(WECHAT_PAY_SUCCESS)) {
			                	String out_trade_no = wechatResult.getOut_trade_no(); 
		                        update(out_trade_no);
		                    } else {
		                        logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",wechatResult.getResult_code(),resultJson);
		                    }
						}
				  		
				  	});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	
	private void check(Map<String, String> params) throws AlipayApiException {      
        String outTradeNo = params.get("out_trade_no");
        TraderecordService traderecordService = SpringContextHolder.getBean(TraderecordService.class);
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        Traderecord traderecord=  traderecordService.get(outTradeNo);
        if(StringUtils.isEmpty(traderecord)) {
        	 throw new AlipayApiException("out_trade_no错误");
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        long total_amount = new BigDecimal(params.get("total_fee")).longValue(); 
        long pay_amount = new BigDecimal(traderecord.getPayAmount()).multiply(new BigDecimal(100)).longValue(); 
        if (total_amount != pay_amount) {
            throw new AlipayApiException("error total_amount");
        }
        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("appid").equals(config.getAppID())) {
            throw new AlipayApiException("app_id不一致");
        }
    }
}
