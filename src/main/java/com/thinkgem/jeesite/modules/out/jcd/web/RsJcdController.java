/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.web;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsJcd;
import com.thinkgem.jeesite.modules.out.jcd.service.RsJcdService;

/**
 * 考试成绩单Controller
 * @author 赵俊飞
 * @version 2017-12-09
 */
@Controller
@RequestMapping(value = "${adminPath}/out/jcd/rsJcd")
public class RsJcdController extends BaseController {

	@Autowired
	private RsJcdService rsJcdService;
	
	
	@ModelAttribute
	public RsJcd get(@RequestParam(required=false) String id) {
		RsJcd entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsJcdService.get(id);
		}
		if (entity == null){
			entity = new RsJcd();
		}
		return entity;
	}
	
	public static void main(String[] args) {
		
		
	}
//	public  void L(List<RsJcd> list) {
//		List<Dict> dicts = DictUtils.getDictList("greathiit_zhaosheng_grade");
//		Double grade = Double.valueOf(dicts.get(0).getValue());
//		
//		for(RsJcd jcd:list) {
//			Double cj = Double.valueOf(jcd.getCj());
//			String majorType1 = jcd.getZy1();
//			if(cj>grade&&checkMajor(majorType1)) {
//				jcd.setStatus("1");
//				jcd.setZy6(majorType1);
//				rsJcdService.save(jcd);
//				continue;
//			}
//			String majorType2 = jcd.getZy2();
//			if (cj > grade && checkMajor(majorType2) && jcd.getZytj().equals("是")) {
//				jcd.setStatus("1");
//				jcd.setZy6(majorType2);
//				rsJcdService.save(jcd);
//				continue;
//			}
//			
//			
//			String majorType3 = jcd.getZy3();
//			if (cj > grade && checkMajor(majorType3) && jcd.getZytj().equals("是")) {
//				jcd.setStatus("1");
//				jcd.setZy6(majorType3);
//				rsJcdService.save(jcd);
//				continue;
//			}
//			
//			
//			String majorType4 = jcd.getZy4();
//			if (cj > grade && checkMajor(majorType4) && jcd.getZytj().equals("是")) {
//				jcd.setStatus("1");
//				jcd.setZy6(majorType4);
//				rsJcdService.save(jcd);
//				continue;
//			}
//			
//			
//			String majorType5 = jcd.getZy5();
//			if (cj > grade && checkMajor(majorType5) && jcd.getZytj().equals("是")) {
//				jcd.setStatus("1");
//				jcd.setZy6(majorType5);
//				rsJcdService.save(jcd);
//				continue;
//			}
//			
//		}
//		
//	}
	

	
	public String val(String value) {
		if(org.springframework.util.StringUtils.isEmpty(value)) {
			value = "0";
		}
		return String.format("%03d" ,Integer.parseInt(value));
	}
	
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<RsJcd> list = ei.getDataList(RsJcd.class);
			for (RsJcd jcd : list){
				try{
					if (!checkKsh(jcd.getKsh())){
						//初始化状态,设置未录取
						String cj = jcd.getZf().concat(".").concat(val(jcd.getKm1())).concat(val(jcd.getKm2())).concat(val(jcd.getKm3()));;
						jcd.setCj(cj);
						jcd.setStatus("0");
						rsJcdService.save(jcd);
						successNum++;
					}else{
						failureMsg.append("<br/>考试号"+jcd.getKsh()+" 姓名: "+ jcd.getXm() +" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>考试号: "+jcd.getKsh()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>考试号: "+jcd.getKsh()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条考生信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条考生信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/jcd/rsJcd/list?repage";
	}
	
	
	
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<RsJcd> list = Lists.newArrayList(); 
    		
    		new ExportExcel("用户数据", RsJcd.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/jcd/rsJcd/list?repage";
    }
	
	public boolean checkKsh(String ksh) {
		RsJcd entity = rsJcdService.getByKsh(ksh);
		if(org.springframework.util.StringUtils.isEmpty(entity)) {
			return false;
		}
		return true;
	}
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsJcd rsJcd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/rsJcdList";
	}
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping("in")
	public String in(RsJcd rsJcd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/rsJcdList";
	}
	
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping("out")
	public String out(RsJcd rsJcd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/rsJcdList";
	}
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping("low")
	public String low(RsJcd rsJcd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/rsJcdList";
	}

	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping(value = "form")
	public String form(RsJcd rsJcd, Model model) {
		model.addAttribute("rsJcd", rsJcd);
		return "modules/out/jcd/rsJcdForm";
	}

	@RequiresPermissions("out:jcd:rsJcd:edit")
	@RequestMapping(value = "save")
	public String save(RsJcd rsJcd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsJcd)){
			return form(rsJcd, model);
		}
		rsJcdService.save(rsJcd);
		addMessage(redirectAttributes, "保存考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/jcd/rsJcd/?repage";
	}
	
	@RequiresPermissions("out:jcd:rsJcd:edit")
	@RequestMapping(value = "delete")
	public String delete(RsJcd rsJcd, RedirectAttributes redirectAttributes) {
		rsJcdService.delete(rsJcd);
		addMessage(redirectAttributes, "删除考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/jcd/rsJcd/?repage";
	}

}