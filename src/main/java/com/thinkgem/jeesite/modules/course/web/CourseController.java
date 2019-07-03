/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SnowflakeIdWorker;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.entity.CourseMaterial;
import com.thinkgem.jeesite.modules.course.entity.CourseSpecificContent;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingtarget;
import com.thinkgem.jeesite.modules.course.service.CourseClassService;
import com.thinkgem.jeesite.modules.course.service.CourseCompositionRulesService;
import com.thinkgem.jeesite.modules.course.service.CourseMaterialService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseSpecificContentService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingtargetService;
import com.thinkgem.jeesite.modules.course.web.param.CourseRequestParam;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.service.TeacherService;

/**
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/course")
public class CourseController extends BaseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseMaterialService courseMaterialService;
	@Autowired
	private CourseTeachingtargetService courseTeachingtargetService;
	@Autowired
	private CourseTeachingModeService courseTeachingModeService;
	@Autowired
	private CourseSpecificContentService courseSpecificContentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SysConfigService sysConfigService;
	private SysConfig config;
	@Autowired
	private DictService dictService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseClassService courseClassService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private CourseCompositionRulesService courseCompositionRulesService;
	@Autowired
	private SelectCourseService selectCourseService;
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id,Model model) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
			CourseTeachingMode courseTeachingMode = courseTeachingModeService.getCourseTeachingModeByCourse(id);
			if(!entity.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
				CourseClass courseClass = new CourseClass();
				courseClass.setCourse(entity);
				List<CourseClass> ccs = courseClassService.findList(courseClass);
				for(CourseClass cs:ccs) {
					Office cls = officeService.get(cs.getCls()); 
					cs.setCls(cls);
				}
				model.addAttribute("ccs", ccs);
			}
			entity.setCourseTeachingMode(courseTeachingMode);
		}
		if (entity == null){
			entity = new Course();
		}
		config = sysConfigService.getModule(Global.SYSCONFIG_COURSE);
		Map<Dict,List<Dict>> groupSelect = new HashMap<Dict,List<Dict>>();
		List<Dict> dicts = DictUtils.getDictList("select_course_type");
		for(Dict dict:dicts) {
			Dict tmp = new Dict();
			tmp.setParentId(dict.getId());
			groupSelect.put(dict, dictService.findList(tmp));
		}
		model.addAttribute("groupSelect", groupSelect);
		model.addAttribute("config", config);
		return entity;
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(!isAdmin()) {
			course.setTeacher(UserUtils.getTeacher());
		}
		
		if(!StringUtils.isEmpty(course.getCursYearTerm())) {
			config = new SysConfig();
			config.setTermYear(course.getCursYearTerm());
		}
		course.setCursYearTerm(config.getTermYear());
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		model.addAttribute("config", config);
		return "modules/course/courseList";
	}

	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "form")
	public String form(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/courseForm";
	}

	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "save")
	public String save(Course course, Model model,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, course)){
			return form(course, model);
		}
		SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);
		String courseId = String.valueOf(snowflakeIdWorker.nextId());
		course.setCursEduNum(course.getCursNum());
		course.setIsNewRecord(true);
		course.setId(courseId);
		if(!isAdmin()) {
			course.setTeacher(UserUtils.getTeacher());
		}
		String[] classIds = request.getParameterValues("classIds");
		if(!org.springframework.util.StringUtils.isEmpty(course.getCourseTeachingMode())) {
			CourseTeachingMode courseTeachingMode = course.getCourseTeachingMode();
			courseTeachingMode.setTeacMethod(course.getCourseTeachingMode().toString());
			courseTeachingMode.setCourseId(courseId);
			courseTeachingMode.setPeriod(course.getCursClassHour());
			courseTeachingModeService.save(courseTeachingMode);
		}
		if(!org.springframework.util.StringUtils.isEmpty(course.getCourseCompositionRules())) {
			CourseCompositionRules courseCompositionRules = new CourseCompositionRules();
			courseCompositionRules.setCourseId(courseId);
		}
		courseService.save(course);
		if(!org.springframework.util.StringUtils.isEmpty(classIds)) {
			for(String classId:classIds) {
				if(!org.springframework.util.StringUtils.isEmpty(classId)) {
					CourseClass courseClass = new CourseClass();
					courseClass.setCourse(course);
					Office cls = new Office();
					cls.setId(classId);
					courseClass.setCls(cls);
					courseClassService.save(courseClass);
				}
			}
		}
		addMessage(redirectAttributes, "保存课程基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}
	//@RequiresPermissions("course:course:submit")
	@RequestMapping(value = "submit",method=RequestMethod.GET)
	public String submit(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/courseSubmit";
	}
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "submit",method=RequestMethod.POST)
	public String submit(Course course, Model model,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		courseService.submit(course);
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}
	
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "delete")
	public String delete(Course course, RedirectAttributes redirectAttributes) {
		courseService.delete(course);
		addMessage(redirectAttributes, "删除课程基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/?repage";
	}
	
	/*新增课程信息-------------------------------------------------------------------------------------*/
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd1")
	public String adminCourseAdd1(Course course,Model model, RedirectAttributes redirectAttributes) {
		
		return "modules/course/add/adminCourseAdd1";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd2")
	public String adminCourseAdd2(Course course,HttpServletRequest request,HttpServletResponse response, Model model) {
		String courseId = systemService.getSequence("serialNo10");
		try {
			logger.info("新增课程基本信息,课程信息:{}",course);
			if(StringUtils.isEmpty(course.getCursCredit())) {
				course.setCursCredit("100");
			}
			course.setCursEduNum(course.getCursNum());
			course.setIsNewRecord(true);
			course.setId(courseId);
			course.setTeacher(UserUtils.getTeacher());
			courseService.save(course);
			String teacMethod = request.getParameter("teacMethod");
			if(!org.springframework.util.StringUtils.isEmpty(teacMethod)) {
				CourseTeachingMode courseTeachingMode = new CourseTeachingMode();
				courseTeachingMode.setTeacMethod(teacMethod);
				courseTeachingMode.setCourseId(courseId);
				courseTeachingMode.setPeriod(course.getCursClassHour());
				courseTeachingModeService.save(courseTeachingMode);
			}
		}catch(Exception e) {
			addMessage(model,"添加信息异常"+e.getMessage());
			return "modules/course/add/adminCourseAdd1";
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"课程基本信息设置成功");
		String op = request.getParameter("op");
		if(!org.springframework.util.StringUtils.isEmpty(op)&&op.equals("ok")) {
			return "redirect:"+Global.getAdminPath()+"/course/course/teacher_Management_1_selectTchrCourse?repage";
		}
		
		return "modules/course/add/adminCourseAdd2";
		
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd3")
	public String adminCourseAdd3(String courseId,CourseRequestParam courseRequestParam,Model model) {
		
		for(CourseTeachingtarget courseTeachingtarget:courseRequestParam.getTargets()) {
			courseTeachingtarget.setCourseId(courseId);
			
			courseTeachingtargetService.save(courseTeachingtarget);
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程教学目标成功");
		return "modules/course/add/adminCourseAdd3";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd4")
	public String adminCourseAdd4(String courseId,CourseRequestParam courseRequestParam,Model model) {
		
		
		for(CourseSpecificContent courseSpecificContent:courseRequestParam.getCsc()) {
			courseSpecificContent.setCourseId(courseId);
			courseSpecificContentService.save(courseSpecificContent);
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程教学目标成功");
	
		return "modules/course/add/adminCourseAdd4";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd5")
	public String adminCourseAdd5(String courseId,CourseRequestParam courseRequestParam,Model model) {
		for(CourseTeachingMode courseTeachingMode:courseRequestParam.getCtm()) {
			courseTeachingMode.setCourseId(courseId);
			courseTeachingModeService.save(courseTeachingMode);
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程教学方式成功");
		return "modules/course/add/adminCourseAdd5";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd6")
	public String adminCourseAdd6(String courseId,Model model) {
		model.addAttribute("courseId",courseId);
		return "modules/course/add/adminCourseAdd6";
	}
	
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd7")
	public String adminCourseAdd7(String courseId, Model model) {
		
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程贡献点成功");
		return "modules/course/add/adminCourseAdd7";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd8")
	public String adminCourseAdd8(String courseId,CourseRequestParam courseRequestParam,Model model) {
		for(CourseMaterial courseMaterial:courseRequestParam.getCrb()) {
			courseMaterial.setCmType("1");
			courseMaterial.setCourseId(courseId);
			courseMaterialService.save(courseMaterial);
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程教材及参考书目成功");
		return "modules/course/add/adminCourseAdd8";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd9")
	public String adminCourseAdd9(String courseId,String cursNote1, String cursNote2,Model model) {
		Course course = courseService.get(courseId);
		course.setCursNote1(cursNote1);
		course.setCursNote2(cursNote2);
		courseService.save(course);
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程说明成功");
		return "modules/course/add/adminCourseAdd9";
	}
	
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd10")
	public String adminCourseAdd10(String courseId,CourseCompositionRules compositionRules,Model model) {
		courseCompositionRulesService.save(compositionRules);
		model.addAttribute("courseId",courseId);
		addMessage(model,"课程考试与教学目标支撑分值设置成功");
		return "modules/course/add/adminCourseAdd10";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAddOk")
	public String adminCourseAddOk(Course course, Model model) {
		addMessage(model,"添加课程成功");
		return "redirect:"+Global.getAdminPath()+"/course/course/teacher_Management_1_selectTchrCourse?repage";
	}
	
	
	//课程查询
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_1_selectTchrCourse")
	public String teacher_Management_1_selectTchrCourse(Course course, Model model) {
		course.setTeacher(UserUtils.getTeacher());
		List<Course> teachCourses = courseService.findList(course);
		model.addAttribute("teachCourses", teachCourses);
		return "modules/course/teacher/teacher_Management_1_selectTchrCourse";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_2_selectTchrCourse")
	public String teacher_Management_2_selectTchrCourse(Course course, Model model) {
		course.setTeacher(UserUtils.getTeacher());
		List<Course> teachCourses = courseService.findList(course);
		model.addAttribute("teachCourses", teachCourses);
		return "modules/course/teacher/teacher_Management_2_selectTchrCourse";
	}
	
	/*课程修改-----------------------------------------------------------------------------------------*/
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourseModify")
	public String teacherCourse_Modify_1_selectByCursId(Course course, Model model) {
		model.addAttribute("course",course);
		return "modules/course/modify/teacherCourseModify";
	}
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_1_selectBasicInfByCursId")
	public String teacherCourse_Modify_1_selectBasicInfByCursId(Course course,  Model model) {
		
		return "modules/course/modify/teacherCourseModify1";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_1_updateCursBasicInf")
	public String teacherCourse_Modify_1_updateCursBasicInf(Course course, Model model) {
		courseService.save(course);
		model.addAttribute("course",course);
		model.addAttribute("courseId",course.getId());
		model.addAttribute("message","更新成功");
		return "modules/course/modify/teacherCourseModify1";
	}
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_2_selectTchingTargetByCursId")
	public String teacherCourse_Modify_2_selectTchingTargetByCursId(Course course,  Model model) {
		String courseId = course.getId();
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		model.addAttribute("courseId",courseId);
		model.addAttribute("targets", targets);
		return "modules/course/modify/teacherCourseModify2";
	}
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_2_modifyTargetByCursId")
	public String teacherCourse_Modify_2_modifyTargetByCursId(String courseId,CourseRequestParam courseRequestParam,Model model) {
		
		for(CourseTeachingtarget courseTeachingtarget:courseRequestParam.getTargets()) {
			courseTeachingtarget.setCourseId(courseId);
			courseTeachingtargetService.save(courseTeachingtarget);
		}
		model.addAttribute("courseId",courseId);
		model.addAttribute("targets", courseRequestParam.getTargets());
		addMessage(model,"修改课程教学目标成功");
		return "modules/course/modify/teacherCourseModify2";
	}
	
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_3_selectSpeContentCursId")
	public String teacherCourse_Modify_3_selectSpeContentCursId(Course course,  Model model) {
		String courseId = course.getId();
		CourseSpecificContent courseSpecificContent = new CourseSpecificContent();
		courseSpecificContent.setCourseId(courseId);
		List<CourseSpecificContent> csc = courseSpecificContentService.findList(courseSpecificContent);
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		model.addAttribute("courseId", courseId);
		model.addAttribute("targets", targets);
		model.addAttribute("csc",csc);
		
		return "modules/course/modify/teacherCourseModify3";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_3_modifyTargetByCursId")
	public String teacherCourse_Modify_3_modifyTargetByCursId(String courseId,CourseRequestParam courseRequestParam,Model model) {
		
		for(CourseSpecificContent courseSpecificContent:courseRequestParam.getCsc()) {
			courseSpecificContent.setCourseId(courseId);
			courseSpecificContentService.save(courseSpecificContent);
		}
		
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		model.addAttribute("targets", targets);
		model.addAttribute("courseId",courseId);
		addMessage(model,"操作成功");
		model.addAttribute("csc", courseRequestParam.getCsc());
	
		return "modules/course/modify/teacherCourseModify3";
	}
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_4_selectTchingModeByCursId")
	public String teacherCourse_Modify_4_selectTchingModeByCursId(Course course,  Model model) {
		String courseId = course.getId();
		CourseTeachingMode courseTeachingMode = new CourseTeachingMode();
		courseTeachingMode.setCourseId(courseId);
		List<CourseTeachingMode> ctm = courseTeachingModeService.findList(courseTeachingMode);
		model.addAttribute("ctm",ctm);
		model.addAttribute("courseId",courseId);
		return "modules/course/modify/teacherCourseModify4";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_4_modifyTargetByCursId")
	public String teacherCourse_Modify_4_modifyTargetByCursId(String courseId,CourseRequestParam courseRequestParam,Model model) {
		for(CourseTeachingMode courseTeachingMode:courseRequestParam.getCtm()) {
			courseTeachingMode.setCourseId(courseId);
			courseTeachingModeService.save(courseTeachingMode);
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"添加课程教学方式成功");
		model.addAttribute("ctm", courseRequestParam.getCtm());
		return "modules/course/modify/teacherCourseModify4";
	}

	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_6_selectPerRuleByCursId")
	public String teacherCourse_Modify_6_selectPerRuleByCursId(Course course,  Model model) {
		String courseId = course.getId();
		CourseCompositionRules rules = courseCompositionRulesService.getCourseCompositionRulesByCourseId(courseId);
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		model.addAttribute("courseId",courseId);
		model.addAttribute("rules",rules);
		model.addAttribute("targets",targets);
		return "modules/course/modify/teacherCourseModify6";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_7_selectBookByCursId")
	public String teacherCourse_Modify_7_selectBookByCursId(Course course,  Model model) {
		CourseMaterial courseMaterial = new CourseMaterial();
		courseMaterial.setCourseId(course.getId());
		courseMaterial.setCmType("1");
		courseMaterialService.findList(courseMaterial);
		model.addAttribute("courseId",course.getId());
		model.addAttribute("cm",courseMaterialService.findList(courseMaterial));
		courseMaterial.setCmType("2");
		model.addAttribute("crb",courseMaterialService.findList(courseMaterial));
		return "modules/course/modify/teacherCourseModify7";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_7_modifyBookByCursId")
	public String teacherCourse_Modify_7_modifyBookByCursId(String courseId,CourseRequestParam courseRequestParam,  Model model) {
		if (!org.springframework.util.StringUtils.isEmpty(courseRequestParam.getCm())) {

			for (CourseMaterial courseMaterial : courseRequestParam.getCm()) {
				courseMaterial.setCmType("1");
				courseMaterial.setCourseId(courseId);
				courseMaterialService.save(courseMaterial);
			}
			
		}
		if (!org.springframework.util.StringUtils.isEmpty(courseRequestParam.getCrb())) {
			for (CourseMaterial courseMaterial : courseRequestParam.getCrb()) {
				courseMaterial.setCmType("2");
				courseMaterial.setCourseId(courseId);
				courseMaterialService.save(courseMaterial);
			}
		}
		
		
		model.addAttribute("courseId",courseId);
		addMessage(model,"操作成功");
		
		CourseMaterial courseMaterial = new CourseMaterial();
		courseMaterial.setCourseId(courseId);
		courseMaterial.setCmType("1");
		courseMaterialService.findList(courseMaterial);
		model.addAttribute("courseId",courseId);
		model.addAttribute("cm",courseMaterialService.findList(courseMaterial));
		courseMaterial.setCmType("2");
		model.addAttribute("crb",courseMaterialService.findList(courseMaterial));
		
		
		return "modules/course/modify/teacherCourseModify7";
	}
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_8_selectNoteByCursId")
	public String teacherCourse_Modify_8_selectNoteByCursId(Course course,  Model model) {
		model.addAttribute("course",course);
		return "modules/course/modify/teacherCourseModify8";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_8_modifyNoteByCursId")
	public String teacherCourse_Modify_8_modifyNoteByCursId(Course course,  Model model) {
		courseService.save(course);
		model.addAttribute("course",course);
		addMessage(model,"操作成功");
		return "modules/course/modify/teacherCourseModify8";
	}
	
	/*课程目标评价开始-----------------------------------------------------------------------------------------*/
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_9_selectTarByCursId")
	public String teacherCourse_Modify_9_selectTarByCursId(String cursId, Model model) {
		Course course = courseService.get(cursId);
		String courseId = course.getId();
		CourseCompositionRules courseCompositionRules = courseCompositionRulesService.getCourseCompositionRulesByCourseId(courseId);
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		model.addAttribute("targets", targets);
		model.addAttribute("course",course);
		model.addAttribute("courseCompositionRules",courseCompositionRules);
		
		return "modules/course/modify/teacherCourseModify9";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_9_modifyTargetValueByCursId")
	public String teacherCourse_Modify_9_modifyTargetValueByCursId(String courseId,CourseCompositionRules courseCompositionRules,CourseRequestParam courseRequestParam , Model model) {
		Course course = courseService.get(courseId);
		for(CourseTeachingtarget courseTeachingtarget:courseRequestParam.getTargets()) {
			courseTeachingtarget.setCourseId(courseId);
			courseTeachingtargetService.save(courseTeachingtarget);
		}
		model.addAttribute("course",course);
		return "modules/course/modify/teacherCourseModify9";
	}
	
	/*毕业要求评价  -----------------------------------------------------------------------------------------*/
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourse_Modify_5_selectAllPoints")
	public String teacherCourse_Modify_5_selectAllPoints(Course course,  Model model) {
		model.addAttribute("course",course);
		return "modules/course/modify/teacherCourseModify9";
	}
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_3_selectTchrCourse")
	public String teacher_Management_3_selectTchrCourse(Course course, Model model) {
		
		course.setTeacher(UserUtils.getTeacher());
		List<Course> teachCourses = courseService.findList(course);
		model.addAttribute("teachCourses", teachCourses);
		return "modules/course/teacher/teacher_Management_3_selectTchrCourse";
	}
	
	//课程基本信息
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "courseDetail")
	public String course_Detail_1_selectByCursId(String cursId, Model model) {
		Course course = courseService.get(cursId);
		model.addAttribute("course",course);
		return "modules/course/detail/courseDetail";
	}
	//课程基本信息
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_1_selectBasicInfByCursId")
	public String selectBasicInfByCursId(Course course,  Model model) {
		return "modules/course/detail/courseDetail1";
	}
	//课程教学目标
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_2_selectTchingTargetByCursId")
	public String selectTchingTargetByCursId(Course course, Model model) {
		String courseId = course.getId();
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		return "modules/course/detail/courseDetail2";
	}
	//课程具体内容
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_3_selectSpeContentCursId")
	public String selectSpeContentCursId(Course course,  Model model) {
		String courseId = course.getId();
		CourseSpecificContent courseSpecificContent = new CourseSpecificContent();
		courseSpecificContent.setCourseId(courseId);
		List<CourseSpecificContent> csc = courseSpecificContentService.findList(courseSpecificContent);
		model.addAttribute("csc",csc);
		return "modules/course/detail/courseDetail3";
	}

	
	// 教学方式
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_4_selectTchingModeByCursId")
	public String selectTchingModeByCursId(Course course,  Model model) {
		String courseId = course.getId();
		CourseTeachingMode courseTeachingMode = new CourseTeachingMode();
		courseTeachingMode.setCourseId(courseId);
		List<CourseTeachingMode> ctm = courseTeachingModeService.findList(courseTeachingMode);
		model.addAttribute("ctm",ctm);
		return "modules/course/detail/courseDetail4";
	}

	// 考核与评定
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_6_selectPerRuleByCursId")
	public String selectPerRuleByCursId(Course course,  Model model) {
		String courseId = course.getId();
	
		CourseCompositionRules rules = courseCompositionRulesService.getCourseCompositionRulesByCourseId(courseId);
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);

		model.addAttribute("rules",rules);
		model.addAttribute("targets",targets);
		return "modules/course/detail/courseDetail6";
	}

	// 参考教材
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_7_selectBookByCursId")
	public String selectBookByCursId(Course course,  Model model) {
		CourseMaterial courseMaterial = new CourseMaterial();
		courseMaterial.setCourseId(course.getId());
		courseMaterial.setCmType("1");
		courseMaterialService.findList(courseMaterial);
		model.addAttribute("cm",courseMaterialService.findList(courseMaterial));
		courseMaterial.setCmType("2");
		model.addAttribute("crb",courseMaterialService.findList(courseMaterial));
		return "modules/course/detail/courseDetail7";
	}

	// 说明
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_8_selectNoteByCursId")
	public String selectNoteByCursId(Course course, RedirectAttributes redirectAttributes) {
		return "modules/course/detail/courseDetail8";
	}
	
	@RequestMapping(value = "ajaxCourse")
	@ResponseBody
	public Course ajaxCourse(Course course, RedirectAttributes redirectAttributes) {
		return courseService.get(course);
	}
	
	@RequiresPermissions("course:course:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Course course, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "课程数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<Course> page = courseService.findPage(new Page<Course>(request, response, -1), course);
    		new ExportExcel("课程数据", Course.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/course/list?repage";
    }
	
	@RequestMapping("exportStudentCourse")
	public String exportStudentCourse(Course course,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws FileNotFoundException, IOException {
		
		if(org.springframework.util.StringUtils.isEmpty(course)) {
			throw new GITException("40400099","系统异常,未选择课程");
		}
		course = courseService.get(course);
		String filename = StringEscapeUtils.unescapeHtml4(course.getCursName()).concat("成绩单.xls");
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/student/成绩单模版.xls");  
		response.setHeader("Content-Disposition", "attachment; filename="+new String(filename.getBytes("gbk"),"ISO-8859-1"));
		File file = new File(modelPath);
		courseService.exportCourse(file, course, response.getOutputStream());
		addMessage(redirectAttributes, "导出成功");
		return "redirect:" + adminPath + "/course/course/list?repage";
	}
	
	@RequestMapping("selectCourseView")
	public String selectCourseView(SelectCourse selectCourse,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes,Model model) throws FileNotFoundException, IOException {
		File directory = new File(request.getSession().getServletContext().getRealPath("/resources/selectcourse"));
		Collection<File> list = FileUtils.listFiles(directory, FileFilterUtils.suffixFileFilter("xls"), null);
		model.addAttribute("list",list);
		return "modules/course/selectCourseView";
	}
	
	@RequestMapping("selectCourse")
	public String selectCourse(SelectCourse selectCourse,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception {
		String termYear = config.getTermYear();
		String cursName  = "";
		if(!org.springframework.util.StringUtils.isEmpty(selectCourse.getCourse())) {
			if(!org.springframework.util.StringUtils.isEmpty(selectCourse.getCourse().getCursYearTerm())) {
				termYear = selectCourse.getCourse().getCursYearTerm();
			}
			cursName = selectCourse.getCourse().getCursName();
		}
		String filename = StringEscapeUtils.unescapeHtml4( termYear + cursName + "公共选课学生名单.xls");
		
		
		String modelPath = request.getSession().getServletContext().getRealPath("/resources/selectcourse/");
		File file = new File(modelPath,filename);
		if(file.exists()) {
			addMessage(redirectAttributes, "操作失败,请等待系统处理相关数据或者直接下载生成的课程数据");
			return "redirect:" + adminPath + "/course/course/selectCourseView?repage";
		}
		courseService.selectCourse(file, selectCourse);
		addMessage(redirectAttributes, "操作导出成功,请等待系统处理相关数据");
		return "redirect:" + adminPath + "/course/course/selectCourseView?repage";
	}
	
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("course:course:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0,0);
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<Course> list = ei.getDataList(Course.class);
			for (Course course : list){
				try{
					Teacher teacher = teacherService.getTeacherInfo(course.getTeacher());
					course.setTeacher(teacher);
					
					String courseId = String.valueOf(snowflakeIdWorker.nextId());
					course.setIsNewRecord(true);
					course.setId(courseId);
					course.setCursEduNum(course.getCursNum());
					courseService.save(course);
					
					if(!org.springframework.util.StringUtils.isEmpty(course.getTeachingMode())) {
						CourseTeachingMode courseTeachingMode = new CourseTeachingMode();
						courseTeachingMode.setTeacMethod(course.getTeachingMode());
						courseTeachingMode.setCourseId(courseId);
						courseTeachingModeService.save(courseTeachingMode);
					}
					
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>课程名 "+course.getCursName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>课程名 "+course.getCursName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条课程，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条课程"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入课程失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/course/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("course:course:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "课程数据导入模板.xlsx";
    		List<Course> list = Lists.newArrayList(); list.add(new Course());
    		new ExportExcel("课程数据", Course.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/course/list?repage";
    }
	

}