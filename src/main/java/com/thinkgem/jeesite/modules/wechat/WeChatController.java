package com.thinkgem.jeesite.modules.wechat;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.wechat.param.WechatParamName;
import com.thinkgem.jeesite.modules.wechat.service.WechatService;
import com.thinkgem.jeesite.modules.wechat.util.SignUtil;

/**
 * @Title: SaleController.java 
 * @Description: 消费交易
 * @author: dingym
 * @date 2017年11月20日 上午11:00:57
 */
@Controller
@RequestMapping(value="/wechat")
public class WeChatController {
	private final static Logger logger = LoggerFactory.getLogger(WeChatController.class);

    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    private static final String WECHAT_TOKEN="bhhFront";
    
    @Autowired
    private WechatService wechatService;
   
	/**
	 * 白花花支付
	 * @param merchantNo,userNo
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/token")
	public void token(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		String signature = request.getParameter(WechatParamName.SIGNATURE);
		String timestamp = request.getParameter(WechatParamName.TIMESTAMP);
		String nonce = request.getParameter(WechatParamName.NONCE);
		String echostr = request.getParameter(WechatParamName.ECHOSTR);
		
		PrintWriter out = response.getWriter();
		if (SignUtil.checkSignature(signature, WECHAT_TOKEN,timestamp, nonce)) {
			out.println(echostr);
		}
		out.flush();
		out.close();
	}
	
	@RequestMapping(value = "/ticket")
	public String ticket(HttpServletRequest request,HttpServletResponse response,Model model) throws Throwable {
		String jsapi_ticket = wechatService.getJSApiTicket();
		String url = wechatService.getUrl(request);
		Map<String,String> map = SignUtil.sign(jsapi_ticket, url);
		model.addAllAttributes(map);
		return "modules/wechat/ticket";
	}
	
	
	
}
