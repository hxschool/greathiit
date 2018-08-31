/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;
import com.thinkgem.jeesite.modules.teacher.service.TeacherClassService;
import com.thinkgem.jeesite.modules.uc.cp.entity.UcContactPerson;
import com.thinkgem.jeesite.modules.uc.cp.service.UcContactPersonService;
import com.thinkgem.jeesite.modules.uc.ec.entity.UcEmergencyContact;
import com.thinkgem.jeesite.modules.uc.ec.service.UcEmergencyContactService;

/**
 * 教师班级信息表Controller
 * @author 赵俊飞
 * @version 2018-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/teacherClass")
public class TeacherClassController extends BaseController {

	@Autowired
	private TeacherClassService teacherClassService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private OfficeService officeService;
	

	@Autowired
	private UcContactPersonService ucContactPersonService;
	@Autowired
	private UcEmergencyContactService ucEmergencyContactService;
	
	
	@ModelAttribute
	public TeacherClass get(@RequestParam(required=false) String id) {
		TeacherClass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = teacherClassService.get(id);
		}
		if (entity == null){
			entity = new TeacherClass();
		}
		return entity;
	}
	
	
	
	@RequestMapping("emergency")
	@ResponseBody
	public List<UcEmergencyContact> emergency(String studentNumber,  HttpServletRequest request, HttpServletResponse response, Model model) {
		UcEmergencyContact ucEmergencyContact = new UcEmergencyContact();
		ucEmergencyContact.setStudentNumber(studentNumber);
		List<UcEmergencyContact> list = ucEmergencyContactService.findList(ucEmergencyContact);
		return list;
	}
	
	@RequestMapping("contact")
	@ResponseBody
	public List<UcContactPerson> contact(String studentNumber,  HttpServletRequest request, HttpServletResponse response, Model model) {
		UcContactPerson ucContactPerson = new UcContactPerson();
		ucContactPerson.setStudentNumber(studentNumber);
		List<UcContactPerson> list = ucContactPersonService.findList(ucContactPerson);
		return list;
	}
	
	//@RequiresPermissions("teacher:teacherClass:view")
	@RequestMapping("student")
	public String student(Student student,  HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		TeacherClass teacherClass = new TeacherClass();
		teacherClass.setTeacherNumber(user.getNo());
		List<TeacherClass> teacherClazzs = teacherClassService.findList(teacherClass);
		List<String> clazzNumbers = new ArrayList<String>();
		for(TeacherClass cla :teacherClazzs) {
			Office clazz = cla.getClazz();
			clazzNumbers.add(clazz.getId());
		}
		if(!org.springframework.util.StringUtils.isEmpty(student.getClazz())&&clazzNumbers.contains(student.getClazz().getId())) {
			clazzNumbers = new ArrayList<String>(); 
			clazzNumbers.add(student.getClazz().getId());
		}
		if(clazzNumbers.size()>0) {
			student.setClazzNumbers(clazzNumbers);
		}
		Page<Student> page = studentService.findPage(new Page<Student>(request, response), student); 
		model.addAttribute("page", page);
		return "modules/teacher/teacherClass/teacherStudentList";
	}

	
	@RequiresPermissions("teacher:teacherClass:view")
	@RequestMapping(value = {"list", ""})
	public String list(TeacherClass teacherClass, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();
		teacherClass.setTeacherNumber(user.getNo());
		Page<TeacherClass> page = teacherClassService.findPage(new Page<TeacherClass>(request, response), teacherClass); 
		model.addAttribute("page", page);
		return "modules/teacher/teacherClass/teacherClassList";
	}

	@RequiresPermissions("teacher:teacherClass:view")
	@RequestMapping(value = "form")
	public String form(TeacherClass teacherClass, Model model) {
		model.addAttribute("teacherClass", teacherClass);
		return "modules/teacher/teacherClass/teacherClassForm";
	}

	@RequiresPermissions("teacher:teacherClass:edit")
	@RequestMapping(value = "save")
	public String save(String[] classNumber,String remarks, Model model, RedirectAttributes redirectAttributes) {

		for(String cla : classNumber) {
			TeacherClass teacherClass = new TeacherClass();
			teacherClass.setRemarks(remarks);
			User user = UserUtils.getUser();
			teacherClass.setTeacherNumber(user.getNo());
			Office clazz = officeService.get(cla);
			teacherClass.setClazz(clazz);
			teacherClassService.save(teacherClass);
		}
		
		
		
		addMessage(redirectAttributes, "保存教师班级信息表成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacherClass/?repage";
	}
	
	@RequiresPermissions("teacher:teacherClass:edit")
	@RequestMapping(value = "delete")
	public String delete(TeacherClass teacherClass, RedirectAttributes redirectAttributes) {
		teacherClassService.delete(teacherClass);
		addMessage(redirectAttributes, "删除教师班级信息表成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacherClass/?repage";
	}

}