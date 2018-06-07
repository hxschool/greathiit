package com.thinkgem.jeesite.modules.payment.pay.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.modules.payment.pay.PayStrategy;
import com.thinkgem.jeesite.modules.payment.pay.enums.PayType;
import com.thinkgem.jeesite.wxpay.sdk.WXPay;
import com.thinkgem.jeesite.wxpay.sdk.WXPayConfigImpl;
@Service("weChatStrategy")
public class WeChatStrategy implements PayStrategy {
    private WXPay wxpay;
    private WXPayConfigImpl config;
    @Value("{wechat.notifyUrl}")
    private String notifyUrl;
    public WeChatStrategy() {
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

}
