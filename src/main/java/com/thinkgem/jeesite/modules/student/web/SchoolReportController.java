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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.service.CourseCompositionRulesService;
import com.thinkgem.jeesite.modules.course.service.CoursePointService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreBuilder;
import com.thinkgem.jeesite.modules.student.adapter.score.ClassScore;
import com.thinkgem.jeesite.modules.student.adapter.score.CourseScore;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
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
	@Autowired
	private SysConfigService sysConfigService;
	private SysConfig config;
	@ModelAttribute
	public StudentCourse get(@RequestParam(required=false) String id,Model model) {
		StudentCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentCourseService.get(id);
		}
		if (entity == null){
			entity = new StudentCourse();
		}
		config = sysConfigService.getModule(Global.SYSCONFIG_COURSE);
		model.addAttribute("config",config);
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/school/schoolReport";
	}
	@RequiresPermissions("student:studentCourse:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		
		
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
	/**
	 * 导出成绩单
	 * @param termYear
	 * @param department
	 * @param specialty
	 * @param clazz
	 * @param request
	 * @param response
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("export")
	public String export(String termYear,String department,String specialty,String clazz,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		
		if(org.springframework.util.StringUtils.isEmpty(termYear)) {
			throw new GITException("40400099","系统异常,未选择学期");
		}
		String[] ss = termYear.split("-");
		String startYear = ss[0];
		String endYear = ss[1];
		String n = ss[2];
		String filename = "成绩单.xls";
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		File file = new File(modelPath);
		Map<String,String> courseNameMap = new HashMap<String,String>();
		courseNameMap.put("{course_name}", "");
		Map<String,String> courseIdMap = new HashMap<String,String>();
		courseIdMap.put("{course_id}", "");
		Map<String,String> departmentMap = new HashMap<String,String>();
		departmentMap.put("{department}", officeService.get(department)!=null?officeService.get(department).getName():"");
		departmentMap.put("{specialty}",officeService.get(specialty)!=null?officeService.get(specialty).getName():"");
		departmentMap.put("{clazz}", officeService.get(clazz)!=null?officeService.get(clazz).getName():"");
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
	/**
	 * 导出成绩单,以课程编码的形式导出成绩单
	 * @param course
	 * @param request
	 * @param response
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("getCourse")
	public String getCourse(Course course,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		Course entity = courseService.get(course);
		if(org.springframework.util.StringUtils.isEmpty(entity)) {
			throw new GITException("40400000","课程信息异常");
		}
		String filename = entity.getCursName().concat("成绩单.xls");
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		File file = new File(modelPath);
		Map<String,String> courseNameMap = new HashMap<String,String>();
		courseNameMap.put("{course_name}",entity.getCursName());
		Map<String,String> courseIdMap = new HashMap<String,String>();
		courseIdMap.put("{course_id}", "");
		Map<String,String> departmentMap = new HashMap<String,String>();
		departmentMap.put("{department}","");
		departmentMap.put("{specialty}","");
		departmentMap.put("{clazz}", "");
		Map<String,String> dateMap = new HashMap<String,String>();
		dateMap.put("{startYear}", "");
		dateMap.put("{endYear}", "");
		dateMap.put("{n}", "");
		if(!org.springframework.util.StringUtils.isEmpty(entity.getCursYearTerm())) {
			String[] ss = entity.getCursYearTerm().split("-");
			dateMap.put("{startYear}",ss[0]);
			dateMap.put("{endYear}", ss[1]);
			dateMap.put("{n}", ss[2]);
		}

		response.setHeader("Content-Disposition", "attachment; filename="+new String(filename.getBytes("gbk"),"ISO-8859-1"));
		CourseScore courseScore = new CourseScore();
		List<StudentCourse> studentScoreCourses = studentCourseService.getStudentCourses(entity);
		List<StudentCourse> list = Lists.newArrayList();
		for(StudentCourse studentCourse : studentScoreCourses) {
			studentCourse.setCourse(entity);
			StudentCourse sc = studentCourseService.getStudentCourseByStudentCourse(studentCourse);
			if(!org.springframework.util.StringUtils.isEmpty(sc)) {
				studentCourse = sc;
			}
			list.add(studentCourse);
		}
		
		courseScore.setList(list);
		StudentScoreBuilder excelUtil = new StudentScoreBuilder();
		excelUtil.oper(file, courseNameMap,courseIdMap,departmentMap, dateMap,response.getOutputStream(),courseScore);
		return "modules/school/schoolReport?repage";
	}
	/*
	@RequestMapping("result")
	public String result(String termYear,String department,String specialty,String clazz,HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException, IOException {
		
		if(org.springframework.util.StringUtils.isEmpty(termYear)) {
			throw new GITException("40400099","系统异常,未选择学期");
		}
		String[] ss = termYear.split("-");
		String startYear = ss[0];
		String endYear = ss[1];
		String n = ss[2];
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
	*/
}
