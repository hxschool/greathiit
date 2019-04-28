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
import com.thinkgem.jeesite.modules.out.score.service.RsScoreBillService;
import com.thinkgem.jeesite.modules.out.score.service.RsEnrollmentPlanService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

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
	
	@ModelAttribute
	public RsScoreBill get(@RequestParam(required=false) String id) {
		RsScoreBill entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsScoreBillService.get(id);
		}
		if (entity == null){
			entity = new RsScoreBill();
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
			List<RsScoreBill> list = ei.getDataList(RsScoreBill.class);
			
			List<RsScoreBill> sortList = new ArrayList<RsScoreBill>();
			
			for (RsScoreBill scoreBill : list){
				try{
					RsScoreBill entity = rsScoreBillService.getByKsh(scoreBill.getKsh());
					if(org.springframework.util.StringUtils.isEmpty(entity)) {
						entity = new RsScoreBill();
					}
					entity.setKsh(scoreBill.getKsh());
					entity.setXm(scoreBill.getXm());
					entity.setSfzh(scoreBill.getSfzh());
					entity.setZf(scoreBill.getZf());
					entity.setKm1(scoreBill.getKm1());
					entity.setKm2(scoreBill.getKm2());
					entity.setKm3(scoreBill.getKm3());
					entity.setKm4(scoreBill.getKm4());
					entity.setZy1(scoreBill.getZy1());
					entity.setZy2(scoreBill.getZy2());
					entity.setZy3(scoreBill.getZy3());
					entity.setZy4(scoreBill.getZy4());
					entity.setZy5(scoreBill.getZy5());
					entity.setZy6(scoreBill.getZy6());
					entity.setZytj(scoreBill.getZytj());
					String cj = scoreBill.getZf().concat(".").concat(val(scoreBill.getKm3())).concat(val(scoreBill.getKm1())).concat(val(scoreBill.getKm2()));
					entity.setCj(cj);
					entity.setStatus("0");
					rsScoreBillService.save(entity);
					sortList.add(entity);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>考试号: "+scoreBill.getKsh()+" 导入失败：");
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
			List<Dict> dicts = DictUtils.getDictList("greathiit_zhaosheng_grade");
			Double grade = Double.valueOf(dicts.get(0).getValue());
			for(RsScoreBill scoreBill:sortList) {
				if(org.springframework.util.StringUtils.isEmpty(scoreBill.getZf())) {
					//未参加考试
					scoreBill.setStatus("5");
					rsScoreBillService.save(scoreBill);
					continue;
				}
				if(Double.valueOf(scoreBill.getZf())==0||Double.valueOf(scoreBill.getZf())<grade) {
					scoreBill.setStatus("4");
					rsScoreBillService.save(scoreBill);
					continue;
				}
				Double cj = Double.valueOf(scoreBill.getCj());
				if(cj>=grade) {
					/**
					 * boolean ret = false;
					RsMajorSetup rsMajorSetup = new RsMajorSetup();
					rsMajorSetup.setMajorName(jcd.getZy1());
					RsMajorSetup rsMajorSetup1 = rsEnrollmentPlanService.getRsMajorSetupByMajorName(rsMajorSetup);
						if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup1)) {
							jcd.setStatus("3");
							rsScoreBillService.save(jcd);
							continue;
						}
						if (Integer.valueOf(rsMajorSetup1.getMajorTotal()) - Integer.valueOf(rsMajorSetup1.getMajorCount()) > 0) {
							ret = true;
							int majorCount = Integer.valueOf(rsMajorSetup1.getMajorCount()) + 1;
							rsMajorSetup1.setMajorCount(String.valueOf(majorCount));
							rsEnrollmentPlanService.save(rsMajorSetup1);
						}else if(jcd.getZytj().equals("是")) {
							
							RsMajorSetup newMajorSetup2 = new RsMajorSetup();
							newMajorSetup2.setMajorName(jcd.getZy2());
							RsMajorSetup rsMajorSetup2 = rsEnrollmentPlanService.getRsMajorSetupByMajorName(newMajorSetup2);
							if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup2)) {
								jcd.setStatus("3");
								rsScoreBillService.save(jcd);
								continue;
							}
							if (Integer.valueOf(rsMajorSetup2.getMajorTotal())
									- Integer.valueOf(rsMajorSetup2.getMajorCount()) > 0) {
								ret = true;
								int majorCount = Integer.valueOf(rsMajorSetup2.getMajorCount()) + 1;
								rsMajorSetup2.setMajorCount(String.valueOf(majorCount));
								rsEnrollmentPlanService.save(rsMajorSetup2);
							} else if (jcd.getZytj().equals("是")) {

								RsMajorSetup newMajorSetup3 = new RsMajorSetup();
								newMajorSetup3.setMajorName(jcd.getZy3());
								RsMajorSetup rsMajorSetup3 = rsEnrollmentPlanService.getRsMajorSetupByMajorName(newMajorSetup3);
								if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup3)) {
									jcd.setStatus("3");
									rsScoreBillService.save(jcd);
									continue;
								}
								if (Integer.valueOf(rsMajorSetup3.getMajorTotal())- Integer.valueOf(rsMajorSetup3.getMajorCount()) > 0) {
									ret = true;
									int majorCount = Integer.valueOf(rsMajorSetup3.getMajorCount()) + 1;
									rsMajorSetup3.setMajorCount(String.valueOf(majorCount));
									rsEnrollmentPlanService.save(rsMajorSetup3);
								} else if (jcd.getZytj().equals("是")) {
									RsMajorSetup newMajorSetup4 = new RsMajorSetup();
									newMajorSetup4.setMajorName(jcd.getZy4());
									RsMajorSetup rsMajorSetup4 = rsEnrollmentPlanService
											.getRsMajorSetupByMajorName(newMajorSetup4);
									if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup4)) {
										jcd.setStatus("3");
										rsScoreBillService.save(jcd);
										continue;
									}
									if (Integer.valueOf(rsMajorSetup4.getMajorTotal()) - Integer.valueOf(rsMajorSetup4.getMajorCount()) > 0) {
										ret = true;
										int majorCount = Integer.valueOf(rsMajorSetup4.getMajorCount()) + 1;
										rsMajorSetup4.setMajorCount(String.valueOf(majorCount));
										rsEnrollmentPlanService.save(rsMajorSetup4);
									} else if (jcd.getZytj().equals("是")) {
										RsMajorSetup newMajorSetup5 = new RsMajorSetup();
										newMajorSetup5.setMajorName(jcd.getZy5());
										RsMajorSetup rsMajorSetup5 = rsEnrollmentPlanService.getRsMajorSetupByMajorName(newMajorSetup5);
										if(org.springframework.util.StringUtils.isEmpty(rsMajorSetup5)) {
											jcd.setStatus("3");
											rsScoreBillService.save(jcd);
											continue;
										}
										if (Integer.valueOf(rsMajorSetup5.getMajorTotal()) - Integer.valueOf(rsMajorSetup5.getMajorCount()) > 0) {
											ret = true;
											int majorCount = Integer.valueOf(rsMajorSetup5.getMajorCount()) + 1;
											rsMajorSetup5.setMajorCount(String.valueOf(majorCount));
											rsEnrollmentPlanService.save(rsMajorSetup5);
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
					if(handMajorSetup(scoreBill,1)) {
						continue;
					}
				}else {
					//分数过低
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
		return "redirect:" + adminPath + "/out/score/rsScoreBill/list?repage";
	}
	
	private boolean handMajorSetup(RsScoreBill scoreBill,int index) {
		String zy = (String) Reflections.getFieldValue(scoreBill, "zy".concat(String.valueOf(index)));
		RsEnrollmentPlan setup = new RsEnrollmentPlan();
		setup.setMajorName(zy);
		RsEnrollmentPlan majorSetup = rsEnrollmentPlanService.getRsEnrollmentPlanByMajorName(setup);
		if(org.springframework.util.StringUtils.isEmpty(majorSetup)) {
			scoreBill.setStatus("3");
			rsScoreBillService.save(scoreBill);
			return true;
		}
		if (Integer.valueOf(majorSetup.getMajorTotal())- Integer.valueOf(majorSetup.getMajorCount()) > 0) {
			scoreBill.setStatus("1");
			int majorCount = Integer.valueOf(majorSetup.getMajorCount()) + 1;
			majorSetup.setMajorCount(String.valueOf(majorCount));
			rsEnrollmentPlanService.save(majorSetup);
		} else {
			index++;
			if(index==6) {
				scoreBill.setStatus("2");
				return false;
			}
			if(scoreBill.getZytj().equals("是")) {
				return handMajorSetup(scoreBill,index++);
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
		return "redirect:" + adminPath + "/out/score/rsScoreBill/list?repage";
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
		return "modules/out/jcd/im";
	}
	
	@RequiresPermissions("out:score:rsScoreBill:view")
	@RequestMapping(value="/status/{stauts}")
	public String status(RsScoreBill rsScoreBill,@PathVariable("stauts") String stauts, HttpServletRequest request, HttpServletResponse response, Model model) {
		rsScoreBill.setStatus(stauts);
		Page<RsScoreBill> page = rsScoreBillService.findPage(new Page<RsScoreBill>(request, response), rsScoreBill); 
		model.addAttribute("page", page);
		model.addAttribute("status", stauts);
		return "modules/out/jcd/status";
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
		return "modules/out/jcd/out";
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
		return "redirect:"+Global.getAdminPath()+"/out/score/rsScoreBill/?repage";
	}
	
	@RequiresPermissions("out:score:rsScoreBill:edit")
	@RequestMapping(value = "delete")
	public String delete(RsScoreBill rsScoreBill, RedirectAttributes redirectAttributes) {
		rsScoreBillService.delete(rsScoreBill);
		addMessage(redirectAttributes, "删除考试成绩单成功");
		return "redirect:"+Global.getAdminPath()+"/out/score/rsScoreBill/?repage";
	}

}