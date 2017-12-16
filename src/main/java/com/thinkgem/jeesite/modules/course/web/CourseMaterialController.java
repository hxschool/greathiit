/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

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
import com.thinkgem.jeesite.modules.course.entity.CourseMaterial;
import com.thinkgem.jeesite.modules.course.service.CourseMaterialService;

/**
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseMaterial")
public class CourseMaterialController extends BaseController {

	@Autowired
	private CourseMaterialService courseMaterialService;
	
	@ModelAttribute
	public CourseMaterial get(@RequestParam(required=false) String id) {
		CourseMaterial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseMaterialService.get(id);
		}
		if (entity == null){
			entity = new CourseMaterial();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseMaterial courseMaterial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseMaterial> page = courseMaterialService.findPage(new Page<CourseMaterial>(request, response), courseMaterial); 
		model.addAttribute("page", page);
		return "modules/course/courseMaterialList";
	}

	@RequiresPermissions("course:courseMaterial:view")
	@RequestMapping(value = "form")
	public String form(CourseMaterial courseMaterial, Model model) {
		model.addAttribute("courseMaterial", courseMaterial);
		return "modules/course/courseMaterialForm";
	}

	@RequiresPermissions("course:courseMaterial:edit")
	@RequestMapping(value = "save")
	public String save(CourseMaterial courseMaterial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseMaterial)){
			return form(courseMaterial, model);
		}
		courseMaterialService.save(courseMaterial);
		addMessage(redirectAttributes, "保存课程基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseMaterial/?repage";
	}
	
	@RequiresPermissions("course:courseMaterial:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseMaterial courseMaterial, RedirectAttributes redirectAttributes) {
		courseMaterialService.delete(courseMaterial);
		addMessage(redirectAttributes, "删除课程基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseMaterial/?repage";
	}

}