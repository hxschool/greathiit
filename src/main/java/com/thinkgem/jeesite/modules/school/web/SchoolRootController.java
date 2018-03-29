/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.web;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.school.entity.SchoolCourse;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.school.service.SchoolCourseService;
import com.thinkgem.jeesite.modules.school.service.SchoolRootService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;

/**
 * 楼宇Controller
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/school/schoolRoot")
public class SchoolRootController extends BaseController {

	@Autowired
	private SchoolRootService schoolRootService;
	@Autowired
	private SchoolCourseService schoolCourseService;
	
	@ModelAttribute
	public SchoolRoot get(@RequestParam(required=false) String id) {
		SchoolRoot entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = schoolRootService.get(id);
		}
		if (entity == null){
			entity = new SchoolRoot();
		}
		return entity;
	}
	
	
	@RequiresPermissions("school:schoolRoot:view")
	@RequestMapping(value = {"list", ""})
	public String list(SchoolRoot schoolRoot, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SchoolRoot> page = schoolRootService.findPage(new Page<SchoolRoot>(request, response), schoolRoot); 
		model.addAttribute("page", page);
		return "modules/school/schoolRootList";
	}

	@RequiresPermissions("school:schoolRoot:view")
	@RequestMapping(value = "form")
	public String form(SchoolRoot schoolRoot, Model model) {
		model.addAttribute("schoolRoot", schoolRoot);
		return "modules/school/schoolRootForm";
	}

	@RequiresPermissions("school:schoolRoot:edit")
	@RequestMapping(value = "save")
	public String save(SchoolRoot schoolRoot, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, schoolRoot)){
			return form(schoolRoot, model);
		}
		schoolRootService.save(schoolRoot);
		addMessage(redirectAttributes, "保存楼宇成功");
		return "redirect:"+Global.getAdminPath()+"/school/schoolRoot/?repage";
	}
	
	@RequiresPermissions("school:schoolRoot:edit")
	@RequestMapping(value = "delete")
	public String delete(SchoolRoot schoolRoot, RedirectAttributes redirectAttributes) {
		schoolRootService.delete(schoolRoot);
		addMessage(redirectAttributes, "删除楼宇成功");
		return "redirect:"+Global.getAdminPath()+"/school/schoolRoot/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "treeLink")
	public List<TreeLink> treeLink( HttpRequest request, HttpServletResponse response) {
		
		List<SchoolRoot> list1 = schoolRootService.findByParentId("0");
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(SchoolRoot school1:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(school1.getValue());
			treeLink.setName(school1.getLabel());
			List<SchoolRoot> list2 = schoolRootService.findByParentId(school1.getId());
			User user = UserUtils.getUser();
			if(!user.getId().equals("1")) {
				SchoolCourse schoolCourse = new SchoolCourse();
				schoolCourse.setCurrentUser(user);
				List<SchoolCourse> schoolCourses = schoolCourseService.findList(schoolCourse);
				List<SchoolRoot> schoolRoots = new ArrayList<SchoolRoot>();
				for(SchoolCourse sc : schoolCourses) {
					schoolRoots.add(sc.getSchoolRoot());
				}
				list2.retainAll(schoolRoots);
			}
			
			
			List<TreeLink> treeLinks2 = new ArrayList<TreeLink>();
			for(SchoolRoot schoolRoot2:list2) {
				TreeLink treeLink2 = new TreeLink();
				treeLink2.setValue(schoolRoot2.getValue());
				treeLink2.setName(schoolRoot2.getLabel());
				treeLinks2.add(treeLink2);
			}
			treeLink.setSub(treeLinks2);
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}
	
	public static void main(String[] args) {
		List l1 = new ArrayList();
		l1.add("1");
		l1.add("2");
		List l2 = new ArrayList();
		l2.add("1");
		l1.retainAll(l2);
		System.out.println(l1);
	}
	
	@ResponseBody
	@RequestMapping(value = "treeLinkId")
	public List<TreeLink> treeLinkId( HttpRequest request, HttpServletResponse response) {
		
		List<SchoolRoot> list1 = schoolRootService.findByParentId("0");
		List<TreeLink> treeLinks1 = new ArrayList<TreeLink>();
		for(SchoolRoot school1:list1) {
			TreeLink treeLink = new TreeLink();
			treeLink.setValue(school1.getId());
			treeLink.setName(school1.getLabel());
			List<SchoolRoot> list2 = schoolRootService.findByParentId(school1.getId());
			List<TreeLink> treeLinks2 = new ArrayList<TreeLink>();
			for(SchoolRoot schoolRoot2:list2) {
				TreeLink treeLink2 = new TreeLink();
				treeLink2.setValue(schoolRoot2.getId());
				treeLink2.setName(schoolRoot2.getLabel());
				treeLinks2.add(treeLink2);
			}
			treeLink.setSub(treeLinks2);
			treeLinks1.add(treeLink);
			
		}
		return treeLinks1;
	}
	
}