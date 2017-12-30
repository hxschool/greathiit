/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentActivity;
import com.thinkgem.jeesite.modules.student.service.StudentActivityService;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
	private StudentService studentService;
	@Autowired
	private StudentActivityService studentActivityService;
	
	@ModelAttribute
	public Student get(@RequestParam(required=false) String id) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentService.get(id);
		}
		if (entity == null){
			entity = new Student();
		}
		return entity;
	}

	//个人资料	30	/student/student/Student_Information_Detail
	@RequiresPermissions("student:student:view")
	@RequestMapping("Student_Information_Detail")
	public String Student_Information_Detail(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		Student student = studentService.getStudentByStudentNumber(user.getNo());
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
	public String Student_Information_Modify_Save(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		Student entity = studentService.getStudentByStudentNumber(user.getNo());
		BeanUtils.copyProperties(student, entity);
		studentService.save(student);
		model.addAttribute("student",student);		
		return "redirect:"+Global.getAdminPath()+"/student/student/StudentInformationModify?repage";
	}
	
	
	
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_addActivity")
	public String Student_Portfolio_Activity_addActivity(StudentActivity studentActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		User student = UserUtils.getUser();
		studentActivity.setStudent(student);
		studentActivityService.save(studentActivity);
		model.addAttribute("message","操作成功");
		return "redirect:"+Global.getAdminPath()+"/student/student/Student_Portfolio_Activity?repage";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Portfolio_Activity_deleteActivity")
	public String Student_Portfolio_Activity_deleteActivity(@RequestParam("actId")String actId, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		StudentActivity studentActivity = new StudentActivity();
		studentActivity.setId(actId);
		studentActivity.setStudent(user);
		studentActivityService.delete(studentActivity);
		model.addAttribute("message","操作成功");
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
	public String Student_Performance(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/student/StudentPerformance";
	}

	
	//成绩查询	10	/student/student/Student_Course_Grade
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Course_Grade")
	public String Student_Course_Grade(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/student/StudentCourseGrade";
	}
	//获奖记录	30	/student/student/Student_Award
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award")
	public String Student_Award(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/student/StudentAward";
	}
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Add")
	public String Student_Award_Add(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/student/StudentAwardAdd";
	}
	
	@RequiresPermissions("Student_Portfolio_Activity_deleteItem")
	@RequestMapping("Student_Portfolio_Activity_deleteItem")
	public String Student_Portfolio_Activity_deleteItem( HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/student/StudentAwardAdd";
	}
	
	
	@RequiresPermissions("student:student:edit")
	@RequestMapping("Student_Award_Info")
	public String Student_Award_Info(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/student/StudentAwardInfo";
	}
	

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "form")
	public String form(Student student, Model model) {
		model.addAttribute("student", student);
		return "modules/student/studentForm";
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