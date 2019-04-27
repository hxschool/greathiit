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
import com.thinkgem.jeesite.modules.payment.entity.SysPayment;
import com.thinkgem.jeesite.modules.payment.service.SysPaymentService;

/**
 * 全局缴费配置Controller
 * @author 赵俊飞
 * @version 2019-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/payment/sysPayment")
public class SysPaymentController extends BaseController {

	@Autowired
	private SysPaymentService sysPaymentService;
	
	@ModelAttribute
	public SysPayment get(@RequestParam(required=false) String id) {
		SysPayment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysPaymentService.get(id);
		}
		if (entity == null){
			entity = new SysPayment();
		}
		return entity;
	}
	
	@RequiresPermissions("payment:sysPayment:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysPayment sysPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysPayment> page = sysPaymentService.findPage(new Page<SysPayment>(request, response), sysPayment); 
		model.addAttribute("page", page);
		return "modules/payment/sysPaymentList";
	}

	@RequiresPermissions("payment:sysPayment:view")
	@RequestMapping(value = "form")
	public String form(SysPayment sysPayment, Model model) {
		model.addAttribute("sysPayment", sysPayment);
		return "modules/payment/sysPaymentForm";
	}

	@RequiresPermissions("payment:sysPayment:edit")
	@RequestMapping(value = "save")
	public String save(SysPayment sysPayment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysPayment)){
			return form(sysPayment, model);
		}
		sysPaymentService.save(sysPayment);
		addMessage(redirectAttributes, "保存全局缴费配置成功");
		return "redirect:"+Global.getAdminPath()+"/payment/sysPayment/?repage";
	}
	
	@RequiresPermissions("payment:sysPayment:edit")
	@RequestMapping(value = "delete")
	public String delete(SysPayment sysPayment, RedirectAttributes redirectAttributes) {
		sysPaymentService.delete(sysPayment);
		addMessage(redirectAttributes, "删除全局缴费配置成功");
		return "redirect:"+Global.getAdminPath()+"/payment/sysPayment/?repage";
	}

}