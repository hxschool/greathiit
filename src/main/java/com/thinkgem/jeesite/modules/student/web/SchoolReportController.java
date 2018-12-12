package com.thinkgem.jeesite.modules.student.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/school/schoolReport";
	}
	
	@RequestMapping("export")
	public String export(String startYear,String endYear,String n,String department,String specialty,String clazz,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		String filename = "成绩单.xls";
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		
		File file = new File(modelPath);
		StudentReportUtil excelUtil = new StudentReportUtil();
		
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
		
		excelUtil.oper(file, courseNameMap,courseIdMap,departmentMap, dateMap,list,response.getOutputStream());
		return "modules/school/schoolReport?repage";
	}
}
