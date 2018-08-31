/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

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
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;
import com.thinkgem.jeesite.modules.sys.service.SysAppconfigService;

/**
 * 系统秘钥Controller
 * @author 赵俊飞
 * @version 2017-12-28
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysAppconfig")
public class SysAppconfigController extends BaseController {

	@Autowired
	private SysAppconfigService sysAppconfigService;
	
	@ModelAttribute
	public SysAppconfig get(@RequestParam(required=false) String id) {
		SysAppconfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysAppconfigService.get(id);
		}
		if (entity == null){
			entity = new SysAppconfig();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysAppconfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysAppconfig sysAppconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysAppconfig> page = sysAppconfigService.findPage(new Page<SysAppconfig>(request, response), sysAppconfig); 
		model.addAttribute("page", page);
		return "modules/sys/sysAppconfigList";
	}

	@RequiresPermissions("sys:sysAppconfig:view")
	@RequestMapping(value = "form")
	public String form(SysAppconfig sysAppconfig, Model model) {
		model.addAttribute("sysAppconfig", sysAppconfig);
		return "modules/sys/sysAppconfigForm";
	}

	@RequiresPermissions("sys:sysAppconfig:edit")
	@RequestMapping(value = "save")
	public String save(SysAppconfig sysAppconfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAppconfig)){
			return form(sysAppconfig, model);
		}
		sysAppconfigService.save(sysAppconfig);
		addMessage(redirectAttributes, "保存系统秘钥成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysAppconfig/?repage";
	}
	
	@RequiresPermissions("sys:sysAppconfig:edit")
	@RequestMapping(value = "delete")
	public String delete(SysAppconfig sysAppconfig, RedirectAttributes redirectAttributes) {
		sysAppconfigService.delete(sysAppconfig);
		addMessage(redirectAttributes, "删除系统秘钥成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysAppconfig/?repage";
	}

}