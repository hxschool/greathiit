package com.thinkgem.jeesite.modules.api.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.greathiit.common.util.SecureRequest;
import com.greathiit.common.util.SecureUser;
import com.greathiit.common.util.SecureUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.service.ApiService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SysAppconfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

@Controller
@RequestMapping(value = "api")
public class ApiController extends BaseController {
	@Autowired
	private ApiService apiService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SysAppconfigService sysAppconfigService;
	 
	
	@RequestMapping(value = "getStudentNumber")
	@ResponseBody
	public Map<String, Object> getStudentNumber(String username,String idCard) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取学生信息成功");
		String studentNumber = apiService.getStudentNumber(username, idCard);
		map.put("studentNumber", studentNumber);
		return map;
	}
	
	@RequestMapping(value = "getUser")
	@ResponseBody
	public Map<String, Object> getUser(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取用户信息成功");
		try {
			
			String json = IOUtils.toString(request.getInputStream());
			SecureRequest secureRequest = new Gson().fromJson(json, SecureRequest.class);
			String appid = secureRequest.getAPPID();
			String cer = secureRequest.getCER();
			String data = secureRequest.getDATA();
			String sign = secureRequest.getSIGN();
			SysAppconfig sysAppconfig = sysAppconfigService.getByAppId(appid);
			String otherPublicKey = sysAppconfig.getPublickey();
			String retVal = SecureUtil.decryptTradeInfo(appid, cer, data, sign, Global.privateKey, otherPublicKey);
			String loginName = new Gson().fromJson(retVal, String.class);
			User user = systemService.getUserByLoginName(loginName);
			if(!StringUtils.isEmpty(user)) {
				SecureUser secureUser = new SecureUser();
				BeanUtils.copyProperties(user, secureUser);
				String tradeJson = new Gson().toJson(secureUser);
				Map<String,String> result = SecureUtil.encryptTradeInfo(appid,tradeJson, Global.privateKey, otherPublicKey);
				map.putAll(result);
			}
		} catch (IOException e) {
			map.put("responseCode", "99999999");
			map.put("responseMessage", "获取用户信息成功");
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "getStudent")
	@ResponseBody
	public Map<String, Object> getStudent(String username,String idCard,String number) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取学生信息成功");
		UcStudent student = apiService.getStudentNumber(username, idCard,number);
		map.put("student", student);
		return map;
	}

	@RequestMapping(value = "parameter/{s}")
	@ResponseBody
	public Map<String, Object> getParameter(@PathVariable("s") String s) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取参数信息成功");
		map.put("result", DictUtils.getDictList(s));
		return map;
	}
	
	@RequestMapping(value = "getDepartment")
	@ResponseBody
	public Map<String, Object> getDepartment() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取参数信息成功");
		map.put("result", apiService.getDepartment());
		return map;
	}
	
	
	@RequestMapping(value = "getMajor")
	@ResponseBody
	public Map<String, Object> getMajor() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取参数信息成功");
		map.put("result", apiService.getMajor());
		return map;
	}
	
	@RequestMapping(value = "getArea")
	@ResponseBody
	public Map<String, Object> getArea(String parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取参数信息成功");
		map.put("result", apiService.getArea(parentId));
		return map;
	}

	
	@ResponseBody
	@RequestMapping(value = "ajaxRoles")
	public List<Role> ajaxRoles(HttpServletRequest request, HttpServletResponse response) {

		List<Role> list1 = systemService.findAllRoles();
		List<Role> ret = new ArrayList<Role>();
		for (Role r : list1) {
			if (Integer.valueOf(r.getId()) > 10 && Integer.valueOf(r.getId()) < 99) {
				Role role = new Role();
				role.setId(r.getId());
				role.setName(r.getName());
				ret.add(role);
			}
		}
		return ret;
	}
	@ResponseBody
	@RequestMapping(value = "ajaxUser")
	public List<User> ajaxUser(HttpServletRequest request, HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		return systemService.findUserByRoleId(roleId);
	}
}
