package com.thinkgem.jeesite.modules.pay;

import java.util.Map;

import com.thinkgem.jeesite.modules.pay.enums.PayType;


public interface PayStrategy {
	String generatePayParams(PayType payType, Map<String, Object> params);
	
	String callbackParams(PayType payType, Map<String, String> params);
	
}
