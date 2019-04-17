/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.service.TeacherCourseService;

/**
 * 教师信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/course")
public class TeacherCourseController extends BaseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private TeacherCourseService teacherCourseService;
	@Autowired
	private StudentCourseService studentCourseService;
	
	@RequiresPermissions("teacher:course:view")
	@RequestMapping(value = {"Teacher_Management_4_excute","", "Teacher_Management_2_selectStuPer"})
	public String list(StudentCourse studentCourse,HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Course course = new Course();
		if(!user.isAdmin()) {
			course.setTeacher(UserUtils.getTeacher());
		}

		List<Course> courses = courseService.findList(course);
		
		SysConfig sysConfig = sysConfigService.getModule(Global.SYSCONFIG_COURSE);
		String termYear = sysConfig.getTermYear();
		if(!StringUtils.isEmpty(studentCourse.getTermYear())) {
			termYear = studentCourse.getTermYear();
		}
		studentCourse.setTermYear(termYear);
		List<StudentCourse>  studentCourses = studentCourseService.findListByStudentCourseAndCourse(studentCourse);
		model.addAttribute("studentCourses", studentCourses);
		model.addAttribute("courses", courses);
		model.addAttribute("yearTerm", DateUtils.termYear().get(termYear));
		return "modules/teacher/course/TeacherManagement4";
	}

	//@RequiresPermissions("teacher:course:view")
	//@RequestMapping(value = {"upload","FileUpload"})
	public String upload_old(@RequestParam("file") MultipartFile multipartFile,String term,String course,String clazz,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		if (!multipartFile.isEmpty()) {
			String oldname = multipartFile.getOriginalFilename();
			String suffix = oldname.substring(oldname.lastIndexOf(".") + 1);
			String fileName = oldname;//FileUtils.getRandomFileName().concat(".").concat(suffix);
			 
			String path = request.getSession().getServletContext().getRealPath("/resources/teacher/"+fileName);  
			File file = new File(path);
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
			String filename;
			int index1 = fileName.lastIndexOf(".");
			int index2 = fileName.length();
			String postf = fileName.substring(index1 + 1, index2);
			if (postf.equals("xlsx")) {
				filename = term + "_" + course + "_" + clazz + ".xlsx";
			} else {
				filename = term + "_" + course + "_" + clazz + ".xls";
			}
			
			teacherCourseService.handleExcel(path);
		}
		
		return "";
	}
	@RequiresPermissions("teacher:course:view")
	@RequestMapping(value = {"upload","FileUpload"})
	public String upload(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) throws Exception {
		String folder=System.getProperty("java.io.tmpdir");
		File file = new File(folder,multipartFile.getOriginalFilename()); 
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);  
		ImportExcel ei = new ImportExcel(file, 1, 0);
		Row courseRow = ei.getRow(1);
		Cell courseIdCell = courseRow.getCell(5);   
		String courseId = courseIdCell.getStringCellValue();
		Course course = courseService.get(courseId);
		if(org.springframework.util.StringUtils.isEmpty(course)) {
			throw new GITException("40000404","上传成绩文件异常,缺少课程编号");
		}
		Course entity = new Course();
		entity.setTeacher(UserUtils.getTeacher());
		List<Course> courses = courseService.findList(entity);
		List<String> csList = new ArrayList<String>();
		for(Course cs : courses) {
			csList.add(cs.getId());
		}
		if(!csList.contains(courseId)) {
			throw new GITException("40000404","上传成绩不是当前任课教师课程,请检查上传文件内容");
		}
		ei = new ImportExcel(file, 2, 0);
		List<StudentCourse> list = ei.getDataList(StudentCourse.class);
		String str = studentCourseService.importStudentCourse(course,list);

		addMessage(redirectAttributes, str);
		return "redirect:" + adminPath + "/teacher/course/Teacher_Management_4_excute?repage";
	}
		
		
	
	
	@RequiresPermissions("teacher:course:view")
	@RequestMapping(value = "ajaxAllClass")
	@ResponseBody
	public List<String> ajaxAllClass(String courseId,HttpServletRequest request, HttpServletResponse response, Model model) {
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setCourseId(courseId);
		List<CourseSchedule> courseSchedules = courseScheduleService.findList(courseSchedule);
		List<String> list = new ArrayList<String>();
		for(CourseSchedule cs : courseSchedules) {
			String courseClass = cs.getCourseClass();
			if(!StringUtils.isEmpty(courseClass)) {
				String[] clazz = courseClass.split(",");
				for(String c:clazz) {
					list.add(c);
				}
			}
		}
		return list;
	}
}