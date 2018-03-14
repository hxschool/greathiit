package com.thinkgem.jeesite.modules.route.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.utils.DomainUtil;
import com.thinkgem.jeesite.common.web.BaseController;

@Controller
@RequestMapping(value = "/route")
public class RouteController extends BaseController {
	
	@RequestMapping(value = {"list", ""})
	public String list( HttpServletRequest request, HttpServletResponse response, Model model) {
		String url = request.getRequestURL().toString();
		if(url.indexOf("localhost")>-1||url.indexOf("www")>-1) {
			return "redirect:/f/2018/index";
		}
		logger.debug("请求地址:{}",url);
		String res1 = DomainUtil.getTopDomain(url);
		System.out.println(url + " ==> " + res1);

		String res2 = DomainUtil.getSecondDomain(url);
		String path = res2.substring(0, res2.length()-res1.length() - 1 );
		
		return "redirect:".concat(path);
	}
	
}
