/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/modify")
public class CourseModifyController extends BaseController {

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
	
	
	//课程查询
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "teacher_Management_1_selectTchrCourse")
	public String selectTchrCourse(Course course, Model model) {
		User teacher = UserUtils.getUser();
		course.setTeacher(teacher);
		List<Course> teachCourses = courseService.findList(course);
		model.addAttribute("teachCourses", teachCourses);
		return "modules/course/teacher/teacher_Management_1_selectTchrCourse";
	}
	//课程基本信息
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "Course_Detail_1_selectByCursId")
	public String course_Detail_1_selectByCursId(String cursId, Model model) {
		Course course = courseService.get(cursId);
		model.addAttribute("course",course);
		return "modules/course/modify/course_detail_1_selectByCursId";
	}
	//课程基本信息
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_1_selectBasicInfByCursId")
	public String selectBasicInfByCursId(Course course,  Model model) {
		return "modules/course/modify/courseDetail1";
	}
	//课程教学目标
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_2_selectTchingTargetByCursId")
	public String selectTchingTargetByCursId(Course course, Model model) {
		String courseId = course.getId();
		CourseTeachingtarget courseTeachingtarget = new CourseTeachingtarget();
		courseTeachingtarget.setCourseId(courseId);
		List<CourseTeachingtarget> targets = courseTeachingtargetService.findList(courseTeachingtarget);
		return "modules/course/modify/courseDetail2";
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
		return "modules/course/modify/courseDetail3";
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
		return "modules/course/modify/courseDetail4";
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
		return "modules/course/modify/courseDetail6";
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
		return "modules/course/modify/courseDetail7";
	}

	// 说明
	@RequiresPermissions("course:course:view")
	@RequestMapping(value = "detail_8_selectNoteByCursId")
	public String selectNoteByCursId(Course course, RedirectAttributes redirectAttributes) {
		return "modules/course/modify/courseDetail8";
	}

}