/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.entity.CourseMaterial;
import com.thinkgem.jeesite.modules.course.entity.CourseSpecificContent;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingtarget;
import com.thinkgem.jeesite.modules.course.service.CourseCompositionRulesService;
import com.thinkgem.jeesite.modules.course.service.CourseMaterialService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseSpecificContentService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingtargetService;
import com.thinkgem.jeesite.modules.course.web.param.CourseRequestParam;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

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
	private CourseCompositionRulesService courseCompositionRulesService;
	@Autowired
	private CourseTeachingtargetService courseTeachingtargetService;
	@Autowired
	private CourseTeachingModeService courseTeachingModeService;
	@Autowired
	private CourseSpecificContentService courseSpecificContentService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = {"list", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
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
	public String save(Course course, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, course)){
			return form(course, model);
		}
		courseService.save(course);
		addMessage(redirectAttributes, "保存课程基本信息成功");
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
	public String adminCourseAdd1(Course course, RedirectAttributes redirectAttributes) {
		
		return "modules/course/add/adminCourseAdd1";
	}
	
	@RequiresPermissions("course:course:edit")
	@RequestMapping(value = "adminCourseAdd2")
	public String adminCourseAdd2(Course course, Model model) {
		String courseId = systemService.getSequence("serialNo10");
		try {
			logger.info("新增课程基本信息,课程信息:{}",course);
			if(StringUtils.isEmpty(course.getCursCredit())) {
				course.setCursCredit("100");
			}
			course.setIsNewRecord(true);
			course.setId(courseId);
			course.setTeacher(UserUtils.getUser());
			courseService.save(course);
		}catch(Exception e) {
			addMessage(model,"添加信息异常"+e.getMessage());
			return "modules/course/add/adminCourseAdd1";
		}
		model.addAttribute("courseId",courseId);
		addMessage(model,"课程基本信息设置成功");
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
		addMessage(model,"添加课程贡献点");
		return "modules/course/add/adminCourseAddOk";
	}
	
	
	//课程查询
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_1_selectTchrCourse")
	public String teacher_Management_1_selectTchrCourse(Course course, Model model) {
		User teacher = UserUtils.getUser();
		course.setTeacher(teacher);
		List<Course> teachCourses = courseService.findList(course);
		model.addAttribute("teachCourses", teachCourses);
		return "modules/course/teacher/teacher_Management_1_selectTchrCourse";
	}
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_2_selectTchrCourse")
	public String teacher_Management_2_selectTchrCourse(Course course, Model model) {
		User teacher = UserUtils.getUser();
		course.setTeacher(teacher);
		List<Course> teachCourses = courseService.findList(course);
		model.addAttribute("teachCourses", teachCourses);
		return "modules/course/teacher/teacher_Management_2_selectTchrCourse";
	}
	
	/*课程修改-----------------------------------------------------------------------------------------*/
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacherCourseModify")
	public String teacherCourse_Modify_1_selectByCursId(String cursId, Model model) {
		Course course = courseService.get(cursId);
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
	
	
	
	
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_3_selectTchrCourse")
	public String teacher_Management_3_selectTchrCourse(Course course, Model model) {
		User teacher = UserUtils.getUser();
		course.setTeacher(teacher);
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

}