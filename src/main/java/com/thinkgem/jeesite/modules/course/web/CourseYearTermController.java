/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseYearTermService;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.school.service.SchoolRootService;

/**
 * 学期初始化Controller
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseYearTerm")
public class CourseYearTermController extends BaseController {

	@Autowired
	private CourseYearTermService courseYearTermService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	 
	@ModelAttribute(value="courseYearTerm")
	public CourseYearTerm get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			CourseYearTerm courseYearTerm = courseYearTermService.get(id);
			if(courseYearTerm!=null) {
				return courseYearTermService.get(id);
			}
		}
		return new CourseYearTerm();
	}
	
	@RequiresPermissions("course:courseYearTerm:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseYearTerm courseYearTerm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseYearTerm> page = courseYearTermService.findPage(new Page<CourseYearTerm>(request, response), courseYearTerm); 
		model.addAttribute("page", page);
		return "modules/course/courseYearTermList";
	}

	@RequiresPermissions("course:courseYearTerm:view")
	@RequestMapping(value = "form")
	public String form(CourseYearTerm courseYearTerm, Model model) {
		model.addAttribute("courseYearTerm", courseYearTerm);
		return "modules/course/courseYearTermForm";
	}

	@RequiresPermissions("course:courseYearTerm:edit")
	@RequestMapping(value = "save")
	public String save(CourseYearTerm courseYearTerm, Model model, RedirectAttributes redirectAttributes) {
		courseYearTermService.save(courseYearTerm);
		addMessage(redirectAttributes, "保存学期初始化成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseYearTerm/?repage";
	}
	
	@RequestMapping(value = "batch")
	public void executeAsyncJsonAvailability() {
		courseScheduleService.executeAsyncJsonAvailability();
	}
	
	
	@RequiresPermissions("course:courseYearTerm:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseYearTerm courseYearTerm, RedirectAttributes redirectAttributes) {
		courseYearTermService.delete(courseYearTerm);
		addMessage(redirectAttributes, "删除学期初始化成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseYearTerm/?repage";
	}

}