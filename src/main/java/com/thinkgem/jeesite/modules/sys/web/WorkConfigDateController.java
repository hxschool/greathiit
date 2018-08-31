/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.WorkConfigDate;
import com.thinkgem.jeesite.modules.sys.service.WorkConfigDateService;

/**
 * 考勤信息Controller
 * @author 赵俊飞
 * @version 2018-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/workConfigDate")
public class WorkConfigDateController extends BaseController {

	@Autowired
	private WorkConfigDateService workConfigDateService;
	
	@ModelAttribute
	public WorkConfigDate get(@RequestParam(required=false) String id) {
		WorkConfigDate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = workConfigDateService.get(id);
		}
		if (entity == null){
			entity = new WorkConfigDate();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:workConfigDate:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkConfigDate workConfigDate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WorkConfigDate> page = workConfigDateService.findPage(new Page<WorkConfigDate>(request, response), workConfigDate); 
		model.addAttribute("page", page);
		return "modules/sys/workConfigDateList";
	}
	//@RequiresPermissions("sys:workConfigDate:view")
	@RequestMapping(value = "conf",method=RequestMethod.POST)
	public String saveConf(String startDate,String endDate, Model model) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date start = sdf.parse(startDate);//开始时间
	        java.util.Date end = sdf.parse(endDate);//结束时间
      
			List<Date> lists = DateUtils.dateSplit(start, end);
			for(Date date:lists) {
				String configDate = DateUtils.formatDate(date);
				WorkConfigDate workConfigDate = new WorkConfigDate();
				workConfigDate.setId(configDate);
				workConfigDate.setConfigDate(configDate);
				workConfigDate.setIsNewRecord(true);
				workConfigDateService.save(workConfigDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "modules/sys/workConfigDateForm";
	}
	//@RequiresPermissions("sys:workConfigDate:view")
	@RequestMapping(value = "conf",method=RequestMethod.GET)
	public String conf(String startDate,String endDate, Model model) {
		
		return "modules/sys/workConfigDateConf";
	}

	@RequiresPermissions("sys:workConfigDate:view")
	@RequestMapping(value = "form")
	public String form(WorkConfigDate workConfigDate, Model model) {
		model.addAttribute("workConfigDate", workConfigDate);
		return "modules/sys/workConfigDateForm";
	}

	@RequiresPermissions("sys:workConfigDate:edit")
	@RequestMapping(value = "save")
	public String save(WorkConfigDate workConfigDate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workConfigDate)){
			return form(workConfigDate, model);
		}
		workConfigDateService.save(workConfigDate);
		addMessage(redirectAttributes, "保存考勤信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/workConfigDate/?repage";
	}
	
	@RequiresPermissions("sys:workConfigDate:edit")
	@RequestMapping(value = "delete")
	public String delete(WorkConfigDate workConfigDate, RedirectAttributes redirectAttributes) {
		workConfigDateService.delete(workConfigDate);
		addMessage(redirectAttributes, "删除考勤信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/workConfigDate/?repage";
	}

}