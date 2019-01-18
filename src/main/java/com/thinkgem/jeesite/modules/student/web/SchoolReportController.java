package com.thinkgem.jeesite.modules.student.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreBuilder;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreCourse;
import com.thinkgem.jeesite.modules.student.adapter.score.ClassScore;
import com.thinkgem.jeesite.modules.student.adapter.score.CourseScore;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;
import com.thinkgem.jeesite.modules.uc.student.service.UcStudentService;

@Controller
@RequestMapping(value = "${adminPath}/school/schoolReport")
public class SchoolReportController {
	@Autowired
	private UcStudentService ucStudentService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private StudentCourseService studentCourseService;
	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/school/schoolReport";
	}
	
	@RequestMapping("export")
	public String export(String startYear,String endYear,String n,String department,String specialty,String clazz,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		String filename = "成绩单.xls";
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		
		File file = new File(modelPath);
	
		
		Map<String,String> courseNameMap = new HashMap<String,String>();
		courseNameMap.put("{course_name}", "");
		
		Map<String,String> courseIdMap = new HashMap<String,String>();
		courseIdMap.put("{course_id}", "");
		
		Map<String,String> departmentMap = new HashMap<String,String>();
		departmentMap.put("{department}", officeService.get(department).getName());
		departmentMap.put("{specialty}",officeService.get(specialty).getName());
		departmentMap.put("{clazz}", officeService.get(clazz).getName());
		Map<String,String> dateMap = new HashMap<String,String>();
		dateMap.put("{startYear}", startYear);
		dateMap.put("{endYear}", endYear);
		dateMap.put("{n}", n);
		response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode(filename, "UTF-8"));
		UcStudent ucStudent = new UcStudent();
		ucStudent.setDepartmentId(department);
		ucStudent.setMajorId(specialty);
		String classNumber = officeService.get(clazz).getName();
		ucStudent.setClassNumber(classNumber);
		List<UcStudent> list = ucStudentService.exportList(ucStudent);
		
		AbsStudentScoreAdapter<UcStudent> classScore = new ClassScore();
		classScore.setList(list);
		StudentScoreBuilder excelUtil = new StudentScoreBuilder();
		excelUtil.oper(file, courseNameMap,courseIdMap,departmentMap, dateMap,response.getOutputStream(),classScore);
		return "modules/school/schoolReport?repage";
	}
	
	@RequestMapping("result")
	public String result(String startYear,String endYear,String n,String department,String specialty,String clazz,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		String filename = "成绩单.xls";
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		
		File file = new File(modelPath);
		
		
		Map<String,String> courseNameMap = new HashMap<String,String>();
		courseNameMap.put("{course_name}", "");
		
		Map<String,String> courseIdMap = new HashMap<String,String>();
		courseIdMap.put("{course_id}", "");
		
		Map<String,String> departmentMap = new HashMap<String,String>();
		departmentMap.put("{department}", officeService.get(department).getName());
		departmentMap.put("{specialty}",officeService.get(specialty).getName());
		departmentMap.put("{clazz}", officeService.get(clazz).getName());
		Map<String,String> dateMap = new HashMap<String,String>();
		dateMap.put("{startYear}", startYear);
		dateMap.put("{endYear}", endYear);
		dateMap.put("{n}", n);
		response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode(filename, "UTF-8"));
		UcStudent ucStudent = new UcStudent();
		ucStudent.setDepartmentId(department);
		ucStudent.setMajorId(specialty);
		String classNumber = officeService.get(clazz).getName();
		ucStudent.setClassNumber(classNumber);
		List<UcStudent> list = ucStudentService.exportList(ucStudent);
		AbsStudentScoreAdapter<StudentScoreCourse> courseScore = new CourseScore();
		List<StudentScoreCourse> studentScoreCourses = new ArrayList<StudentScoreCourse>();
		for(UcStudent uc:list) {
			StudentScoreCourse ssc = new StudentScoreCourse();
			ssc.setUcStudent(uc);
			List<StudentCourse> studentCourses = studentCourseService.findListByStudentNumber(uc.getStudentNumber());
			ssc.setStudentCourses(studentCourses);
			studentScoreCourses.add(ssc);
		}
		courseScore.setList(studentScoreCourses);
		StudentScoreBuilder excelUtil = new StudentScoreBuilder();
		excelUtil.oper(file, courseNameMap,courseIdMap,departmentMap, dateMap,response.getOutputStream(),courseScore);
		return "modules/school/schoolReport?repage";
	}
}
