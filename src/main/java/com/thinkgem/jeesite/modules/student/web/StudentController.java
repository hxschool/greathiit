/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.DocWriter;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
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
import com.thinkgem.jeesite.modules.file.FileResponse;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentActivity;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.entity.StudentItem;
import com.thinkgem.jeesite.modules.student.entity.StudentStatusLog;
import com.thinkgem.jeesite.modules.student.service.StudentActivityService;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.student.service.StudentItemService;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.student.service.StudentStatusLogService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;

/**
 * 学生信息Controller
 * 
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
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private StudentStatusLogService studentStatusLogService;

	@ModelAttribute
	public Student get(@RequestParam(required = false) String id, Model model) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = studentService.get(id);
		}
		if (entity == null) {
			entity = new Student();
		}
		model.addAttribute("config", sysConfigService.getModule(Global.SYSCONFIG_RESULT));
		return entity;
	}

	// 个人资料 30 /student/student/Student_Information_Detail
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Information_Detail")
	public String Student_Information_Detail(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException {
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		// 初始化学生信息
		if (org.springframework.util.StringUtils.isEmpty(student)) {
			String idCard = user.getLoginName();
			student = new Student();
			student.setName(user.getName());
			student.setStudentNumber(user.getNo());
			student.setPhone(user.getPhone());
			student.setMail(user.getEmail());
			if (IdcardUtils.validateIdCard18(idCard)) {
				student.setIdCard(idCard);
				student.setGender(IdcardUtils.getGenderByIdCard(idCard));
				String birthday = IdcardUtils.getBirthByIdCard(idCard);
				if (!org.springframework.util.StringUtils.isEmpty(birthday)) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
					formatter.setLenient(false);
					Date newDate = formatter.parse(birthday);
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					student.setBirthday(DateUtils.parseDate(formatter.format(newDate)));
				}
			}
			studentService.save(student);
		}
		model.addAttribute("student", student);
		return "modules/student/StudentInformationDetail";
	}

	// 编辑个人资料 30 /student/student/Student_Information_Modify
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Information_Modify")
	public String Student_Information_Modify(HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		model.addAttribute("student", student);

		return "modules/student/StudentInformationModify";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Information_Modify_Save")
	public String Student_Information_Modify_Save(Student student, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {

		User user = UserUtils.getUser();
		Student entity = studentService.getStudentByStudentNumber(user.getNo());
		BeanUtils.copyProperties(student, entity);
		studentService.save(student);
		model.addAttribute("student", student);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/Student_Information_Modify?repage";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_addActivity")
	public String Student_Portfolio_Activity_addActivity(StudentActivity studentActivity, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {
		User student = UserUtils.getUser();
		studentActivity.setStudent(student);
		studentActivityService.save(studentActivity);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/Student_Portfolio_Activity?repage";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_deleteActivity")
	public String Student_Portfolio_Activity_deleteActivity(@RequestParam("actId") String actId,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			Model model) {
		User user = UserUtils.getUser();
		StudentActivity studentActivity = new StudentActivity();
		studentActivity.setId(actId);
		studentActivity.setStudent(user);
		studentActivityService.delete(studentActivity);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/Student_Portfolio_Activity?repage";
	}

	// 实践活动 20 /student/student/Student_Portfolio_Activity
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Portfolio_Activity")
	public String Student_Portfolio_Activity(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		List<StudentActivity> orgActivities = studentActivityService.getOrgActivities(user.getNo());
		List<StudentActivity> attendActivities = studentActivityService.getAttendActivities(user.getNo());
		List<StudentActivity> socialActivities = studentActivityService.getSocialActivities(user.getNo());
		// 组织活动
		model.addAttribute("orgActivities", orgActivities);
		// 参与活动
		model.addAttribute("attendActivities", attendActivities);
		// 社会实践
		model.addAttribute("socialActivities", socialActivities);
		return "modules/student/StudentPortfolioActivity";
	}

	// 规划目标 10 /student/student/Student_Portfolio_Goal
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Portfolio_Goal")
	public String Student_Portfolio_Goal(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		model.addAttribute("student", student);
		return "modules/student/StudentPortfolioGoal";
	}

	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Portfolio_Goal_Save")
	public String Student_Portfolio_Goal_Save(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
		String shortGoal = request.getParameter("shortGoal");
		if(!org.springframework.util.StringUtils.isEmpty(shortGoal)) {
			student.setShortGoal(shortGoal);
		}
		String midGoal = request.getParameter("midGoal");
		if(!org.springframework.util.StringUtils.isEmpty(midGoal)) {
			student.setMidGoal(midGoal);
		}
		String longGoal = request.getParameter("longGoal");
		if(!org.springframework.util.StringUtils.isEmpty(longGoal)) {
			student.setLongGoal(longGoal);
		}
		studentService.save(student);
		model.addAttribute("student", student);
		return "redirect:" + Global.getAdminPath() + "/student/student/Student_Portfolio_Goal?repage";
	}

	// 课程成绩 40 /student/student/Student_Performance
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Performance")
	public String Student_Performance(StudentCourse studentCourse, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		studentCourse.setStudentNumber(user.getNo());
		List<StudentCourse> studentCourses = studentCourseService.findByParentIdsLike(studentCourse);
		Set<Course> courses = new HashSet();
		for (StudentCourse sc : studentCourses) {
			courses.add(sc.getCourse());
		}
		model.addAttribute("studentCourses", studentCourses);
		model.addAttribute("courses", courses);
		return "modules/student/StudentPerformance";
	}

	// 课程表
	@RequestMapping("Student_Class_Schedule_Card")
	public String Student_Class_Schedule_Card(Student student, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		User user = UserUtils.getUser();
		if (org.springframework.util.StringUtils.isEmpty(user.getClazz())) {
			throw new RuntimeException("当前学生未初始化班级信息,请设置班级信息");
		}
		String week = DateUtils.getWeek();
		int weekOfDate = DateUtils.getWeekOfDate();
		model.addAttribute("xqs", Global.xqs);
		model.addAttribute("weekOfDate", weekOfDate);
		model.addAttribute("week", week);
		model.addAttribute("weeks", Global.weeks);
		return "modules/student/course/StudentClassScheduleCard";
	}

	@RequestMapping("Ajax_Student_Class_Schedule_Card")
	@ResponseBody
	public List<CourseScheduleExt> getCourseScheduleExts(String weekNumber) {
		if (org.springframework.util.StringUtils.isEmpty(weekNumber)) {
			CourseCalendar courseCalendar = courseCalendarService.get("1");
			String startDate = courseCalendar.getCalendarYear() + "-" + courseCalendar.getCalendarMonth() + "-"
					+ courseCalendar.getCalendarDay();
			String endDate = DateUtils.formatDate(new Date());
			weekNumber = String.valueOf(DateUtils.getWeek(startDate, endDate));
		}
		if (weekNumber.length() == 1) {
			weekNumber = "0" + weekNumber;
		}
		Student student = UserUtils.getStudent();
		CourseScheduleExt cse = new CourseScheduleExt();
		if (!org.springframework.util.StringUtils.isEmpty(student.getClazz())) {
			cse.setCourseClass(student.getClazz().getId());
		}
		cse.setTimeAdd("%" + weekNumber + "__");
		List<CourseScheduleExt> courseSchedules = courseScheduleService.getCourseScheduleExt(cse);
		SelectCourse selectCourse = new SelectCourse();
		selectCourse.setStudent(student);
		List<SelectCourse> selectCourses = selectCourseService.findByParentIdsLike(selectCourse);

		for (SelectCourse sc : selectCourses) {
			CourseScheduleExt courseScheduleExt = new CourseScheduleExt();
			courseScheduleExt.setCourseId(sc.getCourse().getId());
			List<CourseScheduleExt> lcs = courseScheduleService.getCourseScheduleExt(courseScheduleExt);
			if (!CollectionUtils.isEmpty(lcs)) {
				courseSchedules.addAll(lcs);
			}
		}
		return courseSchedules;
	}

	// 学生课程等级 10 /student/student/Student_Course_Grade
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Course_Grade")
	public String Student_Course_Grade(Student student, String termYear, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		SysConfig sysConfig = null;
		if (!org.springframework.util.StringUtils.isEmpty(termYear)) {
			sysConfig = new SysConfig();
			sysConfig.setTermYear(termYear);

		}
		String studentNumber = student.getStudentNumber();
		if (org.springframework.util.StringUtils.isEmpty(studentNumber)) {
			User user = UserUtils.getUser();
			studentNumber = user.getNo();
		}
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudentNumber(studentNumber);
		// zhaojunfei
		if(!org.springframework.util.StringUtils.isEmpty(sysConfig)&&!org.springframework.util.StringUtils.isEmpty(sysConfig.getTermYear())) {
			studentCourse.setTermYear(sysConfig.getTermYear());
		}
		
		model.addAttribute("config", sysConfig);
		model.addAttribute("studentCourses", studentCourseService.findByParentIdsLike(studentCourse));
		return "modules/student/studentcourse/StudentCourseGrade";
	}
	
    @RequestMapping(value = "Student_Course_Grade_Export")
    public String Student_Course_Grade_Export(Student student, String termYear ,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			SysConfig sysConfig = null;
			if (!org.springframework.util.StringUtils.isEmpty(termYear)) {
				sysConfig = new SysConfig();
				sysConfig.setTermYear(termYear);

			}
			String studentNumber = student.getStudentNumber();
			if (org.springframework.util.StringUtils.isEmpty(studentNumber)) {
				User user = UserUtils.getUser();
				studentNumber = user.getNo();
			}
			StudentCourse studentCourse = new StudentCourse();
			studentCourse.setStudentNumber(studentNumber);
			if(!org.springframework.util.StringUtils.isEmpty(sysConfig)&&!org.springframework.util.StringUtils.isEmpty(sysConfig.getTermYear())) {
				studentCourse.setTermYear(sysConfig.getTermYear());
			}
			List<StudentCourse> lists = studentCourseService.findByParentIdsLike(studentCourse);
            String fileName = "学生数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		ExportExcel exportExcel = new ExportExcel();
    		
    		String[] ss = {"课程编号","学号","姓名","课程名称","学分","绩点","成绩","授课教师"};
    		
    		List<String> headerList = Arrays.asList(ss);
    		exportExcel.init("学生成绩", headerList);
    		for(StudentCourse sc:lists) {
    			Course c = sc.getCourse();
    			Student st = sc.getStudent();
    			Teacher ct = c.getTeacher();
    			Row row = exportExcel.addRow();
    			Cell courseNumberCell = row.createCell(0);
    			courseNumberCell.setCellValue(c.getCursNum());
    			Cell studentNumberCell = row.createCell(1);
    			studentNumberCell.setCellValue(st.getStudentNumber());
    			Cell studentNameCell = row.createCell(2);
    			studentNameCell.setCellValue(st.getName());
    			Cell courseNameCell = row.createCell(3);
    			courseNameCell.setCellValue(c.getCursName());
    			Cell courseCreditCell = row.createCell(4);
    			courseCreditCell.setCellValue(sc.getCredit());
    			Cell coursePointCell = row.createCell(5);
    			coursePointCell.setCellValue(sc.getPoint());
    			Cell courseEvaValueCell = row.createCell(6);
    			courseEvaValueCell.setCellValue(sc.getEvaValue());
    			Cell courseTeacherNameCell = row.createCell(7);
    			courseTeacherNameCell.setCellValue(ct.getTchrName());
    		}
    		exportExcel.write(response, fileName);
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学生成绩失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/Student_Course_Grade?repage";
    }

	// 获奖记录 30 /student/student/Student_Award
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Award")
	public String Student_Award(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		StudentItem studentItem = new StudentItem();
		studentItem.setStudent(user);
		List<StudentItem> studentItems = studentItemService.findByParentIdsLike(studentItem);
		model.addAttribute("studentItems", studentItems);
		return "modules/student/StudentAward";
	}

	// 获奖记录 30 /student/student/Student_Award
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Award_Info")
	public String Student_Award_Info(@RequestParam(value = "itemId", required = false) String itemId,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		StudentItem studentItem = new StudentItem();
		studentItem.setStudent(user);
		StudentItem entity = studentItemService.get(itemId);
		model.addAttribute("studentItem", entity);
		return "modules/student/StudentAwardInfo";
	}

	// 获奖记录 30 /student/student/Student_Award
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_deleteItem")
	public String Student_Portfolio_Activity_deleteItem(@RequestParam(value = "itemId", required = false) String itemId,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			Model model) {
		StudentItem studentItem = new StudentItem();
		studentItem.setId(itemId);
		studentItemService.delete(studentItem);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/Student_Award?repage";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Add")
	public String Student_Award_Add(StudentItem studentItem, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		return "modules/student/StudentAwardAdd";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Add_Save")
	public String Student_Award_Add_Save(StudentItem studentItem, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes, Model model) {
		User user = UserUtils.getUser();
		studentItem.setStudent(user);
		studentItemService.save(studentItem);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/Student_Award?repage";
	}

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = { "list", "" })
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if (!org.springframework.util.StringUtils.isEmpty(request.getParameter("search"))) {
			if (org.springframework.util.StringUtils.isEmpty(request.getParameter("clazz"))) {
				String specialty = request.getParameter("specialty");
				List<String> clazzNumbers = new ArrayList<String>();
				List<Office> clsList = null;
				if (!org.springframework.util.StringUtils.isEmpty(specialty)) {
					clsList = officeService.findByParentId(specialty);
				}
				if (org.springframework.util.StringUtils.isEmpty(specialty)) {
					String department = request.getParameter("department");
					if (!org.springframework.util.StringUtils.isEmpty(department)) {
						List<Office> offices = officeService.findByParentId(department);
						clsList = new ArrayList();
						for (Office office : offices) {
							if (office.getGrade().equals("3")) {
								List<Office> ofs = officeService.findByParentId(office.getId());
								clsList.addAll(ofs);
							}
						}
					}
				}
				if (!org.apache.commons.collections.CollectionUtils.isEmpty(clsList)) {
					for (Office cls : clsList) {
						clazzNumbers.add(cls.getId());
					}
				}
				
				if (!org.apache.commons.collections.CollectionUtils.isEmpty(clazzNumbers)) {
					student.setClazzNumbers(clazzNumbers);
				}
			}
			List<Office> ofs = null;
			String primaryPersonId = request.getParameter("primaryPersonId");
			if(!org.springframework.util.StringUtils.isEmpty(primaryPersonId)) {
				Office op = new Office();
				op.setDeputyPerson(systemService.getCasByLoginName(primaryPersonId));
				ofs = officeService.findList(op);
				
			}
			String deputyPersonId = request.getParameter("deputyPersonId");
			if(!org.springframework.util.StringUtils.isEmpty(deputyPersonId)) {
				Office op = new Office();
				User u = new User();
				u.setNo(deputyPersonId);
				
				op.setDeputyPerson(systemService.getCasByLoginName(deputyPersonId));
				ofs = officeService.findList(op);	
			}
			
			if(!org.springframework.util.StringUtils.isEmpty(ofs)) {
				List<String> clazzNumbers = new ArrayList<String>();
				if(ofs.size()==0) {
					clazzNumbers.add("99999999");
					student.setClazzNumbers(clazzNumbers);
				}else {
					
					for (Office cls : ofs) {
						clazzNumbers.add(cls.getId());
					}
					student.setClazzNumbers(clazzNumbers);
				}
			}
			
			Page<Student> page = studentService.findPage(new Page<Student>(request, response), student);
			model.addAttribute("page", page);
		}
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
			for (Student student : list) {
				try {
					Student entity = studentService.getStudentByStudentNumber(student.getStudentNumber());
					if (org.springframework.util.StringUtils.isEmpty(entity)) {
						
						studentService.saveUser(student);
						successNum++;
					} else {
						failureMsg.append("<br/>姓名 " + student.getName() + " 已存在; ");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>姓名 " + student.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>姓名 " + student.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条学生" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入学生失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/list?repage";
	}

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "学籍信息导入模板.xlsx";
			List<Student> list = Lists.newArrayList();
			list.add(new Student());
			new ExportExcel("学籍信息", Student.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/list?repage";
	}

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Student stduent, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "学籍信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<Student> page = studentService.findPage(new Page<Student>(request, response, -1), stduent);
			new ExportExcel("学籍信息", Student.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学籍信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/student/list?repage";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "save")
	public String save(Student student, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, student)) {
			return form(student, model);
		}
		studentService.save(student);
		addMessage(redirectAttributes, "保存学生信息成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/?repage";
	}

	@RequiresPermissions("student:student:edit")
	@RequestMapping(value = "delete")
	public String delete(Student student, RedirectAttributes redirectAttributes) {
		studentService.delete(student);
		addMessage(redirectAttributes, "删除学生信息成功");
		return "redirect:" + Global.getAdminPath() + "/student/student/?repage";
	}

	@RequestMapping(value = "face")
	public String face() {
		return "modules/student/studentFace";
	}

	@RequestMapping(value = "upload")
	public String upload(MultipartFile file, RedirectAttributes redirectAttributes) {

		int successNum = 0;
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		try {

			String name = file.getOriginalFilename();
			String fix = name.substring(name.lastIndexOf("."), name.length());
			File oldFile = File.createTempFile(name.substring(0, name.lastIndexOf(".")), fix);
			FileUtils.copyInputStreamToFile(file.getInputStream(), oldFile);
			ZipEntry zipEntry = null;
			ZipFile zipFile = new ZipFile(oldFile);
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(oldFile));
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				if (!zipEntry.isDirectory()) {
					String filename = zipEntry.getName();
					String prefix = filename.substring(0, filename.lastIndexOf("."));
					String idCard = prefix;
					Student student = studentService.getStudentByIdCard(idCard);
					if (org.springframework.util.StringUtils.isEmpty(student)) {
						failureMsg.append("身份信息异常,身份证号不正确" + idCard);
						failureNum++;
					} else {
						String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
						File tempFile = File.createTempFile(student.getStudentNumber(), suffix);
						FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipEntry), tempFile);
						String str = HttpClientUtil.upload(Global.FILE_SERVER_UPLOAD_URL, student.getStudentNumber(), tempFile);
						if (!org.springframework.util.StringUtils.isEmpty(str)) {
							Gson gson = new Gson();
							FileResponse fileResponse = gson.fromJson(str, FileResponse.class);
							if (fileResponse.getStatus().equals("00000000")) {
								User user = systemService.getUserByLoginName(idCard);
								user.setPhone(fileResponse.getUrl());
								systemService.saveUser(user);
								successNum++;
							} else {
								failureMsg.append("连接文件服务器异常,请联系管理员");
								failureNum++;
							}
						} else {
							failureMsg.append("连接文件服务器异常,请联系管理员");
							failureNum++;
						}

					}
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条头像信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条信息" + failureMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:" + Global.getAdminPath() + "/student/student/face?repage";
	}
	//证明
	@RequiresPermissions("student:student:prove")
	@RequestMapping(value = "prove")
	public String prove(Student student, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {

		Student entity = studentService.get(student);
		if (!org.springframework.util.StringUtils.isEmpty(entity)) {
			Office cls = entity.getClazz();
			if (!org.springframework.util.StringUtils.isEmpty(cls)) {
				cls = officeService.get(cls);
				Office major = officeService.get(cls.getParentId());
				Office department = officeService.get(major.getParentId());
				String filename = null;
				if (!org.springframework.util.StringUtils.isEmpty(department)) {
					filename = request.getSession().getServletContext()
							.getRealPath("/resources/zhengming/" + department.getId() + ".docx");
				} else {
					String departmentName = department.getName();
					Office office = officeService.getOfficeByName(departmentName);
					if (!org.springframework.util.StringUtils.isEmpty(office)) {
						filename = request.getSession().getServletContext()
								.getRealPath("/resources/zhengming/" + office.getId() + ".docx");
					}
				}
				if (!org.springframework.util.StringUtils.isEmpty(filename)) {
					String startDate = entity.getStartDate();
					String yyyy = StringUtils.left(startDate, 4);
					String mm = StringUtils.substring(startDate, 4, 6);
					response.setContentType("application/msword;charset=utf-8");

					response.setHeader("Content-Disposition", "attachment;filename="
							.concat(new String(entity.getName().getBytes("gbk"), "ISO-8859-1")).concat(".docx"));
					OutputStream os = response.getOutputStream();
					FileInputStream is = new FileInputStream(filename);
					Map<String, String> map = new HashMap<String, String>();
					map.put("${name}", entity.getName());
					map.put("${idcard}", entity.getIdCard());
					map.put("${yyyy}", yyyy);
					map.put("${mm}", mm);
					map.put("${zy}", major.getName());
					map.put("${n}", DictUtils.getDictLabel(entity.getStudentLength(), "student_school_system", ""));
					String edu = DictUtils.getDictLabel(entity.getEdu(), "student_edu", "");
					if (!org.springframework.util.StringUtils.isEmpty(edu)) {
						edu = StringUtils.left(edu, 1);
					}
					map.put("${edu}", edu);
					map.put("${date}", DateUtils.getDate("yyyy年MM月dd日"));
					DocWriter.searchAndReplace(is, os, map);
					return "redirect:" + Global.getAdminPath() + "/student/student/?repage";
				} 
				
			}
			
		}
		addMessage(redirectAttributes, "学生数据异常,获取学院信息失败");
		return "redirect:" + Global.getAdminPath() + "/student/student/?repage";
	}

	
	@RequestMapping(value = "info")
	public String info(Student student, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		student = studentService.get(student);
		Office cls = student.getClazz();
		if(!org.springframework.util.StringUtils.isEmpty(cls)) {
			cls = officeService.get(cls);
			Office office = officeService.get(cls.getParentId());
			cls.setParent(office);
			Office company = officeService.get(office.getParentId());
			cls.getParent().setParent(company);
			student.setClazz(cls);
		}
		StudentCourse studentCourse = new StudentCourse();
		studentCourse.setStudent(student);
		StudentStatusLog studentStatusLog = new StudentStatusLog();
		studentStatusLog.setModuleId(student.getId());
		List<StudentStatusLog> studentStatusLogs = studentStatusLogService.findByParentIdsLike(studentStatusLog);
		for(StudentStatusLog ss:studentStatusLogs) {
			ss.setCreateBy(systemService.getUser(studentStatusLog.getCreateBy()));
		}
		model.addAttribute("studentStatusLogs", studentStatusLogs);
		
		model.addAttribute("student", student);
		return "modules/student/studentInfo";
	} 
	
	// 批量生成证明
	@RequestMapping(value = "batchCompress")
	public String batchCompress(String ids, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String action = request.getParameter("action");
		if (!org.springframework.util.StringUtils.isEmpty(action)) {
			if (!org.springframework.util.StringUtils.isEmpty(ids)) {
				String[] arrayIds = ids.split(",");

			}
			addMessage(redirectAttributes, "批量操作成功");
			return "redirect:" + Global.getAdminPath() + "/uc/student/?action=" + action + "&repage";
		}
		return "redirect:" + Global.getAdminPath() + "/student/student/?repage";
	}

}