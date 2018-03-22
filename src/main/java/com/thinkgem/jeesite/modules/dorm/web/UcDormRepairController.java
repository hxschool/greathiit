/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

import java.util.UUID;

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
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormRepair;
import com.thinkgem.jeesite.modules.dorm.service.UcDormRepairService;
import com.thinkgem.jeesite.modules.dorm.service.UcDormService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 报修管理Controller
 * @author 赵俊飞
 * @version 2018-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/dorm/ucDormRepair")
public class UcDormRepairController extends BaseController {

	@Autowired
	private UcDormRepairService ucDormRepairService;
	@Autowired
	private UcDormService ucDormService;
	@ModelAttribute
	public UcDormRepair get(@RequestParam(required=false) String id) {
		UcDormRepair entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucDormRepairService.get(id);
		}
		if (entity == null){
			entity = new UcDormRepair();
		}
		return entity;
	}
	
	@RequiresPermissions("dorm:ucDormRepair:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcDormRepair ucDormRepair, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcDormRepair> page = ucDormRepairService.findPage(new Page<UcDormRepair>(request, response), ucDormRepair); 
		model.addAttribute("page", page);
		return "modules/dorm/ucDormRepairList";
	}

	@RequiresPermissions("dorm:ucDormRepair:view")
	@RequestMapping(value = "form")
	public String form(UcDormRepair ucDormRepair, Model model) {
		model.addAttribute("ucDormRepair", ucDormRepair);
		return "modules/dorm/ucDormRepairForm";
	}

	@RequiresPermissions("dorm:ucDormRepair:edit")
	@RequestMapping(value = "save")
	public String save(UcDormRepair ucDormRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucDormRepair)){
			return form(ucDormRepair, model);
		}
		if(org.springframework.util.StringUtils.isEmpty(ucDormRepair.getUser())) {
			User user = UserUtils.getUser();
			ucDormRepair.setUser(user);
			UcDorm dorm = user.getDorm();
			if(!org.springframework.util.StringUtils.isEmpty(dorm)) {
				ucDormRepair.setDorm(ucDormService.get(dorm));
			}
		}
		
		if(org.springframework.util.StringUtils.isEmpty(ucDormRepair.getRepairState())) {
			ucDormRepair.setRepairState("1");
		}
		
		logger.info("新建维修单,维修信息:{}",ucDormRepair);
		ucDormRepairService.save(ucDormRepair);
		addMessage(redirectAttributes, "保存报修管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDormRepair/?repage";
	}
	
	@RequestMapping(value = "jiedan")
	public String jiedan(UcDormRepair ucDormRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucDormRepair)){
			return form(ucDormRepair, model);
		}
		
		User operation = UserUtils.getUser();
		ucDormRepair.setOperationId(operation.getId());
		
		logger.info("新建维修单,维修信息:{}",ucDormRepair);
		ucDormRepairService.save(ucDormRepair);
		addMessage(redirectAttributes, "保存报修管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDormRepair/?repage";
	}
	
	@RequiresPermissions("dorm:ucDormRepair:edit")
	@RequestMapping(value = "delete")
	public String delete(UcDormRepair ucDormRepair, RedirectAttributes redirectAttributes) {
		ucDormRepairService.delete(ucDormRepair);
		addMessage(redirectAttributes, "删除报修管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDormRepair/?repage";
	}

	public static void main(String[] args) {
		for(int i=0;i<12;i++) {
			System.out.println(UUID.randomUUID().toString().replace("-", ""));
		}
		
	}
}