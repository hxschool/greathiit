/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import com.ckfinder.connector.ServletContextFactory;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 全局配置类
 * @author ThinkGem
 * @version 2014-06-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("jeesite.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";
	
	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdrbIHE1/gvLUhHtoLJvNp3ZeNocI3wqAujZXaoS4F3pMicEM3XxMhSYuhxYlIRQL+L6sXjnUkwrab/eh5KzEIZVVkLvAIMOzmkVawfHD2JwWFaybYpYACAOhS3KOzWuYR4p45dHV/8hB1GjB+0u6pgmGt3xE1s90Doc2pOgSvOQIDAQAB";
	
	public static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ2tsgcTX+C8tSEe2gsm82ndl42hwjfCoC6NldqhLgXekyJwQzdfEyFJi6HFiUhFAv4vqxeOdSTCtpv96HkrMQhlVWQu8Agw7OaRVrB8cPYnBYVrJtilgAIA6FLco7Na5hHinjl0dX/yEHUaMH7S7qmCYa3fETWz3QOhzak6BK85AgMBAAECgYBmnRVQmYE55lUSGtvXih+686SfXXfhhd1srmOphl9HHpQuQ1TbAhqW/R/LxZwT1iWeejMj/2eo5rakSdcyk7MyN6S/oz/FqB/zh9jUfJtj+g/4o1oCT1rkfRhWub2uFV3xI5DVLsjgxH+kBRuPMuE8vTRJDS64keFGY3tvrs0qyQJBANSWE7XqEgrQCqm8NfYer3dDljliT+ZA5ATQ5mJKMXJB2Dg4xvC9P9CfKticJedRcgdGZNyRfIBeBajb3hFh0/cCQQC94RBX/Wu2H5L6OHVUfZwLDbQozu6on1wStA/fjwmUWCwpzV4PkvFPxCSwYqJTj2O9UXwd7lYNAozDgCyZcGpPAkAWMZzZLxi3HEUgt0+C5G05LEswrnzc8wVbI1wKAujqnskuBW9vjrJs21Ej0Bl87tnSvJynEmFHHYnFuT6T3fy9AkAjAUT0E8IxtyxveM0YoHeOCh62jz+sUoS05gEvI72OXZr2seevU1WtcOmaovRuFzc6BbWuBuxSRbARsqWGPBU9AkAKW0IOsAIwuC9LQduNUHWRUi7ZlrDD2f+DSadk24hNzLnkR5BT2VpgJirG99dz47gQ3C8FCYWI70iXHqHK/sAX";
	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}
    
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)){
			try {
				dir = ServletContextFactory.getServletContext().getRealPath("/");
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
//		System.out.println("userfiles.basedir: " + dir);
		return dir;
	}
	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
	
}
