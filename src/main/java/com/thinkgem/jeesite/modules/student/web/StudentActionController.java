/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;


/**
 * 学生基本信息Controller
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/student/action")
public class StudentActionController extends BaseController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeDao officeDao;
	@ModelAttribute
	public Student get(@RequestParam(required=false) String id) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentService.get(id);
		}
		if (entity == null){
			entity = new Student();
		}
		return entity;
	}
	
	@RequiresPermissions("student:student:view")
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		String op = request.getParameter("op");
		if (!org.springframework.util.StringUtils.isEmpty(op) && op.equals("search")) {
			Page<Student> page = studentService.findPage(new Page<Student>(request, response), student); 
			model.addAttribute("page", page);
		}
		return "modules/student/action/actionList";
	}
	
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Student student, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学籍信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("学籍信息", Student.class).setDataList(studentService.findByParentIdsLike(student)).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学籍信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/student/action/?repage";
    }
    
    
    @RequiresPermissions("student:student:action")
	@RequestMapping(value = "batchAction")
	public String batch( String ids,String description,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String action = request.getParameter("action");
		if(!org.springframework.util.StringUtils.isEmpty(action)) {
			if(!org.springframework.util.StringUtils.isEmpty(ids)) {
				String[] arrayIds = ids.split(",");
				for(String id:arrayIds) {
					Student student = studentService.get(id);
					if(!org.springframework.util.StringUtils.isEmpty(student)) {
						student.setStatus(action);
						if(!org.springframework.util.StringUtils.isEmpty(student.getDescription())) {
							description = student.getDescription()  + "\n" + description;
						}
						student.setDescription(description);
						studentService.save(student);
						User user = systemService.getUserByLoginName(student.getIdCard());
						if(!org.springframework.util.StringUtils.isEmpty(user)) {
							boolean ret = false;
							if(action.equals("2")) {
								Office  office = officeDao.get(description);
								if(!org.springframework.util.StringUtils.isEmpty(office)) {
									user.setOffice(office);
									ret = true;
								}
							}
							if(action.equals("3")||action.equals("4")||action.equals("5")||action.equals("6")) {
								Office  clazz = officeDao.get(description);
								if(!org.springframework.util.StringUtils.isEmpty(clazz)) {
									user.setClazz(clazz);
									ret = true;
								}
							}
							if(ret) {
								systemService.saveUser(user);
							}
						}
						LogUtils.saveLog(request, student, null, "学籍异动");
					}
				}
			}
			addMessage(redirectAttributes, "操作成功");
			return "redirect:"+Global.getAdminPath()+"/student/action/?op=search&action="+action+"&repage";
		}
		return "redirect:"+Global.getAdminPath()+"/student/action/?repage";
	}
}