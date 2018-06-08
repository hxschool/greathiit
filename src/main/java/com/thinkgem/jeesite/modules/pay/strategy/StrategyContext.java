package com.thinkgem.jeesite.modules.pay.strategy;

import java.util.Map;

import com.thinkgem.jeesite.modules.pay.PayStrategy;
import com.thinkgem.jeesite.modules.pay.enums.PayType;

/**
 * 支付策略上下文
 * Created by Martin on 2016/7/01.
 */
public class StrategyContext {

    private PayStrategy payStrategy;

    /**
     * 调用对应支付平台组装支付请求报文
     * @param payType
     * @param params
     * @return
     */
    public String generatePayParams(PayType payType, Map<String, Object> params) {
        payStrategy = StrategyFactory.getInstance().creator(payType.value());
        return payStrategy.generatePayParams(payType, params);
    }
    
    public String callbackParams(PayType payType, Map<String, String> params) {
        payStrategy = StrategyFactory.getInstance().creator(payType.value());
        return payStrategy.callbackParams(payType, params);
    }

}
