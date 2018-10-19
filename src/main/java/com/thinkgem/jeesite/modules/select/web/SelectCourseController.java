/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.select.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;

/**
 * 选课信息表Controller
 * @author 赵俊飞
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/select/selectCourse")
public class SelectCourseController extends BaseController {

	@Autowired
	private SelectCourseService selectCourseService;
	
	@ModelAttribute
	public SelectCourse get(@RequestParam(required=false) String id) {
		SelectCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = selectCourseService.get(id);
		}
		if (entity == null){
			entity = new SelectCourse();
		}
		return entity;
	}
	
	@RequiresPermissions("select:selectCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(SelectCourse selectCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SelectCourse> page = selectCourseService.findPage(new Page<SelectCourse>(request, response), selectCourse); 
		model.addAttribute("page", page);
		return "modules/select/selectCourseList";
	}

	@RequiresPermissions("select:selectCourse:view")
	@RequestMapping(value = "form")
	public String form(SelectCourse selectCourse, Model model) {
		model.addAttribute("selectCourse", selectCourse);
		return "modules/select/selectCourseForm";
	}

	@RequiresPermissions("select:selectCourse:edit")
	@RequestMapping(value = "save")
	public String save(SelectCourse selectCourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, selectCourse)){
			return form(selectCourse, model);
		}
		selectCourseService.save(selectCourse);
		addMessage(redirectAttributes, "保存选课信息表成功");
		return "redirect:"+Global.getAdminPath()+"/select/selectCourse/?repage";
	}
	
	@RequiresPermissions("select:selectCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(SelectCourse selectCourse, RedirectAttributes redirectAttributes) {
		selectCourseService.delete(selectCourse);
		addMessage(redirectAttributes, "删除选课信息表成功");
		return "redirect:"+Global.getAdminPath()+"/select/selectCourse/?repage";
	}

	@RequestMapping(value = "selectCourse.json")
	@ResponseBody
	public List<Map<String,Object>> totalSelectCourse() {
		return selectCourseService.totalSelectCourse();
	}
	
}