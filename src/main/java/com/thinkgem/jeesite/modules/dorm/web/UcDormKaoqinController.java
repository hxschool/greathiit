/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping
	public String tab(String tab, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(tab)) {
			tab="tab1";
		}
		model.addAttribute("tab", tab);
		return "modules/kaoqin/ucDormIndex";
	}
	
	
	
	//@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = "list")
	public String list(UcDorm ucDorm, HttpServletRequest request, HttpServletResponse response, Model model) {
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
				
				UcDormRecord entity = ucDormRecordService.get(ucDormRecord);
				if(StringUtils.isEmpty(entity)) {
					list.add(ucDormRecord);
					ucDormRecordService.save(ucDormRecord);
				}else {
					list.add(entity);
				}
			}
		}
		model.addAttribute("list", list);
		return "modules/kaoqin/ucDormInit";
	}
	
	@RequestMapping(value = "query")
	public String query(@RequestParam(value="dormBuildId",required=false)String dormBuildId,@RequestParam(value="detail",required=false)String detail, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		UcDormRecord ucDormRecord = new UcDormRecord();
		ucDormRecord.setDormBuildId(dormBuildId);
		ucDormRecord.setDetail(detail);
		List<UcDormRecord> list = ucDormRecordService.findList(ucDormRecord);
		model.addAttribute("list", list);
		return "modules/kaoqin/ucDormQuery";
	}
	
	//@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "operation")
	@ResponseBody
	public String operation(UcDormRecord ucDormRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		String detail = ucDormRecord.getDetail();
		UcDormRecord entity = ucDormRecordService.get(ucDormRecord);
		if(!StringUtils.isEmpty(entity)) {
			entity.setDetail(detail);
			ucDormRecordService.save(entity);
		}
		
		return "1";
	}

}