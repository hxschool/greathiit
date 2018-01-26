/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.http.HttpRequest;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;
import com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseYearTermService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.service.TeacherService;

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
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private OfficeService officeService;
	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "lock")
	public String lock(CourseCalendar courseCalendar, Model model) {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm="20181";
		if(!org.springframework.util.StringUtils.isEmpty(courseYearTerm)) {
			yearTerm = courseYearTerm.getYearTerm();
		}
		
		model.addAttribute("yearTerm",yearTerm);
		model.addAttribute("courseCalendar", courseCalendarService.systemConfig());
		return "modules/paike/LockCourse";
	}
	
	@RequiresPermissions("course:paike:edit")
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
	
	/**
	 * 新增排课页面
	 * @param courseCalendar
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addCourse")
	public String addCourse(CourseCalendar courseCalendar, Model model) {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm="20181";
		if(!org.springframework.util.StringUtils.isEmpty(courseYearTerm)) {
			yearTerm = courseYearTerm.getYearTerm();
		}
		
		model.addAttribute("yearTerm",yearTerm);
		model.addAttribute("courseCalendar", courseCalendarService.systemConfig());
		return "modules/paike/AddCourse";
	}
	/**
	 * 添加排课信息
	 * @param time_add
	 * @param tips
	 * @param model
	 * @return
	 */
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxAddCourse")
	@ResponseBody
	public String ajaxAddCourse(String time_add,String student_id,String course_id,String tips, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
			courseSchedule.setScLock("2");
			courseSchedule.setCourseClass(student_id);
			courseSchedule.setCourseId(course_id);
			courseSchedule.setTips(tips);
			courseScheduleService.save(courseSchedule);
			return "1";
		}
		return "0";
	}
	
	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxDeleteCourse")
	@ResponseBody
	public String ajaxDeleteCourse(String time_add, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("0")) {
			courseSchedule.setScLock("1");
			courseSchedule.setTips("");
			courseScheduleService.save(courseSchedule);
			return "1";
		}
		return "0";
	}
	
	
	@RequiresPermissions("course:paike:edit")
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
				
				String courseClass = courseSchedule.getCourseClass();
				
				if(courseSchedule.getScLock().equals("0")||courseClass.length()<7) {
					ps.write("<div class=\"course_text\"></div>");
					ps.write("<div class=\"course_text\">"+courseClass+"</div>");
				}else {
					Course course = courseService.get(courseSchedule.getCourseId());
					ps.write("<div class=\"course_text\">课程:"+course.getCursName()+"</div>");
					Teacher teacher = teacherService.getTeacherByTeacherNumber(course.getTeacher().getNo());
					ps.write("<div class=\"course_text\">教师:"+teacher.getTchrName()+"</div>");
		
					String[] courseArray = courseClass.split(",");
					StringBuilder sb = new StringBuilder();
					for(String str:courseArray) {
						Office clazz = officeService.get(str);
						String officeId = str.substring(4,6);
						Office office = officeService.get(officeId);
						String company = "";
						String major = "";
						if(!StringUtils.isEmpty(office)) {
							major = office.getName();
							company = office.getParent().getName();
						}
						sb.append("<a title=\""+company + "," + major + "," + clazz.getName() + "\">"+str+"</a>");
						sb.append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					ps.write("<div class=\"course_text\">"+ sb.toString() +"</div>");
				}
				
				
				ps.write("<div class=\"course_text\">备注:"+courseSchedule.getTips()+"</div>");
				
			}
			ps.write("@");
		}
		ps.flush();
	}
	
	
	@RequestMapping(value = "ajaxStudentCourse")
	public void ajaxStudentCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter ps = response.getWriter();
		
		User student = UserUtils.getUser();
		
		List<CourseSchedule> courseSchedules = courseScheduleService.findListByStudentNumber(student.getNo());
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
				String courseClass = courseSchedule.getCourseClass();
				Course course = courseService.findListByCourse(courseNumber);
				
				ps.write("<div class=\"course_text\">课程:"+course.getCursName()+"</div>");
				
				if(courseSchedule.getScLock().equals("0")||courseClass.length()<7) {
					ps.write("<div class=\"course_text\"></div>");
					ps.write("<div class=\"course_text\">"+courseClass+"</div>");
				}else {
					
					Teacher teacher = teacherService.getTeacherByTeacherNumber(course.getTeacher().getNo());
					ps.write("<div class=\"course_text\">教师:"+teacher.getTchrName()+"</div>");
					String grade = courseClass.substring(0,4);
					String school = courseClass.substring(4,5);
					String major = courseClass.substring(5,7);
					String clazz = courseClass.substring(7);
					Office company = officeService.get(school);
					Office office = officeService.get(major);
					ps.write("<div class=\"course_text\">"+company.getName()+ " " + office.getName() + " "+clazz +"</div>");
				}
				
				ps.write("<div class=\"course_text\">备注:"+courseSchedule.getTips()+"</div>");
				
			}
			ps.write("@");
		}
		ps.flush();
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "ajaxAllCourse")
	public List<TreeLink> ajaxAllCourse( HttpRequest request, HttpServletResponse response) {
		
		List<Course> list1 = courseService.findCoursesByPaike(new Course());
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(Course course:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(course.getId());
			String teacherNumber = course.getTeacher().getNo();
			Teacher teacher = teacherService.getTeacherByTeacherNumber(teacherNumber);
			if(!StringUtils.isEmpty(teacher)) {
				treeLink.setName(course.getCursName().concat("("+course.getCursClassHour()+")").concat("|").concat(teacher.getTchrName()));
			}
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}
}