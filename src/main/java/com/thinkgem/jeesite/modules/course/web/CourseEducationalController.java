/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.CourseEducational;
import com.thinkgem.jeesite.modules.course.service.CourseEducationalService;

/**
 * 教务课程信息Controller
 * @author 赵俊飞
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/course/courseEducational")
public class CourseEducationalController extends BaseController {

	@Autowired
	private CourseEducationalService courseEducationalService;
	
	@ModelAttribute
	public CourseEducational get(@RequestParam(required=false) String id) {
		CourseEducational entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseEducationalService.get(id);
		}
		if (entity == null){
			entity = new CourseEducational();
		}
		return entity;
	}
	
	@RequiresPermissions("course:courseEducational:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseEducational courseEducational, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseEducational> page = courseEducationalService.findPage(new Page<CourseEducational>(request, response), courseEducational); 
		model.addAttribute("page", page);
		return "modules/course/courseEducationalList";
	}

	@RequiresPermissions("course:courseEducational:view")
	@RequestMapping(value = "form")
	public String form(CourseEducational courseEducational, Model model) {
		model.addAttribute("courseEducational", courseEducational);
		return "modules/course/courseEducationalForm";
	}

	@RequiresPermissions("course:courseEducational:edit")
	@RequestMapping(value = "save")
	public String save(CourseEducational courseEducational, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, courseEducational)){
			return form(courseEducational, model);
		}
		courseEducational.setIsNewRecord(true);
		courseEducational.setId(courseEducational.getCursNum());
		courseEducationalService.save(courseEducational);
		addMessage(redirectAttributes, "保存教务课程信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseEducational/?repage";
	}
	
	@RequiresPermissions("course:courseEducational:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseEducational courseEducational, RedirectAttributes redirectAttributes) {
		courseEducationalService.delete(courseEducational);
		addMessage(redirectAttributes, "删除教务课程信息成功");
		return "redirect:"+Global.getAdminPath()+"/course/courseEducational/?repage";
	}
	
	@RequiresPermissions("course:courseEducational:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(CourseEducational courseEducational, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "课程数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<CourseEducational> page = courseEducationalService.findPage(new Page<CourseEducational>(request, response, -1), courseEducational);
    		new ExportExcel("课程数据", CourseEducational.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出课程失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/courseEducational/list?repage";
    }

	/**
	 * 导入课程数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("course:courseEducational:view")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {

		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CourseEducational> list = ei.getDataList(CourseEducational.class);
			for (CourseEducational courseEducational : list){
				try{
					CourseEducational ce = courseEducationalService.get(courseEducational.getCursNum().trim());
					if(org.springframework.util.StringUtils.isEmpty(ce)) {
						courseEducational.setIsNewRecord(true);
						courseEducational.setId(courseEducational.getCursNum());
						courseEducationalService.save(courseEducational);
					}else{
						failureMsg.append("<br/>课程编码 "+ce.getCursNum()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>课程编码 "+courseEducational.getCursNum()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>课程编码 "+courseEducational.getCursNum()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/courseEducational/list?repage";
    }
	
	/**
	 * 下载导入课程数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("course:courseEducational:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "课程数据导入模板.xlsx";
    		List<CourseEducational> list = Lists.newArrayList(); 
    		CourseEducational courseEducational = new CourseEducational();
    		list.add(courseEducational);
    		new ExportExcel("课程数据", CourseEducational.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/course/courseEducational/list?repage";
    }
	
	@RequestMapping(value = "ajaxCourseEducational")
	@ResponseBody
	public List<CourseEducational> ajaxCourseEducational(CourseEducational courseEducational, HttpServletRequest request, HttpServletResponse response, Model model) {
		return courseEducationalService.findList(courseEducational);
	}

}