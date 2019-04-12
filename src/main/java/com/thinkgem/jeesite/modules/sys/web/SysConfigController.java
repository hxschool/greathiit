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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;

/**
 * 全局系统配置Controller
 * @author 赵俊飞
 * @version 2019-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysConfig")
public class SysConfigController extends BaseController {

	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@ModelAttribute
	public SysConfig get(@RequestParam(required=false) String id) {
		SysConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysConfigService.get(id);
		}
		if (entity == null){
			entity = new SysConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysConfig sysConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysConfig> page = sysConfigService.findPage(new Page<SysConfig>(request, response), sysConfig); 
		model.addAttribute("page", page);
		return "modules/sys/sysConfigList";
	}

	@RequiresPermissions("sys:sysConfig:view")
	@RequestMapping(value = "form")
	public String form(SysConfig sysConfig, Model model) {
		model.addAttribute("sysConfig", sysConfig);
		return "modules/sys/sysConfigForm";
	}

	@RequiresPermissions("sys:sysConfig:edit")
	@RequestMapping(value = "save")
	public String save(SysConfig sysConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysConfig)){
			return form(sysConfig, model);
		}
		sysConfigService.save(sysConfig);
		addMessage(redirectAttributes, "保存全局系统配置成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysConfig/?repage";
	}
	
	@RequiresPermissions("sys:sysConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(SysConfig sysConfig, RedirectAttributes redirectAttributes) {
		sysConfigService.delete(sysConfig);
		addMessage(redirectAttributes, "删除全局系统配置成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysConfig/?repage";
	}

	@RequiresPermissions("sys:sysConfig:edit")
	@RequestMapping(value = "schedule")
	public String schedule(SysConfig sysConfig,Model model) {
		SysConfig config = sysConfigService.getModule(Global.SYSCONFIG_COURSE);
		model.addAttribute("config", config);
		return "modules/sys/sysConfigSchedule";
	}
	@RequiresPermissions("sys:sysConfig:edit")
	@RequestMapping(value = "batch")
	public String batch(SysConfig sysConfig, RedirectAttributes redirectAttributes) {
		courseScheduleService.executeAsyncJsonAvailability(sysConfig);
		addMessage(redirectAttributes, "操作成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysConfig/schedule?repage";
	}
}