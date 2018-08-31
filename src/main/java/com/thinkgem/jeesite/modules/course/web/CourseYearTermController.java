/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseYearTermService;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.school.service.SchoolRootService;

/**
 * 学期初始化Controller
 * @author 赵俊飞
 * @version 2017-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseYearTerm")
public class CourseYearTermController extends BaseController {

	@Autowired
	private CourseYearTermService courseYearTermService;
	@Autowired
	private SchoolRootService schoolRootService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	 
	@ModelAttribute
	public CourseYearTerm get(@RequestParam(required=false) String id,@RequestParam(required=false) String yearTerm) {
		CourseYearTerm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseYearTermService.get(id);
		}
		
		if (entity == null){
			entity = new CourseYearTerm();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseYearTerm:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseYearTerm courseYearTerm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseYearTerm> page = courseYearTermService.findPage(new Page<CourseYearTerm>(request, response), courseYearTerm); 
		model.addAttribute("page", page);
		return "modules/course/courseYearTermList";
	}

	@RequiresPermissions("course:courseYearTerm:view")
	@RequestMapping(value = "form")
	public String form(CourseYearTerm courseYearTerm, Model model) {
		model.addAttribute("courseYearTerm", courseYearTerm);
		return "modules/course/courseYearTermForm";
	}

	@RequiresPermissions("course:courseYearTerm:edit")
	@RequestMapping(value = "save")
	public String save(String yearTerm, Model model, RedirectAttributes redirectAttributes) {
		
		CourseYearTerm courseYearTerm = courseYearTermService.get(yearTerm);
		if(org.springframework.util.StringUtils.isEmpty(courseYearTerm)) {
			courseYearTerm = new CourseYearTerm(); 
		}
		courseYearTerm.setYearTerm(yearTerm);
		courseYearTermService.save(courseYearTerm);
		
		//学期初始化过程需要讲全部的教学楼以及班级添加到courseSchedule
		
		List<SchoolRoot> schoolRoots = schoolRootService.findByParentId("0");
		for(SchoolRoot schoolRoot: schoolRoots) {
			List<SchoolRoot> roots = schoolRootService.findByParentId(schoolRoot.getId());
			String schoolNumber = schoolRoot.getValue();
			for(SchoolRoot root: roots) {
				
				for(int $i = 1;$i<=20;$i++)
				{
					for(int $j = 1;$j<=5;$j++)
					{
						for(int $k = 1;$k<=7;$k++)
						{
							CourseSchedule courseSchedule = new CourseSchedule();
							String rootNumber = root.getValue();
							String $id = courseYearTerm.getYearTerm().concat(schoolNumber).concat(rootNumber);
							String timeAdd ="";
							
							if($i<=9)
								timeAdd = $id + '0' + $i + $j + $k;
							else
								timeAdd = $id + $i + $j + $k;
							
							courseSchedule.setTimeAdd(timeAdd);
							courseSchedule.setCourseId("00000000");
							courseSchedule.setScLock("1");
							courseSchedule.setCourseClass("");
							courseSchedule.setTips("");
							courseScheduleService.save(courseSchedule);
						}
					}
				}
				
			}
		}
		
		addMessage(redirectAttributes, "保存学期初始化成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseYearTerm/?repage";
	}
	
	@RequiresPermissions("course:courseYearTerm:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseYearTerm courseYearTerm, RedirectAttributes redirectAttributes) {
		courseYearTermService.delete(courseYearTerm);
		addMessage(redirectAttributes, "删除学期初始化成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseYearTerm/?repage";
	}

}