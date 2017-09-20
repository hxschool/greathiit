package com.thinkgem.jeesite.modules.api.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.service.ApiService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.uc.entity.UcStudent;

@Controller
@RequestMapping(value = "api")
public class ApiController extends BaseController {
	@Autowired
	private ApiService apiService;
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
	public Map<String, Object> getStudent(@PathVariable("s") String s) {
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
	
	
}
