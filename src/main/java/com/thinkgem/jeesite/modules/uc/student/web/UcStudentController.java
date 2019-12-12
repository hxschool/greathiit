/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.web;

import java.io.IOException;
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
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
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
	//招生统计
	
	@RequestMapping("group")
	public String group(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentGroup(ucStudent);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentGroup";
	}
	
	@RequestMapping("groupExport")
	public String groupExcel(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			List<Map<String,Object>> list = ucStudentService.studentGroup(ucStudent);
			String fileName = "招生统计";
			String[] headers  = {"招生数量","招生时间"};
			ExportExcel exportExcel = new ExportExcel("招生统计", headers);
			exportExcel.setDataList(list).write(response, fileName+".xlsx").dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/uc/student/group?repage";
	}
	
	@RequestMapping("group.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxGroup(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentGroup(ucStudent);
	}
	
	
	@RequestMapping("year.json")
	@ResponseBody
	public List<Map<String,Object>> year(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentUser();
	}
	
	@RequestMapping("sex")
	public String sex(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentSex(ucStudent);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentSex";
	}
	
	@RequestMapping("sexExport")
	public String sexExcel(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			List<Map<String,Object>> list = ucStudentService.studentSex(ucStudent);
			String fileName = "性别统计";
			String[] headers  = {"性别统计","招生数量"};
			ExportExcel exportExcel = new ExportExcel(fileName, headers);
			
			exportExcel.setDataList(list).write(response, fileName+".xlsx").dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/uc/student/sex?repage";
	}
	
	@RequestMapping("sex.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxSex(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentSex(ucStudent);
	}
	
	
	@RequestMapping("region")
	public String region(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentRegion(ucStudent);
		model.addAttribute("list", list);
		return "modules/uc/student/studentRegion";
	}
	
	@RequestMapping("regionExport")
	public String regionExcel(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			List<Map<String,Object>> list = ucStudentService.studentRegion(ucStudent);
			String fileName = "行政区统计";
			String[] headers  = {fileName,"招生数量"};
			ExportExcel exportExcel = new ExportExcel(fileName, headers);
			exportExcel.setDataList(list).write(response, fileName+".xlsx").dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/uc/student/region?repage";
	}
	
	@RequestMapping("region.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxRegion(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentGengerRegion(ucStudent);
	}
	
	@RequestMapping("edu")
	public String edu(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentEdu(ucStudent);	
		model.addAttribute("list", list);
		return "modules/uc/student/studentEdu";
	}
	
	@RequestMapping("eduExport")
	public String eduExcel(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			List<Map<String,Object>> list = ucStudentService.studentEdu(ucStudent);
			String fileName = "学历统计";
			String[] headers  = {fileName,"招生数量"};
			ExportExcel exportExcel = new ExportExcel(fileName, headers);
			exportExcel.setDataList(list).write(response, fileName+".xlsx").dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/uc/student/edu?repage";
	}
	
	@RequestMapping("edu.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxEdu(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentEdu(ucStudent);
	}
	
	@RequestMapping("major")
	public String major(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentMajor(ucStudent);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentMajor";
	}
	
	@RequestMapping("majorExport")
	public String majorExcel(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			List<Map<String,Object>> list = ucStudentService.studentMajor(ucStudent);
			String fileName = "专业统计";
			String[] headers  = {fileName,"招生数量"};
			ExportExcel exportExcel = new ExportExcel(fileName, headers);
			exportExcel.setDataList(list).write(response, fileName+".xlsx").dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/uc/student/major?repage";
	}
	
	@RequestMapping("major.json")
	@ResponseBody
	public List<Map<String,Object>>  ajaxMajor(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentMajor(ucStudent);
	}
	
	@RequestMapping("department")
	public String department(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentDepartment(ucStudent);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentDepartment";
	}
	
	@RequestMapping("department.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxDepartment(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentDepartment(ucStudent);
	}
	
	
	@RequestMapping("departmentExport")
	public String departmentExcel(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			List<Map<String,Object>> list = ucStudentService.studentDepartment(ucStudent);
			String fileName = "学院统计";
			String[] headers  = {fileName,"招生数量"};
			ExportExcel exportExcel = new ExportExcel(fileName, headers);
			exportExcel.setDataList(list).write(response, fileName+".xlsx").dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:" + adminPath + "/uc/student/department?repage";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(UcStudent ucStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!org.springframework.util.StringUtils.isEmpty(request.getParameter("search"))) {
			Page<UcStudent> page = ucStudentService.findPage(new Page<UcStudent>(request, response), ucStudent); 
			model.addAttribute("page", page);
		}
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

	@RequestMapping(value = "batchAction")
	public String batch(String ids, String description,UcStudent ucStudent,HttpServletRequest request,
			RedirectAttributes redirectAttributes,Model model) {
		String action = request.getParameter("action");
		StringBuilder sb = new  StringBuilder();
		if(!org.springframework.util.StringUtils.isEmpty(action)) {
			if(!org.springframework.util.StringUtils.isEmpty(ids)) {
				String[] arrayIds = ids.split(",");
				for(String id:arrayIds) {
					UcStudent entity = ucStudentService.get(id);
					
					if (!org.springframework.util.StringUtils.isEmpty(entity)
							) {
						if(entity.getLearning().equals(action)) {
							sb.append("姓名:" + entity.getName() + "操作失败,失败原因。操作前操作后状态一直.取消操作");
							continue;
						}
						entity.setLearning(action);
						entity.setRemarks(description);
						ucStudentService.save(entity);
					}
					
				}
			}
		}
		if(org.springframework.util.StringUtils.isEmpty(sb.toString())) {
			model.addAttribute("message","操作成功");
		}
		model.addAttribute("message",sb.toString());
		return "redirect:"+Global.getAdminPath()+"/uc/student/list?repage&action="+action;
	}
}