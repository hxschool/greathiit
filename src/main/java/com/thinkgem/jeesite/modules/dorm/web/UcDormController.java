/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.service.UcDormService;

/**
 * 宿舍管理Controller
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/dorm/ucDorm")
public class UcDormController extends BaseController {

	@Autowired
	private UcDormService ucDormService;
	
	@ModelAttribute
	public UcDorm get(@RequestParam(required=false) String id) {
		UcDorm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucDormService.get(id);
		}
		if (entity == null){
			entity = new UcDorm();
		}
		return entity;
	}
	
	@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcDorm ucDorm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcDorm> page = ucDormService.findPage(new Page<UcDorm>(request, response), ucDorm); 
		model.addAttribute("page", page);
		return "modules/dorm/ucDormList";
	}

	@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = "form")
	public String form(UcDorm ucDorm, Model model) {
		model.addAttribute("ucDorm", ucDorm);
		return "modules/dorm/ucDormForm";
	}

	@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "save")
	public String save(UcDorm ucDorm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucDorm)){
			return form(ucDorm, model);
		}
		ucDormService.save(ucDorm);
		addMessage(redirectAttributes, "保存宿舍管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDorm/?repage";
	}
	
	@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "delete")
	public String delete(UcDorm ucDorm, RedirectAttributes redirectAttributes) {
		ucDormService.delete(ucDorm);
		addMessage(redirectAttributes, "删除宿舍管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDorm/?repage";
	}

}