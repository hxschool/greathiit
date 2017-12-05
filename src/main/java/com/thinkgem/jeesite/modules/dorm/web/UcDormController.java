/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dorm.entity.UcDorm;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormBuild;
import com.thinkgem.jeesite.modules.dorm.service.UcDormService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 宿舍管理Controller
 * @author 赵俊飞
 * @version 2017-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/dorm/ucDorm")
public class UcDormController extends BaseController {

	@Autowired
	private UcDormService ucDormService;
	
	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public UcDorm get(@RequestParam(required=false) String id) {
		UcDorm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucDormService.get(id);
		}
		if (entity == null){
			entity = new UcDorm();
		}
		return entity;
	}
	
	@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcDorm ucDorm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcDorm> page = ucDormService.findPage(new Page<UcDorm>(request, response), ucDorm); 
		model.addAttribute("page", page);
		return "modules/dorm/ucDormList";
	}

	@RequiresPermissions("dorm:ucDorm:view")
	@RequestMapping(value = "form")
	public String form(UcDorm ucDorm, Model model) {
		model.addAttribute("ucDorm", ucDorm);
		return "modules/dorm/ucDormForm";
	}

	@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "save")
	public String save(UcDorm ucDorm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucDorm)){
			return form(ucDorm, model);
		}
		ucDormService.save(ucDorm);
		addMessage(redirectAttributes, "保存宿舍管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDorm/?repage";
	}
	
	@RequiresPermissions("dorm:ucDorm:edit")
	@RequestMapping(value = "delete")
	public String delete(UcDorm ucDorm, RedirectAttributes redirectAttributes) {
		ucDormService.delete(ucDorm);
		addMessage(redirectAttributes, "删除宿舍管理成功");
		return "redirect:"+Global.getAdminPath()+"/dorm/ucDorm/?repage";
	}

	
	
	@RequestMapping(value = "info")
	public String info(UcDorm ucDorm, Model model) {
		return "modules/dorm/ucDormInfoForm";
	}
	
	@RequestMapping(value = "clazz")
	public String clazz(Model model) {
		return "modules/dorm/ucDormClazzForm";
	}
	
	@RequestMapping(value = "ajaxClazzNumber")
	@ResponseBody
	public Map<String,Object> ajaxClazzNumber(String clazzId, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(org.springframework.util.StringUtils.isEmpty(clazzId)) {
			map.put("responseCode", "9999");
			map.put("responseMessage", "请求参数异常,学号信息异常");
			return map;
		}
		
		User user = new User();
		Office clazz = new Office(clazzId);
		user.setClazz(clazz);
		List<User> tmp = systemService.findAllList(user);
		
		if(!org.springframework.util.StringUtils.isEmpty(tmp)) {
			map.put("responseCode", "9999");
			map.put("responseMessage", "系统异常,根据班号未查找到相关学员信息");
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<User> boyList = new ArrayList<User>();
		List<User> grilList = new ArrayList<User>();
		for(User u:tmp) {
			
			if(!org.springframework.util.StringUtils.isEmpty(u.getDorm())) {
				System.out.println(u.getDorm());
				map.put("responseCode", "9999");
				map.put("responseMessage", "请先腾空当前班级下面的全部学员信息,否则不允许使用按班级分配寝室");
				return map;
			}
			if(!org.springframework.util.StringUtils.isEmpty(u.getSex())) {
				if(u.getSex().equals("1")) {
					boyList.add(u);
				}else {
					grilList.add(u);
				}
			}
		}
		
		resultMap.put("boyList", boyList);
		resultMap.put("grilList", grilList);
		
		map.put("responseCode", "0000");
		map.put("responseMessage", "可以分配寝室");
		map.put("result", resultMap);
		return map;
	}
	
	@RequestMapping(value = "saveClazzDorm")
	public String saveClazzDorm(String clazzId, HttpServletRequest request,Model model) {
		User user = new User();
		Office clazz = new Office(clazzId);
		user.setClazz(clazz);
		List<User> tmp = systemService.findAllList(user);
		
		List<User> boyList = new ArrayList<User>();
		List<User> grilList = new ArrayList<User>();
		for(User u:tmp) {
			if(!org.springframework.util.StringUtils.isEmpty(u.getSex())) {
				if(u.getSex().equals("1")) {
					boyList.add(u);
				}else {
					grilList.add(u);
				}
			}
		}
		
		
		
		
		
		ucDormService.addDorm(user);
		
		model.addAttribute("message", "操作成功");
		
		return "modules/dorm/".concat(request.getParameter("studentDormType"));
	}
	
	
	/**
	 * 腾出操作页面
	 * @param ucDorm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "uninfo")
	public String uninfo(UcDorm ucDorm, Model model) {
		return "modules/dorm/unUcDormInfoForm";
	}
	/**
	 * 按学号分配寝室
	 * @param ucDorm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "ajaxStudentnumber")
	@ResponseBody
	public Map<String,Object> ajaxStudentnumber(String studentNumber, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(org.springframework.util.StringUtils.isEmpty(studentNumber)) {
			map.put("responseCode", "9999");
			map.put("responseMessage", "请求参数异常,学号信息异常");
			return map;
		}
		
		User user = new User();
		user.setNo(studentNumber);
		User tmp = systemService.getUser(user);
		if(org.springframework.util.StringUtils.isEmpty(tmp)) {
			map.put("responseCode", "9999");
			map.put("responseMessage", "系统异常,根据学号未查找到相关学生信息");
			return map;
		}
		UcDorm dorm = tmp.getDorm();
		if(!org.springframework.util.StringUtils.isEmpty(dorm)) {
				UcDormBuild dormBuild=	ucDormService.get(dorm.getId()).getUcDormBuild();
				map.put("responseCode", "9998");
				map.put("responseMessage", "不可以分配寝室,当前学生已经入住"+dormBuild.getDormBuildName() + "栋"+dorm.getDormFloor()+"层"+dorm.getDormNumber()+"室");
				return map;
		}
		map.put("responseCode", "0000");
		map.put("responseMessage", "可以分配寝室");
		map.put("result", tmp);
		return map;
	}
	
	@RequestMapping(value = "studentnumber")
	public String studentnumber(UcDorm ucDorm,String studentNumber, Model model) {
		return "modules/dorm/ucDormStudentNumberForm";
	}
	
	
	
	@RequestMapping(value = "saveDorm")
	public String saveDorm(String dorm,String studentNumber, HttpServletRequest request,Model model) {
		User user = new User();
		user.setNo(studentNumber);
		user = systemService.getUser(user);
		
		
		if(!org.springframework.util.StringUtils.isEmpty(user.getDorm())) {
			UcDormBuild dormBuild=	user.getDorm().getUcDormBuild();
			model.addAttribute("message", "当前学员:["+studentNumber+"]已入住"+dormBuild.getDormBuildName() + "栋" + user.getDorm().getDormFloor() +"层"+ user.getDorm().getDormNumber()+"室");
			return "modules/dorm/".concat(request.getParameter("studentDormType"));
		}
		UcDorm ucDorm = ucDormService.get(dorm);
		user.setDorm(ucDorm);
		ucDormService.addDorm(user);
		
		model.addAttribute("message", "操作成功");
		
		return "modules/dorm/".concat(request.getParameter("studentDormType"));
	}
	
	@RequestMapping(value = "unstudentnumber")
	public String unstudentnumber(UcDorm ucDorm,String studentNumber, Model model) {
		return "modules/dorm/unUcDormInfoForm";
	}
	
	
	
	@RequestMapping(value = "ajaxDorm")
	@ResponseBody
	public Map<String,Object> ajaxDorm(String id, Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		UcDorm ucDorm = ucDormService.get(id);
		map.put("responseCode", "0000");
		map.put("responseMessage", "查询成功");
		map.put("result", ucDorm);
		return map;
	}
	
	
	
	@RequestMapping(value = "ajaxStudent")
	@ResponseBody
	public List<User> ajaxStudent(String officeId,String clazzId) {
		return systemService.findListByOfficeIdAndClazzId(officeId,clazzId);
	}
	
	
}