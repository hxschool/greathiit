/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;
import com.thinkgem.jeesite.modules.course.web.excel.CourseSelectExcel;
import com.thinkgem.jeesite.modules.course.web.param.SelectCourseOfficeExt;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreBuilder;
import com.thinkgem.jeesite.modules.student.adapter.score.ClassScore;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.UserOperationLog;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.UserOperationLogService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

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
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CourseTeachingModeService courseTeachingModeService;
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id,Model model) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
		model.addAttribute("config", config);
		return entity;
	}
	

	@RequestMapping(value = "selectCourseLog")
	public String selectCourseLog(UserOperationLog userOperationLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		userOperationLog.setModule("course");
		if(!isAdmin()) {
			userOperationLog.setUserNumber(UserUtils.getUser().getNo());
		}
		Page<UserOperationLog> page = userOperationLogService.findPage(new Page<UserOperationLog>(request, response), userOperationLog); 
		model.addAttribute("page", page);
		return "modules/course/log/userOperationLogList.jsp";
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
		course.setCursYearTerm(config.getTermYear());
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		return "modules/course/select/courseList";
	}

	@RequestMapping(value = "student")
	public String student(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
		course.setCursYearTerm(config.getTermYear());
		SelectCourse  selectCourse = new  SelectCourse();
		selectCourse.setCourse(course);
		model.addAttribute("list", selectCourseService.findList(selectCourse));
		return "modules/course/select/studentList";
	}
	
	@RequestMapping(value = "clazz")
	public String clazz(SelectCourse selectCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Course course = new Course();
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
		course.setCursYearTerm(config.getTermYear());
		List<Course> courses = new ArrayList<Course>();
		courses = courseService.findList(course);
		selectCourse.setCourses(courses);
		List<SelectCourse> list = selectCourseService.findList(selectCourse);
		Map<String,SelectCourseOfficeExt> cls = new HashMap<String,SelectCourseOfficeExt>();
		
		for(SelectCourse sc:list) {
			try {
				User student = sc.getStudent();
				if(!org.springframework.util.StringUtils.isEmpty(student)) {
					String studentNumber = student.getNo();
					String clazzId = StudentUtil.getClassId(studentNumber);
					Office entity = officeService.get(clazzId);
					if (cls.containsKey(clazzId)) {
						SelectCourseOfficeExt selectCourseOfficeExt = cls.get(clazzId);
						selectCourseOfficeExt.setCnt(selectCourseOfficeExt.getCnt() + 1);
						cls.put(clazzId, selectCourseOfficeExt);
					} else {
						if (org.springframework.util.StringUtils.isEmpty(entity)) {
							entity = new Office();
							entity.setId("99999999");
							entity.setName(clazzId);
							entity.setCode("99999999");
						}
						SelectCourseOfficeExt selectCourseOfficeExt = new SelectCourseOfficeExt();
						selectCourseOfficeExt.setClazz(entity);
						selectCourseOfficeExt.setCnt(1);
						cls.put(clazzId, selectCourseOfficeExt);
					}
				}
				
			} catch (Exception ex) {
				logger.error("异常错误信息:{},{}",ex.getMessage(),sc);
			}
		}
		model.addAttribute("cls", cls);
		return "modules/course/select/studentClazz";
	}
	
	@RequestMapping(value = "info")
	public String info(CourseSelectExcel courseSelectExcel, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String clsId = courseSelectExcel.getCla().getId();
		SelectCourse selectCourse = new SelectCourse();
		Course course = new Course();
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
		course.setCursYearTerm(config.getTermYear());
		List<Course> courses = new ArrayList<Course>();
		courses = courseService.findList(course);
		for(Course c:courses) {
			c.setCourseTeachingMode(courseTeachingModeService.getCourseTeachingModeByCourse(c.getId()));
		}
		
		selectCourse.setCourses(courses);
		
		List<SelectCourse> list = selectCourseService.findList(selectCourse);
		List<SelectCourse> selectCourses = new ArrayList<SelectCourse>();
		for(SelectCourse sc:list) {
			User student = sc.getStudent();
			if(!org.springframework.util.StringUtils.isEmpty(student)) {
				String studentNumber = sc.getStudent().getNo();
				String clazzId = StudentUtil.getClassId(studentNumber);
				if(clsId.equals("99999999")) {
					Office office = officeService.get(clazzId);
					if(org.springframework.util.StringUtils.isEmpty(office)) {
						selectCourses.add(sc);
					}
					continue;
				}
				
				if(clazzId.equals(clsId)) {
					selectCourses.add(sc);
				}
			}
		}
		model.addAttribute("list", selectCourses);
		return "modules/course/select/studentClazzInfo";
	}

	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/select/courseForm";
	}
	
	@RequestMapping(value = "exportView")
	public String exportView(CourseSelectExcel courseSelectExcel, Model model) {
		Course course = new Course();
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
		course.setCursYearTerm(config.getTermYear());
		List<Course> courses = new ArrayList<Course>();
		if(!isAdmin()) {
			course.setTeacher(UserUtils.getTeacher());
		}
		courses = courseService.findList(course);
		courseSelectExcel.setCourses(courses);
		model.addAttribute("list", selectCourseService.exportSelectCourse(courseSelectExcel));
		return "modules/course/select/exportView";
	}
	
	
	@RequestMapping(value = "unknown")
    public String unknown(CourseSelectExcel courseSelectExcel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "未知数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            
            List<SelectCourse> list = selectCourseService.findList(new SelectCourse());
    		List<SelectCourse> selectCourses = new ArrayList<SelectCourse>();
			for (SelectCourse sc : list) {
				String studentNumber = sc.getStudent().getNo();
				String clazzId = StudentUtil.getClassId(studentNumber);

				Office office = officeService.get(clazzId);
				if (org.springframework.util.StringUtils.isEmpty(office)) {
					selectCourses.add(sc);
				}
				continue;

			}
			List<CourseSelectExcel> courseSelectExcels = new ArrayList<CourseSelectExcel>();
			int i = 1;
			for(SelectCourse sc :selectCourses) {
				Course c = sc.getCourse();
				CourseSelectExcel cs = new CourseSelectExcel();
				cs.setId(String.valueOf(i));
				cs.setCourse(c);
				cs.setUser(sc.getStudent());
				courseSelectExcels.add(cs);
				i++;
			}
            
    		new ExportExcel("未知数据", CourseSelectExcel.class).setDataList(courseSelectExcels).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公共选课失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/select/exportView?repage";
    }
	
    @RequestMapping(value = "export")
    public String exportFile(CourseSelectExcel courseSelectExcel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公共选课"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<CourseSelectExcel> courseSelectExcels = selectCourseService.exportSelectCourse(courseSelectExcel);
    		new ExportExcel("公共选课", CourseSelectExcel.class).setDataList(courseSelectExcels).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公共选课失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/select/?cursProperty=20&repage";
    }

    @RequestMapping(value = "studentCourse")
    public String studentCourse(Course course, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes ,Model model) throws FileNotFoundException, IOException {
    	Course entity = courseService.get(course);
		SelectCourse  selectCourse = new  SelectCourse();
		selectCourse.setCourse(entity);
		List<SelectCourse>  ll = selectCourseService.findList(selectCourse);
		String filename = "成绩单.xls";
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		
		File file = new File(modelPath);
		StudentScoreBuilder excelUtil = new StudentScoreBuilder();
		
		Map<String,String> courseNameMap = new HashMap<String,String>();
		courseNameMap.put("{course_name}", entity.getCursName());
		
		Map<String,String> courseIdMap = new HashMap<String,String>();
		courseIdMap.put("{course_id}", entity.getId());
		
		Map<String,String> departmentMap = new HashMap<String,String>();
		departmentMap.put("{department}", "");
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
		
	
		response.setHeader("content-disposition", "attachment;filename="  
                + URLEncoder.encode(filename, "UTF-8"));
		
		List<UcStudent> list = new ArrayList<UcStudent>();
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		for(SelectCourse sc :ll) {
			User user = sc.getStudent();
			if(!org.springframework.util.StringUtils.isEmpty(user)&&!org.springframework.util.StringUtils.isEmpty(user.getNo())) {
				UcStudent uc = new UcStudent();
				uc.setUsername(user.getName());
				uc.setStudentNumber(user.getNo());
				if(org.springframework.util.StringUtils.isEmpty(uc)) {
					failureMsg.append("<br/>学号 "+user.getNo()+" 教务处数据未查找到当前学号数据; ");
					failureNum++;
					
				}else {
					list.add(uc);
				}
			}
		}
		addMessage(redirectAttributes, "失败"+failureNum+" 条"+failureMsg);
		AbsStudentScoreAdapter<UcStudent> classScore = new ClassScore();
		classScore.setList(list);
		excelUtil.oper(file,courseNameMap,courseIdMap, departmentMap, dateMap,response.getOutputStream(),classScore);
		
		return "redirect:" + adminPath + "/course/select/exportView?repage";
    }
	

	
	
	@RequestMapping(value = "student/delete")
	public String studentDelete(SelectCourse selectCourse, RedirectAttributes redirectAttributes) {
		selectCourseService.delete(selectCourse);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/select/student?repage";
	}
	

}