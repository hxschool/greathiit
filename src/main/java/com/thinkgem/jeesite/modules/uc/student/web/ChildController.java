/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.web;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.NumberUtils;
import com.thinkgem.jeesite.common.utils.RegexUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 组织Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/child")
public class ChildController extends BaseController {

	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}
	
	@RequiresPermissions("uc:child:view")
	@RequestMapping(value = "generateSequenceNumber")
	@ResponseBody
	public Map<String,Object>  generateSequenceNumber(Office office, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		Office officeParent = officeService.get(office.getParent().getId());
		if(!org.springframework.util.StringUtils.isEmpty(officeService.getOfficeByName(office.getName()))) {
			map.put("code", "40400000");
			map.put("msg", "信息已存在,请确认相关数据是否正确");
			return map;
		}
		String classNumber = "";
		String year = DateUtils.getYear();
		if(officeParent.getType().equals("3")) {
			//判断是否有中文,有中文认为是本科
			if(RegexUtils.isContainChinese(office.getName())) {
				String number = StringUtils.right(office.getName(), 2);
				if(!NumberUtils.isNumeric(number)) {
					map.put("code", "40400000");
					map.put("msg", "班号最后2位必须位数字,否则无法生成班级信息");
					return map;
				}
				classNumber = year.concat(officeParent.getId()).concat(number);
			}else {
			//高职
				if(office.getName().length()==8) {
					String number = StringUtils.right(office.getName(), 2);
					classNumber = year.concat(officeParent.getId()).concat(number);
				}else {
					String number = StringUtils.right(office.getName(), 1);
					classNumber = year.concat(officeParent.getId()).concat("0").concat(number);
				}
			}
			
		}
		map.put("code", "20000000");
		map.put("msg", classNumber);
		return map;
	}
	
	@RequiresPermissions("uc:child:view")
	@RequestMapping(value = "year")
	public String year(Office office, Model model) {
		model.addAttribute("years", officeService.findYear());
		List<Office> list = null;
		if(!org.springframework.util.StringUtils.isEmpty(office.getYear())) {
			list = officeService.findList(office);
		}
	    model.addAttribute("list", list);
		return "modules/uc/child/childYear";
	}
	
	@RequiresPermissions("uc:child:view")
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
		return "modules/uc/child/childIndex";
	}

	@RequiresPermissions("uc:child:view")
	@RequestMapping(value = {"list"})
	public String list(Office office, Model model) {
		model.addAttribute("label",DictUtils.getDictLabel(office.getType(), "sys_office_type", ""));
        model.addAttribute("list", officeService.findList(office));
		return "modules/uc/child/childList";
	}
	
	@RequiresPermissions("uc:child:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		Office officeParent = officeService.get(office.getParent().getId());
		office.setParent(officeParent);
		if (office.getArea()==null){
			office.setArea(user.getOffice().getArea());
		}
		if(office.getType()==null) {
			office.setType(office.getGrade());
		}
		model.addAttribute("office", office);
		return "modules/uc/child/childForm";
	}
	
	@RequiresPermissions("uc:child:edit")
	@RequestMapping(value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {

		if (!beanValidator(model, office)){
			return form(office, model);
		}
		//只有再班级的时候才去操作相关数据
		if (org.springframework.util.StringUtils.isEmpty(office.getId())) {
			office.setIsNewRecord(true);
			office.setId(office.getCode());
		}
		officeService.save(office);
		addMessage(redirectAttributes, "保存'" + office.getName() + "'成功");
		return "redirect:" + adminPath + "/uc/child/list?grade="+office.getGrade();
	}
	
	@RequiresPermissions("uc:child:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {
		officeService.delete(office);
		addMessage(redirectAttributes, "停用成功");
		return "redirect:" + adminPath + "/uc/child/list?grade="+office.getGrade();
	}
	
	
	@RequiresPermissions("uc:child:edit")
	@RequestMapping(value = "useableList")
	public String useableList(String ids, RedirectAttributes redirectAttributes) {
		if(!org.springframework.util.StringUtils.isEmpty(ids)) {
			String[] arrayIds = ids.split(",");
			for(String id:arrayIds) {
				Office office = new Office();
				office.setId(id);
				office.setUseable("0");
				officeService.save(office);
			}
		}
		
		addMessage(redirectAttributes, "批量停用数据成功");
		return "redirect:"+Global.getAdminPath()+"/uc/child/year?repage";
	}
	
	@RequiresPermissions("uc:child:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String ids, RedirectAttributes redirectAttributes) {
		if(!org.springframework.util.StringUtils.isEmpty(ids)) {
			String[] arrayIds = ids.split(",");
			for(String id:arrayIds) {
				Office office = new Office();
				office.setId(id);
				officeService.delete(office);
				systemService.batchByClassno(id);
			}
		}
		
		addMessage(redirectAttributes, "匹配删除数据成功");
		return "redirect:"+Global.getAdminPath()+"/uc/child/year?repage";
	}
	
	@RequiresPermissions("uc:child:edit")
	@RequestMapping(value = "remove")
	public String remove(Office office, RedirectAttributes redirectAttributes) {
		officeService.remove(office);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/uc/child/list?grade="+office.getGrade();
	}

	
	
}
