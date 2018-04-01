/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.jcd.web;

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
import com.thinkgem.jeesite.modules.out.jcd.entity.RsJcd;
import com.thinkgem.jeesite.modules.out.jcd.entity.RsMajorSetup;
import com.thinkgem.jeesite.modules.out.jcd.service.RsJcdService;
import com.thinkgem.jeesite.modules.out.jcd.service.RsMajorSetupService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

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
	@Autowired
	private RsMajorSetupService rsMajorSetupService;
	
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
			
			List<RsJcd> sortList = new ArrayList<RsJcd>();
			
			for (RsJcd jcd : list){
				try{
					RsJcd entity = rsJcdService.getByKsh(jcd.getKsh());
					if(org.springframework.util.StringUtils.isEmpty(entity)) {
						entity = new RsJcd();
					}
					entity.setKsh(jcd.getKsh());
					entity.setXm(jcd.getXm());
					entity.setSfzh(jcd.getSfzh());
					entity.setZf(jcd.getZf());
					entity.setKm1(jcd.getKm1());
					entity.setKm2(jcd.getKm2());
					entity.setKm3(jcd.getKm3());
					entity.setKm4(jcd.getKm4());
					entity.setZy1(jcd.getZy1());
					entity.setZy2(jcd.getZy2());
					entity.setZy3(jcd.getZy3());
					entity.setZy4(jcd.getZy4());
					entity.setZy5(jcd.getZy5());
					entity.setZy6(jcd.getZy6());
					entity.setZytj(jcd.getZytj());
					String cj = jcd.getZf().concat(".").concat(val(jcd.getKm3())).concat(val(jcd.getKm1())).concat(val(jcd.getKm2()));
					entity.setCj(cj);
					entity.setStatus("0");
					rsJcdService.save(entity);
					sortList.add(entity);
					successNum++;
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
			
			Collections.sort(sortList);  
			List<Dict> dicts = DictUtils.getDictList("greathiit_zhaosheng_grade");
			Double grade = Double.valueOf(dicts.get(0).getValue());
			for(RsJcd jcd:sortList) {
				if(org.springframework.util.StringUtils.isEmpty(jcd.getZf())) {
					//未参加考试
					jcd.setStatus("5");
					rsJcdService.save(jcd);
					continue;
				}
				if(Double.valueOf(jcd.getZf())==0||Double.valueOf(jcd.getZf())<grade) {
					jcd.setStatus("4");
					rsJcdService.save(jcd);
					continue;
				}
				Double cj = Double.valueOf(jcd.getCj());
				if(cj>=grade) {
					/**
					 * boolean ret = false;
					RsMajorSetup rsMajorSetup = new RsMajorSetup();
					rsMajorSetup.setMajorName(jcd.getZy1());
					RsMajorSetup rsMajorSetup1 = rsMajorSetupService.getRsMajorSetupByMajorName(rsMajorSetup);
						if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup1)) {
							jcd.setStatus("3");
							rsJcdService.save(jcd);
							continue;
						}
						if (Integer.valueOf(rsMajorSetup1.getMajorTotal()) - Integer.valueOf(rsMajorSetup1.getMajorCount()) > 0) {
							ret = true;
							int majorCount = Integer.valueOf(rsMajorSetup1.getMajorCount()) + 1;
							rsMajorSetup1.setMajorCount(String.valueOf(majorCount));
							rsMajorSetupService.save(rsMajorSetup1);
						}else if(jcd.getZytj().equals("是")) {
							
							RsMajorSetup newMajorSetup2 = new RsMajorSetup();
							newMajorSetup2.setMajorName(jcd.getZy2());
							RsMajorSetup rsMajorSetup2 = rsMajorSetupService.getRsMajorSetupByMajorName(newMajorSetup2);
							if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup2)) {
								jcd.setStatus("3");
								rsJcdService.save(jcd);
								continue;
							}
							if (Integer.valueOf(rsMajorSetup2.getMajorTotal())
									- Integer.valueOf(rsMajorSetup2.getMajorCount()) > 0) {
								ret = true;
								int majorCount = Integer.valueOf(rsMajorSetup2.getMajorCount()) + 1;
								rsMajorSetup2.setMajorCount(String.valueOf(majorCount));
								rsMajorSetupService.save(rsMajorSetup2);
							} else if (jcd.getZytj().equals("是")) {

								RsMajorSetup newMajorSetup3 = new RsMajorSetup();
								newMajorSetup3.setMajorName(jcd.getZy3());
								RsMajorSetup rsMajorSetup3 = rsMajorSetupService.getRsMajorSetupByMajorName(newMajorSetup3);
								if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup3)) {
									jcd.setStatus("3");
									rsJcdService.save(jcd);
									continue;
								}
								if (Integer.valueOf(rsMajorSetup3.getMajorTotal())- Integer.valueOf(rsMajorSetup3.getMajorCount()) > 0) {
									ret = true;
									int majorCount = Integer.valueOf(rsMajorSetup3.getMajorCount()) + 1;
									rsMajorSetup3.setMajorCount(String.valueOf(majorCount));
									rsMajorSetupService.save(rsMajorSetup3);
								} else if (jcd.getZytj().equals("是")) {
									RsMajorSetup newMajorSetup4 = new RsMajorSetup();
									newMajorSetup4.setMajorName(jcd.getZy4());
									RsMajorSetup rsMajorSetup4 = rsMajorSetupService
											.getRsMajorSetupByMajorName(newMajorSetup4);
									if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup4)) {
										jcd.setStatus("3");
										rsJcdService.save(jcd);
										continue;
									}
									if (Integer.valueOf(rsMajorSetup4.getMajorTotal()) - Integer.valueOf(rsMajorSetup4.getMajorCount()) > 0) {
										ret = true;
										int majorCount = Integer.valueOf(rsMajorSetup4.getMajorCount()) + 1;
										rsMajorSetup4.setMajorCount(String.valueOf(majorCount));
										rsMajorSetupService.save(rsMajorSetup4);
									} else if (jcd.getZytj().equals("是")) {
										RsMajorSetup newMajorSetup5 = new RsMajorSetup();
										newMajorSetup5.setMajorName(jcd.getZy5());
										RsMajorSetup rsMajorSetup5 = rsMajorSetupService.getRsMajorSetupByMajorName(newMajorSetup5);
										if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup5)) {
											jcd.setStatus("3");
											rsJcdService.save(jcd);
											continue;
										}
										if (Integer.valueOf(rsMajorSetup5.getMajorTotal()) - Integer.valueOf(rsMajorSetup5.getMajorCount()) > 0) {
											ret = true;
											int majorCount = Integer.valueOf(rsMajorSetup5.getMajorCount()) + 1;
											rsMajorSetup5.setMajorCount(String.valueOf(majorCount));
											rsMajorSetupService.save(rsMajorSetup5);
										}
									}
								}
							}
						}
						if(ret) {
							//已录取
							jcd.setStatus("1");
						}else {
							//名额已满
							jcd.setStatus("2");
						}
					
					 */
					if(handMajorSetup(jcd,1)) {
						continue;
					}
				}else {
					//分数过低
					jcd.setStatus("4");
				}
				rsJcdService.save(jcd);
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条考生信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条考生信息"+failureMsg);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/jcd/rsJcd/list?repage";
	}
	
	private boolean handMajorSetup(RsJcd jcd,int index) {
		String zy = (String) Reflections.getFieldValue(jcd, "zy".concat(String.valueOf(index)));
		RsMajorSetup setup = new RsMajorSetup();
		setup.setMajorName(zy);
		RsMajorSetup majorSetup = rsMajorSetupService.getRsMajorSetupByMajorName(setup);
		if(org.springframework.util.StringUtils.isEmpty(majorSetup)) {
			jcd.setStatus("3");
			rsJcdService.save(jcd);
			return true;
		}
		if (Integer.valueOf(majorSetup.getMajorTotal())- Integer.valueOf(majorSetup.getMajorCount()) > 0) {
			jcd.setStatus("1");
			int majorCount = Integer.valueOf(majorSetup.getMajorCount()) + 1;
			majorSetup.setMajorCount(String.valueOf(majorCount));
			rsMajorSetupService.save(majorSetup);
		} else {
			index++;
			if(index==6) {
				jcd.setStatus("2");
				return false;
			}
			if(jcd.getZytj().equals("是")) {
				return handMajorSetup(jcd,index++);
			}else {
				jcd.setStatus("5");
			}
			
		}
		return false;
	}
	
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<RsJcd> list = Lists.newArrayList(); 
    		
    		new ExportExcel("成绩单数据", RsJcd.class, 2).setDataList(list).write(response, fileName).dispose();
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
		model.addAttribute("status", rsJcd.getStatus());
		model.addAttribute("zytj", rsJcd.getZytj());
		return "modules/out/jcd/rsJcdList";
	}
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping("im")
	public String im(RsJcd rsJcd, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/im";
	}
	
	@RequiresPermissions("out:jcd:rsJcd:view")
	@RequestMapping(value="/status/{stauts}")
	public String status(RsJcd rsJcd,@PathVariable("stauts") String stauts, HttpServletRequest request, HttpServletResponse response, Model model) {
		rsJcd.setStatus(stauts);
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		model.addAttribute("status", stauts);
		return "modules/out/jcd/status";
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
		rsJcd.setStatus("all");
		Page<RsJcd> page = rsJcdService.findPage(new Page<RsJcd>(request, response), rsJcd); 
		model.addAttribute("page", page);
		return "modules/out/jcd/out";
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