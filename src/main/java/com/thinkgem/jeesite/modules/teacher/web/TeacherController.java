/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.web;

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
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
	
	@ModelAttribute
	public Teacher get(@RequestParam(required=false) String id) {
		Teacher entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = teacherService.get(id);
		}
		if(entity == null) {
			User user = UserUtils.getUser();
			if(user.getUserType().equals("6")) {
				return entity;
			}
			Teacher teacher = new Teacher();
			teacher.setTeacher(user);
			entity = teacherService.get(teacher);
			if(entity==null) {
				entity = new Teacher();
				String idCard = user.getLoginName();
				if(IdcardUtils.validateCard(idCard)) {
					entity.setTchrIdcard(idCard);
					entity.setTchrGender(IdcardUtils.getGenderByIdCard(idCard));
				}
				entity.setTchrEmail(user.getEmail());
				entity.setTchrPhone(user.getPhone());
				
				entity.setTchrName(user.getName());
				entity.setTeacher(user);
				teacherService.save(entity);
			}
		}
		return entity;
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
	public String info(Teacher teacher, Model model) {
		model.addAttribute("teacher", teacher);
		TeacherExperiment teacherExperiment = new TeacherExperiment();
		teacherExperiment.setTeacher(UserUtils.getUser());
		List<TeacherExperiment> teacherExperiments = teacherExperimentService.findList(teacherExperiment);
		model.addAttribute("teacherExperiments", teacherExperiments);
		return "modules/teacher/teacherInfo";
	}
	
	@RequiresPermissions("teacher:teacher:view")
	@RequestMapping(value = "teacherEdit")
	public String edit(Teacher teacher, Model model) {
		model.addAttribute("teacher", teacher);
		TeacherExperiment teacherExperiment = new TeacherExperiment();
		teacherExperiment.setTeacher(UserUtils.getUser());
		List<TeacherExperiment> teacherExperiments = teacherExperimentService.findList(teacherExperiment);
		model.addAttribute("teacherExperiments", teacherExperiments);
		return "modules/teacher/teacherEdit";
	}

}