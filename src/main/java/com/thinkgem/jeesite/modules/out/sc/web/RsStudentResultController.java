/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.sc.web;

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
import com.thinkgem.jeesite.modules.out.sc.entity.RsStudentResult;
import com.thinkgem.jeesite.modules.out.sc.service.RsStudentResultService;

/**
 * 省成绩Controller
 * @author 赵俊飞
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/out/sc/rsStudentResult")
public class RsStudentResultController extends BaseController {

	@Autowired
	private RsStudentResultService rsStudentResultService;
	
	@ModelAttribute
	public RsStudentResult get(@RequestParam(required=false) String id) {
		RsStudentResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsStudentResultService.get(id);
		}
		if (entity == null){
			entity = new RsStudentResult();
		}
		return entity;
	}
	
	@RequiresPermissions("out:sc:rsStudentResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsStudentResult rsStudentResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsStudentResult> page = rsStudentResultService.findPage(new Page<RsStudentResult>(request, response), rsStudentResult); 
		model.addAttribute("page", page);
		return "modules/out/sc/rsStudentResultList";
	}

	@RequiresPermissions("out:sc:rsStudentResult:view")
	@RequestMapping(value = "form")
	public String form(RsStudentResult rsStudentResult, Model model) {
		model.addAttribute("rsStudentResult", rsStudentResult);
		return "modules/out/sc/rsStudentResultForm";
	}

	@RequiresPermissions("out:sc:rsStudentResult:edit")
	@RequestMapping(value = "save")
	public String save(RsStudentResult rsStudentResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsStudentResult)){
			return form(rsStudentResult, model);
		}
		rsStudentResultService.save(rsStudentResult);
		addMessage(redirectAttributes, "保存省成绩成功");
		return "redirect:"+Global.getAdminPath()+"/out/sc/rsStudentResult/?repage";
	}
	
	@RequiresPermissions("out:sc:rsStudentResult:edit")
	@RequestMapping(value = "delete")
	public String delete(RsStudentResult rsStudentResult, RedirectAttributes redirectAttributes) {
		rsStudentResultService.delete(rsStudentResult);
		addMessage(redirectAttributes, "删除省成绩成功");
		return "redirect:"+Global.getAdminPath()+"/out/sc/rsStudentResult/?repage";
	}

}