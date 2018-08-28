/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.web.excel.CourseSelectExcel;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.UserOperationLog;
import com.thinkgem.jeesite.modules.sys.service.UserOperationLogService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/select")
public class CourseSelectController extends BaseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private SelectCourseService selectCourseService;
	@Autowired
	private UserOperationLogService userOperationLogService;
	
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "selectCourseLog")
	public String selectCourseLog(UserOperationLog userOperationLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		userOperationLog.setModule("course");
		if(!UserUtils.getUser().isAdmin()) {
			userOperationLog.setUserNumber(UserUtils.getUser().getNo());
		}
		Page<UserOperationLog> page = userOperationLogService.findPage(new Page<UserOperationLog>(request, response), userOperationLog); 
		model.addAttribute("page", page);
		return "modules/course/log/userOperationLogList.jsp";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		return "modules/course/select/courseList";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "student")
	public String student(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		SelectCourse  selectCourse = new  SelectCourse();
		selectCourse.setCourse(course);
		Page<SelectCourse> page = selectCourseService.findPage(new Page<SelectCourse>(request, response),  selectCourse); 
		
		model.addAttribute("page", page);
		return "modules/course/select/studentList";
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/select/courseForm";
	}
	
	@RequestMapping(value = "exportView")
	public String exportView(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/select/exportView";
	}
	
	@RequiresPermissions("course:course:view")
    @RequestMapping(value = "export")
    public String exportFile(CourseSelectExcel courseSelectExcel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公共选课"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("公共选课", CourseSelectExcel.class).setDataList(selectCourseService.exportSelectCourse(courseSelectExcel)).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公共选课失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/select/exportView?repage";
    }

	
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "student/delete")
	public String studentDelete(SelectCourse selectCourse, RedirectAttributes redirectAttributes) {
		selectCourseService.delete(selectCourse);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/select/student?repage";
	}
	

}