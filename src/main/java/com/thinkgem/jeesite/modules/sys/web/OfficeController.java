/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

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

import com.aliyuncs.http.HttpRequest;
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
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 组织Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute("office")
	public Office get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return officeService.get(id);
		}else{
			return new Office();
		}
	}
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {""})
	public String index(Office office, Model model) {
//        model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"list"})
	public String list(Office office, Model model) {
        model.addAttribute("list", officeService.findByParentIdsLike(office));
		return "modules/sys/officeList";
	}
	
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = {"detail"})
	public String detail(Office office, Model model) {
		model.addAttribute("type",office.getType());
		model.addAttribute("label",DictUtils.getDictLabel(office.getType(), "sys_office_type", ""));
        model.addAttribute("list", officeService.findList(office));
        
		return "modules/sys/officeDetail";
	}
	
	
	
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(Office office, Model model) {
		User user = UserUtils.getUser();
		if (office.getParent()==null || office.getParent().getId()==null){
			office.setParent(user.getOffice());
		}
		Office officeParent = officeService.get(office.getParent().getId());
		int type = Integer.valueOf(officeParent.getType())+1;
		office.setType(String.valueOf(type));
		office.setGrade(String.valueOf(type));
		office.setParent(officeParent);
		if (office.getArea()==null){
			office.setArea(user.getOffice().getArea());
		}
		// 自动获取排序号
		/*
		if (StringUtils.isBlank(office.getId())&&office.getParent()!=null){
			int size = 0;
			List<Office> list = officeService.findAll();
			for (int i=0; i<list.size(); i++){
				Office e = list.get(i);
				if (e.getParent()!=null && e.getParent().getId()!=null
						&& e.getParent().getId().equals(office.getParent().getId())){
					size++;
				}
			}
			office.setCode(office.getParent().getCode() + StringUtils.leftPad(String.valueOf(size > 0 ? size+1 : 1), 3, "0"));
		}
		*/
		
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}
	
	@RequiresPermissions("sys:office:edit")
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
		
		if(office.getChildDeptList()!=null){
			Office childOffice = null;
			for(String id : office.getChildDeptList()){
				childOffice = new Office();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("2");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade())+1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}
		
		addMessage(redirectAttributes, "保存组织'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id="+id+"&parentIds="+office.getParentIds();
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(Office office, RedirectAttributes redirectAttributes) {

//		if (Office.isRoot(id)){
//			addMessage(redirectAttributes, "删除组织失败, 不允许删除顶级组织或编号空");
//		}else{
			officeService.delete(office);
			addMessage(redirectAttributes, "删除组织成功");
//		}
		return "redirect:" + adminPath + "/sys/office/list?id="+office.getParentId()+"&parentIds="+office.getParentIds();
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "remove")
	public String remove(Office office, RedirectAttributes redirectAttributes) {

		officeService.remove(office);
		addMessage(redirectAttributes, "删除组织成功");
		return "redirect:" + adminPath + "/sys/office/list?id="+office.getParentId()+"&parentIds="+office.getParentIds();
	}

	/**
	 * 获取组织JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findList(isAll);
		for (int i=0; i<list.size(); i++){
			Office office = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(office.getId()) && office.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(office.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(office.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(office.getUseable())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", office.getId());
				map.put("pId", office.getParentId());
				map.put("pIds", office.getParentIds());
				map.put("name", office.getName());
				if (type != null && "4".equals(type)){
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		
		
		return mapList;
	}
	
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeLink")
	public List<TreeLink> treeLink(@RequestParam(required=false,defaultValue="1") String parnetId, HttpRequest request, HttpServletResponse response) {
		return officeService.treeLink(parnetId);
	}

	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeClassLink")
	public List<TreeLink> treeClassLink(@RequestParam(required=false,defaultValue="1") String parnetId, HttpRequest request, HttpServletResponse response) {
		return officeService.treeClassLink(parnetId);
	}
	
	@ResponseBody
	@RequestMapping(value = "ajaxClass")
	public List<Office> ajaxClass(@RequestParam(required=false,defaultValue="1") String parnetId,@RequestParam(required=false,defaultValue="2017") String grade, HttpRequest request, HttpServletResponse response) {
		List<Office> list = officeService.findByParentIdAndYear(parnetId,grade);
		return list;
	}
}
