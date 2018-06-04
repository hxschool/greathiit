/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.recruit.web.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;
import com.thinkgem.jeesite.modules.recruit.service.student.RecruitStudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 统招数据Controller
 * @author 赵俊飞
 * @version 2018-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/recruit/student/recruitStudent")
public class RecruitStudentController extends BaseController {

	@Autowired
	private RecruitStudentService recruitStudentService;
	
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public RecruitStudent get(@RequestParam(required=false) String id) {
		RecruitStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recruitStudentService.get(id);
		}
		if (entity == null){
			entity = new RecruitStudent();
		}
		return entity;
	}
	//@RequiresPermissions("recruit:student:recruitStudent:view")
	@RequestMapping
	public String index(RecruitStudent recruitStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/recruit/student/recruitStudentIndex";
	}
	
	//@RequiresPermissions("recruit:student:recruitStudent:view")
	@RequestMapping(value = {"list"})
	public String list(RecruitStudent recruitStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecruitStudent> page = recruitStudentService.findPage(new Page<RecruitStudent>(request, response), recruitStudent); 
		model.addAttribute("page", page);
		return "modules/recruit/student/recruitStudentList";
	}

	//@RequiresPermissions("recruit:student:recruitStudent:view")
	@RequestMapping(value = "form")
	public String form(RecruitStudent recruitStudent, Model model) {
		model.addAttribute("recruitStudent", recruitStudent);
		return "modules/recruit/student/recruitStudentForm";
	}

	//@RequiresPermissions("recruit:student:recruitStudent:edit")
	@RequestMapping(value = "save")
	public String save(RecruitStudent recruitStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, recruitStudent)){
			return form(recruitStudent, model);
		}
		recruitStudentService.save(recruitStudent);
		addMessage(redirectAttributes, "保存统招数据成功");
		return "redirect:"+Global.getAdminPath()+"/recruit/student/recruitStudent/list?repage";
	}
	
	//@RequiresPermissions("recruit:student:recruitStudent:edit")
	@RequestMapping(value = "baodao")
	public String baodao(Model model, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		
		RecruitStudent recruitStudent = new RecruitStudent();
		recruitStudent.setIdCard(user.getLoginName());
		RecruitStudent pojo = recruitStudentService.getRecruitStudent(recruitStudent);
		pojo.setStatus("01");
		recruitStudentService.save(pojo);
		addMessage(redirectAttributes, "报到成功.接下来可以进行修改个人信息,或在线缴费");
		model.addAttribute("recruitStudent", pojo);
		return "modules/recruit/student/recruitStudentFirst";
	}
	
	//@RequiresPermissions("recruit:student:recruitStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(RecruitStudent recruitStudent, RedirectAttributes redirectAttributes) {
		recruitStudentService.delete(recruitStudent);
		addMessage(redirectAttributes, "删除统招数据成功");
		return "redirect:"+Global.getAdminPath()+"/recruit/student/recruitStudent/list?repage";
	}
	
	
	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("recruit:student:recruitStudent:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<RecruitStudent> list = ei.getDataList(RecruitStudent.class);
			for (RecruitStudent recruitStudent : list){
				try{
					RecruitStudent pojo = recruitStudentService.getRecruitStudent(recruitStudent);
					if (org.springframework.util.StringUtils.isEmpty(pojo)){
						User user = new User();
						String compayname = recruitStudent.getCompanyName();
						if(!org.springframework.util.StringUtils.isEmpty(compayname)) {
							Office company = officeService.getOfficeByName(compayname);
							if(!org.springframework.util.StringUtils.isEmpty(company)) {
								recruitStudent.setCompany(company);
								user.setCompany(company);
							}
						}
						
						String officename = recruitStudent.getOfficeName();
						if(!org.springframework.util.StringUtils.isEmpty(officename)) {
							Office office = officeService.getOfficeByName(officename);
							if(!org.springframework.util.StringUtils.isEmpty(office)) {
								recruitStudent.setOffice(office);
								user.setOffice(office);
							}
						}
						//初始化数据导入
						recruitStudent.setStatus("00");
						
						recruitStudentService.save(recruitStudent);
						String idCard = recruitStudent.getIdCard();
						user.setAccountNo("");
						user.setName(recruitStudent.getUsername());
						user.setLoginName(idCard);
						String password = systemService.entryptPassword(idCard.substring(idCard.length()-6));
						user.setPassword(password);
						user.setLoginIp("0.0.0.0");
						user.setDelFlag("0");
						systemService.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>身份证号: "+recruitStudent.getIdCard()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>身份证号: "+recruitStudent.getIdCard()+" 已存在; ");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>身份证号: "+recruitStudent.getIdCard()+" 已存在; "+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/recruit/student/recruitStudent/list?repage";
    }

}