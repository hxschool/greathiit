/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.ec.web;

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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.ec.entity.UcEmergencyContact;
import com.thinkgem.jeesite.modules.uc.ec.service.UcEmergencyContactService;

/**
 * 社交通讯录Controller
 * @author 社交通讯录
 * @version 2017-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/ec/ucEmergencyContact")
public class UcEmergencyContactController extends BaseController {

	@Autowired
	private UcEmergencyContactService ucEmergencyContactService;
	
	@ModelAttribute
	public UcEmergencyContact get(@RequestParam(required=false) String id) {
		UcEmergencyContact entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucEmergencyContactService.get(id);
		}
		if (entity == null){
			entity = new UcEmergencyContact();
		}
		return entity;
	}
	
	@RequiresPermissions("uc:ec:ucEmergencyContact:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcEmergencyContact ucEmergencyContact, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(user.getRoleIdList().size()==1) {
			String roleId = user.getRoleIdList().get(0);
			if(roleId.equals("99")) {
				ucEmergencyContact.setStudentNumber(user.getNo());
			}
		}
		Page<UcEmergencyContact> page = ucEmergencyContactService.findPage(new Page<UcEmergencyContact>(request, response), ucEmergencyContact); 
		model.addAttribute("page", page);
		return "modules/uc/ec/ucEmergencyContactList";
	}

	@RequiresPermissions("uc:ec:ucEmergencyContact:view")
	@RequestMapping(value = "form")
	public String form(UcEmergencyContact ucEmergencyContact, Model model) {
		model.addAttribute("ucEmergencyContact", ucEmergencyContact);
		return "modules/uc/ec/ucEmergencyContactForm";
	}

	@RequiresPermissions("uc:ec:ucEmergencyContact:edit")
	@RequestMapping(value = "save")
	public String save(UcEmergencyContact ucEmergencyContact, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucEmergencyContact)){
			return form(ucEmergencyContact, model);
		}
		User user = UserUtils.getUser();
		if(user.getRoleIdList().size()==1) {
			String roleId = user.getRoleIdList().get(0);
			if(roleId.equals("99")) {
				ucEmergencyContact.setStudentNumber(user.getNo());
			}
		}
		ucEmergencyContactService.save(ucEmergencyContact);
		addMessage(redirectAttributes, "保存社交通讯录成功");
		return "redirect:"+Global.getAdminPath()+"/uc/ec/ucEmergencyContact/?repage";
	}
	
	@RequiresPermissions("uc:ec:ucEmergencyContact:edit")
	@RequestMapping(value = "delete")
	public String delete(UcEmergencyContact ucEmergencyContact, RedirectAttributes redirectAttributes) {
		ucEmergencyContactService.delete(ucEmergencyContact);
		addMessage(redirectAttributes, "删除社交通讯录成功");
		return "redirect:"+Global.getAdminPath()+"/uc/ec/ucEmergencyContact/?repage";
	}

}