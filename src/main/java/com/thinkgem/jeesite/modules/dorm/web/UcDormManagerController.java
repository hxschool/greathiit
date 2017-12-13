/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormManager;
import com.thinkgem.jeesite.modules.dorm.service.UcDormManagerService;

/**
 * 宿舍管理Controller
 * 
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/dorm/ucDormManager")
public class UcDormManagerController extends BaseController {

	@Autowired
	private UcDormManagerService ucDormManagerService;
	@ModelAttribute
	public UcDormManager get(@RequestParam(required=false) String id) {
		return new UcDormManager();
	}
	
	@RequestMapping(value = { "list", "" })
	public String list(UcDormManager ucDormManager, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<UcDormManager> page = ucDormManagerService.findPage(new Page<UcDormManager>(request, response),
				ucDormManager);
		model.addAttribute("page", page);
		return "modules/dorm/ucDormManagerList";
	}

	
	@RequestMapping(value = "form")
	public String form(UcDorm ucDorm, Model model) {
		model.addAttribute("ucDorm", ucDorm);
		return "modules/dorm/ucDormForm";
	}

}