package com.thinkgem.jeesite.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.thinkgem.jeesite.common.utils.PropertiesLoader;

public class WXPayConfigImpl extends WXPayConfig{

    private byte[] certData;
    private static WXPayConfigImpl INSTANCE;
    private PropertiesLoader propertiesLoader;

    private WXPayConfigImpl() throws Exception{
    	propertiesLoader = new PropertiesLoader("jeesite.properties");
        String certPath = propertiesLoader.getProperty("wechat.cert");// "D://CERT/common/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public static WXPayConfigImpl getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
    	return propertiesLoader.getProperty("wechat.appid");
        //return "wx5b0885bb488038f6";
    }

    public String getMchID() {
    	return propertiesLoader.getProperty("wechat.mchid");
    	//return "1458126002";
    }

    public String getKey() {
    	return propertiesLoader.getProperty("wechat.key");
        //return "o0i5fcjy0eplsbzxsavk501msffhcbfg";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
