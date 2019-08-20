/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdcardValidator;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentStatusLog;
import com.thinkgem.jeesite.modules.student.service.StudentStatusLogService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;
import com.thinkgem.jeesite.modules.uc.student.service.UcStudentService;

/**
 * 学生基本信息Controller
 * @author 赵俊飞
 * @version 2017-09-19
 */
@Controller
@RequestMapping(value = "${adminPath}/uc/student")
public class UcStudentController extends BaseController {

	@Autowired
	private UcStudentService ucStudentService;
	@Autowired
	private StudentStatusLogService studentStatusLogService;
	@ModelAttribute
	public UcStudent get(@RequestParam(required=false) String id) {
		UcStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ucStudentService.get(id);
		}
		if (entity == null){
			entity = new UcStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("group")
	public String group(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentGroup(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentGroup";
	}
	
	@RequestMapping("group.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxGroup(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentGroup(beginDate, endDate);
	}
	
	
	@RequestMapping("year.json")
	@ResponseBody
	public List<Map<String,Object>> year(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentUser();
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("sex")
	public String sex(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentSex(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentSex";
	}
	
	@RequestMapping("sex.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxSex(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentSex(beginDate, endDate);
	}
	
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("region")
	public String region(String year,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentRegion(year);
		model.addAttribute("list", list);
		return "modules/uc/student/studentRegion";
	}
	
	@RequestMapping("region.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxRegion(String year,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentRegion(year);
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("edu")
	public String edu(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentEdu(beginDate, endDate);	
		model.addAttribute("list", list);
		return "modules/uc/student/studentEdu";
	}
	
	@RequestMapping("edu.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxEdu(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentEdu(beginDate, endDate);
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("major")
	public String major(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentMajor(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentMajor";
	}
	
	@RequestMapping("major.json")
	@ResponseBody
	public List<Map<String,Object>>  ajaxMajor(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentMajor(beginDate, endDate);
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("department")
	public String department(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentDepartment(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentDepartment";
	}
	
	@RequestMapping("department.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxDepartment(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentDepartment(beginDate, endDate);
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcStudent ucStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcStudent> page = ucStudentService.findPage(new Page<UcStudent>(request, response), ucStudent); 
		model.addAttribute("page", page);
		return "modules/uc/student/ucStudentList";
	}
	
	//成绩
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping(value = "result")
	public String result(UcStudent ucStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UcStudent> page = ucStudentService.findPage(new Page<UcStudent>(request, response), ucStudent); 
		model.addAttribute("page", page);
		return "modules/uc/student/ucStudentResult";
	}

	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping(value = "form")
	public String form(UcStudent ucStudent, Model model) {
		model.addAttribute("ucStudent", ucStudent);
		return "modules/uc/student/ucStudentForm";
	}

	@RequiresPermissions("uc:ucStudent:edit")
	@RequestMapping(value = "save")
	public String save(UcStudent us,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		
		if (!beanValidator(model, us)){
			return form(us, model);
		}
		ucStudentService.save(us);
		
		Student student = new Student();
		BeanUtils.copyProperties(us, student);
    	if(!org.springframework.util.StringUtils.isEmpty(us.getBirthday())) {
    		String birthday = us.getBirthday();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(birthday);
            student.setBirthday(date);
    	}
    	student.setPhone(us.getPhone());
    	student.setAddress(us.getHomeAddress());
    	student.setNation(DictUtils.getDictValue(us.getNation(), "nation", "未知"));
    	student.setPolitical(DictUtils.getDictValue(us.getPolitical(), "political", "未知"));
    	student.setClassno(us.getClassNumber());
    	
    	
		StudentStatusLog studentStatusLog = new StudentStatusLog();
		studentStatusLog.setLogType("1");
		studentStatusLog.setStudent(student);
		studentStatusLog.setStatus(us.getStatus());
		studentStatusLog.setDescription("学籍状态:" + DictUtils.getDictLabel(request.getParameter("action"), "student_uc_status", "") + "->" + DictUtils.getDictLabel(us.getStatus(), "student_uc_status", ""));
		studentStatusLogService.save(studentStatusLog);
		
		addMessage(redirectAttributes, "保存学生基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
	}
	
	@RequiresPermissions("uc:ucStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(UcStudent ucStudent, RedirectAttributes redirectAttributes) {
		ucStudentService.delete(ucStudent);
		addMessage(redirectAttributes, "删除学生基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
	}
	
    @RequestMapping(value = "download")
    public String download(UcStudent student, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    	return "modules/uc/student/ucStudentDownload";
    }

	
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(UcStudent student, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    	String action = request.getParameter("action");
		if(org.springframework.util.StringUtils.isEmpty(action)) {
			action = "";
		}
		try {	
            String fileName = "学籍信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("学籍信息", UcStudent.class).setDataList(ucStudentService.findByParentIdsLike(student)).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学籍信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/uc/student/"+action+"?repage";
    }

    
    @RequestMapping(value = "toStudent", method=RequestMethod.POST)
    public String toStudent(UcStudent student, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
    	String action = request.getParameter("action");
		if(org.springframework.util.StringUtils.isEmpty(action)) {
			action = "";
		}
		try {	
            String fileName = "学生信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<UcStudent> students = ucStudentService.findByParentIdsLike(student);
            List<Student> sts = new ArrayList<Student>();
            for(UcStudent us:students) {
            	Student st = new Student();

            	BeanUtils.copyProperties(us, st);
            	st.setName(us.getName());
            	
            	if(!org.springframework.util.StringUtils.isEmpty(us.getBirthday())) {
            		String birthday = us.getBirthday();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Date date = sdf.parse(birthday);
                    st.setBirthday(date);
            	}
            	st.setPhone(us.getPhone());
            	st.setAddress(us.getHomeAddress());
            	st.setNation(DictUtils.getDictValue(us.getNation(), "nation", "未知"));
            	st.setPolitical(DictUtils.getDictValue(us.getPolitical(), "political", "未知"));
            	st.setClassno(us.getClassNumber());
            	st.setStudentLength(us.getStudentLength());
            	sts.add(st);
            }
    		new ExportExcel("学生信息", Student.class).setDataList(sts).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学生信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/uc/student/"+action+"?repage";
    }
    
    @RequestMapping(value = "view")
    public String view() {
    	return "modules/uc/student/ucStudentView";
    }

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<UcStudent> list = ei.getDataList(UcStudent.class);
			for (UcStudent student : list){
				if(org.springframework.util.StringUtils.isEmpty(student.getIdCard())) {
					failureMsg.append("<br/>姓名 "+student.getName()+" 信息不合法,身份证信息为空");
					failureNum++;
				}
				if (!org.springframework.util.StringUtils.isEmpty(student.getIdCard())
						&& !IdcardValidator.validate18Idcard(student.getIdCard())) {
					failureMsg.append("<br/>姓名 " + student.getName() + " 信息不合法,身份证信息不合法");
					failureNum++;
				}
				try{
					UcStudent entity = ucStudentService.findByIdCard(student.getIdCard());
					if (org.springframework.util.StringUtils.isEmpty(entity)){
						
						ucStudentService.saveUser(student);
						successNum++;
					}else{
						failureMsg.append("<br/>姓名 "+student.getName()+" 已存在,学号重复.学号信息:"+student.getStudentNumber()+"; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名 "+student.getName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>姓名 "+student.getName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条学籍信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条学籍信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入学籍信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "学籍数据导入模板.xlsx";
    		List<UcStudent> list = Lists.newArrayList(); 
    		list.add(new UcStudent());
    		new ExportExcel("学籍数据", UcStudent.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
    }
	
	
	@RequiresPermissions("uc:student:operation")
	@RequestMapping(value = "deleteList")
	public String deleteList(String ids, RedirectAttributes redirectAttributes) {
		if(!org.springframework.util.StringUtils.isEmpty(ids)) {
			String[] arrayIds = ids.split(",");
			for(String id:arrayIds) {
				UcStudent ucStudent = new UcStudent();
				ucStudent.setId(id);
				ucStudentService.delete(ucStudent);
			}
		}
		
		addMessage(redirectAttributes, "停用学生数据成功");
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
	}

}