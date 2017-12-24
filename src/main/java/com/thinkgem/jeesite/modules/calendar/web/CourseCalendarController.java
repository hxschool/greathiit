/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.calendar.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;
import com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseYearTermService;
import com.thinkgem.jeesite.modules.sys.entity.User;

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
	@Autowired
	private CourseYearTermService courseYearTermService;
	
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private CourseService courseService;
	
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
	
	@RequiresPermissions("calendar:courseCalendar:view")
	@RequestMapping(value = "manageCourseSchedule")
	public String manageCourseSchedule(CourseCalendar courseCalendar, Model model) {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm="20171";
		if(!org.springframework.util.StringUtils.isEmpty(courseYearTerm)) {
			yearTerm = courseYearTerm.getYearTerm();
		}
		
		model.addAttribute("yearTerm",yearTerm);
		model.addAttribute("courseCalendar", courseCalendarService.systemConfig());
		return "modules/calendar/manageCourseSchedule";
	}
	
	@RequestMapping(value = "ajaxChangeTable")
	public void ajaxChangeTable(String time_add,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter ps = response.getWriter();
		List<CourseSchedule> courseSchedules = courseScheduleService.findListByTimeAdd(time_add);
		for(CourseSchedule courseSchedule:courseSchedules) {
			ps.write(courseSchedule.getScLock());
			if(courseSchedule.getScLock().equals("1")) {
				ps.write("<div class=\"course_text\">教师:</div>");
				ps.write("<div class=\"course_text\">课程:</div>");
				ps.write("<div class=\"course_text\"></div>");
				ps.write("<div class=\"course_text\">备注:</div>");
				
			}else {
				//科目号,理论不应该出现异常现象,不应该出现空指针现象
				String courseNumber = courseSchedule.getCourseId();
				Course course = courseService.findListByCourse(courseNumber);
				User teacher = course.getTeacher();
				ps.write("<div class=\"course_text\">教师:"+teacher.getName()+"</div>");
				ps.write("<div class=\"course_text\">课程:"+course.getCursName()+"</div>");
				ps.write("<div class=\"course_text\">"+courseSchedule.getCourseClass()+"</div>");
				ps.write("<div class=\"course_text\">备注:"+courseSchedule.getTips()+"</div>");
				
			}
			ps.write("@");
		}
		ps.flush();
	}
	

	@RequiresPermissions("calendar:courseCalendar:edit")
	@RequestMapping(value = "save")
	public String save(CourseCalendar courseCalendar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseCalendar)){
			return form(courseCalendar, model);
		}
		courseCalendarService.save(courseCalendar);
		addMessage(redirectAttributes, "保存校历校准成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/courseCalendar/?repage";
	}
	
	@RequiresPermissions("calendar:courseCalendar:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseCalendar courseCalendar, RedirectAttributes redirectAttributes) {
		courseCalendarService.delete(courseCalendar);
		addMessage(redirectAttributes, "删除校历校准成功");
		return "redirect:"+Global.getAdminPath()+"/calendar/courseCalendar/?repage";
	}

}