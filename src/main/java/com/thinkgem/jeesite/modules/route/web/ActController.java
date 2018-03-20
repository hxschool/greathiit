package com.thinkgem.jeesite.modules.route.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.annotation.SameUrlData;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "/actUser")
public class ActController {
	
	private String deptId = "00";
	
	private static Map<String,String> map = new HashMap<String,String>();
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	
	@RequestMapping
	@ResponseBody
	@SameUrlData
	public Map<String,String> actUser(ActUser actUser) {
		
		Map<String,String> resultMap = new HashMap<String,String>();
		
		if(StringUtils.isEmpty(actUser.getCode())||!actUser.getCode().toLowerCase().equals("hxci")) {
			resultMap.put("responseCode", "99999999");
			resultMap.put("responseMessage", "口令信息不合法");
			return resultMap;
		}
		
		if(StringUtils.isEmpty(actUser.getName())||StringUtils.isEmpty(actUser.getIdcard())) {
			resultMap.put("responseCode", "99999999");
			resultMap.put("responseMessage", "姓名或身份证不允许为空");
			return resultMap;
		}
		if(StringUtils.isEmpty(actUser.getDept())) {
			resultMap.put("responseCode", "99999999");
			resultMap.put("responseMessage", "部门不允许为空");
			return resultMap;
		}
		
		if(StringUtils.isEmpty(actUser.getPhone())) {
			resultMap.put("responseCode", "99999999");
			resultMap.put("responseMessage", "手机号不允许为空");
			return resultMap;
		}
		if(StringUtils.isEmpty(actUser.getEmail())) {
			resultMap.put("responseCode", "99999999");
			resultMap.put("responseMessage", "email不允许为空");
			return resultMap;
		}
		
		if(!StringUtils.isEmpty(actUser.getNo())) {
			User tmp = new User();
			tmp.setNo(actUser.getNo());
			User u = systemService.getUser(tmp);
			if(!StringUtils.isEmpty(u)) {
				if(u.getName().equals(actUser.getName())&&u.getLoginName().equals(actUser.getIdcard())) {
					resultMap.put("responseCode", "00000000");
					resultMap.put("responseMessage", "操作成功,请记住教师编号("+ actUser.getNo()+")");
					resultMap.put("responseResult", actUser.getNo());
					return resultMap;
				}
			}
			
		}
		User tmp = new User();
		tmp.setName(actUser.getName());
		tmp.setLoginName(actUser.getIdcard());
		User entity = systemService.getUserByNameAndIdCard(tmp);
		if(!StringUtils.isEmpty(entity)) {
			resultMap.put("responseCode", "00000000");
			resultMap.put("responseMessage", "操作成功,请记住教师编号("+entity.getNo()+")");
			resultMap.put("responseResult", entity.getNo());
			return resultMap;
		}

		
		String key = actUser.getDept();
		String no = rec(key);
		
		User user = new User();
		user.setNo(no);
		user.setName(actUser.getName());
		user.setLoginName(actUser.getIdcard());
		user.setMobile(actUser.getMobile());
		user.setPhone(actUser.getPhone());
		user.setEmail(actUser.getEmail());
		String password = actUser.getIdcard().substring(actUser.getIdcard().length()-6);
		user.setPassword(SystemService.entryptPassword(password));
		Role role = new Role(key);
		List<Role> rs = new ArrayList<Role>();
		rs.add(role);
		user.setRole(role);
		user.setRoleList(rs);
		user.setLoginFlag("1");
		user.setUserType("1");
		user.setCompany(new Office("1"));
		if(deptId.equals("00")) {
			deptId = "1";
		}
		user.setOffice(new Office("1"));
		
		User u = UserUtils.get("1");
		user.setCreateBy(u);
		user.setCreateDate(new Date());
		user.setDelFlag("0");
		user.setUpdateBy(u);
		user.setUpdateDate(new Date());
		user.setRemarks("认证教师信息");
		if(Global.getConfig("virtualAccount").equals("true")){
			//开通虚拟账户系统
			String accountNo = "1";
			user.setAccountNo(accountNo);
		}
		systemService.saveUser(user);
		
		
		resultMap.put("responseCode", "00000000");
		resultMap.put("responseMessage", "交易成功");
		resultMap.put("responseResult", no);
		//resultMap.put("responseCode", "99999999");
		//resultMap.put("responseMessage", "系统异常分法提交参数");
		return resultMap;
	}

	
	public String rec(String key) {
		String ret = null;
		String value = map.get(key);
		if (StringUtils.isEmpty(value)) {
			map.put(key, "01");
		} else {
			Integer tmp = Integer.valueOf(value);
			tmp = tmp + 1;
			if (tmp < 10) {
				map.put(key, "0".concat(String.valueOf(tmp)));
				value = "0".concat(String.valueOf(tmp));
			} else {
				map.put(key, String.valueOf(tmp));
				value = String.valueOf(tmp);
			}
		}
		Role role = systemService.getRole(key);

		Office pojo = new Office();
		pojo.setName(role.getName());
		Office entity = officeService.get(pojo);
		deptId = "00";
		if (!StringUtils.isEmpty(entity)) {
			deptId = entity.getId();
		}

		ret = "66".concat(deptId).concat(key).concat(map.get(key));
		User user = new User();
		user.setNo(ret);
		User u = systemService.getUser(user);
		if(!StringUtils.isEmpty(u)) {
			rec(value);
		}
		
		return ret;
	}
}
