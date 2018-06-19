package com.thinkgem.jeesite.modules.callback.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.pay.enums.PayType;
import com.thinkgem.jeesite.modules.pay.strategy.StrategyContext;
import com.thinkgem.jeesite.wxpay.sdk.WXPayUtil;

@Controller
@RequestMapping(value = "callback")
public class CallBackController {
	@RequestMapping("wechat_notify_url.html")
	public void wechat_notify_url( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String xml = IOUtils.toString(request.getInputStream());
		try {
			Map<String, String> params = WXPayUtil.xmlToMap(xml);
	        StrategyContext strategyContext = new StrategyContext();
	     	strategyContext.callbackParams(PayType.WECHAT_APP, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		
	}
	@RequestMapping("alipay_notify_url.html")
	public void alipay_notify_url(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
        StrategyContext strategyContext = new StrategyContext();
     	strategyContext.callbackParams(PayType.ALIPAY_APP, params);
	}
	
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
	
	@RequestMapping("alipay_url.html")
	public void alipay_url( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
	}
}
