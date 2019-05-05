/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.score.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.out.score.entity.RsEnrollmentPlan;
import com.thinkgem.jeesite.modules.out.score.entity.RsScoreBill;
import com.thinkgem.jeesite.modules.out.score.service.RsEnrollmentPlanService;
import com.thinkgem.jeesite.modules.out.score.service.RsScoreBillService;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;

/**
 * 考试成绩单Controller
 * @author 赵俊飞
 * @version 2017-12-09
 */
@Controller
@RequestMapping(value = "${adminPath}/out/score/bill")
public class RsScoreBillController extends BaseController {

	@Autowired
	private RsScoreBillService rsScoreBillService;
	@Autowired
	private RsEnrollmentPlanService rsEnrollmentPlanService;
	@Autowired
	private SysConfigService sysConfigService;
	private SysConfig config;
	@ModelAttribute
	public RsScoreBill get(@RequestParam(required=false) String id,Model model) {
		RsScoreBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsScoreBillService.get(id);
		}
		if (entity == null){
			entity = new RsScoreBill();
		}
		config = sysConfigService.getModule(Global.SYSCONFIG_WELCOME);
		model.addAttribute("config", config);
		return entity;
	}
	
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<RsScoreBill> list = ei.getDataList(RsScoreBill.class);
			
			List<RsScoreBill> sortList = new ArrayList<RsScoreBill>();
			
			for (RsScoreBill scoreBill : list){
				try{
					if(org.springframework.util.StringUtils.isEmpty(scoreBill.getXm())||org.springframework.util.StringUtils.isEmpty(scoreBill.getSfzh())) {
						continue;
					}
					RsScoreBill entity = rsScoreBillService.getByKsh(scoreBill.getKsh());
					if(!org.springframework.util.StringUtils.isEmpty(entity)) {
						failureMsg.append("<br/>考试号: "+scoreBill.getKsh()+" 姓名:" + scoreBill.getXm() + "已存在.");
						continue;
					}
					scoreBill.setTermYear(config.getTermYear());
					scoreBill.setStatus("0");
					rsScoreBillService.save(scoreBill);
					sortList.add(scoreBill);
					successNum++;
				}catch(ConstraintViolationException ex){
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>考试号: "+scoreBill.getKsh()+" 导入失败："+ex.getMessage());
				}
			}
			
			Collections.sort(sortList);
			Double grade = Double.valueOf(config.getValue());
			for(RsScoreBill scoreBill:sortList) {
				if(org.springframework.util.StringUtils.isEmpty(scoreBill.getZf())) {
					scoreBill.setStatus("5");
					rsScoreBillService.save(scoreBill);
					continue;
				}
				if (Double.valueOf(scoreBill.getZf()) == 0 || Double.valueOf(scoreBill.getZf()) < grade) {
					scoreBill.setStatus("4");
					rsScoreBillService.save(scoreBill);
					continue;
				}
				Double cj = Double.valueOf(scoreBill.getZf());
				if(cj>=grade) {
					if(scoreBillHandler(scoreBill,1)) {
						continue;
					}
				}else {
					scoreBill.setStatus("4");
				}
				rsScoreBillService.save(scoreBill);
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条考生信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条考生信息"+failureMsg);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/score/bill/im?repage";
	}
	
	private boolean scoreBillHandler(RsScoreBill scoreBill,int index) {
		String zy = (String) Reflections.getFieldValue(scoreBill, "zy".concat(String.valueOf(index)));
		RsEnrollmentPlan rsp = new RsEnrollmentPlan();
		rsp.setMajorName(zy);
		RsEnrollmentPlan ep = rsEnrollmentPlanService.getRsEnrollmentPlanByMajorName(rsp);
		if(org.springframework.util.StringUtils.isEmpty(ep)) {
			scoreBill.setStatus("3");
			rsScoreBillService.save(scoreBill);
			return true;
		}
		if (Integer.valueOf(ep.getMajorTotal()) - Integer.valueOf(ep.getMajorCount()) > 0) {
			scoreBill.setStatus("1");
			int majorCount = Integer.valueOf(ep.getMajorCount()) + 1;
			ep.setMajorCount(String.valueOf(majorCount));
			rsEnrollmentPlanService.save(ep);
		} else {
			index++;
			if(index==6) {
				scoreBill.setStatus("2");
				return false;
			}
			if(scoreBill.getZytj().equals("是")) {
				return scoreBillHandler(scoreBill,index++);
			}else {
				scoreBill.setStatus("5");
			}
			
		}
		return false;
	}
	
	
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<RsScoreBill> list = Lists.newArrayList(); 
    		new ExportExcel("成绩单数据", RsScoreBill.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/score/bill/list?repage";
    }
	
	public boolean checkKsh(String ksh) {
		RsScoreBill entity = rsScoreBillService.getByKsh(ksh);
		if(org.springframework.util.StringUtils.isEmpty(entity)) {
			return false;
		}
		return true;
	}
	
	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsScoreBill rsScoreBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsScoreBill> page = rsScoreBillService.findPage(new Page<RsScoreBill>(request, response), rsScoreBill); 
		model.addAttribute("page", page);
		model.addAttribute("status", rsScoreBill.getStatus());
		model.addAttribute("zytj", rsScoreBill.getZytj());
		return "modules/out/score/rsScoreBillList";
	}
	
	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping("im")
	public String im(RsScoreBill rsScoreBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsScoreBill> page = rsScoreBillService.findPage(new Page<RsScoreBill>(request, response), rsScoreBill); 
		model.addAttribute("page", page);
		return "modules/out/score/im";
	}
	
	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping(value="/status/{stauts}")
	public String status(RsScoreBill rsScoreBill,@PathVariable("stauts") String stauts, HttpServletRequest request, HttpServletResponse response, Model model) {
		rsScoreBill.setStatus(stauts);
		Page<RsScoreBill> page = rsScoreBillService.findPage(new Page<RsScoreBill>(request, response), rsScoreBill); 
		model.addAttribute("page", page);
		model.addAttribute("status", stauts);
		return "modules/out/score/status";
	}
	
	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping("in")
	public String in(RsScoreBill rsScoreBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsScoreBill> page = rsScoreBillService.findPage(new Page<RsScoreBill>(request, response), rsScoreBill); 
		
		model.addAttribute("page", page);
		return "modules/out/score/rsScoreBillList";
	}
	

	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping("out")
	public String out(RsScoreBill rsScoreBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		rsScoreBill.setStatus("all");
		Page<RsScoreBill> page = rsScoreBillService.findPage(new Page<RsScoreBill>(request, response), rsScoreBill); 
		model.addAttribute("page", page);
		return "modules/out/score/out";
	}

	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping(value = "form")
	public String form(RsScoreBill rsScoreBill, Model model) {
		model.addAttribute("rsScoreBill", rsScoreBill);
		return "modules/out/score/rsScoreBillForm";
	}

	@RequiresPermissions("out:score:rsScoreBill:edit")
	@RequestMapping(value = "save")
	public String save(RsScoreBill rsScoreBill, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsScoreBill)){
			return form(rsScoreBill, model);
		}
		rsScoreBillService.save(rsScoreBill);
		addMessage(redirectAttributes, "保存考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/score/bill/?repage";
	}
	
	@RequiresPermissions("out:score:rsScoreBill:edit")
	@RequestMapping(value = "delete")
	public String delete(RsScoreBill rsScoreBill, RedirectAttributes redirectAttributes) {
		rsScoreBillService.delete(rsScoreBill);
		addMessage(redirectAttributes, "删除考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/score/bill/?repage";
	}

}