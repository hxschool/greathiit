package com.thinkgem.jeesite.modules.payment.pay.strategy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.thinkgem.jeesite.modules.payment.pay.PayStrategy;
import com.thinkgem.jeesite.modules.payment.pay.enums.PayType;

@Service("alipayStrategy")
public class AlipayStrategy implements PayStrategy {
	private static String APP_ID = "2016091400512924";
	private static String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwKn56xSqTecAfifTvd/eAX+Vdy+72U5Kz0P5wH/+8FFO9mZv3r0sQiilDpVKJhYL824VZHeUp0QnqGekIch0ZM+O0Bvu4prTKMql4wWa+uM5/pxfJ4ijgG8aIsvDlNmcSWQPIvxJ+xLEaZKmimbA+JL6vl3+31i6UlgBIo3rfyLwdWcy1AqkhqGx+sx1bAyoD/H1ariQK+LDNLv14ry8Z9sWnvPfRPvi/KH4YP9Udeam0H/gYAyMB9uT3J5xVcM/UuMQTnX0MmthZtdq9oB3oO7inAzfTWOqPcr2Lj8RF4NRlqvPAHhXuP0ZqJJWk682YyvEJnCVW+PgJQPDqm9f/AgMBAAECggEAXDqzIwVsp+8a9agdUu4E5vHNvJCTF+VjLb8iJoOqOMaPnPj53e+pJgyfU+oOzy7WCBnJs4FiQ8VMIoLULPbJYzN2Z/8r9F1a0204qp7ecsWAXvaVDr3X1pTUGSdN1ULpLYLq50OZpELH0eCXqyg5TB+isX1ogU+h7Wqm1WC5aMToQlyMkXIoSgrK6x48sYjIeRkykqblIRVyPM7fbtWDXcDE2VQZjdZ1kWA+I9udbi2497ZxBotQxrVVx865xkYYhEvWXBZ4Eor0wdlcdwDQfiixRYp8+faCGNK2XtT12ccWOVmrrWml+QJwKtmVuRx0fG6H5/e7sEdOTdJe0HTKqQKBgQDvE+Xx/dHrVBNEWru63uIyPRVnEeL2s68hRQd7aOCmO6rfUCdnxRQ97tYOrkExmyeL9fHFiWtp13vaeH3rnDYEEQDVzzpFzMJQlJ8/QJPhHB5I4KMfV4r3+gAzZbdzrJYkJCBs64k2/kRZz17LY7xoNyu1SgVxNcVqgKNmHI7uHQKBgQC8oqG2EP65R7TUaXdHJhzosP3gOrYBA6xZ/wCceA/gxhdp2CuZoMz4QA3qXaX1605ADbS5Zxkigos+NQChhIhN2NS37ZQEof1g+SitDKDcNI4TGRleDjvR+8Vwbr7cFlOPT8zZSHkt+eDJVWDQWF+EHV39jk2MquHdGyPiGCZzywKBgHmtq+uU3mEiQkgY0dETHRaqphj2LoiW/Pw20M8LmsKgPaA4gEW9NUcsJoAESiQALol6XFnITgXpaRzRFG107Lz3FnC7bpIV25P9gGtF5727fOJkikEqYg1BjRabn1ndLfEo/ePRoN1/XbRD6aAkm8CCy9kR2mE7F8XTwNX4lPo1AoGBAIEgWs5E5/lw6ooU//+GSXfH4KHjzr73Ar5AXuy9CzF0qIZd4cqyVl0BjAUIwhiLUO9r8nCP8Ja9AhA9PAdUr/GKImMdkJtzP/1n0b5TzLGwkCjmn4TQ/YYKcOc8CA6kgeKyX6nFgJ5GVOe9OB6mwAuyBqsRBGjseNxgPboBiiDtAoGAfi6OUVzhQwJDMbAPmRjpphWY60pqC4kzU0uWeAoCZ+CFE2lZo31ijImQBhJXzJVyYLPdhOshMx6BkCy6yJuD8eQDfzksB+ATv1syFkLfMwHtF3dECxWxq1D51U+NHsLIO79zA20NQhjW6BYeUY3VzLX7b4SNZxSMRN3CAIbHrDo=";
	private static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvY7SnWgDhMmBnCxr5LtJC6/4Yj573AmjmJR05lSJCViNkAHHOLjtWQKODqYnUK7gtRx7gyoFpegl8ZOiAwOHCBi3oY0WW+ABt3hqD6dpAOUHndeDDQaqUwbMAeH/EtUK6KEht7ansJze6onC1cp38db71HBe+gSiwqg+9GgkIbPx2/s7f4K+YAX5j3OBJu3XWVcOivjjWOW/PZtGhTF25jFV+9Pn/XCmqOCZbCXPEuGG0R07PnhVqBO2W7GTjvoUA6Vu4LJNaxM/z7FKFD0r7Ts/Y9jNw9Q1NDj+H/pjMp+Hr8rvhx3lJfQCxyrr954chuhh6Oah00xgonfIM9xI+wIDAQAB";
	private static String CHARSET="UTF-8";
	@Value("{alipay.notifyUrl}")
    private String notifyUrl;
	@Value("{alipay.returnUrl}")
    private String returnUrl;
	@Override
	public String generatePayParams(PayType payType, Map<String, Object> params) {
		
		String totalAmount = params.get("totalAmount").toString();
		String subject =  params.get("subject").toString();
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); 
		AlipayTradePrecreateRequest 		alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
		AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
		alipayTradePrecreateModel.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
		alipayTradePrecreateModel.setTotalAmount(totalAmount);
		alipayTradePrecreateModel.setSubject(subject);
		alipayTradePrecreateRequest.setBizModel(alipayTradePrecreateModel);
		alipayTradePrecreateRequest.setReturnUrl("http://wechat.tunnel.qydev.com/callback/return_url.html");
		alipayTradePrecreateRequest.setNotifyUrl("http://wechat.tunnel.qydev.com/callback/notify_url.html");
		AlipayTradePrecreateResponse alipayTradePrecreateResponse;
		try {
			alipayTradePrecreateResponse = alipayClient.execute(alipayTradePrecreateRequest);
			return alipayTradePrecreateResponse.getQrCode();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} 
		return "";
	}

}
