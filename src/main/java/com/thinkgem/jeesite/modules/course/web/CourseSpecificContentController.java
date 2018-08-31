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
import com.thinkgem.jeesite.modules.course.entity.CourseSpecificContent;
import com.thinkgem.jeesite.modules.course.service.CourseSpecificContentService;

/**
 * 课程具体内容Controller
 * @author 赵俊飞
 * @version 2017-12-24
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseSpecificContent")
public class CourseSpecificContentController extends BaseController {

	@Autowired
	private CourseSpecificContentService courseSpecificContentService;
	
	@ModelAttribute
	public CourseSpecificContent get(@RequestParam(required=false) String id) {
		CourseSpecificContent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseSpecificContentService.get(id);
		}
		if (entity == null){
			entity = new CourseSpecificContent();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseSpecificContent:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseSpecificContent courseSpecificContent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseSpecificContent> page = courseSpecificContentService.findPage(new Page<CourseSpecificContent>(request, response), courseSpecificContent); 
		model.addAttribute("page", page);
		return "modules/course/courseSpecificContentList";
	}

	@RequiresPermissions("course:courseSpecificContent:view")
	@RequestMapping(value = "form")
	public String form(CourseSpecificContent courseSpecificContent, Model model) {
		model.addAttribute("courseSpecificContent", courseSpecificContent);
		return "modules/course/courseSpecificContentForm";
	}

	@RequiresPermissions("course:courseSpecificContent:edit")
	@RequestMapping(value = "save")
	public String save(CourseSpecificContent courseSpecificContent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseSpecificContent)){
			return form(courseSpecificContent, model);
		}
		courseSpecificContentService.save(courseSpecificContent);
		addMessage(redirectAttributes, "保存课程具体内容成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseSpecificContent/?repage";
	}
	
	@RequiresPermissions("course:courseSpecificContent:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseSpecificContent courseSpecificContent, RedirectAttributes redirectAttributes) {
		courseSpecificContentService.delete(courseSpecificContent);
		addMessage(redirectAttributes, "删除课程具体内容成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseSpecificContent/?repage";
	}

}