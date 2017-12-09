/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.web;

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
import com.thinkgem.jeesite.modules.out.jcd.entity.RsJcd;
import com.thinkgem.jeesite.modules.out.jcd.service.RsJcdService;

/**
 * 考试成绩单Controller
 * @author 赵俊飞
 * @version 2017-12-09
 */
@Controller
@RequestMapping(value = "${adminPath}/out/jcd/rsJcd")
public class RsJcdController extends BaseController {

	@Autowired
	private RsJcdService rsJcdService;
	
	@ModelAttribute
	public RsJcd get(@RequestParam(required=false) String id) {
		RsJcd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsJcdService.get(id);
		}
		if (entity == null){
			entity = new RsJcd();
		}
		return entity;
	}
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsJcd rsJcd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/rsJcdList";
	}

	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping(value = "form")
	public String form(RsJcd rsJcd, Model model) {
		model.addAttribute("rsJcd", rsJcd);
		return "modules/out/jcd/rsJcdForm";
	}

	@RequiresPermissions("out:jcd:rsJcd:edit")
	@RequestMapping(value = "save")
	public String save(RsJcd rsJcd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsJcd)){
			return form(rsJcd, model);
		}
		rsJcdService.save(rsJcd);
		addMessage(redirectAttributes, "保存考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/jcd/rsJcd/?repage";
	}
	
	@RequiresPermissions("out:jcd:rsJcd:edit")
	@RequestMapping(value = "delete")
	public String delete(RsJcd rsJcd, RedirectAttributes redirectAttributes) {
		rsJcdService.delete(rsJcd);
		addMessage(redirectAttributes, "删除考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/jcd/rsJcd/?repage";
	}

}