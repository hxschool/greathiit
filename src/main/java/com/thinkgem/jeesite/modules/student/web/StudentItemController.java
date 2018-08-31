/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

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
import com.thinkgem.jeesite.modules.student.entity.StudentItem;
import com.thinkgem.jeesite.modules.student.service.StudentItemService;

/**
 * 获奖信息Controller
 * @author 赵俊飞
 * @version 2017-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/student/studentItem")
public class StudentItemController extends BaseController {

	@Autowired
	private StudentItemService studentItemService;
	
	@ModelAttribute
	public StudentItem get(@RequestParam(required=false) String id) {
		StudentItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentItemService.get(id);
		}
		if (entity == null){
			entity = new StudentItem();
		}
		return entity;
	}
	
	@RequiresPermissions("student:studentItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentItem studentItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentItem> page = studentItemService.findPage(new Page<StudentItem>(request, response), studentItem); 
		model.addAttribute("page", page);
		return "modules/student/studentItemList";
	}

	@RequiresPermissions("student:studentItem:view")
	@RequestMapping(value = "form")
	public String form(StudentItem studentItem, Model model) {
		model.addAttribute("studentItem", studentItem);
		return "modules/student/studentItemForm";
	}

	@RequiresPermissions("student:studentItem:edit")
	@RequestMapping(value = "save")
	public String save(StudentItem studentItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentItem)){
			return form(studentItem, model);
		}
		studentItemService.save(studentItem);
		addMessage(redirectAttributes, "保存获奖信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentItem/?repage";
	}
	
	@RequiresPermissions("student:studentItem:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentItem studentItem, RedirectAttributes redirectAttributes) {
		studentItemService.delete(studentItem);
		addMessage(redirectAttributes, "删除获奖信息成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentItem/?repage";
	}

}