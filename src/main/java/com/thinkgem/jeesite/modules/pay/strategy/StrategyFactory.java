package com.thinkgem.jeesite.modules.pay.strategy;

import java.util.HashMap;
import java.util.Map;

import com.thinkgem.jeesite.modules.pay.PayStrategy;
import com.thinkgem.jeesite.modules.pay.enums.PayType;

public class StrategyFactory {

    private static Map<Integer, PayStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(PayType.ALIPAY_APP.value(), new AlipayStrategy());
       // strategyMap.put(PayType.WECHAT_APP.value(), new WeChatStrategy());

    }

    private StrategyFactory() {
    }

    private static class InstanceHolder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return InstanceHolder.instance;
    }

    public PayStrategy creator(Integer type) {
        return strategyMap.get(type);
    }

}
