/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.answering.admin.web;

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
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnsweringStudent;
import com.thinkgem.jeesite.modules.answering.admin.service.AsAnsweringService;
import com.thinkgem.jeesite.modules.answering.admin.service.AsAnsweringStudentService;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 答辩抽签Controller
 * @author 赵俊飞
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/answering/admin/asAnswering")
public class AsAnsweringController extends BaseController {

	@Autowired
	private AsAnsweringService asAnsweringService;
	@Autowired
	private AsAnsweringStudentService asAnsweringStudentService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	
	@ModelAttribute
	public AsAnswering get(@RequestParam(required=false) String id) {
		AsAnswering entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = asAnsweringService.get(id);
		}
		if (entity == null){
			entity = new AsAnswering();
		}
		return entity;
	}
	
	@RequestMapping(value ="search")
	public String search(AsAnswering asAnswering, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AsAnswering> page = asAnsweringService.findPage(new Page<AsAnswering>(request, response), asAnswering); 
		model.addAttribute("page", page);
		return "modules/answering/admin/asAnsweringSearch";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(AsAnswering asAnswering, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AsAnswering> page = asAnsweringService.findPage(new Page<AsAnswering>(request, response), asAnswering); 
		model.addAttribute("page", page);
		return "modules/answering/admin/asAnsweringList";
	}

	@RequestMapping(value = "form")
	public String form(AsAnswering asAnswering, Model model) {
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setScLock("0");
		courseSchedule.setTips("答辩");
		List<CourseSchedule> courseSchedules = courseScheduleService.findList(courseSchedule);
		model.addAttribute("asAnswering", asAnswering);
		model.addAttribute("courseSchedules", courseSchedules);
		return "modules/answering/admin/asAnsweringForm";
	}

	@RequestMapping(value = "save")
	public String save(AsAnswering asAnswering, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, asAnswering)){
			return form(asAnswering, model);
		}
		String timeAdd = asAnswering.getTimeAdd();
		CourseSchedule courseSchedule = courseScheduleService.getByAddTime(timeAdd);
		courseSchedule.setTips("答辩占用");
		courseScheduleService.save(courseSchedule);
		
		asAnsweringService.save(asAnswering);
		
		asAnsweringStudentService.batchInsert(asAnswering);
		addMessage(redirectAttributes, "保存答辩抽签成功");
		return "redirect:"+Global.getAdminPath()+"/answering/admin/asAnswering/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(AsAnswering asAnswering, RedirectAttributes redirectAttributes) {
		asAnsweringService.delete(asAnswering);
		addMessage(redirectAttributes, "删除答辩抽签成功");
		return "redirect:"+Global.getAdminPath()+"/answering/admin/asAnswering/?repage";
	}
	
	
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		//zhaojunfei
		return "redirect:" + adminPath + "/answering/admin/asAnswering/search?repage";
    }
	
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file,String asAnsweringId, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<AsAnsweringStudent> list = ei.getDataList(AsAnsweringStudent.class);
			for (AsAnsweringStudent asAnsweringStudent : list){
				try {
					asAnsweringStudent.setAsAnsweringId(asAnsweringId);
					asAnsweringStudent.setStatus("0");
					int cnt = asAnsweringStudentService.count(asAnsweringStudent);
					if (cnt > 0) {
						failureMsg.append("<br/>学号 " + asAnsweringStudent.getStudentNumber() + " 已存在当前队列; ");
						failureNum++;
					} else {
						asAnsweringStudentService.save(asAnsweringStudent);
						successNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>学号 " + asAnsweringStudent.getStudentNumber() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>学号" + asAnsweringStudent.getStudentNumber() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/answering/admin/asAnswering/list?repage";
    }
	
	
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "导入模板.xlsx";
    		List<AsAnsweringStudent> list = Lists.newArrayList(); list.add(new AsAnsweringStudent());
    		new ExportExcel("数据", AsAnsweringStudent.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/answering/admin/asAnswering/list?repage";
    }

}