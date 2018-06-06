package com.thinkgem.jeesite.modules.payment.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.order.OrderService;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "payment")
public class QRCodeController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private TraderecordService  traderecordService;
	@Autowired
	private OrderService  orderService;

	@RequestMapping("qrcode")
	public String qrcode( HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String ua = request.getHeader("User-Agent");
		String client = "alipay";
		String channelId = "ALIPAY_WAP";
		if (StringUtils.isEmpty(ua)) {
			String errorMessage = "User-Agent为空！";

		} else {
			if (ua.contains("Alipay")) {
				client = "alipay";
				channelId = "ALIPAY_WAP";
			} else if (ua.contains("MicroMessenger")) {
				client = "wx";
				channelId = "WX_JSAPI";
			}else if (ua.contains("QQ")) {
				client = "qq";
				channelId = "QQ_JSAPI";
			}
		}

		String key = request.getParameter("k");
		Traderecord traderecord = (Traderecord) JedisUtils.getObject(key);
		return "modules/payment/student/index";
	}
	
	
}
