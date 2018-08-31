/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.school.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.school.entity.SchoolCourse;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.school.service.SchoolCourseService;
import com.thinkgem.jeesite.modules.school.service.SchoolRootService;

/**
 * 学院教室管理Controller
 * @author 赵俊飞
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/school/schoolCourse")
public class SchoolCourseController extends BaseController {

	@Autowired
	private SchoolCourseService schoolCourseService;
	@Autowired
	private SchoolRootService schoolRootService;
	
	@ModelAttribute
	public SchoolCourse get(@RequestParam(required=false) String id) {
		SchoolCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = schoolCourseService.get(id);
		}
		if (entity == null){
			entity = new SchoolCourse();
		}
		return entity;
	}
	
	@RequiresPermissions("school:schoolCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(SchoolCourse schoolCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SchoolCourse> page = schoolCourseService.findPage(new Page<SchoolCourse>(request, response), schoolCourse); 
		model.addAttribute("page", page);
		return "modules/school/schoolCourseList";
	}

	@RequiresPermissions("school:schoolCourse:view")
	@RequestMapping(value = "form")
	public String form(SchoolCourse schoolCourse, Model model) {
		model.addAttribute("schoolCourse", schoolCourse);
		return "modules/school/schoolCourseForm";
	}

	@RequiresPermissions("school:schoolCourse:edit")
	@RequestMapping(value = "save")
	public String save(SchoolCourse schoolCourse,String[] schoolRoots, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, schoolCourse)){
			return form(schoolCourse, model);
		}
		for(String id:schoolRoots) {
			SchoolRoot schoolRoot = schoolRootService.get(id);
			schoolCourse.setSchoolRoot(schoolRoot);
			logger.info("配置相关学院拥有课程信息:{}",schoolCourse);
			SchoolCourse entity = schoolCourseService.get(schoolCourse);
			
			if(!org.springframework.util.StringUtils.isEmpty(entity)&&!org.springframework.util.StringUtils.isEmpty(entity.getId())) {
				logger.info("更新操作相关记录信息");
				if(!org.springframework.util.StringUtils.isEmpty(schoolCourse.getSchoolRoot())) {
					schoolCourseService.save(schoolCourse);
				}
			}
			
			if(org.springframework.util.StringUtils.isEmpty(entity)) {
				schoolCourseService.save(schoolCourse);
			}
		}
		
		addMessage(redirectAttributes, "保存学院教室管理成功");
		return "redirect:"+Global.getAdminPath()+"/school/schoolCourse/?repage";
	}
	
	@RequiresPermissions("school:schoolCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(SchoolCourse schoolCourse, RedirectAttributes redirectAttributes) {
		schoolCourseService.delete(schoolCourse);
		addMessage(redirectAttributes, "删除学院教室管理成功");
		return "redirect:"+Global.getAdminPath()+"/school/schoolCourse/?repage";
	}

}