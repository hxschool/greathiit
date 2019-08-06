/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherExperiment;
import com.thinkgem.jeesite.modules.teacher.service.TeacherExperimentService;
import com.thinkgem.jeesite.modules.teacher.service.TeacherService;

/**
 * 教师信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/teacher")
public class TeacherController extends BaseController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherExperimentService teacherExperimentService;
	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public Teacher get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return teacherService.get(id);
		}else {
			return new Teacher();
		}
		
	}
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = {"list", ""})
	public String list(Teacher teacher, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Teacher> page = teacherService.findPage(new Page<Teacher>(request, response), teacher); 
		model.addAttribute("page", page);
		return "modules/teacher/teacherList";
	}

	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "form")
	public String form(Teacher teacher, Model model) {
		model.addAttribute("teacher", teacher);
		return "modules/teacher/teacherForm";
	}

	@RequiresPermissions("teacher:teacher:edit")
	@RequestMapping(value = "save")
	public String save(Teacher teacher, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teacher)){
			return form(teacher, model);
		}
		User user = teacher.getUser();
		if(org.springframework.util.StringUtils.isEmpty(user.getNo())) {
			String seq = systemService.getSequence("serialNo4");
			String no = seq.substring(seq.length() - 4);
			teacher.setTeacherNumber(no);
			
			String idcard = teacher.getTchrIdcard()==null||teacher.getTchrIdcard().equals("")?no:teacher.getTchrIdcard();
			user = new User();
			Office office = new Office();
			office.setId("1");
			user.setCompany(office);
			user.setName(teacher.getTchrName());
			user.setLoginName(idcard);
			user.setNo(no);
			user.setMobile(teacher.getTchrPhone());
			user.setPhone(teacher.getTchrPhone());
			Role role = systemService.getRoleByEnname("teacher");
			user.setRole(role);
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);
			user.setRoleList(roleList);
			user.setPassword(SystemService.entryptPassword("888888"));
			systemService.saveUser(user);
		}
		teacherService.save(teacher);
		addMessage(redirectAttributes, "保存教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacher/?repage";
	}
	
	@RequiresPermissions("teacher:teacher:edit")
	@RequestMapping(value = "delete")
	public String delete(Teacher teacher, RedirectAttributes redirectAttributes) {
		teacherService.delete(teacher);
		addMessage(redirectAttributes, "删除教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacher/?repage";
	}
	

	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "teacherInfo")
	public String info(Model model) {
		Teacher teacher = teacherService.getTeacherInfo(UserUtils.getTeacher());
		model.addAttribute("teacher", teacher);
		TeacherExperiment teacherExperiment = new TeacherExperiment();
		teacherExperiment.setUser(UserUtils.getUser());
		List<TeacherExperiment> teacherExperiments = teacherExperimentService.findByParentIdsLike(teacherExperiment);
		model.addAttribute("teacherExperiments", teacherExperiments);
		return "modules/teacher/teacherInfo";
	}
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "teacherEdit")
	public String teacherEdit(Model model) {

		Teacher teacher = teacherService.getTeacherInfo(UserUtils.getTeacher());
		model.addAttribute("teacher", teacher);
		TeacherExperiment teacherExperiment = new TeacherExperiment();
		teacherExperiment.setUser(UserUtils.getUser());
		List<TeacherExperiment> teacherExperiments = teacherExperimentService.findByParentIdsLike(teacherExperiment);
		model.addAttribute("teacherExperiments", teacherExperiments);
		return "modules/teacher/teacherEdit";
	}
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "teacherEditSave")
	public String teacherEditSave(Teacher teacher,RedirectAttributes redirectAttributes) {
		Teacher entity = UserUtils.getTeacher();
		BeanUtils.copyProperties(teacher, entity);
		teacherService.save(entity);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacher/teacherInfo?repage";
	}
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "Teacher_Information_Modify_addExpByTchrNum")
	public String Teacher_Information_Modify_addExpByTchrNum(TeacherExperiment teacherExperiment, RedirectAttributes redirectAttributes,Model model) {
		User user = UserUtils.getUser();
		teacherExperiment.setUser(user);
		teacherExperimentService.save(teacherExperiment);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacher/teacherInfo?repage";
	}
	
	
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "Teacher_Information_Modify_deleteExpById")
	public String Teacher_Information_Modify_deleteExpById(@RequestParam(value="expId",required=false)String expId,RedirectAttributes redirectAttributes, Model model) {
		User user = UserUtils.getUser();
		TeacherExperiment teacherExperiment = new TeacherExperiment();
		teacherExperiment.setId(expId);
		teacherExperiment.setUser(user);
		teacherExperimentService.delete(teacherExperiment);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacher/teacherInfo?repage";
	}
	
	@RequestMapping(value = "ajaxTeacher")
	@ResponseBody
	public List<Teacher> ajaxTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
		return teacherService.findByParentIdsLike(teacher);
	}
	

}