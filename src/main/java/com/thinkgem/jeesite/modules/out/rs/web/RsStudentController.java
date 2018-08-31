/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.rs.web;

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
import com.thinkgem.jeesite.modules.out.rs.entity.RsStudent;
import com.thinkgem.jeesite.modules.out.rs.service.RsStudentService;

/**
 * 单招报名申请表Controller
 * @author qq773152
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/out/rs/rsStudent")
public class RsStudentController extends BaseController {

	@Autowired
	private RsStudentService rsStudentService;
	
	@ModelAttribute
	public RsStudent get(@RequestParam(required=false) String id) {
		RsStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsStudentService.get(id);
		}
		if (entity == null){
			entity = new RsStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("out:rs:rsStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsStudent rsStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsStudent> page = rsStudentService.findPage(new Page<RsStudent>(request, response), rsStudent); 
		model.addAttribute("page", page);
		return "modules/out/rs/rsStudentList";
	}

	@RequiresPermissions("out:rs:rsStudent:view")
	@RequestMapping(value = "form")
	public String form(RsStudent rsStudent, Model model) {
		model.addAttribute("rsStudent", rsStudent);
		return "modules/out/rs/rsStudentForm";
	}

	@RequiresPermissions("out:rs:rsStudent:edit")
	@RequestMapping(value = "save")
	public String save(RsStudent rsStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsStudent)){
			return form(rsStudent, model);
		}
		rsStudentService.save(rsStudent);
		addMessage(redirectAttributes, "保存单招报名成功");
		return "redirect:"+Global.getAdminPath()+"/out/rs/rsStudent/?repage";
	}
	
	@RequiresPermissions("out:rs:rsStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(RsStudent rsStudent, RedirectAttributes redirectAttributes) {
		rsStudentService.delete(rsStudent);
		addMessage(redirectAttributes, "删除单招报名成功");
		return "redirect:"+Global.getAdminPath()+"/out/rs/rsStudent/?repage";
	}

}