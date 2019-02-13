/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;
import com.thinkgem.jeesite.modules.answering.admin.service.AsAnsweringService;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;

/**
 * 答辩抽签Controller
 * @author 赵俊飞
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/answering/admin/asAnswering")
public class AsAnsweringController extends BaseController {

	@Autowired
	private AsAnsweringService asAnsweringService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	
	@ModelAttribute
	public AsAnswering get(@RequestParam(required=false) String id) {
		AsAnswering entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = asAnsweringService.get(id);
		}
		if (entity == null){
			entity = new AsAnswering();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(AsAnswering asAnswering, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AsAnswering> page = asAnsweringService.findPage(new Page<AsAnswering>(request, response), asAnswering); 
		model.addAttribute("page", page);
		return "modules/answering/admin/asAnsweringList";
	}

	@RequestMapping(value = "form")
	public String form(AsAnswering asAnswering, Model model) {
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setScLock("0");
		courseSchedule.setTips("答辩");
		List<CourseSchedule> courseSchedules = courseScheduleService.findList(courseSchedule);
		model.addAttribute("asAnswering", asAnswering);
		model.addAttribute("courseSchedules", courseSchedules);
		return "modules/answering/admin/asAnsweringForm";
	}

	@RequestMapping(value = "save")
	public String save(AsAnswering asAnswering, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, asAnswering)){
			return form(asAnswering, model);
		}
		String timeAdd = asAnswering.getTimeAdd();
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(timeAdd);
		courseSchedule.setTips("答辩占用");
		courseScheduleService.save(courseSchedule);
		asAnsweringService.save(asAnswering);
		addMessage(redirectAttributes, "保存答辩抽签成功");
		return "redirect:"+Global.getAdminPath()+"/answering/admin/asAnswering/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(AsAnswering asAnswering, RedirectAttributes redirectAttributes) {
		asAnsweringService.delete(asAnswering);
		addMessage(redirectAttributes, "删除答辩抽签成功");
		return "redirect:"+Global.getAdminPath()+"/answering/admin/asAnswering/?repage";
	}

}