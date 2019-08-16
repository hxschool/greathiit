/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;
import com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentActivity;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.entity.StudentItem;
import com.thinkgem.jeesite.modules.student.service.StudentActivityService;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.student.service.StudentItemService;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 学生信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/student/student")
public class StudentController extends BaseController {
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentActivityService studentActivityService;
	@Autowired
	private StudentItemService studentItemService;
	@Autowired
	private CourseCalendarService courseCalendarService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private SelectCourseService selectCourseService;
	@Autowired
	private StudentCourseService studentCourseService;
	@ModelAttribute
	public Student get(@RequestParam(required=false) String id,Model model) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentService.get(id);
		}
		if (entity == null){
			entity = new Student();
		}
		model.addAttribute("config", sysConfigService.getModule(Global.SYSCONFIG_RESULT));
		return entity;
	}

	//个人资料	30	/student/student/Student_Information_Detail
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Information_Detail")
	public String Student_Information_Detail(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		//初始化学生信息
		if(org.springframework.util.StringUtils.isEmpty(student)) {
				String idCard = user.getLoginName();
				student = new Student();
				student.setName(user.getName());
				student.setStudentNumber(user.getNo());
				student.setPhone(user.getPhone());
				student.setMail(user.getEmail());
				if(IdcardUtils.validateIdCard18(idCard)) {
					student.setIdCard(idCard);
					student.setGender(IdcardUtils.getGenderByIdCard(idCard));
					String birthday = IdcardUtils.getBirthByIdCard(idCard);
					if(!org.springframework.util.StringUtils.isEmpty(birthday)) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
						formatter.setLenient(false);
						Date newDate = formatter.parse(birthday);
						formatter = new SimpleDateFormat("yyyy-MM-dd");
						student.setBirthday(DateUtils.parseDate(formatter.format(newDate)));
					}
				}
				studentService.save(student);
		}
		model.addAttribute("student",student);		
		return "modules/student/StudentInformationDetail";
	}
	
	//编辑个人资料	30	/student/student/Student_Information_Modify
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Information_Modify")
	public String Student_Information_Modify( HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		model.addAttribute("student",student);		
		
		return "modules/student/StudentInformationModify";
	}
	
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Information_Modify_Save")
	public String Student_Information_Modify_Save(Student student, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		
		User user = UserUtils.getUser();
		Student entity = studentService.getStudentByStudentNumber(user.getNo());
		BeanUtils.copyProperties(student, entity);
		studentService.save(student);
		model.addAttribute("student",student);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/Student_Information_Modify?repage";
	}
	
	
	
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_addActivity")
	public String Student_Portfolio_Activity_addActivity(StudentActivity studentActivity, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		User student = UserUtils.getUser();
		studentActivity.setStudent(student);
		studentActivityService.save(studentActivity);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/Student_Portfolio_Activity?repage";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_deleteActivity")
	public String Student_Portfolio_Activity_deleteActivity(@RequestParam("actId")String actId, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		User user = UserUtils.getUser();
		StudentActivity studentActivity = new StudentActivity();
		studentActivity.setId(actId);
		studentActivity.setStudent(user);
		studentActivityService.delete(studentActivity);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/Student_Portfolio_Activity?repage";
	}
	
	//实践活动	20	/student/student/Student_Portfolio_Activity
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity")
	public String Student_Portfolio_Activity( HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		List<StudentActivity> orgActivities = studentActivityService.getOrgActivities(user.getNo());
		List<StudentActivity> attendActivities = studentActivityService.getAttendActivities(user.getNo());
		List<StudentActivity> socialActivities = studentActivityService.getSocialActivities(user.getNo());
		//组织活动
		model.addAttribute("orgActivities",orgActivities);
		//参与活动
		model.addAttribute("attendActivities",attendActivities);
		//社会实践
		model.addAttribute("socialActivities",socialActivities);
		return "modules/student/StudentPortfolioActivity";
	}
	//规划目标	10	/student/student/Student_Portfolio_Goal
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Goal")
	public String Student_Portfolio_Goal(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		model.addAttribute("student",student);		
		return "modules/student/StudentPortfolioGoal";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Goal_Save")
	public String Student_Portfolio_Goal_Save(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		studentService.save(student);
		model.addAttribute("student",student);		
		return "redirect:"+Global.getAdminPath()+"/student/student/Student_Portfolio_Goal?repage";
	}
	
	//课程成绩	40	/student/student/Student_Performance
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Performance")
	public String Student_Performance(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		studentCourse.setStudentNumber(user.getNo());
		List<StudentCourse> studentCourses = studentCourseService.findByParentIdsLike(studentCourse);
		Set<Course> courses = new HashSet();
		for(StudentCourse sc:studentCourses) {
			courses.add(sc.getCourse());
		}
		model.addAttribute("studentCourses",studentCourses);
		model.addAttribute("courses",courses);
		return "modules/student/StudentPerformance";
	}
	
	//课程表
	@RequestMapping("Student_Class_Schedule_Card ")
	public String Student_Class_Schedule_Card(Student student, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		User user = UserUtils.getUser();
		if(org.springframework.util.StringUtils.isEmpty(user.getClazz())) {
			throw new RuntimeException("当前学生未初始化班级信息,请设置班级信息");
		}
		String week = DateUtils.getWeek();
		int weekOfDate = DateUtils.getWeekOfDate();
		model.addAttribute("xqs",Global.xqs);
		model.addAttribute("weekOfDate",weekOfDate);
		model.addAttribute("week",week);
		model.addAttribute("weeks", Global.weeks);
		return "modules/student/course/StudentClassScheduleCard";
	}
	@RequestMapping("Ajax_Student_Class_Schedule_Card")
	@ResponseBody
	public List<CourseScheduleExt> getCourseScheduleExts(String weekNumber){
		if(org.springframework.util.StringUtils.isEmpty(weekNumber)) {
			CourseCalendar courseCalendar = courseCalendarService.get("1");
			String startDate = courseCalendar.getCalendarYear() + "-" + courseCalendar.getCalendarMonth() + "-" + courseCalendar.getCalendarDay();
			String endDate = DateUtils.formatDate(new Date());
			weekNumber = String.valueOf(DateUtils.getWeek(startDate, endDate));	
		}
		if(weekNumber.length()==1) {
			weekNumber = "0"+weekNumber;
		}
		Student student = UserUtils.getStudent();
		CourseScheduleExt cse = new CourseScheduleExt();
		if(!org.springframework.util.StringUtils.isEmpty(student.getClazz())) {
			cse.setCourseClass(student.getClazz().getId());
		}
		cse.setTimeAdd("%"+weekNumber+"__");
		List<CourseScheduleExt> courseSchedules = courseScheduleService.getCourseScheduleExt(cse);
		SelectCourse selectCourse = new SelectCourse();
		selectCourse.setStudent(student);
		List<SelectCourse> selectCourses = selectCourseService.findByParentIdsLike(selectCourse);
		
		for(SelectCourse sc:selectCourses) {
			CourseScheduleExt courseScheduleExt = new CourseScheduleExt();
			courseScheduleExt.setCourseId(sc.getCourse().getId());
			List<CourseScheduleExt> lcs = courseScheduleService.getCourseScheduleExt(courseScheduleExt);
			if(!CollectionUtils.isEmpty(lcs)) {
				courseSchedules.addAll(lcs);
			}
		}
		return courseSchedules;
	}
	
	//学生课程等级	10	/student/student/Student_Course_Grade
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Course_Grade")
	public String Student_Course_Grade(Student student,String termYear, HttpServletRequest request, HttpServletResponse response, Model model) {
		SysConfig sysConfig = null;
		if(!org.springframework.util.StringUtils.isEmpty(termYear)) {
			sysConfig = new SysConfig();
			sysConfig.setTermYear(termYear);
			
		}
		if(org.springframework.util.StringUtils.isEmpty(sysConfig)) {
			sysConfig = sysConfigService.getModule(Global.SYSCONFIG_RESULT);
		}
		String studentNumber = student.getStudentNumber();
		if(org.springframework.util.StringUtils.isEmpty(studentNumber)) {
			User user = UserUtils.getUser();
			studentNumber = user.getNo();
		}
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudentNumber(studentNumber);
		//zhaojunfei
		//studentCourse.setTermYear(sysConfig.getTermYear());
		model.addAttribute("config", sysConfig);
		model.addAttribute("studentCourses", studentCourseService.findByParentIdsLike(studentCourse));
		return "modules/student/studentcourse/StudentCourseGrade";
	}
	//获奖记录	30	/student/student/Student_Award
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award")
	public String Student_Award(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		StudentItem studentItem = new StudentItem();
		studentItem.setStudent(user);
		List<StudentItem> studentItems = studentItemService.findByParentIdsLike(studentItem);
		model.addAttribute("studentItems", studentItems);
		return "modules/student/StudentAward";
	}
	
	//获奖记录	30	/student/student/Student_Award
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Info")
	public String Student_Award_Info(@RequestParam(value="itemId",required=false) String itemId,HttpServletRequest request, HttpServletResponse response,
			Model model) {
		User user = UserUtils.getUser();
		StudentItem studentItem = new StudentItem();
		studentItem.setStudent(user);
		StudentItem entity = studentItemService.get(itemId);
		model.addAttribute("studentItem", entity);
		return "modules/student/StudentAwardInfo";
	}
	
	
	//获奖记录	30	/student/student/Student_Award
		@RequiresPermissions("student:student:edit")
		@RequestMapping("Student_Portfolio_Activity_deleteItem")
		public String Student_Portfolio_Activity_deleteItem(@RequestParam(value="itemId",required=false) String itemId,HttpServletRequest request, HttpServletResponse response,
				RedirectAttributes redirectAttributes,Model model) {
			StudentItem studentItem = new StudentItem();
			studentItem.setId(itemId);
			studentItemService.delete(studentItem);
			addMessage(redirectAttributes, "操作成功");
			return "redirect:"+Global.getAdminPath()+"/student/student/Student_Award?repage";
		}

	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Add")
	public String Student_Award_Add(StudentItem studentItem, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		return "modules/student/StudentAwardAdd";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Add_Save")
	public String Student_Award_Add_Save(StudentItem studentItem, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,Model model) {
		User user = UserUtils.getUser();
		studentItem.setStudent(user);
		studentItemService.save(studentItem);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/Student_Award?repage";
	}
	
	

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Student> page = studentService.findPage(new Page<Student>(request, response), student);
        model.addAttribute("page", page);
		return "modules/student/studentList";
	}

	

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "form")
	public String form(Student student, Model model) {
		model.addAttribute("student", student);
		return "modules/student/studentForm";
	}

	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Student> list = ei.getDataList(Student.class);
			for (Student student : list){
				try{
					Student entity = studentService.getStudentByStudentNumber(student.getStudentNumber());
					if (org.springframework.util.StringUtils.isEmpty(entity)){
						User user = new User();
						user.setPassword(SystemService.entryptPassword("123456"));
						BeanValidators.validateWithException(validator, user);
						studentService.saveUser(student);
						successNum++;
					}else{
						failureMsg.append("<br/>姓名 "+student.getName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名 "+student.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>姓名 "+student.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条学生"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入学生失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/list?repage";
	}
	
	@RequiresPermissions("student:student:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学籍信息导入模板.xlsx";
    		List<Student> list = Lists.newArrayList(); list.add(new Student());
    		new ExportExcel("学籍信息", Student.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/list?repage";
    }
	@RequiresPermissions("student:student:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Student stduent, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学籍信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Student> page = studentService.findPage(new Page<Student>(request, response, -1), stduent);
    		new ExportExcel("学籍信息", User.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学籍信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/list?repage";
    }
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "save")
	public String save(Student student, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, student)){
			return form(student, model);
		}
		studentService.save(student);
		addMessage(redirectAttributes, "保存学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/?repage";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "delete")
	public String delete(Student student, RedirectAttributes redirectAttributes) {
		studentService.delete(student);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/?repage";
	}

}