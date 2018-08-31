/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherExperiment;
import com.thinkgem.jeesite.modules.teacher.service.TeacherExperimentService;

/**
 * 教师履历Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/teacher/teacherExperiment")
public class TeacherExperimentController extends BaseController {

	@Autowired
	private TeacherExperimentService teacherExperimentService;
	
	@ModelAttribute
	public TeacherExperiment get(@RequestParam(required=false) String id) {
		TeacherExperiment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = teacherExperimentService.get(id);
		}
		if (entity == null){
			entity = new TeacherExperiment();
		}
		return entity;
	}
	
	@RequiresPermissions("teacher:teacherExperiment:view")
	@RequestMapping(value = {"list", ""})
	public String list(TeacherExperiment teacherExperiment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeacherExperiment> page = teacherExperimentService.findPage(new Page<TeacherExperiment>(request, response), teacherExperiment); 
		model.addAttribute("page", page);
		return "modules/teacher/teacherExperimentList";
	}

	@RequiresPermissions("teacher:teacherExperiment:view")
	@RequestMapping(value = "form")
	public String form(TeacherExperiment teacherExperiment, Model model) {
		model.addAttribute("teacherExperiment", teacherExperiment);
		return "modules/teacher/teacherExperimentForm";
	}

	@RequiresPermissions("teacher:teacherExperiment:edit")
	@RequestMapping(value = "save")
	public String save(TeacherExperiment teacherExperiment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, teacherExperiment)){
			return form(teacherExperiment, model);
		}
		teacherExperimentService.save(teacherExperiment);
		addMessage(redirectAttributes, "保存教师履历成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacherExperiment/?repage";
	}
	
	@RequiresPermissions("teacher:teacherExperiment:edit")
	@RequestMapping(value = "delete")
	public String delete(TeacherExperiment teacherExperiment, RedirectAttributes redirectAttributes) {
		teacherExperimentService.delete(teacherExperiment);
		addMessage(redirectAttributes, "删除教师履历成功");
		return "redirect:"+Global.getAdminPath()+"/teacher/teacherExperiment/?repage";
	}

}