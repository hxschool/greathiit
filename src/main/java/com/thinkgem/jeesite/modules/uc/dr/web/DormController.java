/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.dr.web;

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
import com.thinkgem.jeesite.modules.uc.dr.entity.Dorm;
import com.thinkgem.jeesite.modules.uc.dr.service.DormService;

/**
 * 寝室信息Controller
 * @author 赵俊飞
 * @version 2017-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/dr/dorm")
public class DormController extends BaseController {

	@Autowired
	private DormService dormService;
	
	@ModelAttribute
	public Dorm get(@RequestParam(required=false) String id) {
		Dorm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dormService.get(id);
		}
		if (entity == null){
			entity = new Dorm();
		}
		return entity;
	}
	
	@RequiresPermissions("uc:dr:dorm:view")
	@RequestMapping(value = {"list", ""})
	public String list(Dorm dorm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Dorm> page = dormService.findPage(new Page<Dorm>(request, response), dorm); 
		model.addAttribute("page", page);
		return "modules/uc/dr/dormList";
	}

	@RequiresPermissions("uc:dr:dorm:view")
	@RequestMapping(value = "form")
	public String form(Dorm dorm, Model model) {
		model.addAttribute("dorm", dorm);
		return "modules/uc/dr/dormForm";
	}

	@RequiresPermissions("uc:dr:dorm:edit")
	@RequestMapping(value = "save")
	public String save(Dorm dorm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dorm)){
			return form(dorm, model);
		}
		dormService.save(dorm);
		addMessage(redirectAttributes, "保存寝室信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/dr/dorm/?repage";
	}
	
	@RequiresPermissions("uc:dr:dorm:edit")
	@RequestMapping(value = "delete")
	public String delete(Dorm dorm, RedirectAttributes redirectAttributes) {
		dormService.delete(dorm);
		addMessage(redirectAttributes, "删除寝室信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/dr/dorm/?repage";
	}

}