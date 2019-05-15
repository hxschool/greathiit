package com.thinkgem.jeesite.modules.reg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.IdcardValidator;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "/reg")
public class RegController extends BaseController{
	@Autowired
	private SystemService systemService;
	@RequestMapping("password")
	public String apply(Model model) {
		return "modules/reg/password";
	}
	@RequestMapping("validate")
	public String validate(String idcard,String captcha,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) {
		
		
		if(!ValidateCodeServlet.validate(request, captcha)) {
			addMessage(redirectAttributes,"输入的验证码不合法,请重新输入");
			return "modules/reg/fail";
		}
		if(IdcardValidator.isValidatedAllIdcard(idcard)) {
			addMessage(redirectAttributes,"身份证信息不合法");
			return "modules/reg/fail";
		}
		User user = systemService.getUserByLoginName(idcard);
		if(org.springframework.util.StringUtils.isEmpty(user)) {
			addMessage(redirectAttributes,"通过输入的身份证信息未查找到用户信息");
			return "modules/reg/fail";
		}
		String password = idcard.substring(idcard.length()-6);
		systemService.updatePasswordById(user.getId(),idcard,password);
		return "redirect:/reg/success?repage";
	}
	
	public static void main(String[] args) {
		String password = "230302198402175312";
		System.out.println(password.substring(password.length()-6));
	}
	
	
}
