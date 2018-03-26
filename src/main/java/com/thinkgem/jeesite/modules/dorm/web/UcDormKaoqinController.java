/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormRecord;
import com.thinkgem.jeesite.modules.dorm.service.UcDormRecordService;
import com.thinkgem.jeesite.modules.dorm.service.UcDormService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 宿舍管理Controller
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/dorm/kaoqin")
public class UcDormKaoqinController extends BaseController {

	private String[] chuangwei = {"a","b","c","d"};
	
	private final static String DORM_INIT = "0";//初始化
	private final static String DORM_NORMAL = "1";//正常
	private final static String DORM_DUTY = "2";//缺勤
	private final static String DORM_LEAVE = "3";//请假

	@Autowired
	private UcDormService ucDormService;
	@Autowired
	private UcDormRecordService ucDormRecordService;
	@Autowired
	private SystemService systemService;
	
	@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcDorm ucDorm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcDorm> page = ucDormService.findPage(new Page<UcDorm>(request, response), ucDorm); 
		model.addAttribute("list", page.getList());
		return "modules/kaoqin/ucDormList";
	}
	
	
	
	//@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = "dorm")
	public String dorm(UcDorm ucDorm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcDorm> page = ucDormService.findPage(new Page<UcDorm>(request, response), ucDorm); 
		model.addAttribute("list", page.getList());
		return "modules/kaoqin/ucDormList";
	}
	
	//@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "init")
	public String init(UcDorm ucDorm, HttpServletRequest request, HttpServletResponse response, Model model) {
		UcDorm dorm = ucDormService.get(ucDorm.getId());
		List<UcDormRecord> list = new ArrayList<UcDormRecord>();
		for (String cw : chuangwei) {
			Object object = Reflections.getFieldValue(dorm, cw);
			if (!org.springframework.util.StringUtils.isEmpty(object)
					&&! org.springframework.util.StringUtils.isEmpty(object.toString())) {
				UcDormRecord ucDormRecord = new UcDormRecord();
				User user = systemService.getUser(object.toString());
				ucDormRecord.setStudentNumber(user.getNo());
				ucDormRecord.setUsername(user.getName());
				ucDormRecord.setDormBuildId(dorm.getUcDormBuild().getId());
				ucDormRecord.setDormId(dorm.getId());
				ucDormRecord.setDate(new Date());
				ucDormRecord.setDetail(DORM_INIT);
				list.add(ucDormRecord);
				UcDormRecord entity = ucDormRecordService.get(ucDormRecord);
				if(StringUtils.isEmpty(entity)) {
					ucDormRecordService.save(ucDormRecord);
				}
			}
		}
		model.addAttribute("list", list);
		return "modules/kaoqin/ucDormInit";
	}
	
	//@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "operation")
	public String operation(UcDormRecord ucDormRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		ucDormRecordService.save(ucDormRecord);
		return "modules/kaoqin/operation";
	}

}