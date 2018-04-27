/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.ems.web;

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
import com.thinkgem.jeesite.modules.out.ems.entity.RsStudentEms;
import com.thinkgem.jeesite.modules.out.ems.service.RsStudentEmsService;

/**
 * 单招录取通知书Controller
 * @author 赵俊飞
 * @version 2018-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/out/ems/rsStudentEms")
public class RsStudentEmsController extends BaseController {

	@Autowired
	private RsStudentEmsService rsStudentEmsService;
	
	@ModelAttribute
	public RsStudentEms get(@RequestParam(required=false) String id) {
		RsStudentEms entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsStudentEmsService.get(id);
		}
		if (entity == null){
			entity = new RsStudentEms();
		}
		return entity;
	}
	
	@RequiresPermissions("out:ems:rsStudentEms:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsStudentEms rsStudentEms, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsStudentEms> page = rsStudentEmsService.findPage(new Page<RsStudentEms>(request, response), rsStudentEms); 
		model.addAttribute("page", page);
		return "modules/out/ems/rsStudentEmsList";
	}

	@RequiresPermissions("out:ems:rsStudentEms:view")
	@RequestMapping(value = "form")
	public String form(RsStudentEms rsStudentEms, Model model) {
		model.addAttribute("rsStudentEms", rsStudentEms);
		return "modules/out/ems/rsStudentEmsForm";
	}

	@RequiresPermissions("out:ems:rsStudentEms:edit")
	@RequestMapping(value = "save")
	public String save(RsStudentEms rsStudentEms, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsStudentEms)){
			return form(rsStudentEms, model);
		}
		rsStudentEmsService.save(rsStudentEms);
		addMessage(redirectAttributes, "保存单招录取通知书成功");
		return "redirect:"+Global.getAdminPath()+"/out/ems/rsStudentEms/?repage";
	}
	
	@RequiresPermissions("out:ems:rsStudentEms:edit")
	@RequestMapping(value = "delete")
	public String delete(RsStudentEms rsStudentEms, RedirectAttributes redirectAttributes) {
		rsStudentEmsService.delete(rsStudentEms);
		addMessage(redirectAttributes, "删除单招录取通知书成功");
		return "redirect:"+Global.getAdminPath()+"/out/ems/rsStudentEms/?repage";
	}

}