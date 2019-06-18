/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
import com.thinkgem.jeesite.modules.student.entity.StudentClass;
import com.thinkgem.jeesite.modules.student.service.StudentClassService;

/**
 * 学生班级Controller
 * @author 赵俊飞
 * @version 2019-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/student/studentClass")
public class StudentClassController extends BaseController {

	@Autowired
	private StudentClassService studentClassService;
	
	@ModelAttribute
	public StudentClass get(@RequestParam(required=false) String id) {
		StudentClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentClassService.get(id);
		}
		if (entity == null){
			entity = new StudentClass();
		}
		return entity;
	}
	
	@RequiresPermissions("student:studentClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentClass studentClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentClass> page = studentClassService.findPage(new Page<StudentClass>(request, response), studentClass); 
		model.addAttribute("page", page);
		return "modules/student/studentClassList";
	}

	@RequiresPermissions("student:studentClass:view")
	@RequestMapping(value = "form")
	public String form(StudentClass studentClass, Model model) {
		model.addAttribute("studentClass", studentClass);
		return "modules/student/studentClassForm";
	}

	@RequiresPermissions("student:studentClass:edit")
	@RequestMapping(value = "save")
	public String save(StudentClass studentClass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentClass)){
			return form(studentClass, model);
		}
		studentClassService.save(studentClass);
		addMessage(redirectAttributes, "保存学生班级成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentClass/?repage";
	}
	
	@RequiresPermissions("student:studentClass:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentClass studentClass, RedirectAttributes redirectAttributes) {
		studentClassService.delete(studentClass);
		addMessage(redirectAttributes, "删除学生班级成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentClass/?repage";
	}

}