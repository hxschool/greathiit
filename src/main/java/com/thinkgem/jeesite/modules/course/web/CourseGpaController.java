/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
import com.thinkgem.jeesite.modules.course.entity.CourseGpa;
import com.thinkgem.jeesite.modules.course.service.CourseGpaService;

/**
 * gpaController
 * @author 赵俊飞
 * @version 2019-02-01
 */
@Controller
@RequestMapping(value = "${adminPath}/course/gpa/courseGpa")
public class CourseGpaController extends BaseController {

	@Autowired
	private CourseGpaService courseGpaService;
	
	@ModelAttribute
	public CourseGpa get(@RequestParam(required=false) String id) {
		CourseGpa entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseGpaService.get(id);
		}
		if (entity == null){
			entity = new CourseGpa();
		}
		return entity;
	}
	
	@RequiresPermissions("course:gpa:courseGpa:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseGpa courseGpa, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseGpa> page = courseGpaService.findPage(new Page<CourseGpa>(request, response), courseGpa); 
		model.addAttribute("page", page);
		return "modules/course/gpa/courseGpaList";
	}

	@RequiresPermissions("course:gpa:courseGpa:view")
	@RequestMapping(value = "form")
	public String form(CourseGpa courseGpa, Model model) {
		model.addAttribute("courseGpa", courseGpa);
		return "modules/course/gpa/courseGpaForm";
	}

	@RequiresPermissions("course:gpa:courseGpa:edit")
	@RequestMapping(value = "save")
	public String save(CourseGpa courseGpa, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseGpa)){
			return form(courseGpa, model);
		}
		courseGpaService.save(courseGpa);
		addMessage(redirectAttributes, "保存gpa成功");
		return "redirect:"+Global.getAdminPath()+"/course/gpa/courseGpa/?repage";
	}
	
	@RequiresPermissions("course:gpa:courseGpa:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseGpa courseGpa, RedirectAttributes redirectAttributes) {
		courseGpaService.delete(courseGpa);
		addMessage(redirectAttributes, "删除gpa成功");
		return "redirect:"+Global.getAdminPath()+"/course/gpa/courseGpa/?repage";
	}

}