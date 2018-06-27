/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.aliyuncs.http.HttpRequest;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.CourseUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.RegexUtils;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;
import com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseYearTermService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;
import com.thinkgem.jeesite.modules.teacher.service.TeacherClassService;
import com.thinkgem.jeesite.modules.teacher.service.TeacherService;

/**
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/paike")
public class PaikeCourseController extends BaseController {
	@Autowired
	private CourseCalendarService courseCalendarService;
	@Autowired
	private CourseYearTermService courseYearTermService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TeacherClassService teacherClassService;
	@Autowired
	private DictService dictService;

	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "lock")
	public String lock(CourseCalendar courseCalendar, Model model) {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm="20181";
		if(!org.springframework.util.StringUtils.isEmpty(courseYearTerm)) {
			yearTerm = courseYearTerm.getYearTerm();
		}
		
		model.addAttribute("yearTerm",yearTerm);
		model.addAttribute("courseCalendar", courseCalendarService.systemConfig());
		return "modules/paike/LockCourse";
	}
	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxAddLock")
	@ResponseBody
	public String addlock(String time_add,String tips,@RequestParam(value="w",required=false,defaultValue="0")int w, Model model) {
		
		int s = Integer.valueOf( CourseUtil.GetTimeCol(time_add).get("week"));
		if(StringUtils.isEmpty(w)||w==0) {
			w = s;
		}
		boolean flag = false;
		for(;s<=w;s++) {
			String week="";
			if(s<=9) {
				week = "0".concat(String.valueOf(s));
			}else {
				week =  String.valueOf(s);
			}
			String zhou = time_add.substring(12);
			
			time_add = time_add.substring(0,10).concat(week).concat(zhou);
			CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
			if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
				courseSchedule.setScLock("0");
				courseSchedule.setTips(tips);
				courseScheduleService.save(courseSchedule);
				flag = true;
			}
		}
		
		if(flag) {
			return "1";
		}
		return "0";
	}
	
	/**
	 * 新增排课页面
	 * @param courseCalendar
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addCourse")
	public String addCourse(CourseCalendar courseCalendar, Model model) {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm="20181";
		if(!org.springframework.util.StringUtils.isEmpty(courseYearTerm)) {
			yearTerm = courseYearTerm.getYearTerm();
		}
		
		model.addAttribute("yearTerm",yearTerm);
		model.addAttribute("courseCalendar", courseCalendarService.systemConfig());
		return "modules/paike/AddCourse";
	}
	/**
	 * 添加排课信息
	 * @param time_add
	 * @param tips
	 * @param model
	 * @return
	 */
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxAddCourse")
	@ResponseBody
	public String ajaxAddCourse(String time_add,String student_id,String course_id,String tips,@RequestParam(value="w",required=false,defaultValue="0")int w, Model model) {
		
		int s = Integer.valueOf( CourseUtil.GetTimeCol(time_add).get("week"));
		if(StringUtils.isEmpty(w)||w==0) {
			w = s;
		}
		boolean flag = false;
		for(;s<=w;s++) {
			String week="";
			if(s<=9) {
				week = "0".concat(String.valueOf(s));
			}else {
				week =  String.valueOf(s);
			}
			String zhou = time_add.substring(12);
			
			time_add = time_add.substring(0,10).concat(week).concat(zhou);
			CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
			if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
				courseSchedule.setScLock("2");
				courseSchedule.setCourseClass(student_id);
				courseSchedule.setCourseId(course_id);
				courseSchedule.setTips(tips);
				courseScheduleService.save(courseSchedule);
				flag = true;
			}
		}
		
		if(flag) {
			return "1";
		}
		
		return "0";
	}
	
	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxDeleteCourse")
	@ResponseBody
	public String ajaxDeleteCourse(String time_add,String week, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("0")) {
			courseSchedule.setScLock("1");
			courseSchedule.setTips("");
			courseScheduleService.save(courseSchedule);
			return "1";
		}
		return "0";
	}
	
	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxDeleteLock")
	@ResponseBody
	public String dellock(String time_add, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("0")) {
			courseSchedule.setScLock("1");
			courseSchedule.setTips("");
			courseScheduleService.save(courseSchedule);
			return "1";
		}
		return "0";
	}
	
	
	@RequestMapping(value = "ajaxChangeTable")
	public void ajaxChangeTable(String time_add,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter ps = response.getWriter();
		List<CourseSchedule> courseSchedules = courseScheduleService.findListByTimeAdd(time_add);
		for(CourseSchedule courseSchedule:courseSchedules) {
			ps.write(courseSchedule.getScLock());
			if(courseSchedule.getScLock().equals("1")) {
				ps.write("<div class=\"course_text\">教师:</div>");
				ps.write("<div class=\"course_text\">课程:</div>");
				ps.write("<div class=\"course_text\"></div>");
				ps.write("<div class=\"course_text\">备注:</div>");
				
			}else {
				//科目号,理论不应该出现异常现象,不应该出现空指针现象
				
				String courseClass = courseSchedule.getCourseClass();
				
				if(courseSchedule.getScLock().equals("0")||courseClass.length()<7) {
					ps.write("<div class=\"course_text\"></div>");
					ps.write("<div class=\"course_text\">"+courseClass+"</div>");
				}else {
					Course course = courseService.get(courseSchedule.getCourseId());
					ps.write("<div class=\"course_text\">课程:"+course.getCursName()+"</div>");
					Teacher teacher = teacherService.getTeacherByTeacherNumber(course.getTeacher().getNo());
					ps.write("<div class=\"course_text\">教师:"+teacher.getTchrName()+"</div>");
		
					String[] courseArray = courseClass.split(",");
					StringBuilder sb = new StringBuilder();
					for(int i=0;i<courseArray.length;i++) {
						
						String str = courseArray[i];
						Office clazz = officeService.get(str);
						String officeId = str.substring(4,6);
						Office office = officeService.get(officeId);
						String company = "";
						String major = "";
						if(!StringUtils.isEmpty(office)) {
							major = office.getName();
							company = office.getParent().getName();
						}
						sb.append("<a title=\""+company + "," + major + "," + clazz.getName() + "\">"+clazz.getName()+"</a>");
						if(i%2==1) {
							sb.append("<br>");
						}else {
							sb.append(",");
						}
						
					}
					sb.deleteCharAt(sb.length() - 1);
					ps.write("<div class=\"course_text\">"+ sb.toString() +"</div>");
				}
				
				
				ps.write("<div class=\"course_text\">备注:"+courseSchedule.getTips()+"</div>");
				
			}
			ps.write("@");
		}
		ps.flush();
	}
	
	
	@RequestMapping(value = "ajaxStudentCourse")
	public void ajaxStudentCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter ps = response.getWriter();
		
		User student = UserUtils.getUser();
		
		Office clazz = student.getClazz();
		if(!StringUtils.isEmpty(clazz)) {
			List<CourseScheduleExt> courseSchedules = courseScheduleService.getCourseScheduleExt(null,Integer.valueOf(student.getClazz().getId()),null);
			for(CourseScheduleExt courseSchedule:courseSchedules) {
				ps.write(courseSchedule.getScLock());
				if(courseSchedule.getScLock().equals("1")) {
					ps.write("<div class=\"course_text\">教师:</div>");
					ps.write("<div class=\"course_text\">课程:</div>");
					ps.write("<div class=\"course_text\"></div>");
					ps.write("<div class=\"course_text\">备注:</div>");
					
				}else {
					//科目号,理论不应该出现异常现象,不应该出现空指针现象
					String courseId = courseSchedule.getCourseId();
					String courseClass = courseSchedule.getCourseClass();
					Course course = courseService.getCourseByCourseId(courseId);
					
					ps.write("<div class=\"course_text\">课程:"+course.getCursName()+"</div>");
					
					if(courseSchedule.getScLock().equals("0")||courseClass.length()<7) {
						ps.write("<div class=\"course_text\"></div>");
						ps.write("<div class=\"course_text\">"+courseClass+"</div>");
					}else {
						
						Teacher teacher = teacherService.getTeacherByTeacherNumber(course.getTeacher().getNo());
						ps.write("<div class=\"course_text\">教师:"+teacher.getTchrName()+"</div>");
						String grade = courseClass.substring(0,4);
						String school = courseClass.substring(4,5);
						String major = courseClass.substring(5,7);
						String c = courseClass.substring(7);
						Office company = officeService.get(school);
						Office office = officeService.get(major);
						ps.write("<div class=\"course_text\">"+company.getName()+ " " + office.getName() + " "+c +"</div>");
					}
					
					ps.write("<div class=\"course_text\">备注:"+courseSchedule.getTips()+"</div>");
					
				}
				ps.write("@");
			}
		}
		ps.flush();
		ps.close();
	}
	
	@ResponseBody
	@RequestMapping(value = "ajaxAllClassByCourseId")
	public List<TeacherClass> ajaxAllClassByCourseId( HttpServletRequest request, HttpServletResponse response) {
		String courseId = request.getParameter("courseId");
		Course course = courseService.get(courseId);
		List<TeacherClass> tcs = new ArrayList<TeacherClass>();
		if(!StringUtils.isEmpty(course)) {
			User teacher = course.getTeacher();
			TeacherClass teacherClass = new TeacherClass();
			teacherClass.setTeacherNumber(teacher.getNo());
			tcs = teacherClassService.findList(teacherClass);
		}
		return tcs;
	}
	
	@ResponseBody
	@RequestMapping(value = "ajaxAllCourse")
	public List<TreeLink> ajaxAllCourse( HttpServletRequest request, HttpServletResponse response) {
		
		List<Course> list1 = courseService.findCoursesByPaike(new Course());
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(Course course:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(course.getId());
			String teacherNumber = course.getTeacher().getNo();
			Teacher teacher = teacherService.getTeacherByTeacherNumber(teacherNumber);
			if(!StringUtils.isEmpty(teacher)) {
				treeLink.setName(course.getCursName().concat("("+course.getCursClassHour()+")").concat("|").concat(teacher.getTchrName()));
			}
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}
	
	@RequestMapping(value = "view")
	public String view() {
		return "modules/paike/ImportView";
	}
	
	@RequestMapping(value = "import")
	public String importFile(MultipartFile file, HttpRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes) {
		Dict dict = new Dict();
		dict.setType("course_curs_type");
		List<Dict> courseCurrsTypes = dictService.findList(dict);
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			
			ImportExcel importExcel = new ImportExcel(file,3);
			String[][] curriculumPlans = importExcel.importFile();
			for(String[] curriculumPlan:curriculumPlans) {
				try {
					String id = curriculumPlan[0];
					String office_name = curriculumPlan[1];
					String major_name = curriculumPlan[2];
					String curs_num = curriculumPlan[3];
					String curs_name = curriculumPlan[4];
					String curs_type = curriculumPlan[5];// 课程类型
					String assessment_type = curriculumPlan[6];// 考核类型
					String clazz = RegexUtils.getUnChinese(curriculumPlan[7]);
					String count = curriculumPlan[8];
					String tchr_name = curriculumPlan[9];
					String tchr_title = curriculumPlan[10];
					String user_type = curriculumPlan[11];
					String week_count = curriculumPlan[12];
					String week = curriculumPlan[13];
					String curs_class_hour = curriculumPlan[14];
					String remark = curriculumPlan[15];

					List<String> cls = new ArrayList<String>();

					if (clazz.indexOf("、") > -1 && clazz.indexOf("-") > -1) {
						String[] sss = clazz.split("、");
						for (String s : sss) {
							if (s.indexOf("-") > -1) {
								String[] ss = s.split("-");
								for (int k = Integer.valueOf(ss[0]); k <= Integer.valueOf(ss[1]); k++) {
									cls.add(String.valueOf(k));
								}
							} else {
								cls.add(s);
							}
						}
					} else if (clazz.indexOf("、") > -1) {
						String[] ss = clazz.split("、");
						for (String s : ss) {
							cls.add(s);
						}
					} else if (clazz.indexOf("-") > -1) {
						String[] ss = clazz.split("-");
						for (int k = Integer.valueOf(ss[0]); k <= Integer.valueOf(ss[1]); k++) {
							cls.add(String.valueOf(k));
						}
					} else {
						cls.add(clazz);
					}
					Office major = officeService.getOfficeByName(major_name);
					// 判断任课教师是否存在
					User user = systemService.getUserSingleByName(tchr_name);
					
					if (StringUtils.isEmpty(user)) {
						Office office = officeService.getOfficeByName(office_name);
						if (StringUtils.isEmpty(office)) {
							office = new Office();
							office.setId("1");
						}
						
						String serialNo = systemService.getSequence("serialNo3");
						user = new User();
						String no = DateUtils.getYear().concat(serialNo.substring(serialNo.length() - 4));// 自增剩下教师号
						user.setNo(no);
						user.setLoginName(no);
						user.setName(tchr_name);
						user.setCompany(office);// 学院
						user.setOffice(major);// 专业
						String password = "888888";
						user.setPassword(SystemService.entryptPassword(password));
						Role role = new Role("");
						List<Role> rs = new ArrayList<Role>();
						rs.add(role);
						user.setRole(role);
						user.setRoleList(rs);
						user.setLoginFlag("1");
						user.setDelFlag("0");
						user.setRemarks("执行计划导入教师信息");
						if (Global.getConfig("virtualAccount").equals("true")) {
							// 开通虚拟账户系统
							String accountNo = "1";
							user.setAccountNo(accountNo);
						}
						systemService.saveUser(user);
						

					}
					
					
					Teacher teacher = teacherService.getTeacherByTeacherNumber(user.getNo());
					if(StringUtils.isEmpty(teacher)) {
						teacher = new Teacher();
						teacher.setTchrName(tchr_name);
						teacher.setTeacher(user);
						teacher.setDelFlag("0");
						teacher.setTchrTitle(tchr_title);
						teacherService.save(teacher);
					}
					
					for (String cs : cls) {
						String year = "20" + cs.substring(0, 2);
						String classNo = cs.substring(2);
						Office cl = StudentUtil.createClasses(year, major.getId(), classNo);
						TeacherClass teacherClass = new TeacherClass();
						teacherClass.setTeacherNumber(user.getNo());
						teacherClass.setClazz(cl);
						TeacherClass entityteacherClass = teacherClassService.get(teacherClass);
						if (StringUtils.isEmpty(entityteacherClass)) {
							teacherClassService.save(teacherClass);
						}
					}
					
					Course course = new Course();
					course.setTeacher(user);
					course.setCursNum(curs_num);
					course.setCursName(curs_name);
					Course entity = courseService.getCourse(course);
					if (StringUtils.isEmpty(entity)) {
						// 新增课程相关信息
						entity = new Course();
						entity.setId(systemService.getSequence("serialNo10"));
						entity.setIsNewRecord(true);
						entity.setCursMajor(major_name);
						entity.setTeacher(user);
						entity.setCursNum(curs_num);
						entity.setCursName(curs_name);
						curs_type = curs_type.substring(0, 2); 
						String cursValue = "other";
						for(Dict d:courseCurrsTypes) {
							if(d.getLabel().indexOf(curs_type)>-1) {
								cursValue = d.getValue();
								break;
							}
						}
						
						entity.setCursType(cursValue);
			
						String cursClassHour = curs_class_hour.split(".")[0];
						entity.setCursClassHour(cursClassHour);
						entity.setRemarks(remark);
						courseService.save(entity);
					}
					successNum++;
				} catch (Exception e) {
					failureMsg.append("<br/>课程信息异常: "+curriculumPlan+" ; "+e.getMessage());
					failureNum++;
					
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(model, "已成功导入 "+successNum+" 条课程信息");
		}catch (Exception ex) {
			ex.printStackTrace();
			addMessage(model, "导入失败！失败信息："+ex.getMessage());
		}
		return "modules/paike/ImportView";
	}
		
}