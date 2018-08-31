/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;
import com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService;

/**
 * 校历校准Controller
 * @author 赵俊飞
 * @version 2017-12-14
 */
@Controller
@RequestMapping(value = "${adminPath}/calendar/courseCalendar")
public class CourseCalendarController extends BaseController {

	@Autowired
	private CourseCalendarService courseCalendarService;		
	@ModelAttribute
	public CourseCalendar get(@RequestParam(required=false) String id) {
		CourseCalendar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseCalendarService.get(id);
		}
		if (entity == null){
			entity = new CourseCalendar();
		}
		return entity;
	}
	
	@RequiresPermissions("calendar:courseCalendar:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseCalendar courseCalendar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseCalendar> page = courseCalendarService.findPage(new Page<CourseCalendar>(request, response), courseCalendar); 
		model.addAttribute("page", page);
		return "modules/calendar/courseCalendarList";
	}

	@RequiresPermissions("calendar:courseCalendar:view")
	@RequestMapping(value = "form")
	public String form(CourseCalendar courseCalendar, Model model) {
		model.addAttribute("courseCalendar", courseCalendar);
		return "modules/calendar/courseCalendarForm";
	}
	
	@RequiresPermissions("calendar:courseCalendar:edit")
	@RequestMapping(value = "save")
	public String save(CourseCalendar courseCalendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseCalendar)){
			return form(courseCalendar, model);
		}
		courseCalendarService.save(courseCalendar);
		addMessage(redirectAttributes, "保存校历校准成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/courseCalendar/form?id=1&repage";
	}
	
	@RequiresPermissions("calendar:courseCalendar:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseCalendar courseCalendar, RedirectAttributes redirectAttributes) {
		courseCalendarService.delete(courseCalendar);
		addMessage(redirectAttributes, "删除校历校准成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/courseCalendar/?repage";
	}

}