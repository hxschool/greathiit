package com.thinkgem.jeesite.modules.pay.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.modules.pay.PayStrategy;
import com.thinkgem.jeesite.modules.pay.enums.PayType;
import com.thinkgem.jeesite.wxpay.sdk.WXPay;
import com.thinkgem.jeesite.wxpay.sdk.WXPayConfigImpl;

public class WeChatStrategy implements PayStrategy {
    private WXPay wxpay;
    private WXPayConfigImpl config;
    private String notifyUrl;
    private PropertiesLoader propertiesLoader = null;
    public WeChatStrategy() {
    	propertiesLoader = new PropertiesLoader("jeesite.properties");
    	notifyUrl = propertiesLoader.getProperty("wechat.notifyUrl");
    	try {
			config = WXPayConfigImpl.getInstance();
			wxpay = new WXPay(config);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	@Override
	public String generatePayParams(PayType payType, Map<String, Object> params) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("body",params.get("body").toString());
        data.put("out_trade_no", params.get("out_trade_no").toString());
        data.put("fee_type", "CNY");
        data.put("total_fee", params.get("total_fee").toString());
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
	
	@Override
	public String callbackParams(PayType payType, Map<String, String> params) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("body",params.get("body").toString());
        data.put("out_trade_no", params.get("out_trade_no").toString());
        data.put("fee_type", "CNY");
        data.put("total_fee", params.get("total_fee").toString());
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
	
}
