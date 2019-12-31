package com.thinkgem.jeesite.modules.pay.strategy;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.callback.constants.AlipayTradeStatus;
import com.thinkgem.jeesite.modules.callback.result.AlipayResult;
import com.thinkgem.jeesite.modules.pay.PayStrategy;
import com.thinkgem.jeesite.modules.pay.enums.PayType;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;


public class AlipayStrategy extends TradeStrategy implements PayStrategy {
    private static Logger logger = LoggerFactory.getLogger(AlipayStrategy.class);
    public static String APP_ID = "2016091400512924";
	private static String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwKn56xSqTecAfifTvd/eAX+Vdy+72U5Kz0P5wH/+8FFO9mZv3r0sQiilDpVKJhYL824VZHeUp0QnqGekIch0ZM+O0Bvu4prTKMql4wWa+uM5/pxfJ4ijgG8aIsvDlNmcSWQPIvxJ+xLEaZKmimbA+JL6vl3+31i6UlgBIo3rfyLwdWcy1AqkhqGx+sx1bAyoD/H1ariQK+LDNLv14ry8Z9sWnvPfRPvi/KH4YP9Udeam0H/gYAyMB9uT3J5xVcM/UuMQTnX0MmthZtdq9oB3oO7inAzfTWOqPcr2Lj8RF4NRlqvPAHhXuP0ZqJJWk682YyvEJnCVW+PgJQPDqm9f/AgMBAAECggEAXDqzIwVsp+8a9agdUu4E5vHNvJCTF+VjLb8iJoOqOMaPnPj53e+pJgyfU+oOzy7WCBnJs4FiQ8VMIoLULPbJYzN2Z/8r9F1a0204qp7ecsWAXvaVDr3X1pTUGSdN1ULpLYLq50OZpELH0eCXqyg5TB+isX1ogU+h7Wqm1WC5aMToQlyMkXIoSgrK6x48sYjIeRkykqblIRVyPM7fbtWDXcDE2VQZjdZ1kWA+I9udbi2497ZxBotQxrVVx865xkYYhEvWXBZ4Eor0wdlcdwDQfiixRYp8+faCGNK2XtT12ccWOVmrrWml+QJwKtmVuRx0fG6H5/e7sEdOTdJe0HTKqQKBgQDvE+Xx/dHrVBNEWru63uIyPRVnEeL2s68hRQd7aOCmO6rfUCdnxRQ97tYOrkExmyeL9fHFiWtp13vaeH3rnDYEEQDVzzpFzMJQlJ8/QJPhHB5I4KMfV4r3+gAzZbdzrJYkJCBs64k2/kRZz17LY7xoNyu1SgVxNcVqgKNmHI7uHQKBgQC8oqG2EP65R7TUaXdHJhzosP3gOrYBA6xZ/wCceA/gxhdp2CuZoMz4QA3qXaX1605ADbS5Zxkigos+NQChhIhN2NS37ZQEof1g+SitDKDcNI4TGRleDjvR+8Vwbr7cFlOPT8zZSHkt+eDJVWDQWF+EHV39jk2MquHdGyPiGCZzywKBgHmtq+uU3mEiQkgY0dETHRaqphj2LoiW/Pw20M8LmsKgPaA4gEW9NUcsJoAESiQALol6XFnITgXpaRzRFG107Lz3FnC7bpIV25P9gGtF5727fOJkikEqYg1BjRabn1ndLfEo/ePRoN1/XbRD6aAkm8CCy9kR2mE7F8XTwNX4lPo1AoGBAIEgWs5E5/lw6ooU//+GSXfH4KHjzr73Ar5AXuy9CzF0qIZd4cqyVl0BjAUIwhiLUO9r8nCP8Ja9AhA9PAdUr/GKImMdkJtzP/1n0b5TzLGwkCjmn4TQ/YYKcOc8CA6kgeKyX6nFgJ5GVOe9OB6mwAuyBqsRBGjseNxgPboBiiDtAoGAfi6OUVzhQwJDMbAPmRjpphWY60pqC4kzU0uWeAoCZ+CFE2lZo31ijImQBhJXzJVyYLPdhOshMx6BkCy6yJuD8eQDfzksB+ATv1syFkLfMwHtF3dECxWxq1D51U+NHsLIO79zA20NQhjW6BYeUY3VzLX7b4SNZxSMRN3CAIbHrDo=";
	public static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvY7SnWgDhMmBnCxr5LtJC6/4Yj573AmjmJR05lSJCViNkAHHOLjtWQKODqYnUK7gtRx7gyoFpegl8ZOiAwOHCBi3oY0WW+ABt3hqD6dpAOUHndeDDQaqUwbMAeH/EtUK6KEht7ansJze6onC1cp38db71HBe+gSiwqg+9GgkIbPx2/s7f4K+YAX5j3OBJu3XWVcOivjjWOW/PZtGhTF25jFV+9Pn/XCmqOCZbCXPEuGG0R07PnhVqBO2W7GTjvoUA6Vu4LJNaxM/z7FKFD0r7Ts/Y9jNw9Q1NDj+H/pjMp+Hr8rvhx3lJfQCxyrr954chuhh6Oah00xgonfIM9xI+wIDAQAB";
	public static String CHARSET="UTF-8";	
	public static String SIGNTYPE = "RSA2";
    private String notifyUrl;
    private String returnUrl;
	private PropertiesLoader propertiesLoader;
	
    private ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	public AlipayStrategy() {
		propertiesLoader = new PropertiesLoader("jeesite.properties");
		notifyUrl = propertiesLoader.getProperty("alipay.notifyUrl");
		returnUrl = propertiesLoader.getProperty("alipay.returnUrl");
	}
	
	@Override
	public String generatePayParams(PayType payType, Map<String, Object> params) {
		String outTradeNo = params.get("outTradeNo").toString();
		String totalAmount = params.get("totalAmount").toString();
		String subject =  params.get("subject").toString();
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); 
		AlipayTradePrecreateRequest alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
		AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
		alipayTradePrecreateModel.setOutTradeNo(outTradeNo);
		alipayTradePrecreateModel.setTotalAmount(totalAmount);
		alipayTradePrecreateModel.setSubject(subject);
		alipayTradePrecreateRequest.setBizModel(alipayTradePrecreateModel);
		alipayTradePrecreateRequest.setReturnUrl(returnUrl);
		alipayTradePrecreateRequest.setNotifyUrl(notifyUrl);
		AlipayTradePrecreateResponse alipayTradePrecreateResponse;
		try {
			alipayTradePrecreateResponse = alipayClient.execute(alipayTradePrecreateRequest);
			return alipayTradePrecreateResponse.getQrCode();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} 
		return "";
	}
	
	@Override
	public String callbackParams(PayType payType, Map<String, String> params) {
		try {
			boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY,
					CHARSET, SIGNTYPE);
			  if (signVerified) {
				  	this.check(params);
				  	executorService.execute(new Runnable() {

						@Override
						public void run() {
							Gson gson = new Gson();
						  	String resultJson = gson.toJson(params);
						  	AlipayResult alipayResult = gson.fromJson(resultJson, AlipayResult.class);
			                logger.info("支付宝回调签名认证成功");
			                if (alipayResult.getTrade_status().equals(AlipayTradeStatus.TRADE_SUCCESS)
		                            || alipayResult.getTrade_status().equals(AlipayTradeStatus.TRADE_FINISHED)) {
			                	String out_trade_no = alipayResult.getOut_trade_no(); 
		                        update(out_trade_no);
		                    } else {
		                        logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",alipayResult.getTrade_status(),resultJson);
		                    }
						}
				  		
				  	});
				  	
			  }
		} catch (AlipayApiException e) {
			e.printStackTrace();
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
        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue(); 
        long pay_amount = new BigDecimal(traderecord.getPayAmount()).multiply(new BigDecimal(100)).longValue(); 
        if (total_amount != pay_amount) {
            throw new AlipayApiException("error total_amount");
        }
        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(APP_ID)) {
            throw new AlipayApiException("app_id不一致");
        }
    }
}
