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
import com.thinkgem.jeesite.modules.course.entity.CoursePoint;
import com.thinkgem.jeesite.modules.course.service.CoursePointService;

/**
 * 课程班级计数点Controller
 * @author 赵俊飞
 * @version 2018-12-25
 */
@Controller
@RequestMapping(value = "${adminPath}/course/point/coursePoint")
public class CoursePointController extends BaseController {

	@Autowired
	private CoursePointService coursePointService;
	
	@ModelAttribute
	public CoursePoint get(@RequestParam(required=false) String id) {
		CoursePoint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = coursePointService.get(id);
		}
		if (entity == null){
			entity = new CoursePoint();
		}
		return entity;
	}
	
	@RequiresPermissions("course:point:coursePoint:view")
	@RequestMapping(value = {"list", ""})
	public String list(CoursePoint coursePoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CoursePoint> page = coursePointService.findPage(new Page<CoursePoint>(request, response), coursePoint); 
		model.addAttribute("page", page);
		return "modules/course/point/coursePointList";
	}

	@RequiresPermissions("course:point:coursePoint:view")
	@RequestMapping(value = "form")
	public String form(CoursePoint coursePoint, Model model) {
		model.addAttribute("coursePoint", coursePoint);
		return "modules/course/point/coursePointForm";
	}

	@RequiresPermissions("course:point:coursePoint:edit")
	@RequestMapping(value = "save")
	public String save(CoursePoint coursePoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, coursePoint)){
			return form(coursePoint, model);
		}
		coursePointService.save(coursePoint);
		addMessage(redirectAttributes, "保存课程班级计数点成功");
		return "redirect:"+Global.getAdminPath()+"/course/point/coursePoint/?repage";
	}
	
	@RequiresPermissions("course:point:coursePoint:edit")
	@RequestMapping(value = "delete")
	public String delete(CoursePoint coursePoint, RedirectAttributes redirectAttributes) {
		coursePointService.delete(coursePoint);
		addMessage(redirectAttributes, "删除课程班级计数点成功");
		return "redirect:"+Global.getAdminPath()+"/course/point/coursePoint/?repage";
	}

}