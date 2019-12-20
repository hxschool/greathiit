/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.export.StudentAction;
import com.thinkgem.jeesite.modules.student.export.StudentTrackedExport;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;

/**
 * 分班操作
 * 
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/student/tracked")
public class StudentTrackedController extends BaseController {
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private OfficeService officeService;

	@ModelAttribute
	public Student get(@RequestParam(required = false) String id, Model model) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = studentService.get(id);
		}
		if (entity == null) {
			entity = new Student();
		}
		model.addAttribute("config", sysConfigService.getModule(Global.SYSCONFIG_RESULT));
		return entity;
	}


	// 分班
	@RequiresPermissions("student:student:view")
	@RequestMapping(value = { "list", "" })
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (!org.springframework.util.StringUtils.isEmpty(request.getMethod())&&!request.getMethod().equals("GET")) {
			if(!org.springframework.util.StringUtils.isEmpty(student.getClazz())) {
				String clsName = student.getClazz().getName();
				if(!org.springframework.util.StringUtils.isEmpty(clsName)) {
					List<Office> offices = officeService.findByOfficeNameLike(clsName);
					List<String> clazzNumbers = new ArrayList<String>();
					for(Office cls : offices) {
						clazzNumbers.add(cls.getId());
					}
					student.setClazzNumbers(clazzNumbers);
				}
			}
			Page<Student> page = studentService.trackedPage(new Page<Student>(request, response),student);
			model.addAttribute("page", page);
		}
		return "modules/student/tracked/studentTracked";
	}

	
	@RequestMapping(value = "batchCls")
	public String batchCls( String ids,String description,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			if(!org.springframework.util.StringUtils.isEmpty(ids)) {
				String[] arrayIds = ids.split(",");
				for(String id:arrayIds) {
					Student student = studentService.get(id);
					if(!org.springframework.util.StringUtils.isEmpty(student)) {
						Office oldClazz = student.getClazz();
						if(!org.springframework.util.StringUtils.isEmpty(oldClazz)) {
							student.setClazzName(oldClazz.getName());
						}
						Office cls = officeService.get(description);
						student.setClazz(cls);
						student.setStudentAction(StudentAction.tracked);
						studentService.save(student);
					}
				}
			}
			addMessage(redirectAttributes, "批量操作成功");
		return "redirect:"+Global.getAdminPath()+"/student/tracked?repage";
	}
	//导入班级信息
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<StudentTrackedExport> list = ei.getDataList(StudentTrackedExport.class);
			for (StudentTrackedExport st : list) {
				try {
					Student entity = studentService.getStudentByStudentNumber(st.getStudentNumber());
					if (org.springframework.util.StringUtils.isEmpty(entity)) {
						failureMsg.append("<br/>姓名 " + st.getName() + " 不存在; ");
						failureNum++;
						
					} else {
						entity.setClazz(st.getClazz());
						entity.setStudentAction(StudentAction.tracked);
						studentService.save(entity);
						successNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>姓名 " + st.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>姓名 " + st.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条学生" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入学生失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/tracked/list?repage";
	}

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "分班导入模板.xlsx";
			List<Student> list = Lists.newArrayList();
			list.add(new Student());
			new ExportExcel("分班信息", StudentTrackedExport.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/tracked/list?repage";
	}

	@RequiresPermissions("student:student:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Student stduent, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "分班信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<Student> page = studentService.findPage(new Page<Student>(request, response, -1), stduent);
			List<StudentTrackedExport> sts = new ArrayList<StudentTrackedExport>();
			for(Student st : page.getList()) {
				StudentTrackedExport  ste = new StudentTrackedExport();
				BeanUtils.copyProperties(st, ste);
				sts.add(ste);
			}
			new ExportExcel("分班信息", StudentTrackedExport.class).setDataList(sts).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学籍信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/tracked/list?repage";
	}
}