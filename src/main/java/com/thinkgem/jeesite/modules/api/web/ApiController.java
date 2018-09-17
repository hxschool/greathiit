package com.thinkgem.jeesite.modules.api.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greathiit.common.util.SecureRequest;
import com.greathiit.common.util.SecureUser;
import com.greathiit.common.util.SecureUtil;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.service.ApiService;
import com.thinkgem.jeesite.modules.api.web.adapter.BigDecimalDefault0Adapter;
import com.thinkgem.jeesite.modules.api.web.adapter.DoubleDefault0Adapter;
import com.thinkgem.jeesite.modules.api.web.adapter.IntegerDefault0Adapter;
import com.thinkgem.jeesite.modules.api.web.adapter.LongDefault0Adapter;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.SysAppconfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;

@Controller
@RequestMapping(value = "api")
public class ApiController extends BaseController {
	@Autowired
	private ApiService apiService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DictService dictService;
	@Autowired
	private SysAppconfigService sysAppconfigService;
	
	
	public <T> T getRequest(HttpServletRequest request, HttpServletResponse response,Class<T> clazz) throws Exception {
		try {
			String json = IOUtils.toString(request.getInputStream());
			if (StringUtils.isEmpty(json)) {
				Map<String, String[]> map = request.getParameterMap();
				Iterator<String> it = map.keySet().iterator();
				Map<String, String> resultMap = new HashMap<String, String>();
				while (it.hasNext()) {
					String key = it.next().toString();
					String[] values = map.get(key);
					resultMap.put(key, values[0]);
				}
				json = new Gson().toJson(resultMap);
			}
			
			if(StringUtils.isEmpty(json)) {
				throw new Exception("请求业务信息不合法,请查看相关接口文档");
			}
			
			Gson gson = new GsonBuilder().setLenient().enableComplexMapKeySerialization().serializeNulls()
					.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
			SecureRequest secureRequest = gson.fromJson(json, SecureRequest.class);
			String appid = secureRequest.getAPPID();
			SysAppconfig sysAppconfig = sysAppconfigService.getByAppId(appid);
			String otherPublicKey = sysAppconfig.getPublickey();
			
			String resultJson = SecureUtil.decryptTradeInfo(appid, secureRequest.getCER(), secureRequest.getDATA(), secureRequest.getSIGN(), "", otherPublicKey);
			T t = new  GsonBuilder()
					.registerTypeAdapter(BigDecimal.class,new BigDecimalDefault0Adapter())
				    .registerTypeAdapter(Integer.class,new IntegerDefault0Adapter())
				    .registerTypeAdapter(int.class,new IntegerDefault0Adapter())
				    .registerTypeAdapter(Double.class,new DoubleDefault0Adapter())
				    .registerTypeAdapter(double.class,new DoubleDefault0Adapter())
				    .registerTypeAdapter(Long.class,new LongDefault0Adapter())
				    .registerTypeAdapter(long.class,new LongDefault0Adapter())
				    .create().fromJson(resultJson,clazz);
			return t;
		} catch (Exception ex) {
			logger.error("解析出错...错误原因:{}",ex.getMessage());
			throw new Exception("解析异常");
			
		}
	}
	
	 
	
	@RequestMapping(value = "getStudentNumber")
	@ResponseBody
	public Map<String, Object> getStudentNumber(String username,String idCard) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "00000000");
		map.put("message", "获取学生信息成功");
		String studentNumber = apiService.getStudentNumber(username,idCard);
		map.put("studentNumber", studentNumber);
		return map;
	}
	
	@RequestMapping(value = "getStudents")
	@ResponseBody
	public Map<String, Object> getStudents(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String  appid = getRequest(request,response,String.class);
			SysAppconfig sysAppconfig = sysAppconfigService.getByAppId(appid);
			if(StringUtils.isEmpty(sysAppconfig)) {
				map.put("status", "99999999");
				map.put("message", "请求没有授权,请求消息APPID不存在");
				return map;
			}
			LogUtils.saveLog(request, "APPID:"+appid+"获取全部学生数据");
			map.put("status", "00000000");
			map.put("message", "获取全部学生信息成功");
			List<Student> students = apiService.getStudents(new Student());

			map.put("result", students);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getStudent")
	@ResponseBody
	public Map<String, Object> getStudent(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String  studentNumber = getRequest(request,response,String.class);
			map.put("status", "00000000");
			map.put("message", "获取学生信息成功");
			Student student = apiService.getStudent(studentNumber);
			student.setIdCard(null);
			map.put("result", student);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getTeachers")
	@ResponseBody
	public Map<String, Object> getTeachers(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String  appid = getRequest(request,response,String.class);
			SysAppconfig sysAppconfig = sysAppconfigService.getByAppId(appid);
			if(StringUtils.isEmpty(sysAppconfig)) {
				map.put("status", "99999999");
				map.put("message", "请求没有授权,请求消息APPID不存在");
				return map;
			}
			LogUtils.saveLog(request, "APPID:"+appid+"获取全部教师数据");
			map.put("status", "00000000");
			map.put("message", "获取教师信息成功");
			List<Teacher> teachers = apiService.getTeachers(new Teacher());
			map.put("result", teachers);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getTeacher")
	@ResponseBody
	public Map<String, Object> getTeacher(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String  teacherName = getRequest(request,response,String.class);
			map.put("status", "00000000");
			map.put("message", "获取教师信息成功");
			Teacher teacher = apiService.getTeacher(teacherName);
			teacher.setTchrIdcard(null);
			map.put("result", teacher);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	
	@RequestMapping(value = "getDicts")
	@ResponseBody
	public Map<String, Object> getDicts(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("status", "00000000");
			map.put("message", "获取字典信息成功");
			List<Dict> dicts = dictService.findList(new Dict());
			map.put("result", dicts);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getDictByType")
	@ResponseBody
	public Map<String, Object> getDictByType(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String  type = getRequest(request,response,String.class);
			map.put("status", "00000000");
			map.put("message", "获取字典信息成功");
			List<Dict> dicts = DictUtils.getDictList(type);
			map.put("result", dicts);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getDictByTypeAndValue")
	@ResponseBody
	public Map<String, Object> getDictByTypeAndValue(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String,String>  m = getRequest(request,response,Map.class);
			map.put("status", "00000000");
			map.put("message", "获取字典信息成功");
			Dict dict = new Dict();
			dict.setType(m.get("type"));
			dict.setValue(m.get("value"));
			Dict entity = dictService.get(dict);
			map.put("result", entity);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getDictByTypeAndLabel")
	@ResponseBody
	public Map<String, Object> getDictByTypeAndLabel(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String,String>  m = getRequest(request,response,Map.class);
			map.put("status", "00000000");
			map.put("message", "获取字典信息成功");
			Dict dict = new Dict();
			dict.setType(m.get("type"));
			dict.setLabel(m.get("label"));
			Dict entity = dictService.get(dict);
			map.put("result", entity);
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getClass")
	@ResponseBody
	public Map<String, Object> getClass(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String classno = getRequest(request,response,String.class);
			map.put("status", "00000000");
			map.put("message", "班级信息成功");
			map.put("result", 	apiService.getClass(classno));
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	@RequestMapping(value = "getInClass")
	@ResponseBody
	public Map<String, Object> getInClass(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String classno = getRequest(request,response,String.class);
			map.put("status", "00000000");
			map.put("message", "班级信息成功");
			map.put("result", 	apiService.getInClass(classno));
		} catch (Exception e) {
			map.put("status", "99999999");
			map.put("message", e.getMessage());
			renderString(response, map);
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "getRole")
	public Map<String, Object> getRole(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Role> list1 = systemService.findAllRoles();
		map.put("status", "00000000");
		map.put("message", "班级信息成功");
		map.put("result", 	list1);
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
			map.put("status", "00000000");
			map.put("message", "获取用户信息成功");
			e.printStackTrace();
		}
		return map;
	}
	

	
	//获取学院信息
	@RequestMapping(value = "getCollege")
	@ResponseBody
	public Map<String, Object> getCollege() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("responseCode", "00000000");
		map.put("responseMessage", "获取参数信息成功");
		map.put("result", apiService.getCollege());
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
