/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dorm.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.thinkgem.jeesite.common.utils.CommonUtils;
import com.thinkgem.jeesite.common.utils.CommonUtils.GroupBy;
import com.thinkgem.jeesite.common.utils.RandomListUtils;
import com.thinkgem.jeesite.common.utils.Reflections;
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

	
	private String[] chuangwei = {"a","b","c","d"};
	
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
	public String saveClazzDorm(String clazzId,String boy,String gril, HttpServletRequest request,Model model) {
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
		
		parseDorm(boy, boyList);
		parseDorm(gril, grilList);
		
		
		
		model.addAttribute("message", "操作成功");
		
		return "modules/dorm/".concat(request.getParameter("studentDormType"));
	}

	private void handerDorm(String boy, List<User> boyList) {
		UcDorm ucDorm = new UcDorm();
		ucDorm.setUcDormBuild(new UcDormBuild(boy));
		
		List<UcDorm> boyDorms = ucDormService.findByParentIdsLike(ucDorm);
		
		 Map<String, List<UcDorm>> floorMap = CommonUtils.group(boyDorms, new GroupBy<String>() {  
	            @Override  
	            public String groupby(Object obj) {  
	            	UcDorm d = (UcDorm) obj;  
	                return d.getDormFloor();
	            }  
	     });  
		
//		for(User u:boyList) {
//			List<UcDorm> boyDorms = ucDormService.findList(ucDorm);
//			//这个地方很麻烦,需要把当前操作的放到数据库里面
//			for(UcDorm dorm:boyDorms) {
//				int total = Integer.valueOf(dorm.getTotal())-Integer.valueOf(dorm.getCnt());
//				for(int i=0;i<total;i++) {
//					u.setDorm(dorm);
//					ucDormService.addDorm(u);
//				}
//			}
//		}
	}
	
	public void parseDorm(String  ucDormBuild,List<User> users) {
		UcDorm ucDorm = new UcDorm();
		ucDorm.setUcDormBuild(new UcDormBuild(ucDormBuild));
		List<UcDorm> boyDorms = ucDormService.findByParentIdsLike(ucDorm);
		
		Map<String, List<UcDorm>> floorMap = CommonUtils.group(boyDorms, new GroupBy<String>() {
			@Override
			public String groupby(Object obj) {
				UcDorm d = (UcDorm) obj;
				return d.getDormFloor();
			}
		});

		List<Integer> list = new ArrayList<Integer>();
		//这个地方有问题,不是计算list大小,计算可入住寝室人数
		for (Map.Entry<String, List<UcDorm>> m : floorMap.entrySet()) {
//			List<UcDorm> tmp = m.getValue();
//			for(UcDorm tm:tmp) {
//				list.add(Integer.valueOf(tm.getCnt()));
//				logger.info("当前楼层:{},当前楼层共有:{}寝室", m.getKey(), m.getValue().size());
//			}
			list.add(m.getValue().size());
			logger.info("当前楼层:{},当前楼层共有:{}寝室", m.getKey(), m.getValue().size());
		}
		int ret = binarysearchKey(list.toArray(new Integer[list.size()]), users.size());
		logger.info("需要分配床位为:{},其中{}趋近于分配条件.权重计算", users.size(), ret);
		int index = list.indexOf(ret);
		List<UcDorm> resultList = null;
		
		for(Map.Entry<String,  List<UcDorm>> m:floorMap.entrySet()) {
			if(m.getValue().size()==ret) {
				resultList = m.getValue();
			}
		}
		logger.info("从{}楼层分配寝室,寝室信息:{}", String.valueOf(index + 1), resultList);
		int cnt = users.size()%4;
		List<User> remainder = RandomListUtils.createRandomList(users, cnt);
		users.removeAll(remainder);
		int y = 0;
		 Collections.sort(resultList);
		loop1: for (UcDorm dorm : resultList) {
			logger.info("准备开始分配寝室,寝室信息:{}", dorm);
			int total = Integer.valueOf(dorm.getTotal()) - Integer.valueOf(dorm.getCnt());
			
			logger.info("当前可分配入住人数:{}", total);
			if (y == users.size()) {
				logger.info("分配结束,跳出循环", total);
				break loop1;
			}
			if (total != 4) {
				for (int i = 0; i < total; i++) {
					User user = remainder.get(i);
					user.setDorm(dorm);
					//添加床位信息
					for(String cw:chuangwei) {
						if(org.springframework.util.StringUtils.isEmpty(Reflections.getFieldValue(dorm, cw))) {
							Reflections.setFieldValue(dorm, cw, user.getId());
							ucDormService.save(dorm);
						}
					}
					
					ucDormService.addDorm(user);
					y++;
				}
			} else {
				// 正常的list学生寝室学生处理
				for (int i = 0; i < 4; i++) {
					String cw = chuangwei[i];
					User user = users.get(y);
					user.setDorm(dorm);
					//添加床位信息
					Reflections.setFieldValue(dorm, cw, user.getId());
					ucDormService.save(dorm);
					
					ucDormService.addDorm(user);
					y++;
				}
			}
			continue loop1;
		}
	}
	
	
	
	
	
	public static void init(String dormFloor,List<UcDorm> boyDorms) {
		int randomNumber = (int) Math.round(Math.random()*99);  
		for(int i=0;i<randomNumber;i++) {
			UcDorm ucDorm = new UcDorm();
			ucDorm.setDormFloor(dormFloor);
			ucDorm.setDormNumber(dormFloor.concat(String.valueOf(randomNumber)));
			
			if (i == 1) {
				ucDorm.setCnt("3");
			} else if (i == 2) {
				ucDorm.setCnt("1");
			} else {
				ucDorm.setCnt("0");
			}
			ucDorm.setTotal("4");
			boyDorms.add(ucDorm);
		}
		
	}
	
	public static int binarysearchKey(Object[] array, int targetNum) {
		Arrays.sort(array);
		int left = 0, right = 0;
		for (right = array.length - 1; left != right;) {
			int midIndex = (right + left) / 2;
			int mid = (right - left);
			int midValue = (Integer) array[midIndex];
			if (targetNum == midValue) {
				return midIndex;
			}

			if (targetNum > midValue) {
				left = midIndex;
			} else {
				right = midIndex;
			}

			if (mid <= 1) {
				break;
			}
		}
		int rightnum = ((Integer) array[right]).intValue();
		int leftnum = ((Integer) array[left]).intValue();
		int ret = Math.abs((rightnum - leftnum) / 2) > Math.abs(rightnum - targetNum) ? rightnum : leftnum;
		if(ret<targetNum) {
			int index = binarysearchKey(array,ret);
			List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < array.length; i++) {
				list.add(array[i]);
			}
			list.remove(index);
			return binarysearchKey(list.toArray(),targetNum);
		}
		return ret;
	} 

	private void handerDorm(String boy, User user) {
		UcDorm ucDorm = new UcDorm();
		ucDorm.setUcDormBuild(new UcDormBuild(boy));
		List<UcDorm> boyDorms = ucDormService.findByParentIdsLike(ucDorm);
		for(UcDorm dorm:boyDorms) {
			int total = Integer.valueOf(dorm.getTotal())-Integer.valueOf(dorm.getCnt());
			for(int i=0;i<total;i++) {
				user.setDorm(dorm);
				ucDormService.addDorm(user);
			}
		}
	}
	
	private void hander(String id, User user,List<User> list) {
		UcDorm ucDorm = new UcDorm();
		ucDorm.setUcDormBuild(new UcDormBuild(id));
		List<UcDorm> boyDorms = ucDormService.findByParentIdsLike(ucDorm);
		
		for(UcDorm dorm:boyDorms) {
			int total = Integer.valueOf(dorm.getTotal())-Integer.valueOf(dorm.getCnt());
			for(int i=0;i<total;i++) {
				user.setDorm(dorm);
				list.add(user);
			}
		}
	}

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
				map.put("result", tmp);
				return map;
		}
		map.put("responseCode", "0000");
		map.put("responseMessage", "可以分配寝室");
		map.put("result", tmp);
		return map;
	}

	/**
	 * 按学号分配寝室
	 * @param ucDorm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "studentnumber")
	public String studentnumber(UcDorm ucDorm,String studentNumber, Model model) {
		return "modules/dorm/ucDormStudentNumberForm";
	}
	
	@RequestMapping(value = "saveDorm")
	public String saveDorm(String dorm,String studentNumber,String bed, HttpServletRequest request,Model model) {
		User user = new User();
		user.setNo(studentNumber);
		user = systemService.getUser(user);
		
		if(!org.springframework.util.StringUtils.isEmpty(user.getDorm())) {
			UcDormBuild dormBuild=	user.getDorm().getUcDormBuild();
			model.addAttribute("message", "当前学员:["+studentNumber+"]已入住"+dormBuild.getDormBuildName() + "栋" + user.getDorm().getDormFloor() +"层"+ user.getDorm().getDormNumber()+"室");
			return "modules/dorm/".concat(request.getParameter("studentDormType"));
		}
		UcDorm ucDorm = ucDormService.get(dorm);
		if(!StringUtils.isEmpty(bed)) {
			Object object = Reflections.getFieldValue(ucDorm, bed);
			if(object!=null||!object.toString().equals("")) {
				model.addAttribute("message", "操作失败,当前床位已经分配给其他学员");
				return "modules/dorm/".concat(request.getParameter("studentDormType"));
			}
			//添加床位信息
			Reflections.setFieldValue(ucDorm, bed, user.getId());
		}
		user.setDorm(ucDorm);
		ucDormService.addDorm(user);
		
		model.addAttribute("message", "操作成功");
		
		return "modules/dorm/".concat(request.getParameter("studentDormType"));
	}
	
	
	
	/**
	 * 按学号腾空寝室
	 * @param ucDorm
	 * @param studentNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "unstudentnumber")
	public String unstudentnumber(UcDorm ucDorm,String studentNumber, Model model) {
		return "modules/dorm/unUcDormStudentNumberForm";
	}
	
	@RequestMapping(value = "flightDormByStudentNumber")
	public String flightDormByStudentNumber(String studentNumber, HttpServletRequest request,Model model) {
		User user = new User();
		user.setNo(studentNumber);
		user = systemService.getUser(user);
		UcDorm ucDorm = user.getDorm();
		if(!org.springframework.util.StringUtils.isEmpty(ucDorm)) {
			int cnt = 0;
			if(!org.springframework.util.StringUtils.isEmpty(ucDorm.getCnt())&&!ucDorm.getCnt().equals("0")) {
				cnt = Integer.valueOf(ucDorm.getCnt())-1;
			}
			ucDorm.setCnt(String.valueOf(cnt));
			//清空床位操作
			for(String s : chuangwei) {
				Object object = Reflections.getFieldValue(ucDorm, s);
				if(!org.springframework.util.StringUtils.isEmpty(object)) {
					if(user.getId().equals(object.toString())) {
						Reflections.setFieldValue(ucDorm, s, null);
					}
				}
			}
			
			
			user.setDorm(null);
			ucDormService.save(ucDorm);
			systemService.updateUserInfo(user);
		}
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
	
	@RequestMapping(value = "flightDormByStudentNumbers")
	public String flightDormByStudentNumbers(String[] studentNumber, HttpServletRequest request,Model model) {
		
		for(String s : studentNumber) {
			User user = new User();
			user.setNo(s);
			user = systemService.getUser(user);
			UcDorm ucDorm = ucDormService.get(user.getDorm());
			if(!org.springframework.util.StringUtils.isEmpty(ucDorm)) {
				int cnt = 0;
				if(!org.springframework.util.StringUtils.isEmpty(ucDorm.getCnt())&&ucDorm.getCnt().equals("0")) {
					 cnt = Integer.valueOf(ucDorm.getCnt())-1;
					 //床位信息
					for (String cw : chuangwei) {
						Object object = Reflections.getFieldValue(ucDorm, cw);
						if (!org.springframework.util.StringUtils.isEmpty(object)) {
							if (user.getId().equals(object.toString())) {
								Reflections.setFieldValue(ucDorm, cw, null);
							}
						}
					}
				}
				ucDorm.setCnt(String.valueOf(cnt));
				user.setDorm(null);
				systemService.updateUserInfo(user);
				ucDormService.save(ucDorm);
			}
			
		}
		model.addAttribute("message", "操作成功");
		return "modules/dorm/".concat(request.getParameter("studentDormType"));
	}
	
	
	@RequestMapping(value = "unclazz")
	public String unclazz(Model model) {
		return "modules/dorm/unUcDormClazzForm";
	}
	
	
	@RequestMapping(value = "flightDormByClazz")
	public String flightDormByStudentNumbers(String clazzId, HttpServletRequest request,Model model) {
		
		User pojo = new User();
		Office clazz = new Office();
		clazz.setId(clazzId);
		pojo.setClazz(clazz);
		List<User> users = systemService.findUserByUserPojo(pojo);
		for(User user : users) {
			user = systemService.getUser(user);
			if(!org.springframework.util.StringUtils.isEmpty(user.getDorm())) {
				UcDorm ucDorm = ucDormService.get(user.getDorm());
				int cnt = 0;
				if(!org.springframework.util.StringUtils.isEmpty(ucDorm.getCnt())&&ucDorm.getCnt().equals("0")) {
					 cnt = Integer.valueOf(ucDorm.getCnt())-1;
				}
				ucDorm.setCnt(String.valueOf(cnt));
				user.setDorm(null);
				systemService.updateUserInfo(user);
				ucDormService.save(ucDorm);
			}
		}
		model.addAttribute("message", "操作成功");
		return "modules/dorm/".concat(request.getParameter("studentDormType"));
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
	
	
	@RequestMapping(value = "ajaxStudentByDorm")
	@ResponseBody
	public List<User> ajaxStudent(@RequestParam("dormId") String dormId) {
		User user = new User();
		UcDorm dorm = new UcDorm();
		dorm.setId(dormId);
		user.setDorm(dorm);
		return systemService.findUserByUserPojo(user);
	}
	
}