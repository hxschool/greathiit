/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.modules.uc.entity.UcStudent;
import com.thinkgem.jeesite.modules.uc.service.UcStudentService;

/**
 * 学生基本信息Controller
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/ucStudent")
public class UcStudentController extends BaseController {

	@Autowired
	private UcStudentService ucStudentService;
	
	@ModelAttribute
	public UcStudent get(@RequestParam(required=false) String id) {
		UcStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucStudentService.get(id);
		}
		if (entity == null){
			entity = new UcStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("group")
	public String group(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentGroup(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/studentGroup";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("sex")
	public String sex(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentSex(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/studentSex";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("region")
	public String region(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentRegion(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/studentRegion";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("edu")
	public String edu(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentEdu(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/studentEdu";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("major")
	public String major(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentMajor(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/studentMajor";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("department")
	public String department(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentDepartment(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/studentDepartment";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcStudent ucStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcStudent> page = ucStudentService.findPage(new Page<UcStudent>(request, response), ucStudent); 
		model.addAttribute("page", page);
		return "modules/uc/ucStudentList";
	}

	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping(value = "form")
	public String form(UcStudent ucStudent, Model model) {
		model.addAttribute("ucStudent", ucStudent);
		return "modules/uc/ucStudentForm";
	}

	@RequiresPermissions("uc:ucStudent:edit")
	@RequestMapping(value = "save")
	public String save(UcStudent ucStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucStudent)){
			return form(ucStudent, model);
		}
		ucStudentService.save(ucStudent);
		addMessage(redirectAttributes, "保存学生基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/ucStudent/?repage";
	}
	
	@RequiresPermissions("uc:ucStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(UcStudent ucStudent, RedirectAttributes redirectAttributes) {
		ucStudentService.delete(ucStudent);
		addMessage(redirectAttributes, "删除学生基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/ucStudent/?repage";
	}

}