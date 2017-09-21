/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;

/**
 * 生成随机验证码
 * @author ThinkGem
 * @version 2014-7-27
 */
@SuppressWarnings("serial")
public class SMSValidateCodeServlet extends HttpServlet {
	
	public static final String VALIDATE_CODE = "smsValidateCode";

	
	public SMSValidateCodeServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}
	
	public static boolean validate(HttpServletRequest request, String validateCode){
		String code = (String)request.getSession().getAttribute(VALIDATE_CODE);
		return validateCode.toUpperCase().equals(code); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String validateCode = request.getParameter(VALIDATE_CODE); // AJAX验证，成功返回true
		if (StringUtils.isNotBlank(validateCode)){
			response.getOutputStream().print(validate(request, validateCode)?"true":"false");
		}else{
			this.doPost(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mobile = request.getParameter("mobile");
		String code = autoRandomCode(4);
		sendMessage(mobile,code);
		request.getSession().setAttribute(VALIDATE_CODE, code);
		response.getOutputStream().print("true");
	}
	
	public static String autoRandomCode(int length){
		Random  d = new Random();
		String str = "";
		for (int i = 0; i < length; i++) {
			int num = d.nextInt(10);
			str += num + "";
		}
		return str;
	}
	
	public static boolean sendMessage(String mobile,String code)
    {
		
		Properties p = SpringContextHolder.getBean("APP_PROP");
		String from = p.get("mobile.username").toString();
		String password = p.get("mobile.password").toString();
		String title = p.get("mobile.title").toString();
		String body = p.get("mobile.body").toString();
		body = body.replace("{code}", code);
        
        System.out.println(body);
		return true;
    }

}
