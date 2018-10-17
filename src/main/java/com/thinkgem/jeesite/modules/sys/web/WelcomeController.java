package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;
import com.thinkgem.jeesite.modules.sys.service.SysAppconfigService;
@Controller
@RequestMapping(value = "${adminPath}/welcome")
public class WelcomeController extends BaseController {
	@Autowired
	private SysAppconfigService sysAppconfigService;
	
	@RequestMapping(value = {"index", ""})
	public String list(SysAppconfig sysAppconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SysAppconfig> list = sysAppconfigService.findList(sysAppconfig);
        model.addAttribute("list", list);
		return "modules/sys/welcome";
	}

	
}
