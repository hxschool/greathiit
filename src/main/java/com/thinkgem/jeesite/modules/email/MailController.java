package com.thinkgem.jeesite.modules.email;

import javax.mail.Message.RecipientType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.MailUtil;

@Controller
@RequestMapping(value = "${adminPath}/mail")
public class MailController {
	@Value("${mail.username}")
	private String username;
	@Value("${mail.password}")
	private String password;
	@RequestMapping(value = "send")
	@ResponseBody
	public String sendMail(String email,String subject,String context){
		String[] to = { email };
		
		MailUtil mail = new MailUtil(username, password, to, RecipientType.TO, subject, context);
		mail.setMailBody(context);
		try {
			mail.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
}
