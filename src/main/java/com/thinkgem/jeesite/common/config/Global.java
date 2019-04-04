/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	public final static String SYSCONFIG_DEFAULT= "default";//默认
	public final static String SYSCONFIG_EXAM= "exam";//单考单招

	public static List<String> weeks = new ArrayList();
	static {
		for(int i=1;i<=20;i++) {
			weeks.add(""+i);
		}
	}
	public static List<String> xqs = new LinkedList<String>();
	static {
		xqs.add("星期一");
		xqs.add("星期二");
		xqs.add("星期三");
		xqs.add("星期四");
		xqs.add("星期五");
		xqs.add("星期六");
		xqs.add("星期日");
	}
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
	
	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQFE1wHAH9LcGdF5Mx3CtqzVJ1p/oszTGF2no8ifjA5U8VKwDIQMPGnM9P6xbElRTiNgkAE5n3EBwCOgy6u4SIOf3nOtSnVYTMNNDVP2R/fSWqgV/J0M0sF3XkjQlKvn95L6uF6T09jOfV+VukNMzCq+o2lGM/LLmvaPjCRXR3yRLQIDAQAB";
	
	public static final String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAUTXAcAf0twZ0XkzHcK2rNUnWn+izNMYXaejyJ+MDlTxUrAMhAw8acz0/rFsSVFOI2CQATmfcQHAI6DLq7hIg5/ec61KdVhMw00NU/ZH99JaqBX8nQzSwXdeSNCUq+f3kvq4XpPT2M59X5W6Q0zMKr6jaUYz8sua9o+MJFdHfJEtAgMBAAECgYEBI5na0Wv/7mMi3qjbS2KO7d2g6LjtI4ZY9FcbjtW8O9ZSQwVJOG63DyzSwdSSUHFTYqiC4QZW/JpNoVeT9B7sP3Q8wfhaRBzDEFyDml2hn7IQx9CXPJ6aludzD0pm9xa2nXwVUl4aPuAx09qsyxpVV7vLrY9GRC1awdxrAem8teECQQFfA3iOmX8Due6VdeE+FGwWYqCgr/RH+tCOLWfPe3EJ2bIZZu3AxeZw95/ITzanlzlmvvOll3vRfGBW3H4qkwkJAkEA7Ok9l8en8bGOZ9ZBI7fwrcq2nJ1TAAiVIOMyyd7P1rpHf+YdYo8feawXU8aJWM4DJ3nxCTdZbnVWAT8ikz9EBQJAENQTxZJqWn1hiNzb7aQBApm75bhJ1+GYehiHL5VVeAlt1nXu1B0ozSWyDWJu1l4TrG9fMwMuNn7mB4QOsG/YMQJBAKc/eYX/IybTTE8tyEqo/jYSl5w0zHWHmY/gFOjCbUOPug4mq7P6iiPhQ46W6DtTJJLn2SvCcVYza4XxxjzhOukCQQCH63rq0ahccGgiDkkqYWpqvMRJkDLWaErZm80Yy2CuVSZa0F4paeJVyKF3+YffOIkaBI6oT1tIU/BmAitjU23z";
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
