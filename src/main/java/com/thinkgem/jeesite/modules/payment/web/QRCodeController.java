package com.thinkgem.jeesite.modules.payment.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.pay.enums.PayType;
import com.thinkgem.jeesite.modules.payment.pay.strategy.StrategyContext;

@Controller
@RequestMapping(value = "payment")
public class QRCodeController {


	@RequestMapping("qrcode")
	public void qrcode( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		String ua = request.getHeader("User-Agent");
		PayType payType = null;
		if (StringUtils.isEmpty(ua)) {
			String errorMessage = "User-Agent为空！";
		} else {
			if (ua.contains("Alipay")) {
				payType = PayType.ALIPAY_APP;
				
			} else if (ua.contains("MicroMessenger")) {
				payType = PayType.WECHAT_APP;
			}
		}
		
		StrategyContext strategyContext = new StrategyContext();

		String location = strategyContext.generatePayParams(payType, params);
		
		String key = request.getParameter("k");
		Traderecord traderecord = (Traderecord) JedisUtils.getObject(key);
		
		response.sendRedirect(location);
		
	}
	//{nonce_str=flO4TZZjfKqlHnYI, code_url=weixin://wxpay/bizpayurl?pr=7W4oUM3, appid=wx5b0885bb488038f6, sign=92C30570DA775BFA2C7E4CF891627479DB5F49E75CA73E27E68175986FCE45B4, trade_type=NATIVE, return_msg=OK, result_code=SUCCESS, mch_id=1458126002, return_code=SUCCESS, prepay_id=wx07161944483109095c7bc3eb0828112306}

}
