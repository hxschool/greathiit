/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

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
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingtarget;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingtargetService;

/**
 * 设置课程考试与教学目标支撑分值Controller
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseTeachingtarget")
public class CourseTeachingtargetController extends BaseController {

	@Autowired
	private CourseTeachingtargetService courseTeachingtargetService;
	
	@ModelAttribute
	public CourseTeachingtarget get(@RequestParam(required=false) String id) {
		CourseTeachingtarget entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseTeachingtargetService.get(id);
		}
		if (entity == null){
			entity = new CourseTeachingtarget();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseTeachingtarget:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseTeachingtarget courseTeachingtarget, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseTeachingtarget> page = courseTeachingtargetService.findPage(new Page<CourseTeachingtarget>(request, response), courseTeachingtarget); 
		model.addAttribute("page", page);
		return "modules/course/courseTeachingtargetList";
	}

	@RequiresPermissions("course:courseTeachingtarget:view")
	@RequestMapping(value = "form")
	public String form(CourseTeachingtarget courseTeachingtarget, Model model) {
		model.addAttribute("courseTeachingtarget", courseTeachingtarget);
		return "modules/course/courseTeachingtargetForm";
	}

	@RequiresPermissions("course:courseTeachingtarget:edit")
	@RequestMapping(value = "save")
	public String save(CourseTeachingtarget courseTeachingtarget, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseTeachingtarget)){
			return form(courseTeachingtarget, model);
		}
		courseTeachingtargetService.save(courseTeachingtarget);
		addMessage(redirectAttributes, "保存设置课程考试与教学目标支撑分值成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseTeachingtarget/?repage";
	}
	
	@RequiresPermissions("course:courseTeachingtarget:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseTeachingtarget courseTeachingtarget, RedirectAttributes redirectAttributes) {
		courseTeachingtargetService.delete(courseTeachingtarget);
		addMessage(redirectAttributes, "删除设置课程考试与教学目标支撑分值成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseTeachingtarget/?repage";
	}

}