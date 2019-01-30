/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.thinkgem.jeesite.common.utils.CourseUtil;
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
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.school.service.SchoolRootService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
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
	@Autowired
	private SchoolRootService schoolRootService;

	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "lock")
	public String lock(CourseCalendar courseCalendar, Model model) {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm="2018-2019-02";
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
		String yearTerm="2018-2019-01";
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
			//设置已排课
			Course course = courseService.get(course_id);
			course.setCursStatus(Course.PAIKE_STATUS_YI_PAIKE);
			courseService.save(course);
			return "1";
		}
		
		return "0";
	}
	
	
	@RequiresPermissions("course:paike:edit")
	@RequestMapping(value = "ajaxDeleteCourse")
	@ResponseBody
	public String ajaxDeleteCourse(String time_add,String week, Model model) {
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)) {
			courseSchedule.setScLock("1");
			courseSchedule.setCourseId("00000000");
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
		if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)) {
			courseSchedule.setScLock("1");
			courseSchedule.setTips("");
			courseSchedule.setCourseClass("");
			courseSchedule.setCourseId("");
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
					if(!StringUtils.isEmpty(course)) {
						ps.write("<div class=\"course_text\">课程:"+course.getCursName()+"</div>");
						Teacher teacher = teacherService.getTeacherByTeacherNumber(course.getTeacher().getTeacherNumber());
						ps.write("<div class=\"course_text\">教师:"+teacher.getTchrName()+"</div>");
					}
					
					if(!StringUtils.isEmpty(courseClass)) {
						if(courseClass.indexOf("00000000")>-1) {
							ps.write("<div class=\"course_text\">公共课</div>");
						}else {
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
						
					}
					
				}
				
				
				ps.write("<div class=\"course_text\">备注:"+courseSchedule.getTips()+"</div>");
				
			}
			ps.write("@");
		}
		ps.flush();
		ps.close();
	}
	
	
	@RequestMapping(value = "ajaxStudentCourse")
	public void ajaxStudentCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter ps = response.getWriter();
		
		User student = UserUtils.getUser();
		
		Office clazz = student.getClazz();
		if(!StringUtils.isEmpty(clazz)) {
			CourseScheduleExt courseScheduleExt = new CourseScheduleExt();
			courseScheduleExt.setCourseClass(student.getClazz().getId());
			List<CourseScheduleExt> courseSchedules = courseScheduleService.getCourseScheduleExt(courseScheduleExt);
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
						
						Teacher teacher = teacherService.getTeacherByTeacherNumber(course.getTeacher().getTeacherNumber());
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
			Teacher teacher = course.getTeacher();
			TeacherClass teacherClass = new TeacherClass();
			teacherClass.setTeacherNumber(teacher.getTeacherNumber());
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
			String teacherNumber = course.getTeacher().getTeacherNumber();
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
	public String importFile(MultipartFile file,String currTerm,HttpServletRequest request, HttpServletResponse response,Model model,RedirectAttributes redirectAttributes) {
		Dict dict = new Dict();
		dict.setType("course_curs_type");
		List<Dict> courseCurrsTypes = dictService.findList(dict);
		dict.setType("course_curs_form");
		List<Dict> courseCurrsForms = dictService.findList(dict);
		
		dict.setType("sys_user_type");
		List<Dict> userTypes = dictService.findList(dict);
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
					String week = curriculumPlan[12];
					String week_count = curriculumPlan[13];
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
					
					if(major_name.indexOf("/")>-1) {
						major_name = major_name.split("/")[0];
					}
					
					Office major = officeService.getOfficeByName(major_name);
					// 判断任课教师是否存在
					User user = systemService.isExisUser(office_name, curs_type, tchr_name, tchr_title, major);
					
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
					Teacher tt = new Teacher();
					tt.setTeacher(user);
					Course course = new Course();
					course.setTeacher(tt);
					course.setCursNum(curs_num);
					course.setCursName(curs_name);
					Course entity = courseService.getCourse(course);
					if (StringUtils.isEmpty(entity)) {
						// 新增课程相关信息
						entity = new Course();
						entity.setId(systemService.getSequence("serialNo10"));
						entity.setIsNewRecord(true);
						entity.setCursMajor(major_name);
						entity.setCursYearTerm(currTerm);
						entity.setCursWeekTotal(new Double(week_count).intValue()+"");
						entity.setCursWeekHour(new Double(week).intValue()+"");
						entity.setTeacher(tt);
						entity.setCursNum(curs_num);
						entity.setCursName(curs_name);
						entity.setCursTotal(new Double(count).intValue()+"");
						entity.setCursStatus(Course.PAIKE_STATUS_WEI_PAIKE);
					
						curs_type = curs_type.substring(0, 2); 
						String cursValue = "other";
						for(Dict d:courseCurrsTypes) {
							if(d.getLabel().indexOf(curs_type)>-1) {
								cursValue = d.getValue();
								break;
							}
						}
						entity.setCursType(cursValue);
						
						String cursForm = "99";
						for(Dict d:courseCurrsForms) {
							if(d.getLabel().indexOf(assessment_type)>-1) {
								cursForm = d.getValue();
								break;
							}
						}
						entity.setCursForm(cursForm);
			
						entity.setCursClassHour(new Double(curs_class_hour).intValue()+"");
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

			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条课程信息"+failureMsg);
		}catch (Exception ex) {
			ex.printStackTrace();
			addMessage(model, "导入失败！失败信息："+ex.getMessage());
		}
		return "modules/paike/ImportView";
	}
	@RequestMapping(value = "viewSelect")
	public String viewSelect() {
		return "modules/paike/ImportSelectView";
	}
	@RequestMapping(value = "import_select")
	public String importSelectFile(MultipartFile file, String currTerm, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		Dict dict = new Dict();
		dict.setType("course_curs_type");
		List<Dict> courseCurrsTypes = dictService.findList(dict);
		dict.setType("course_curs_form");
		List<Dict> courseCurrsForms = dictService.findList(dict);
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel importExcel = new ImportExcel(file, 2);
			String[][] selectCourses = importExcel.importFile();
			for (String[] selectCourse : selectCourses) {
				try {
					String id = selectCourse[0];
					String select_course_type = selectCourse[1];
					String curs_num = selectCourse[2];// 课程编码
					String curs_edu_num = selectCourse[3];// 课程编码
					String curs_name = selectCourse[4];// 课程名称
					String upper_limit = selectCourse[5];// 上限
					String lower_limit = selectCourse[6];// 下限
					String tchr_name = selectCourse[7];// 教师名称
					String tchr_title = selectCourse[8];// 职称
					String curs_credit = selectCourse[9];// 学分
					String curs_class_hour = selectCourse[10];// 学时
					String course_learning_model = selectCourse[11];// 授课类型
					String remark = selectCourse[14];// 备注
					User user = systemService.isExisUser("", "", tchr_name, tchr_title, null);
					if(StringUtils.isEmpty(curs_num)||curs_num.equals("——")) {
						curs_num = curs_edu_num;
					}
					Teacher tt = new Teacher();
					tt.setTeacher(user);
					Course course = new Course();
					course.setTeacher(tt);
					course.setCursNum(curs_num);
					course.setCursName(curs_name);
					Course entity = courseService.getCourse(course);
					if (StringUtils.isEmpty(entity)) {
						// 新增课程相关信息
						entity = new Course();
						entity.setId(systemService.getSequence("serialNo10"));
						entity.setIsNewRecord(true);
						String select_course_type_item_id = null;
						if (select_course_type.indexOf("（") > -1 && select_course_type.indexOf("）") > -1) {
							
							String label  = select_course_type.substring(select_course_type.indexOf("（") + 1,
									select_course_type.lastIndexOf("）"));
							
							select_course_type_item_id = DictUtils.getDictValue(label,"select_course_type_item","01");
						}
						entity.setCursSelectCourseType(select_course_type_item_id);
						entity.setCursYearTerm(currTerm);
						entity.setCursCredit(curs_credit);
						entity.setTeacher(tt);
						entity.setCursNum(curs_num);
						entity.setCursEduNum(curs_edu_num);
						entity.setCursName(curs_name);
						int upperLimit=0,lowerLimit = 0;
						if(!StringUtils.isEmpty(lower_limit)&&!lower_limit.equals("无")) {
							lowerLimit = new Double(lower_limit).intValue();
						}
						if(!StringUtils.isEmpty(upper_limit)&&!upper_limit.equals("无")) {
							upperLimit =new Double(upper_limit).intValue(); 
						}
						entity.setLowerLimit(lowerLimit);
						entity.setUpperLimit(upperLimit);
						entity.setCursStatus(Course.PAIKE_STATUS_WEI_PAIKE);
						String curs_type = "考查";
						curs_type = curs_type.substring(0, 2);
						String cursValue = "other";
						for (Dict d : courseCurrsTypes) {
							if (d.getLabel().indexOf(curs_type) > -1) {
								cursValue = d.getValue();
								break;
							}
						}
						entity.setCursType(cursValue);

						String cursForm = "99";
						String assessment_type = "其他";
						for (Dict d : courseCurrsForms) {
							if (d.getLabel().indexOf(assessment_type) > -1) {
								cursForm = d.getValue();
								break;
							}
						}

						entity.setCursForm(cursForm);

						entity.setCursClassHour(new Double(curs_class_hour).intValue() + "");
						entity.setCursIntro(remark);
						entity.setRemarks(remark);
						courseService.save(entity);
					}
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
		return "modules/paike/ImportSelectView";
	}
		
	
	public void auto(HttpServletRequest request) {
		
		Course course = new Course();
		//如何选择学院,进行如下操作
		if(!StringUtils.isEmpty(request.getParameter("officeId"))) {
			List<Office> majors = officeService.findByParentId(request.getParameter("officeId"));
			if (!StringUtils.isEmpty(majors) && majors.size() > 0) {
				List<String> item = new ArrayList<String>();
				for(Office major:majors) {
					if(!StringUtils.isEmpty(request.getParameter("majorId")) && request.getParameter("majorId").equals(major.getId())) {
						item.add(major.getName());
						break;
					}
					item.add(major.getName());
				}
				course.setItem(item);
			}
		}
		course.setCursStatus("00");
		
		List<Course> courses = courseService.findList(course);
		//需要进行排课的课程大概有
		logger.info("需要排课的课程大概有:{},具体需要排课总数:{}",courses,courses.size());
		//计算可排课教室,默认显示全部教室.管理员显示全部教室
		List<TreeLink> treelinks = schoolRootService.treeLinkId();
		List<String> schoolRoots = new ArrayList<String>();
		for(TreeLink treeLink:treelinks) {
			schoolRoots.add(treeLink.getValue());
		}
		//zhaojunfei
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setSchoolRoots(schoolRoots);
		List<CourseSchedule> courseSchedules = courseScheduleService.auto(courseSchedule);
		
		Iterator<CourseSchedule> its = courseSchedules.iterator();
		Iterator<Course> itcs = courses.iterator();
		while(its.hasNext()) {
			CourseSchedule cs = its.next();
			String $time_add = cs.getTimeAdd();
			Map<String,String> timeAddMap = CourseUtil.GetTimeCol($time_add);
			String schoolId = timeAddMap.get("school");
			SchoolRoot schoolRoot = schoolRootService.get(schoolId);
			while(itcs.hasNext()) {
				
				Course c = itcs.next();
				//1。确定课程类型 ,2.确定人数
				int cursTotal = Integer.valueOf(c.getCursTotal());//人数
				int weekTotal = Integer.valueOf(c.getCursWeekTotal());//周数
				int weekHour = Integer.valueOf(c.getCursWeekHour());//周学时
				int cursClassHour = Integer.valueOf(c.getCursClassHour());//总学时
				//课程人数小于40
				int total = schoolRoot.getTotal();
				//确定了人数
				int retTotal = fenpeibanji(cursTotal);
				if (total <= retTotal && retTotal != 0) {
					
				}
				
			}
		}
	}
	
	
	
	public int fenpeibanji(int total) {
		//40
		if (total < 40) {
			return 40;
		} else if (40 < total && total < 60) {
			return 60;
		} else if (60 < total && total < 68) {
			return 68;
		} else if (68 < total && total < 80) {
			return 80;
		} else if (80 < total && total < 90) {
			return 90;
		} else if (90 < total && total < 124) {
			return 124;
		} else if (90 < total && total < 124) {
			return 124;
		} else if (124 < total && total < 132) {
			return 132;
		} else if (132 < total && total < 133) {
			return 124;
		} else if (133 < total && total < 134) {
			return 133;
		} else if (134 < total && total < 135) {
			return 135;
		} else if (135 < total && total < 186) {
			return 186;
		} else if (186 < total && total < 190) {
			return 190;
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
		String a="2.0";
		String b="人文（历史与文化）";
		System.out.println(b.substring(0,b.indexOf("（")));
		System.out.println(b.substring(b.indexOf("（")+1,b.lastIndexOf("）")));
	}
}