/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.dr.web;

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
import com.thinkgem.jeesite.modules.uc.dr.entity.UcPerson;
import com.thinkgem.jeesite.modules.uc.dr.service.UcPersonService;

/**
 * 学员基本信息Controller
 * @author 赵俊飞
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/dr/ucPerson")
public class UcPersonController extends BaseController {

	@Autowired
	private UcPersonService ucPersonService;
	
	@ModelAttribute
	public UcPerson get(@RequestParam(required=false) String id) {
		UcPerson entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucPersonService.get(id);
		}
		if (entity == null){
			entity = new UcPerson();
		}
		return entity;
	}
	
	@RequiresPermissions("uc:dr:ucPerson:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcPerson ucPerson, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcPerson> page = ucPersonService.findPage(new Page<UcPerson>(request, response), ucPerson); 
		model.addAttribute("page", page);
		return "modules/uc/dr/ucPersonList";
	}

	@RequiresPermissions("uc:dr:ucPerson:view")
	@RequestMapping(value = "form")
	public String form(UcPerson ucPerson, Model model) {
		model.addAttribute("ucPerson", ucPerson);
		return "modules/uc/dr/ucPersonForm";
	}

	@RequiresPermissions("uc:dr:ucPerson:edit")
	@RequestMapping(value = "save")
	public String save(UcPerson ucPerson, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucPerson)){
			return form(ucPerson, model);
		}
		ucPersonService.save(ucPerson);
		addMessage(redirectAttributes, "保存学院基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/dr/ucPerson/?repage";
	}
	
	@RequiresPermissions("uc:dr:ucPerson:edit")
	@RequestMapping(value = "delete")
	public String delete(UcPerson ucPerson, RedirectAttributes redirectAttributes) {
		ucPersonService.delete(ucPerson);
		addMessage(redirectAttributes, "删除学院基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/dr/ucPerson/?repage";
	}

}