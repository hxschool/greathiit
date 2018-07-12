/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.ems.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.out.ems.entity.RsStudentEms;
import com.thinkgem.jeesite.modules.out.ems.service.RsStudentEmsService;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;
import com.thinkgem.jeesite.modules.recruit.service.student.RecruitStudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 单招录取通知书Controller
 * @author 赵俊飞
 * @version 2018-04-27
 */
@Controller
@RequestMapping(value = "${adminPath}/out/ems/rsStudentEms")
public class RsStudentEmsController extends BaseController {

	@Autowired
	private RsStudentEmsService rsStudentEmsService;
	
	@ModelAttribute
	public RsStudentEms get(@RequestParam(required=false) String id) {
		RsStudentEms entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsStudentEmsService.get(id);
		}
		if (entity == null){
			entity = new RsStudentEms();
		}
		return entity;
	}
	
	@RequiresPermissions("out:ems:rsStudentEms:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsStudentEms rsStudentEms, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsStudentEms> page = rsStudentEmsService.findPage(new Page<RsStudentEms>(request, response), rsStudentEms); 
		model.addAttribute("page", page);
		return "modules/out/ems/rsStudentEmsList";
	}

	@RequiresPermissions("out:ems:rsStudentEms:view")
	@RequestMapping(value = "form")
	public String form(RsStudentEms rsStudentEms, Model model) {
		model.addAttribute("rsStudentEms", rsStudentEms);
		return "modules/out/ems/rsStudentEmsForm";
	}

	@RequiresPermissions("out:ems:rsStudentEms:edit")
	@RequestMapping(value = "save")
	public String save(RsStudentEms rsStudentEms, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsStudentEms)){
			return form(rsStudentEms, model);
		}
		rsStudentEmsService.save(rsStudentEms);
		addMessage(redirectAttributes, "保存单招录取通知书成功");
		return "redirect:"+Global.getAdminPath()+"/out/ems/rsStudentEms/?repage";
	}
	
	@RequiresPermissions("out:ems:rsStudentEms:edit")
	@RequestMapping(value = "delete")
	public String delete(RsStudentEms rsStudentEms, RedirectAttributes redirectAttributes) {
		rsStudentEmsService.delete(rsStudentEms);
		addMessage(redirectAttributes, "删除单招录取通知书成功");
		return "redirect:"+Global.getAdminPath()+"/out/ems/rsStudentEms/?repage";
	}
	
	
	@RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 0, 0);
			List<RsStudentEms> list = ei.getDataList(RsStudentEms.class);
			for (RsStudentEms rsStudentEms : list){
				try{
	
					RsStudentEms pojo = rsStudentEmsService.getByUsernameAndIdCard(rsStudentEms.getHcFormXm(), rsStudentEms.getHcFormSfzh());
					if (org.springframework.util.StringUtils.isEmpty(pojo)){
						rsStudentEmsService.save(rsStudentEms);
						successNum++;
					}else{
						failureMsg.append("<br/>身份证号: "+rsStudentEms.getHcFormSfzh()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>身份证号: "+rsStudentEms.getHcFormSfzh()+" 已存在; ");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>身份证号: "+rsStudentEms.getHcFormSfzh()+" 已存在; "+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/out/ems/rsStudentEms/list?repage";
    }

}