/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.config.web;

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
import com.thinkgem.jeesite.modules.out.config.entity.RsStudentConfig;
import com.thinkgem.jeesite.modules.out.config.service.RsStudentConfigService;

/**
 * 系统配置Controller
 * @author 赵俊飞
 * @version 2019-01-31
 */
@Controller
@RequestMapping(value = "${adminPath}/out/config/rsStudentConfig")
public class RsStudentConfigController extends BaseController {

	@Autowired
	private RsStudentConfigService rsStudentConfigService;
	
	@ModelAttribute
	public RsStudentConfig get(@RequestParam(required=false) String id) {
		RsStudentConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsStudentConfigService.get(id);
		}
		if (entity == null){
			entity = new RsStudentConfig();
		}
		return entity;
	}
	

	@RequiresPermissions("out:config:rsStudentConfig:view")
	@RequestMapping(value = "form")
	public String form(RsStudentConfig rsStudentConfig, Model model) {
		model.addAttribute("rsStudentConfig", rsStudentConfig);
		return "modules/out/config/rsStudentConfigForm";
	}

	@RequiresPermissions("out:config:rsStudentConfig:edit")
	@RequestMapping(value = "save")
	public String save(RsStudentConfig rsStudentConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsStudentConfig)){
			return form(rsStudentConfig, model);
		}
		rsStudentConfigService.save(rsStudentConfig);
		addMessage(redirectAttributes, "保存系统配置成功");
		return "redirect:"+Global.getAdminPath()+"/out/config/rsStudentConfig/form?id=1&repage";
	}
	

}