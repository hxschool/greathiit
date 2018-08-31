/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;

/**
 * 计划教室Controller
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseSchedule")
public class CourseScheduleController extends BaseController {

	@Autowired
	private CourseScheduleService courseScheduleService;
	
	@ModelAttribute
	public CourseSchedule get(@RequestParam(required=false) String id) {
		CourseSchedule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseScheduleService.get(id);
		}
		if (entity == null){
			entity = new CourseSchedule();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseSchedule courseSchedule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseSchedule> page = courseScheduleService.findPage(new Page<CourseSchedule>(request, response), courseSchedule); 
		model.addAttribute("page", page);
		return "modules/course/courseScheduleList";
	}

	@RequiresPermissions("course:courseSchedule:view")
	@RequestMapping(value = "form")
	public String form(CourseSchedule courseSchedule, Model model) {
		model.addAttribute("courseSchedule", courseSchedule);
		return "modules/course/courseScheduleForm";
	}

	@RequiresPermissions("course:courseSchedule:edit")
	@RequestMapping(value = "save")
	public String save(CourseSchedule courseSchedule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseSchedule)){
			return form(courseSchedule, model);
		}
		courseScheduleService.save(courseSchedule);
		addMessage(redirectAttributes, "保存计划教室成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseSchedule/?repage";
	}
	
	@RequiresPermissions("course:courseSchedule:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseSchedule courseSchedule, RedirectAttributes redirectAttributes) {
		courseScheduleService.delete(courseSchedule);
		addMessage(redirectAttributes, "删除计划教室成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseSchedule/?repage";
	}

}