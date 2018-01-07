/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/paike")
public class PaikeCourseController extends BaseController {
	@Autowired
	private CourseCalendarService courseCalendarService;
	@Autowired
	private CourseYearTermService courseYearTermService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private CourseService courseService;
	@RequiresPermissions("course:paike:view")
	@RequestMapping(value = "lock")
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
	
	
	@RequiresPermissions("calendar:courseCalendar:view")
	@RequestMapping(value = "ajaxAddLock")
	@ResponseBody
	public String addlock(String time_add,String tips, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
			courseSchedule.setScLock("0");
			courseSchedule.setTips(tips);
			courseScheduleService.save(courseSchedule);
			return "1";
		}
		return "0";
	}
	
	@RequiresPermissions("calendar:courseCalendar:view")
	@RequestMapping(value = "ajaxDeleteLock")
	@ResponseBody
	public String dellock(String time_add, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("0")) {
			courseSchedule.setScLock("1");
			courseSchedule.setTips("");
			courseScheduleService.save(courseSchedule);
			return "1";
		}
		return "0";
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
}