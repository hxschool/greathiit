/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.uc.student.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.commons.io.FileUtils;
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
import com.google.gson.Gson;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.DocWriter;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.common.utils.IdcardValidator;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.file.FileResponse;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
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
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	
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
	public String region(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<Map<String,Object>> list = ucStudentService.studentRegion(beginDate, endDate);
				
		model.addAttribute("list", list);
		return "modules/uc/student/studentRegion";
	}
	
	@RequestMapping("region.json")
	@ResponseBody
	public List<Map<String,Object>> ajaxRegion(Date beginDate,Date endDate,HttpServletRequest request, HttpServletResponse response,Model model) {
		return ucStudentService.studentRegion(beginDate, endDate);
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
	public String save(UcStudent ucStudent,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ucStudent)){
			return form(ucStudent, model);
		}
		ucStudentService.save(ucStudent);
		LogUtils.saveLog(request, ucStudent, null, "学籍异动");
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
		try {
            String fileName = "学籍信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("学籍信息", UcStudent.class).setDataList(ucStudentService.findByParentIdsLike(student)).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出学籍信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
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
					failureMsg.append("<br/>姓名 "+student.getUsername()+" 信息不合法,身份证信息为空");
					failureNum++;
				}
				if (!org.springframework.util.StringUtils.isEmpty(student.getIdCard())
						&& !IdcardValidator.validate18Idcard(student.getIdCard())) {
					failureMsg.append("<br/>姓名 " + student.getUsername() + " 信息不合法,身份证信息不合法");
					failureNum++;
				}
				try{
					UcStudent entity = ucStudentService.findByIdCard(student.getIdCard());
					if (org.springframework.util.StringUtils.isEmpty(entity)){
						
						ucStudentService.saveUser(student);
						successNum++;
					}else{
						failureMsg.append("<br/>姓名 "+student.getUsername()+" 已存在,学号重复.学号信息:"+student.getStudentNumber()+"; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>姓名 "+student.getUsername()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>姓名 "+student.getUsername()+" 导入失败："+ex.getMessage());
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

	@RequestMapping(value = "face")
	public String face() {
		return "modules/student/studentFace";
	}
	@RequestMapping(value = "upload")
	public String upload(MultipartFile file, RedirectAttributes redirectAttributes) {
	
		int successNum = 0;
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		try {
			
			String name = file.getName();
			String fix =  name.substring(name.lastIndexOf("."), name.length());
			File oldFile = File.createTempFile(name.substring(0,name.lastIndexOf(".")),fix);
			FileUtils.copyInputStreamToFile(file.getInputStream(), oldFile);
			ZipEntry zipEntry = null;
			ZipFile zipFile = new ZipFile(oldFile); 
			ZipInputStream zipInputStream = new ZipInputStream( new FileInputStream(oldFile)); 
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
	            if(!zipEntry.isDirectory()){ 
	            	  String filename = zipEntry.getName();
	                  String prefix  =	 filename.substring(0,filename.lastIndexOf("."));
	                  String idCard = prefix;
	                  UcStudent ucStudent = ucStudentService.findByIdCard(idCard);
	                  if(org.springframework.util.StringUtils.isEmpty(ucStudent)) {
	                	  failureMsg.append("身份信息异常,身份证号不正确" + idCard);
	                	  failureNum ++ ;
	                  }else {
	                	  String suffix = filename.substring(filename.lastIndexOf("."), filename.length());
		                  File tempFile = File.createTempFile(prefix,suffix);
		                  FileUtils.copyInputStreamToFile(zipFile.getInputStream(zipEntry), tempFile);
		                  String str = HttpClientUtil.upload(Global.FILE_SERVER_UPLOAD_URL,filename, tempFile);
		                  if(!org.springframework.util.StringUtils.isEmpty(str)) {
		                      Gson gson = new Gson();
		                      FileResponse fileResponse = gson.fromJson(str, FileResponse.class);
		                      if(fileResponse.getStatus().equals("00000000")) {
		                    	  User user = systemService.getUserByLoginName(idCard);
				                  user.setPhone(fileResponse.getUrl());
				                  systemService.saveUser(user);
				                  successNum ++;
		                      }else {
		                    	  failureMsg.append("连接文件服务器异常,请联系管理员");
			                	  failureNum ++ ;
		                      }
		                  }else{
		                	  failureMsg.append("连接文件服务器异常,请联系管理员");
		                	  failureNum ++ ;
		                  }
		                 
	                  }
	            }
	        }
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条头像信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条信息"+failureMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:"+Global.getAdminPath()+"/uc/student/face?repage";
	}

	
	@RequiresPermissions("uc:ucStudent:edit")
	@RequestMapping(value = "zhengming")
	public String zhengming(UcStudent ucStudent,HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		

		
		UcStudent entity = ucStudentService.get(ucStudent);
		if(!org.springframework.util.StringUtils.isEmpty(entity)) {
			String id = entity.getDepartmentId();
			String filename = null;
			if(!org.springframework.util.StringUtils.isEmpty(id)) {
				filename = request.getSession().getServletContext().getRealPath("/resources/zhengming/"+id+".docx");  
			}else {
				String departmentName = entity.getDepartmentName();
				Office office = officeService.getOfficeByName(departmentName);
				if(!org.springframework.util.StringUtils.isEmpty(office)) {
					filename = request.getSession().getServletContext().getRealPath("/resources/zhengming/"+office.getId()+".docx");  
				}
			}
			if(!org.springframework.util.StringUtils.isEmpty(filename)) {
				String startDate = entity.getStartDate();
				String yyyy = StringUtils.left(startDate, 4);
				String mm = StringUtils.substring(startDate, 4,6);
				response.setContentType("application/msword;charset=utf-8");
		       
				response.setHeader("Content-Disposition", "attachment;filename=".concat(new String(entity.getUsername().getBytes("gbk"),"ISO-8859-1")).concat(".docx"));  
				OutputStream os = response.getOutputStream();
				FileInputStream is = new FileInputStream(filename);
				 Map<String, String> map = new HashMap<String, String>();
			    map.put("${name}", entity.getUsername());
			    map.put("${idcard}", entity.getIdCard());
		        map.put("${yyyy}",yyyy);
		        map.put("${mm}", mm);
		        map.put("${zy}", entity.getMajorName());
		        map.put("${n}",DictUtils.getDictLabel(entity.getSchoolSystem(), "student_school_system", ""));
		        String edu = DictUtils.getDictLabel(entity.getEdu(), "student_edu", "");
		        if(!org.springframework.util.StringUtils.isEmpty(edu)) {
		        	edu = StringUtils.left(edu, 1);
		        }
		        map.put("${edu}",edu);
		        map.put("${date}",DateUtils.getDate("yyyy年MM月dd日"));
				DocWriter.searchAndReplace(is, os, map);
				
			}else {
				addMessage(redirectAttributes, "学生数据异常,获取学院信息失败");
			}
			
		}
		
		return "redirect:"+Global.getAdminPath()+"/uc/student/?repage";
	}
	
	//批量生成证明
	@RequestMapping(value = "batchCompress")
	public String batchCompress( String ids,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String action = request.getParameter("action");
		if(!org.springframework.util.StringUtils.isEmpty(action)) {
			if(!org.springframework.util.StringUtils.isEmpty(ids)) {
				String[] arrayIds = ids.split(",");
				
			}
			addMessage(redirectAttributes, "批量操作成功");
			return "redirect:"+Global.getAdminPath()+"/uc/student/?action="+action+"&repage";
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
	@RequiresPermissions("uc:ucStudent:fenban")
	@RequestMapping("fenban")
	public String fenban(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<UcStudent> list = ucStudentService.fenban(ucStudent);
		Page<UcStudent> page = new Page<UcStudent>(request, response); 
		ucStudent.setPage(page);
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/uc/student/studentFenban";
	}
	
	
	@RequestMapping(value = "batchCls")
	public String batchCls( String ids,String description,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		String action = request.getParameter("action");
		if(!org.springframework.util.StringUtils.isEmpty(action)) {
			if(!org.springframework.util.StringUtils.isEmpty(ids)) {
				String[] arrayIds = ids.split(",");
				for(String id:arrayIds) {
					UcStudent ucStudent = ucStudentService.get(id);
					if(!org.springframework.util.StringUtils.isEmpty(ucStudent)) {
						ucStudent.setClassNumber(description);
						ucStudentService.save(ucStudent);
					}
				}
			}
			addMessage(redirectAttributes, "批量操作成功");
			return "redirect:"+Global.getAdminPath()+"/uc/student/fenban?repage";
		}
		return "redirect:"+Global.getAdminPath()+"/uc/student/fenban?repage";
	}
	
	@RequiresPermissions("uc:ucStudent:view")
	@RequestMapping("biyesheng")
	public String biyesheng(UcStudent ucStudent,HttpServletRequest request, HttpServletResponse response,Model model) {
		List<UcStudent> list = ucStudentService.biyesheng(ucStudent);
		Page<UcStudent> page = new Page<UcStudent>(request, response); 
		ucStudent.setPage(page);
		page.setList(list);
		model.addAttribute("page", page);
		return "modules/uc/student/studentBys";
	}
	

}