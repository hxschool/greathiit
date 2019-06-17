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
import com.thinkgem.jeesite.modules.course.entity.CourseClass;
import com.thinkgem.jeesite.modules.course.service.CourseClassService;

/**
 * 课程班级Controller
 * @author 赵俊飞
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseClass")
public class CourseClassController extends BaseController {

	@Autowired
	private CourseClassService courseClassService;
	
	@ModelAttribute
	public CourseClass get(@RequestParam(required=false) String id) {
		CourseClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseClassService.get(id);
		}
		if (entity == null){
			entity = new CourseClass();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseClass courseClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseClass> page = courseClassService.findPage(new Page<CourseClass>(request, response), courseClass); 
		model.addAttribute("page", page);
		return "modules/course/courseClassList";
	}

	@RequiresPermissions("course:courseClass:view")
	@RequestMapping(value = "form")
	public String form(CourseClass courseClass, Model model) {
		model.addAttribute("courseClass", courseClass);
		return "modules/course/courseClassForm";
	}

	@RequiresPermissions("course:courseClass:edit")
	@RequestMapping(value = "save")
	public String save(CourseClass courseClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseClass)){
			return form(courseClass, model);
		}
		courseClassService.save(courseClass);
		addMessage(redirectAttributes, "保存课程班级成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseClass/?repage";
	}
	
	@RequiresPermissions("course:courseClass:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseClass courseClass, RedirectAttributes redirectAttributes) {
		courseClassService.delete(courseClass);
		addMessage(redirectAttributes, "删除课程班级成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseClass/?repage";
	}

}