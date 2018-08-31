/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
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
import com.thinkgem.jeesite.modules.sys.entity.UserOperationLog;
import com.thinkgem.jeesite.modules.sys.service.UserOperationLogService;

/**
 * 用户操作日志Controller
 * @author 赵俊飞
 * @version 2018-08-24
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/userOperationLog")
public class UserOperationLogController extends BaseController {

	@Autowired
	private UserOperationLogService userOperationLogService;
	
	@ModelAttribute
	public UserOperationLog get(@RequestParam(required=false) String id) {
		UserOperationLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userOperationLogService.get(id);
		}
		if (entity == null){
			entity = new UserOperationLog();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:userOperationLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserOperationLog userOperationLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserOperationLog> page = userOperationLogService.findPage(new Page<UserOperationLog>(request, response), userOperationLog); 
		model.addAttribute("page", page);
		return "modules/sys/userOperationLogList";
	}

	@RequiresPermissions("sys:userOperationLog:view")
	@RequestMapping(value = "form")
	public String form(UserOperationLog userOperationLog, Model model) {
		model.addAttribute("userOperationLog", userOperationLog);
		return "modules/sys/userOperationLogForm";
	}

	@RequiresPermissions("sys:userOperationLog:edit")
	@RequestMapping(value = "save")
	public String save(UserOperationLog userOperationLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userOperationLog)){
			return form(userOperationLog, model);
		}
		userOperationLogService.save(userOperationLog);
		addMessage(redirectAttributes, "保存用户操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/sys/userOperationLog/?repage";
	}
	
	@RequiresPermissions("sys:userOperationLog:edit")
	@RequestMapping(value = "delete")
	public String delete(UserOperationLog userOperationLog, RedirectAttributes redirectAttributes) {
		userOperationLogService.delete(userOperationLog);
		addMessage(redirectAttributes, "删除用户操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/sys/userOperationLog/?repage";
	}

}