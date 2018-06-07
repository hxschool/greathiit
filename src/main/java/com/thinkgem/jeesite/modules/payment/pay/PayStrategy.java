package com.thinkgem.jeesite.modules.payment.pay;

import java.util.Map;

import com.thinkgem.jeesite.modules.payment.pay.enums.PayType;


public interface PayStrategy {
	String generatePayParams(PayType payType, Map<String, Object> params);
}
