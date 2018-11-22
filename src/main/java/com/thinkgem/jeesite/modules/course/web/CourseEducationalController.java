/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.CourseEducational;
import com.thinkgem.jeesite.modules.course.service.CourseEducationalService;

/**
 * 教务课程信息Controller
 * @author 赵俊飞
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseEducational")
public class CourseEducationalController extends BaseController {

	@Autowired
	private CourseEducationalService courseEducationalService;
	
	@ModelAttribute
	public CourseEducational get(@RequestParam(required=false) String id) {
		CourseEducational entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseEducationalService.get(id);
		}
		if (entity == null){
			entity = new CourseEducational();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseEducational:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseEducational courseEducational, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseEducational> page = courseEducationalService.findPage(new Page<CourseEducational>(request, response), courseEducational); 
		model.addAttribute("page", page);
		return "modules/course/courseEducationalList";
	}

	@RequiresPermissions("course:courseEducational:view")
	@RequestMapping(value = "form")
	public String form(CourseEducational courseEducational, Model model) {
		model.addAttribute("courseEducational", courseEducational);
		return "modules/course/courseEducationalForm";
	}

	@RequiresPermissions("course:courseEducational:edit")
	@RequestMapping(value = "save")
	public String save(CourseEducational courseEducational, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseEducational)){
			return form(courseEducational, model);
		}
		courseEducational.setIsNewRecord(true);
		courseEducational.setId(courseEducational.getCursNum());
		courseEducationalService.save(courseEducational);
		addMessage(redirectAttributes, "保存教务课程信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseEducational/?repage";
	}
	
	@RequiresPermissions("course:courseEducational:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseEducational courseEducational, RedirectAttributes redirectAttributes) {
		courseEducationalService.delete(courseEducational);
		addMessage(redirectAttributes, "删除教务课程信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseEducational/?repage";
	}
	
	@RequestMapping(value = "ajaxCourseEducational")
	@ResponseBody
	public List<CourseEducational> ajaxCourseEducational(CourseEducational courseEducational, HttpServletRequest request, HttpServletResponse response, Model model) {
		return courseEducationalService.findList(courseEducational);
	}

}