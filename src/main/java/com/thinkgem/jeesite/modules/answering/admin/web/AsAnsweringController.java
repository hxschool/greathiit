/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.web;

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
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;
import com.thinkgem.jeesite.modules.answering.admin.service.AsAnsweringService;

/**
 * 答辩抽签Controller
 * @author 赵俊飞
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/answering/admin/asAnswering")
public class AsAnsweringController extends BaseController {

	@Autowired
	private AsAnsweringService asAnsweringService;
	
	@ModelAttribute
	public AsAnswering get(@RequestParam(required=false) String id) {
		AsAnswering entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = asAnsweringService.get(id);
		}
		if (entity == null){
			entity = new AsAnswering();
		}
		return entity;
	}
	
	@RequiresPermissions("answering:admin:asAnswering:view")
	@RequestMapping(value = {"list", ""})
	public String list(AsAnswering asAnswering, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AsAnswering> page = asAnsweringService.findPage(new Page<AsAnswering>(request, response), asAnswering); 
		model.addAttribute("page", page);
		return "modules/answering/admin/asAnsweringList";
	}

	@RequiresPermissions("answering:admin:asAnswering:view")
	@RequestMapping(value = "form")
	public String form(AsAnswering asAnswering, Model model) {
		model.addAttribute("asAnswering", asAnswering);
		return "modules/answering/admin/asAnsweringForm";
	}

	@RequiresPermissions("answering:admin:asAnswering:edit")
	@RequestMapping(value = "save")
	public String save(AsAnswering asAnswering, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, asAnswering)){
			return form(asAnswering, model);
		}
		asAnsweringService.save(asAnswering);
		addMessage(redirectAttributes, "保存答辩抽签成功");
		return "redirect:"+Global.getAdminPath()+"/answering/admin/asAnswering/?repage";
	}
	
	@RequiresPermissions("answering:admin:asAnswering:edit")
	@RequestMapping(value = "delete")
	public String delete(AsAnswering asAnswering, RedirectAttributes redirectAttributes) {
		asAnsweringService.delete(asAnswering);
		addMessage(redirectAttributes, "删除答辩抽签成功");
		return "redirect:"+Global.getAdminPath()+"/answering/admin/asAnswering/?repage";
	}

}