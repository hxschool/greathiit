package com.thinkgem.jeesite.modules.recruit.web.student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitTotalMajorClass;
import com.thinkgem.jeesite.modules.recruit.service.student.RecruitStudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/recruit/student/assign")
public class AssignClazzController  extends BaseController{
	@Autowired
	private RecruitStudentService recruitStudentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@RequestMapping(value = {"config", ""})
	public String config(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回专业相关信息
		Object object = JedisUtils.getObject(JedisUtils.GREEN_CLASS_MARK);
		if(StringUtils.isEmpty(object)) {
			object = recruitStudentService.totalMajor(null);
			JedisUtils.setObject(JedisUtils.GREEN_CLASS_MARK, object , 0);
		}
		model.addAttribute("list", object);
		return "modules/recruit/student/recruitConfig";
	}
	
	@RequestMapping("operation")
	public String operation(String modeSwitch,String majorId,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		//返回专业相关信息
		
		String majorName = "";
		if(!org.springframework.util.StringUtils.isEmpty(modeSwitch)) {
			
			List<RecruitTotalMajorClass> list = (List<RecruitTotalMajorClass>)JedisUtils.getObject(JedisUtils.GREEN_CLASS_MARK);
			Iterator<RecruitTotalMajorClass> iter = list.iterator();
			
	        while (iter.hasNext()) {
	        	RecruitTotalMajorClass item = iter.next();
	            if (item.getMajorId().equals(majorId)) {
	            	majorName = item.getMajorName();
	            	if(modeSwitch.equals("1")) {
	            		item.setFlagShow("1");
	            		item.setFlagStatus("1");
	            	}
	            }
	        }
	        JedisUtils.setObject(JedisUtils.GREEN_CLASS_MARK, list , 0);
			//自动分班
			if(!modeSwitch.equals("1")) {
				User user = new User();
				Office office = new Office();
				office.setId(majorId);
				user.setOffice(office);
				List<User> users = systemService.nassignedStudentNumberByMajorId(user);
				model.addAttribute("modeSwitch", modeSwitch);
				model.addAttribute("majorId", majorId);
				model.addAttribute("users", users);
				return "modules/recruit/student/assignUser";
			}
		}
		addMessage(redirectAttributes, "["+majorName+"] 专业设置自动分班状态成功");
		return "modules/recruit/student/assignSuccess";
	}

	@RequestMapping("ajaxResetSetup")
	@ResponseBody
	public Map<String,String> ajaxResetSetup(String majorId,String classNo,String ids) {
		Map<String,String> map = new HashMap<String,String>();
		User user = UserUtils.get(ids);
		user.setClazz(null);
		user.setNo(null);
		user.setLoginIp("0.0.0.0");
		user.setRemarks("重置学号");
		systemService.saveUser(user);
		map.put("responseCode", "00000000");
		map.put("responseMessage", "为当前班级分配学号成功");
		return map;
	}
	
	
	
	@RequestMapping("ajaxSingleSetup")
	@ResponseBody
	public Map<String,String> ajaxSingleSetup(String majorId,String classNo,String ids) {
		String clazzId = StudentUtil.assignClasses(majorId, classNo);
		Office clazz = officeService.get(clazzId);
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isEmpty(clazz.getRemarks())) {
			clazz.setRemarks("1");
		}

		int i = Integer.valueOf(clazz.getRemarks());
		User user = UserUtils.get(ids);
		clazz.setId(clazzId);
		user.setClazz(clazz);
		String s = String.format("%02d", i);
		String no = clazzId.concat(s);
		user.setNo(no);
		user.setLoginIp("0.0.0.0");
		user.setRemarks("手动设置学号");
		systemService.saveUser(user);
		i++;
		clazz.setRemarks(String.valueOf(i));
		officeService.save(clazz);

		map.put("responseCode", "00000000");
		map.put("responseMessage", "为当前班级分配学号成功");
		return map;
	}
	
	@RequestMapping("ajaxSetup")
	@ResponseBody
	public Map<String,String> ajaxSetup(String majorId,String classNo,@RequestParam("ids[]") String[] ids) {
		String clazzId = StudentUtil.assignClasses(majorId, classNo);
		Office clazz = officeService.get(clazzId);
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtils.isEmpty(clazz.getRemarks())) {
			clazz.setRemarks("1");
		}
		if((Integer.valueOf(clazz.getRemarks()) + ids.length)>30) {
			map.put("responseCode", "99999999");
			map.put("responseMessage", "当前分班学生量大于默认人数30,超出人数:" + ((Integer.valueOf(clazz.getRemarks()) + ids.length) - 30));
			return map;
		}
		if(Integer.valueOf(clazz.getRemarks())>30) {
			map.put("responseCode", "99999999");
			map.put("responseMessage", "当前班级人数已经大于30,请手动进行分配学号");
			return map;
		}
		
		int i = 0;
		for(String str : ids) {
			i = Integer.valueOf(clazz.getRemarks());
			User user = UserUtils.get(str);
			clazz.setId(clazzId);
			user.setClazz(clazz);
			String s = String.format("%02d", i);
			String no = clazzId.concat(s);
			user.setNo(no);
			user.setLoginIp("0.0.0.0");
			user.setRemarks("手动设置学号");
			systemService.saveUser(user);
			i++;
			clazz.setRemarks(String.valueOf(i));
			officeService.save(clazz);
		}
		map.put("responseCode", "00000000");
		map.put("responseMessage", "为当前班级分配学号成功");
		return map;
	}
}
