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
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;

/**
 * 教学方式Controller
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseTeachingMode")
public class CourseTeachingModeController extends BaseController {

	@Autowired
	private CourseTeachingModeService courseTeachingModeService;
	
	@ModelAttribute
	public CourseTeachingMode get(@RequestParam(required=false) String id) {
		CourseTeachingMode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseTeachingModeService.get(id);
		}
		if (entity == null){
			entity = new CourseTeachingMode();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseTeachingMode:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseTeachingMode courseTeachingMode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseTeachingMode> page = courseTeachingModeService.findPage(new Page<CourseTeachingMode>(request, response), courseTeachingMode); 
		model.addAttribute("page", page);
		return "modules/course/courseTeachingModeList";
	}

	@RequiresPermissions("course:courseTeachingMode:view")
	@RequestMapping(value = "form")
	public String form(CourseTeachingMode courseTeachingMode, Model model) {
		model.addAttribute("courseTeachingMode", courseTeachingMode);
		return "modules/course/courseTeachingModeForm";
	}

	@RequiresPermissions("course:courseTeachingMode:edit")
	@RequestMapping(value = "save")
	public String save(CourseTeachingMode courseTeachingMode, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseTeachingMode)){
			return form(courseTeachingMode, model);
		}
		courseTeachingModeService.save(courseTeachingMode);
		addMessage(redirectAttributes, "保存教学方式成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseTeachingMode/?repage";
	}
	
	@RequiresPermissions("course:courseTeachingMode:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseTeachingMode courseTeachingMode, RedirectAttributes redirectAttributes) {
		courseTeachingModeService.delete(courseTeachingMode);
		addMessage(redirectAttributes, "删除教学方式成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseTeachingMode/?repage";
	}

}