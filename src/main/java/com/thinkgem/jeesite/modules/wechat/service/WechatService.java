package com.thinkgem.jeesite.modules.wechat.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.thinkgem.jeesite.modules.wechat.WechatAccessToken;
import com.thinkgem.jeesite.modules.wechat.WechatJsAccessToken;
import com.thinkgem.jeesite.modules.wechat.config.WechatConfig;

@Service
public class WechatService {
	private static final String BHH_WECHAT_ACCESSTOKEN = "BHH_WECHAT_ACCESSTOKEN";
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String HTTPS_API_WEIXIN_QQ_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static final String HTTPS_API_WEIXIN_QQ_ACCESS_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
	

    
	public String getAccessToken() {
		String accessToken = "";
		WechatConfig wechatConfig;
		try {
			wechatConfig = getWechatConfig("");
			RestTemplate restTemplate = new RestTemplate();
			String url = HTTPS_API_WEIXIN_QQ_ACCESS_TOKEN.concat("&appid=" + wechatConfig.getWechatAppid() + "&secret=" + wechatConfig.getWechatSecret());
			ResponseEntity<WechatAccessToken> responseEntity = restTemplate.getForEntity(url,WechatAccessToken.class);
			WechatAccessToken wechatAccessToken = responseEntity.getBody();
			accessToken = wechatAccessToken.getAccess_token();
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		return accessToken;

	}
	
	public String getJSApiTicket() {
		String accessToken = getAccessToken();
		RestTemplate restTemplate = new RestTemplate();
		String url = HTTPS_API_WEIXIN_QQ_ACCESS_TICKET.concat("&access_token="+accessToken);
		ResponseEntity<WechatJsAccessToken> responseEntity = 	restTemplate.getForEntity(url, WechatJsAccessToken.class);
		WechatJsAccessToken wechatJsAccessToken = responseEntity.getBody();
		return wechatJsAccessToken.getTicket();
	}
	

	public WechatConfig getWechatConfig(String organizationNo) throws Throwable {
		WechatConfig wechatConfig =  new WechatConfig();
		wechatConfig.setWechatAppid("wxe996a36188be510b");
		wechatConfig.setWechatSecret("5696ff5263cde15a461c248c380f9650");
		return wechatConfig;
	}

	public static String getUrl(HttpServletRequest request) {
		String queryString = request.getQueryString();
		StringBuffer sb = request.getRequestURL();
		if(!StringUtils.isEmpty(queryString)) {
			sb.append("?").append(queryString);
		}
		return sb.toString();
	}
	
	public static boolean isEnglish(String charaString) {
		return charaString.matches("^[a-zA-Z]*");
	}
}
