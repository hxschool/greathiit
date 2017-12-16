/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

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
import com.thinkgem.jeesite.modules.student.entity.StudentActivity;
import com.thinkgem.jeesite.modules.student.service.StudentActivityService;

/**
 * 参与活动Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/student/studentActivity")
public class StudentActivityController extends BaseController {

	@Autowired
	private StudentActivityService studentActivityService;
	
	@ModelAttribute
	public StudentActivity get(@RequestParam(required=false) String id) {
		StudentActivity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentActivityService.get(id);
		}
		if (entity == null){
			entity = new StudentActivity();
		}
		return entity;
	}
	
	@RequiresPermissions("student:studentActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentActivity studentActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentActivity> page = studentActivityService.findPage(new Page<StudentActivity>(request, response), studentActivity); 
		model.addAttribute("page", page);
		return "modules/student/studentActivityList";
	}

	@RequiresPermissions("student:studentActivity:view")
	@RequestMapping(value = "form")
	public String form(StudentActivity studentActivity, Model model) {
		model.addAttribute("studentActivity", studentActivity);
		return "modules/student/studentActivityForm";
	}

	@RequiresPermissions("student:studentActivity:edit")
	@RequestMapping(value = "save")
	public String save(StudentActivity studentActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentActivity)){
			return form(studentActivity, model);
		}
		studentActivityService.save(studentActivity);
		addMessage(redirectAttributes, "保存参与活动成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentActivity/?repage";
	}
	
	@RequiresPermissions("student:studentActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentActivity studentActivity, RedirectAttributes redirectAttributes) {
		studentActivityService.delete(studentActivity);
		addMessage(redirectAttributes, "删除参与活动成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentActivity/?repage";
	}

}