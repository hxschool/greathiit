/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.web.admin;

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
import com.thinkgem.jeesite.modules.payment.entity.SysPaymentType;
import com.thinkgem.jeesite.modules.payment.service.SysPaymentTypeService;

/**
 * 全局缴费类型配置Controller
 * @author 赵俊飞
 * @version 2019-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/payment/sysPaymentType")
public class SysPaymentTypeController extends BaseController {

	@Autowired
	private SysPaymentTypeService sysPaymentTypeService;
	
	@ModelAttribute
	public SysPaymentType get(@RequestParam(required=false) String id) {
		SysPaymentType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysPaymentTypeService.get(id);
		}
		if (entity == null){
			entity = new SysPaymentType();
		}
		return entity;
	}
	
	@RequiresPermissions("payment:sysPaymentType:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysPaymentType sysPaymentType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysPaymentType> page = sysPaymentTypeService.findPage(new Page<SysPaymentType>(request, response), sysPaymentType); 
		model.addAttribute("page", page);
		return "modules/payment/sysPaymentTypeList";
	}

	@RequiresPermissions("payment:sysPaymentType:view")
	@RequestMapping(value = "form")
	public String form(SysPaymentType sysPaymentType, Model model) {
		model.addAttribute("sysPaymentType", sysPaymentType);
		return "modules/payment/sysPaymentTypeForm";
	}

	@RequiresPermissions("payment:sysPaymentType:edit")
	@RequestMapping(value = "save")
	public String save(SysPaymentType sysPaymentType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysPaymentType)){
			return form(sysPaymentType, model);
		}
		sysPaymentTypeService.save(sysPaymentType);
		addMessage(redirectAttributes, "保存全局缴费类型配置成功");
		return "redirect:"+Global.getAdminPath()+"/payment/sysPaymentType/?repage";
	}
	
	@RequiresPermissions("payment:sysPaymentType:edit")
	@RequestMapping(value = "delete")
	public String delete(SysPaymentType sysPaymentType, RedirectAttributes redirectAttributes) {
		sysPaymentTypeService.delete(sysPaymentType);
		addMessage(redirectAttributes, "删除全局缴费类型配置成功");
		return "redirect:"+Global.getAdminPath()+"/payment/sysPaymentType/?repage";
	}

}