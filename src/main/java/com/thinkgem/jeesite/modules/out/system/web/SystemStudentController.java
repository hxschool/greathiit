/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.system.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.out.system.entity.SystemStudent;
import com.thinkgem.jeesite.modules.out.system.service.SystemStudentService;

/**
 * 单招报名申请表Controller
 * @author 赵俊飞
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/out/system/systemStudent")
public class SystemStudentController extends BaseController {

	@Autowired
	private SystemStudentService systemStudentService;
	
	@ModelAttribute
	public SystemStudent get(@RequestParam(required=false) String id) {
		SystemStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = systemStudentService.get(id);
		}
		if (entity == null){
			entity = new SystemStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("out:system:systemStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(SystemStudent systemStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SystemStudent> page = systemStudentService.findPage(new Page<SystemStudent>(request, response), systemStudent); 
		model.addAttribute("page", page);
		return "modules/out/system/systemStudentList";
	}

	@RequiresPermissions("out:system:systemStudent:view")
	@RequestMapping(value = "form")
	public String form(SystemStudent systemStudent, Model model) {
		model.addAttribute("systemStudent", systemStudent);
		return "modules/out/system/systemStudentForm";
	}

	@RequiresPermissions("out:system:systemStudent:edit")
	@RequestMapping(value = "save")
	public String save(SystemStudent systemStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, systemStudent)){
			return form(systemStudent, model);
		}
		systemStudentService.save(systemStudent);
		addMessage(redirectAttributes, "保存单招报名申请表成功");
		return "redirect:"+Global.getAdminPath()+"/out/system/systemStudent/?repage";
	}
	
	@RequiresPermissions("out:system:systemStudent:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(SystemStudent systemStudent, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "单考单招数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
           Page<SystemStudent> page = systemStudentService.findPage(new Page<SystemStudent>(request, response, -1), systemStudent);
    		new ExportExcel("单考单招数据", SystemStudent.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/system/systemStudent/list?repage";
    }
	
	@RequiresPermissions("out:system:systemStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(SystemStudent systemStudent, RedirectAttributes redirectAttributes) {
		systemStudentService.delete(systemStudent);
		addMessage(redirectAttributes, "删除单招报名申请表成功");
		return "redirect:"+Global.getAdminPath()+"/out/system/systemStudent/?repage";
	}

	
	
	
	@RequestMapping("tj")
	public String major(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = systemStudentService.tj();
				
		model.addAttribute("list", list);
		return "modules/out/system/tj";
	}
	
	@RequestMapping("kl")
	public String kl(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = systemStudentService.kl();
		model.addAttribute("list", list);
		return "modules/out/system/kl";
	}
	
	@RequestMapping("lb")
	public String lb(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = systemStudentService.lb();
		model.addAttribute("list", list);
		return "modules/out/system/lb";
	}
	
	@RequestMapping("xb")
	public String xb(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = systemStudentService.xb();
		model.addAttribute("list", list);
		return "modules/out/system/xb";
	}
	
	@RequestMapping("zy")
	public String zy(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = systemStudentService.zy();
		model.addAttribute("list", list);
		return "modules/out/system/zy";
	}
	
	
	
}