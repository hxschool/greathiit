package com.thinkgem.jeesite.modules.student.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.NumberUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.entity.CoursePoint;
import com.thinkgem.jeesite.modules.course.service.CourseCompositionRulesService;
import com.thinkgem.jeesite.modules.course.service.CoursePointService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreBuilder;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreCourse;
import com.thinkgem.jeesite.modules.student.adapter.score.ClassScore;
import com.thinkgem.jeesite.modules.student.adapter.score.CourseScore;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;
import com.thinkgem.jeesite.modules.uc.student.service.UcStudentService;

@Controller
@RequestMapping(value = "${adminPath}/school/schoolReport")
public class SchoolReportController extends BaseController {
	@Autowired
	private UcStudentService ucStudentService;
	@Autowired
	private OfficeService officeService;


	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseCompositionRulesService courseCompositionRulesService;
	@Autowired
	private CoursePointService coursePointService;
	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/school/schoolReport";
	}
	@RequiresPermissions("student:studentCourse:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {
		
		String folder=System.getProperty("java.io.tmpdir");
		File file = new File(folder,multipartFile.getOriginalFilename()); 
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);  
		
		ImportExcel ei;
		try {
			ei = new ImportExcel(file, 1, 0);
			Row courseRow = ei.getRow(1);
			Cell courseCell = courseRow.getCell(0);   
			String courseId = courseCell.getStringCellValue();
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
			ei = new ImportExcel(file, 13, 0);
			List<StudentCourse> list = ei.getDataList(StudentCourse.class);
			studentCourseService.importStudentCourse(course,list);
		} catch (InvalidFormatException e) {
			throw new GITException("50000404","读取excel文件异常");
		} catch (IOException e) {
			throw new GITException("50000404","读取excel文件异常");
		} catch (InstantiationException e) {
			throw new GITException("50000404","系统异常,请联系管理员 : 18801029695");
		} catch (IllegalAccessException e) {
			throw new GITException("50000404","系统异常,请联系管理员 : 18801029695");
		}
		return "redirect:" + adminPath + "/student/studentCourse/list?repage";
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
			StudentCourse studentCourse = new StudentCourse();
			Student student = new Student();
			student.setStudentNumber(uc.getStudentNumber());
			studentCourse.setStudent(student);
			List<StudentCourse> studentCourses = studentCourseService.findList(studentCourse);
			ssc.setStudentCourses(studentCourses);
			studentScoreCourses.add(ssc);
		}
		courseScore.setList(studentScoreCourses);
		StudentScoreBuilder excelUtil = new StudentScoreBuilder();
		excelUtil.oper(file, courseNameMap,courseIdMap,departmentMap, dateMap,response.getOutputStream(),courseScore);
		return "modules/school/schoolReport?repage";
	}
}
