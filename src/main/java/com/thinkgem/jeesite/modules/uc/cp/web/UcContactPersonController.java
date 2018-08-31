/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.cp.web;

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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.cp.entity.UcContactPerson;
import com.thinkgem.jeesite.modules.uc.cp.service.UcContactPersonService;

/**
 * 紧急联系人Controller
 * @author 赵俊飞
 * @version 2017-10-11
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/cp/ucContactPerson")
public class UcContactPersonController extends BaseController {

	@Autowired
	private UcContactPersonService ucContactPersonService;
	
	@ModelAttribute
	public UcContactPerson get(@RequestParam(required=false) String id) {
		UcContactPerson entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucContactPersonService.get(id);
		}
		if (entity == null){
			entity = new UcContactPerson();
		}
		return entity;
	}
	
	@RequiresPermissions("uc:cp:ucContactPerson:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcContactPerson ucContactPerson, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(user.getRoleIdList().size()==1) {
			String roleId = user.getRoleIdList().get(0);
			if(roleId.equals("99")) {
				ucContactPerson.setStudentNumber(user.getNo());
			}
		}
		Page<UcContactPerson> page = ucContactPersonService.findPage(new Page<UcContactPerson>(request, response), ucContactPerson); 
		model.addAttribute("page", page);
		return "modules/uc/cp/ucContactPersonList";
	}

	@RequiresPermissions("uc:cp:ucContactPerson:view")
	@RequestMapping(value = "form")
	public String form(UcContactPerson ucContactPerson, Model model) {
		model.addAttribute("ucContactPerson", ucContactPerson);
		return "modules/uc/cp/ucContactPersonForm";
	}

	@RequiresPermissions("uc:cp:ucContactPerson:edit")
	@RequestMapping(value = "save")
	public String save(UcContactPerson ucContactPerson, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucContactPerson)){
			return form(ucContactPerson, model);
		}
		User user = UserUtils.getUser();
		if(user.getRoleIdList().size()==1) {
			String roleId = user.getRoleIdList().get(0);
			if(roleId.equals("99")) {
				ucContactPerson.setStudentNumber(user.getNo());
			}
		}
		ucContactPersonService.save(ucContactPerson);
		addMessage(redirectAttributes, "保存紧急联系人成功");
		return "redirect:"+Global.getAdminPath()+"/uc/cp/ucContactPerson/?repage";
	}
	
	@RequiresPermissions("uc:cp:ucContactPerson:edit")
	@RequestMapping(value = "delete")
	public String delete(UcContactPerson ucContactPerson, RedirectAttributes redirectAttributes) {
		ucContactPersonService.delete(ucContactPerson);
		addMessage(redirectAttributes, "删除紧急联系人成功");
		return "redirect:"+Global.getAdminPath()+"/uc/cp/ucContactPerson/?repage";
	}

}