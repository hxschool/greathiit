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
import com.thinkgem.jeesite.modules.out.jcd.entity.RsZsjh;
import com.thinkgem.jeesite.modules.out.jcd.service.RsZsjhService;

/**
 * 招生计划Controller
 * @author 赵俊飞
 * @version 2018-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/out/jcd/rsZsjh")
public class RsZsjhController extends BaseController {

	@Autowired
	private RsZsjhService rsZsjhService;
	
	@ModelAttribute
	public RsZsjh get(@RequestParam(required=false) String id) {
		RsZsjh entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsZsjhService.get(id);
		}
		if (entity == null){
			entity = new RsZsjh();
		}
		return entity;
	}
	
	@RequiresPermissions("out:jcd:rsZsjh:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsZsjh rsZsjh, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsZsjh> page = rsZsjhService.findPage(new Page<RsZsjh>(request, response), rsZsjh); 
		model.addAttribute("page", page);
		return "modules/out/jcd/rsZsjhList";
	}

	@RequiresPermissions("out:jcd:rsZsjh:view")
	@RequestMapping(value = "form")
	public String form(RsZsjh rsZsjh, Model model) {
		model.addAttribute("rsZsjh", rsZsjh);
		return "modules/out/jcd/rsZsjhForm";
	}

	@RequiresPermissions("out:jcd:rsZsjh:edit")
	@RequestMapping(value = "save")
	public String save(RsZsjh rsZsjh, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsZsjh)){
			return form(rsZsjh, model);
		}
		rsZsjhService.save(rsZsjh);
		addMessage(redirectAttributes, "保存招生计划成功");
		return "redirect:"+Global.getAdminPath()+"/out/jcd/rsZsjh/?repage";
	}
	
	@RequiresPermissions("out:jcd:rsZsjh:edit")
	@RequestMapping(value = "delete")
	public String delete(RsZsjh rsZsjh, RedirectAttributes redirectAttributes) {
		rsZsjhService.delete(rsZsjh);
		addMessage(redirectAttributes, "删除招生计划成功");
		return "redirect:"+Global.getAdminPath()+"/out/jcd/rsZsjh/?repage";
	}

}