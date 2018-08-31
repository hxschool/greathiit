/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.visitor.web;

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
import com.thinkgem.jeesite.modules.visitor.entity.TmVisitor;
import com.thinkgem.jeesite.modules.visitor.service.TmVisitorService;

/**
 * 访客信息Controller
 * @author 赵俊飞
 * @version 2017-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/visitor/tmVisitor")
public class TmVisitorController extends BaseController {

	@Autowired
	private TmVisitorService tmVisitorService;
	
	@ModelAttribute
	public TmVisitor get(@RequestParam(required=false) String id) {
		TmVisitor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmVisitorService.get(id);
		}
		if (entity == null){
			entity = new TmVisitor();
		}
		return entity;
	}
	
	@RequiresPermissions("visitor:tmVisitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmVisitor tmVisitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmVisitor> page = tmVisitorService.findPage(new Page<TmVisitor>(request, response), tmVisitor); 
		model.addAttribute("page", page);
		return "modules/visitor/tmVisitorList";
	}

	@RequiresPermissions("visitor:tmVisitor:view")
	@RequestMapping(value = "form")
	public String form(TmVisitor tmVisitor, Model model) {
		model.addAttribute("tmVisitor", tmVisitor);
		return "modules/visitor/tmVisitorForm";
	}

	@RequiresPermissions("visitor:tmVisitor:edit")
	@RequestMapping(value = "save")
	public String save(TmVisitor tmVisitor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmVisitor)){
			return form(tmVisitor, model);
		}
		tmVisitorService.save(tmVisitor);
		addMessage(redirectAttributes, "保存访客信息成功");
		return "redirect:"+Global.getAdminPath()+"/visitor/tmVisitor/?repage";
	}
	
	@RequiresPermissions("visitor:tmVisitor:edit")
	@RequestMapping(value = "delete")
	public String delete(TmVisitor tmVisitor, RedirectAttributes redirectAttributes) {
		tmVisitorService.delete(tmVisitor);
		addMessage(redirectAttributes, "删除访客信息成功");
		return "redirect:"+Global.getAdminPath()+"/visitor/tmVisitor/?repage";
	}

}