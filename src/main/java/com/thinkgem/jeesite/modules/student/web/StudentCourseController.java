/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportResult;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.entity.StudentCourseExt;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 学生成绩Controller
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/student/studentCourse")
public class StudentCourseController extends BaseController {
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SysConfigService sysConfigService;
	private SysConfig config;

	@ModelAttribute
	public StudentCourse get(@RequestParam(required=false) String id,Model model) {
		StudentCourse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentCourseService.get(id);
		}
		if (entity == null){
			entity = new StudentCourse();
		}
		config = sysConfigService.getModule(Global.SYSCONFIG_COURSE);
		model.addAttribute("config",config);
		return entity;
	}
	
	@RequestMapping(value = "wechat")
	public String wechat(StudentCourse studentCourse, Model model) {
		Student student = studentCourse.getStudent();
		if(org.springframework.util.StringUtils.isEmpty(student)) {
			User user = UserUtils.getUser();
			student = studentService.getStudentByStudentNumber(user.getNo());
		}
		if(org.springframework.util.StringUtils.isEmpty(student.getId())) {
			student = studentService.getStudentByStudentNumber(student.getStudentNumber());
		}
		studentCourse.setStudent(student);
		model.addAttribute("student", student);
		model.addAttribute("studentCourses", studentCourseService.findByParentIdsLike(studentCourse));
		return "modules/student/studentcourse/StudentCourseWechat";
	}
	
	
	@RequiresPermissions("student:studentCourse:view")
	@RequestMapping(value = {"Teacher_Management_4_excute"})
	public String Teacher_Management_4_excute(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StudentCourse> page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
		model.addAttribute("page", page);
		return "modules/student/studentcourse/studentCourseList";
	}
	
	@RequiresPermissions("student:studentCourse:view")
	@RequestMapping(value = {"list", ""})
	public String list(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, Model model) {
		String companyId = request.getParameter("companyId");
		String officeId = request.getParameter("officeId");
		String clazzId = request.getParameter("clazzId");
		List<String> item = Lists.newArrayList();
		User user = new User();
		List<User> users = null;
		if (!org.springframework.util.StringUtils.isEmpty(companyId)) {
			Office company = new Office();
			company.setId(companyId);
			user.setCompany(company);
			users = systemService.findUser(user);
		}
		
		if (!org.springframework.util.StringUtils.isEmpty(officeId)) {
			item.clear();
			Office office = new Office();
			office.setId(officeId);
			user.setOffice(office);
			users = systemService.findUser(user);
		}
		
		if (!org.springframework.util.StringUtils.isEmpty(clazzId)) {
			item.clear();
			Office clazz = new Office();
			clazz.setId(clazzId);
			user.setClazz(clazz);
			users = systemService.findUser(user);
		}
		
		if(!CollectionUtils.isEmpty(users)) {
			for(User u :users) {
				if (!org.springframework.util.StringUtils.isEmpty(u.getNo()) && u.getNo().length() != 4) {
					item.add(u.getNo());
				}
			}
		}

		//过滤学生数据
		if(!CollectionUtils.isEmpty(item)) {
			studentCourse.setItem(item);
		}
		
		Course course = studentCourse.getCourse();
		if(!isAdmin()) {
			if(org.springframework.util.StringUtils.isEmpty(course)) {
				 course = new Course();
			}
			course.setTeacher(UserUtils.getTeacher());
			studentCourse.setCourse(course);
		}
		
		Page<StudentCourse> page = null;
		if((!org.springframework.util.StringUtils.isEmpty(companyId)||!org.springframework.util.StringUtils.isEmpty(officeId)||!org.springframework.util.StringUtils.isEmpty(clazzId))&&CollectionUtils.isEmpty(users)) {
			page = new Page<StudentCourse>();
		}else {
			page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
		}
		
		
		List<StudentCourseExt> ses = Lists.newArrayList();
		for (StudentCourse sc : page.getList()) {
			StudentCourseExt se = new StudentCourseExt();
			BeanUtils.copyProperties(sc, se);
			String studentNumber = sc.getStudentNumber();
			User u = systemService.getCasByLoginName(studentNumber);
			if (!org.springframework.util.StringUtils.isEmpty(u.getClazz())) {
				Office clazz = officeService.get(u.getClazz());
				se.setClazz(clazz);
				Office office = officeService.get(u.getOffice());
				se.setOffice(office);
				Office company = officeService.get(u.getCompany());
				se.setCompany(company);
			}
			Course entity = courseService.get(sc.getCourse());
			se.setCourse(entity);
			ses.add(se);
		}
		Page<StudentCourseExt> pp = new Page<StudentCourseExt>();
		BeanUtils.copyProperties(page, pp);
		pp.setList(ses);
		model.addAttribute("page", pp);
		return "modules/student/studentcourse/studentCourseList";
	}

	@RequiresPermissions("student:studentCourse:view")
	@RequestMapping(value = "form")
	public String form(StudentCourse studentCourse, Model model) {
		model.addAttribute("studentCourse", studentCourse);
		return "modules/student/studentcourse/studentCourseForm";
	}

	@RequiresPermissions("student:studentCourse:edit")
	@RequestMapping(value = "save")
	public String save(StudentCourse studentCourse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, studentCourse)){
			return form(studentCourse, model);
		}
		studentCourseService.save(studentCourse);
		addMessage(redirectAttributes, "保存学生成绩成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentCourse/?repage";
	}
	
	@RequiresPermissions("student:studentCourse:edit")
	@RequestMapping(value = "delete")
	public String delete(StudentCourse studentCourse, RedirectAttributes redirectAttributes) {
		studentCourseService.delete(studentCourse);
		addMessage(redirectAttributes, "删除学生成绩成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentCourse/?repage";
	}
	@RequiresPermissions("student:studentCourse:operation")
	@RequestMapping(value = "deleteList")
	public String deleteList(String ids, RedirectAttributes redirectAttributes) {
		if(!org.springframework.util.StringUtils.isEmpty(ids)) {
			String[] arrayIds = ids.split(",");
			for(String id:arrayIds) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setId(id);
				studentCourseService.delete(studentCourse);
			}
		}
		
		addMessage(redirectAttributes, "删除学生成绩成功");
		return "redirect:"+Global.getAdminPath()+"/student/studentCourse/?repage";
	}
	

	@RequiresPermissions("student:studentCourse:export")
    @RequestMapping(value = "export")
    public String exportFile(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成绩汇总表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<StudentCourse> page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
            
            List<StudentCourseExt> ses = Lists.newArrayList();  
            for(StudentCourse sc : page.getList()) {
            	StudentCourseExt se = new StudentCourseExt();
            	BeanUtils.copyProperties(sc, se);
            	String studentNumber = sc.getStudentNumber();
            	User user = systemService.getCasByLoginName(studentNumber);
            	if(!org.springframework.util.StringUtils.isEmpty(user.getClazz())) {
            		Office clazz = officeService.get(user.getClazz());
            		se.setClazz(clazz);
            		Office office = officeService.get(clazz.getParentId());
            		se.setOffice(office);
            		Office company = officeService.get(office.getParentId());
            		se.setCompany(company);
            	}
            	Course course = courseService.get(sc.getCourse());
            	se.setCourse(course);
            	ses.add(se);
            }
    		new ExportExcel("成绩数据", StudentCourseExt.class).setDataList(ses).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "成绩汇总表！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/studentCourse/list?repage";
    }
	/**
	 * 导入成绩
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "importView")
	public String importView(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {
		return "modules/student/studentcourse/importView";
	}
	@RequiresPermissions("student:studentCourse:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		try {
			ImportResult<Course> ir = studentCourseService.importStudentCourse(file);
			if (ir.getFailureNum()>0){
				ir.getFailureMsg().insert(0, "，失败 "+ir.getFailureNum()+" 条成绩，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+ir.getSuccessNum()+" 条成绩"+ir.getFailureMsg());
		}catch (Exception e) {
			addMessage(redirectAttributes, "成绩数据！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/studentCourse/list?repage";
    }
	
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成绩数据导入模板.xlsx";
    		List<StudentCourse> list = Lists.newArrayList(); list.add(new StudentCourse());
    		ExportExcel exportExcel = new ExportExcel();
    		List<String> headerList = exportExcel.getHeaders(StudentCourse.class);
    		exportExcel.init("成绩数据",headerList);
    		Row row = exportExcel.addRow();
    		Cell cell = row.createCell(0);
    		cell.setCellValue("学期");
    		
    		Cell clazzCell = row.createCell(2);
    		clazzCell.setCellValue("课程名称");
    		
    		Cell courseIdLabelCell = row.createCell(4);
    		courseIdLabelCell.setCellValue("课程编码");
    		
    		
    		Cell teacherLabelCell = row.createCell(6);
    		teacherLabelCell.setCellValue("任课教师");
    		exportExcel.setHeader(headerList);
    		
    		exportExcel.setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/studentCourse/list?repage";
    }
	
	
	@RequiresPermissions("student:studentCourse:export")
    @RequestMapping(value = "export/student")
    public String importFileTemplate(Course course,HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		try {
			course = courseService.get(course.getId());
			if (org.springframework.util.StringUtils.isEmpty(course)) {
				addMessage(redirectAttributes, "课程信息异常,非法参数");
			}
			
			String fileName = StringEscapeUtils.unescapeHtml4(course.getCursName()).concat("成绩导入模板.xlsx");
			
			List<StudentCourse> list = studentCourseService.getStudentCourses(course);
			
			ExportExcel exportExcel = new ExportExcel();
			List<String> headerList = exportExcel.getHeaders(StudentCourse.class);
			exportExcel.init("成绩数据", headerList);
			Row row = exportExcel.addRow();
			Cell cell = row.createCell(0);
			cell.setCellValue("学期");
			Cell xueqiCell = row.createCell(1);
			xueqiCell.setCellValue(course.getCursYearTerm());

			Cell clazzCell = row.createCell(2);
			clazzCell.setCellValue("课程名称");
			Cell courseCell = row.createCell(3);
			courseCell.setCellValue(course.getCursName());

			Cell courseIdLabelCell = row.createCell(4);
			courseIdLabelCell.setCellValue("课程编码");
			Cell courseIdValueCell = row.createCell(5);
			courseIdValueCell.setCellValue(course.getId());
			Row row1 = exportExcel.addRow();
			
			Cell teacherLabelCell = row1.createCell(0);
			teacherLabelCell.setCellValue("任课教师");
			Cell teacherValueCell = row1.createCell(1);
			teacherValueCell.setCellValue(course.getTeacher().getTchrName());
			
			Cell statusLabelCell = row1.createCell(2);
			statusLabelCell.setCellValue("考核状态(注)");
			Cell statusRemarkLabelCell = row1.createCell(3);
			statusRemarkLabelCell.setCellValue("重修、缓考、未选修、旷考、违纪");
			
			exportExcel.setHeader(headerList);
			Collections.sort(list);
			exportExcel.setDataList(list).write(response, fileName).dispose();

		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/course/course/list?repage";
    }
	
}