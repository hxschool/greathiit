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
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;

/**
 * 学生成绩Controller
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/student/studentCourse")
public class StudentCourseController extends BaseController {

	@Autowired
	private StudentCourseService studentCourseService;
	
	@ModelAttribute
	public StudentCourse get(@RequestParam(required=false) String id) {
		StudentCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentCourseService.get(id);
		}
		if (entity == null){
			entity = new StudentCourse();
		}
		return entity;
	}
	
	
	
	@RequiresPermissions("student:studentCourse:view")
	@RequestMapping(value = {"Teacher_Management_4_excute"})
	public String Teacher_Management_4_excute(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentCourse> page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
		model.addAttribute("page", page);
		return "modules/student/studentCourseList";
	}
	
	@RequiresPermissions("student:studentCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentCourse> page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
		model.addAttribute("page", page);
		return "modules/student/studentCourseList";
	}

	@RequiresPermissions("student:studentCourse:view")
	@RequestMapping(value = "form")
	public String form(StudentCourse studentCourse, Model model) {
		model.addAttribute("studentCourse", studentCourse);
		return "modules/student/studentCourseForm";
	}

	@RequiresPermissions("student:studentCourse:edit")
	@RequestMapping(value = "save")
	public String save(StudentCourse studentCourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentCourse)){
			return form(studentCourse, model);
		}
		studentCourseService.save(studentCourse);
		addMessage(redirectAttributes, "保存学生成绩成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentCourse/?repage";
	}
	
	@RequiresPermissions("student:studentCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentCourse studentCourse, RedirectAttributes redirectAttributes) {
		studentCourseService.delete(studentCourse);
		addMessage(redirectAttributes, "删除学生成绩成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentCourse/?repage";
	}

}