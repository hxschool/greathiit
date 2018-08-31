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
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.service.CourseCompositionRulesService;

/**
 * 课程考试与教学目标支撑分值设置Controller
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseCompositionRules")
public class CourseCompositionRulesController extends BaseController {

	@Autowired
	private CourseCompositionRulesService courseCompositionRulesService;
	
	@ModelAttribute
	public CourseCompositionRules get(@RequestParam(required=false) String id) {
		CourseCompositionRules entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseCompositionRulesService.get(id);
		}
		if (entity == null){
			entity = new CourseCompositionRules();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseCompositionRules:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseCompositionRules courseCompositionRules, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseCompositionRules> page = courseCompositionRulesService.findPage(new Page<CourseCompositionRules>(request, response), courseCompositionRules); 
		model.addAttribute("page", page);
		return "modules/course/courseCompositionRulesList";
	}

	@RequiresPermissions("course:courseCompositionRules:view")
	@RequestMapping(value = "form")
	public String form(CourseCompositionRules courseCompositionRules, Model model) {
		model.addAttribute("courseCompositionRules", courseCompositionRules);
		return "modules/course/courseCompositionRulesForm";
	}

	@RequiresPermissions("course:courseCompositionRules:edit")
	@RequestMapping(value = "save")
	public String save(CourseCompositionRules courseCompositionRules, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseCompositionRules)){
			return form(courseCompositionRules, model);
		}
		courseCompositionRulesService.save(courseCompositionRules);
		addMessage(redirectAttributes, "保存课程考试与教学目标支撑分值设置成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseCompositionRules/?repage";
	}
	
	@RequiresPermissions("course:courseCompositionRules:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseCompositionRules courseCompositionRules, RedirectAttributes redirectAttributes) {
		courseCompositionRulesService.delete(courseCompositionRules);
		addMessage(redirectAttributes, "删除课程考试与教学目标支撑分值设置成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseCompositionRules/?repage";
	}

}