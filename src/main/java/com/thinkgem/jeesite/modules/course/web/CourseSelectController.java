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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.CourseUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;
import com.thinkgem.jeesite.modules.course.web.excel.CourseSelectExcel;
import com.thinkgem.jeesite.modules.course.web.param.SelectCourseOfficeExt;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreBuilder;
import com.thinkgem.jeesite.modules.student.adapter.score.ClassScore;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.UserOperationLog;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.service.UserOperationLogService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
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
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private SystemService systemService;
	
	private SysConfig config;
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id,Model model) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		config = sysConfigService.getModule(Global.SYSCONFIG_SELECT);
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
		
		if(!StringUtils.isEmpty(course.getCursYearTerm())) {
			config = new SysConfig();
			config.setTermYear(course.getCursYearTerm());
		}
			course.setCursYearTerm(config.getTermYear());
		if(!isAdmin()) {
			course.setTeacher(UserUtils.getTeacher());
		}
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("config", config);
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
				Student student = sc.getStudent();
				if(!org.springframework.util.StringUtils.isEmpty(student)) {
					String studentNumber = student.getStudentNumber();
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
			Student student = sc.getStudent();
			if(!org.springframework.util.StringUtils.isEmpty(student)) {
				String studentNumber = student.getStudentNumber();
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
				String studentNumber = sc.getStudent().getStudentNumber();
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
				User user = new User();
				user.setNo(sc.getStudent().getStudentNumber());
				cs.setUser(user);
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
    
    
    /**
     * 可以废弃的导出成绩单功能
     * @param course
     * @param request
     * @param response
     * @param redirectAttributes
     * @param model
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
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
		for (SelectCourse sc : ll) {
			Student student = sc.getStudent();
			if (!org.springframework.util.StringUtils.isEmpty(student)
					&& !org.springframework.util.StringUtils.isEmpty(student.getStudentNumber())) {
				UcStudent uc = new UcStudent();
				uc.setUsername(student.getName());
				uc.setStudentNumber(student.getStudentNumber());
				if (org.springframework.util.StringUtils.isEmpty(uc)) {
					failureMsg.append("<br/>学号 " + student.getStudentNumber() + " 教务处数据未查找到当前学号数据; ");
					failureNum++;

				} else {
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
	

    @RequestMapping(value = "import_select_view")
	public String viewSelect() {
		return "modules/course/select/import_select_view";
	}
	@RequestMapping(value = "import_select")
	public String importSelectFile(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {

		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel importExcel = new ImportExcel(file, 2);
			String[][] selectCourses = importExcel.importFile();
			for (String[] selectCourse : selectCourses) {
				try {
					String curs_num = selectCourse[0];// 课程编码
					String curs_edu_num = curs_num;// 课程编码
					String curs_name = selectCourse[1];// 课程名称
					String tchr_name = selectCourse[2];// 教师名称
					String curs_class_hour = selectCourse[3];// 总学时
					String curs_credit = selectCourse[4];// 学分
					String curs_year_term = selectCourse[5];// 学期
					String curs_property = DictUtils.getDictValue(selectCourse[6], "course_property", "公共选修课");// 课程性质
					String teac_method = DictUtils.getDictValue(selectCourse[7], "teac_method", "面授");// 授课类型/课程类型
					String remark = selectCourse[8];
					String curs_face = selectCourse[9];// 面向学生
					String lower_limit = selectCourse[10];// 下限
					String upper_limit = selectCourse[11];// 上限

					User user = systemService.isExisUser("", "", tchr_name, null, null);

					Teacher teacher = new Teacher();
					teacher.setTeacherNumber(user.getNo());
					Course course = new Course();
					course.setIsNewRecord(true);
					String courseId = systemService.getSequence("serialNo10");
					course.setId(courseId);
					course.setTeacher(teacher);
					course.setCursEduNum(curs_edu_num);
					course.setCursNum(curs_num);
					course.setCursName(curs_name);
					course.setCursClassHour(curs_class_hour);
					course.setCursCredit(curs_credit);
					course.setCursYearTerm(curs_year_term);
					course.setCursProperty(curs_property);

					int upperLimit = 0, lowerLimit = 0;
					if (!StringUtils.isEmpty(lower_limit) && !lower_limit.equals("无")) {
						lowerLimit = new Double(lower_limit).intValue();
					}
					if (!StringUtils.isEmpty(upper_limit) && !upper_limit.equals("无")) {
						upperLimit = new Double(upper_limit).intValue();
					}
					course.setLowerLimit(lowerLimit);
					course.setUpperLimit(upperLimit);
					course.setCursFace(CourseUtil.grade(curs_face));
					course.setCursStatus(Course.PAIKE_STATUS_YI_PAIKE);
					String curs_type = "考查";
					course.setCursType(DictUtils.getDictValue(curs_type, "course_curs_type", "考查"));
					String cursForm = "其他";
					course.setCursForm(DictUtils.getDictValue(cursForm, "course_curs_form", "其他"));
					course.setCursIntro(remark);
					course.setRemarks(remark);
					
					CourseTeachingMode courseTeachingMode = new CourseTeachingMode();
					courseTeachingMode.setTeacMethod(teac_method);
					courseTeachingMode.setCourseId(courseId);
					courseTeachingMode.setPeriod(curs_class_hour);
					courseTeachingModeService.save(courseTeachingMode);
					
					
					if(!StringUtils.isEmpty(selectCourse[7])) {
						String method = selectCourse[7].trim();
						if(method.equals("面授")) {
							String time = selectCourse[12];
							String address = selectCourse[13];
							if(!StringUtils.isEmpty(time)&&!StringUtils.isEmpty(address)) {
								//周一，5-6节
								String ss[] = time.split("，");
								
								if(ss.length==1) {
									String xq = CourseUtil.zhouValue("周一");
									String jie =  CourseUtil.jieValue(ss[0]);
									String school = CourseUtil.schoolRootMap.get(address.substring(0,1));
									String room = address.substring(1);
									String time_add = curs_year_term.concat(school).concat(room).concat("01").concat(jie).concat(xq);
									CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
									if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
										courseSchedule.setScLock("2");
										courseSchedule.setCourseClass("00000000");
										courseSchedule.setCourseId(courseId);
										courseSchedule.setTips(remark);
										courseScheduleService.save(courseSchedule);
									}
								}
								
								if(ss.length==2) {
									String xq = CourseUtil.zhouValue(ss[0]);
									String jie =  CourseUtil.jieValue(ss[1]);
									String school = CourseUtil.schoolRootMap.get(address.substring(0,1));
									String room = address.substring(1);
									String time_add = curs_year_term.concat(school).concat(room).concat("01").concat(jie).concat(xq);
									CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
									if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
										courseSchedule.setScLock("2");
										courseSchedule.setCourseClass("00000000");
										courseSchedule.setCourseId(courseId);
										courseSchedule.setTips(remark);
										courseScheduleService.save(courseSchedule);
									}
								}
	
							}
							
						}
					}
					courseService.save(course);
					successNum++;
				} catch (Exception e) {
					e.printStackTrace();
					failureMsg.append("<br/>课程信息异常: " + selectCourse + " ; " + e.getMessage());
					failureNum++;

				}
				if (failureNum > 0) {
					failureMsg.insert(0, "，失败 " + failureNum + " 条，导入信息如下：");
				}

				addMessage(redirectAttributes, "已成功导入 " + successNum + " 条课程信息" + failureMsg);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			addMessage(model, "导入失败！失败信息："+ex.getMessage());
		}
		return "redirect:" + adminPath + "/course/select/import_select_view?repage";
	}

	
	
	
	@RequestMapping(value = "student/delete")
	public String studentDelete(SelectCourse selectCourse, RedirectAttributes redirectAttributes) {
		selectCourseService.delete(selectCourse);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/select/student?repage";
	}
	

}