/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.student.service.StudentService;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;
import com.thinkgem.jeesite.modules.teacher.service.TeacherClassService;

/**
 * 学生成绩Controller
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/student/studentCourse")
public class StudentCourseController extends BaseController {
	
	@Autowired
	private TeacherClassService teacherClassService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SelectCourseService selectCourseService;
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private CourseService courseService;
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
		User user = UserUtils.getUser();
		Student student = new Student();
		student.setStudent(user);
		studentCourse.setStudent(student);
		model.addAttribute("studentCourses", studentCourseService.findList(studentCourse));
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
		Course course = studentCourse.getCourse();
		if(!isAdmin()) {
			if(org.springframework.util.StringUtils.isEmpty(course)) {
				 course = new Course();
			}
			course.setTeacher(UserUtils.getTeacher());
			studentCourse.setCourse(course);
		}
		
		if(org.springframework.util.StringUtils.isEmpty(studentCourse.getTermYear())) {
			studentCourse.setTermYear(config.getTermYear());
		}
		Page<StudentCourse> page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
		model.addAttribute("page", page);
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

	@RequiresPermissions("student:studentCourse:export")
    @RequestMapping(value = "export")
    public String exportFile(StudentCourse studentCourse, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "成绩数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<StudentCourse> page = studentCourseService.findPage(new Page<StudentCourse>(request, response), studentCourse); 
    		new ExportExcel("成绩数据", StudentCourse.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "成绩数据！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/student/studentCourse/list?repage";
    }
	
	@RequiresPermissions("student:studentCourse:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		
		ImportExcel ei;
		try {
			ei = new ImportExcel(file, 1, 0);
			Row courseRow = ei.getRow(1);
			Cell courseIdCell = courseRow.getCell(5);   
			String courseId = courseIdCell.getStringCellValue();
			Course course = courseService.get(courseId);
			if(org.springframework.util.StringUtils.isEmpty(course)) {
				throw new GITException("40000404","上传成绩文件异常,缺少课程编号");
			}
			Course entity = new Course();
			entity.setTeacher(UserUtils.getTeacher());
			List<Course> courses = courseService.findList(entity);
			List<String> csList = new ArrayList<String>();
			if(!isAdmin()) {
				for(Course cs : courses) {
					csList.add(cs.getId());
				}
			}
			if(!CollectionUtils.isEmpty(csList) && !csList.contains(courseId)) {
				throw new GITException("40000404","上传成绩不是当前任课教师课程,请检查上传文件内容");
			}
			ei = new ImportExcel(file, 2, 0);
			List<StudentCourse> list = ei.getDataList(StudentCourse.class);
			
			addMessage(redirectAttributes, studentCourseService.importStudentCourse(course,list));
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
			User user = UserUtils.getUser();
			String fileName = course.getCursName().concat("成绩导入模板.xlsx");
			List<StudentCourse> list = Lists.newArrayList();

			if (!course.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
				logger.info("普通课模式");
				TeacherClass teacherClass = new TeacherClass();
				teacherClass.setTeacherNumber(user.getNo());
				List<TeacherClass> teacherList = teacherClassService.findList(teacherClass);
				List<String> clazzNumbers = Lists.newArrayList();
				logger.info("导入班级学生信息");
				for (TeacherClass tc : teacherList) {
					clazzNumbers.add(tc.getClazz().getId());
				}
				if(CollectionUtils.isEmpty(clazzNumbers)) {
					throw new RuntimeException("当前教师未设置班级信息");
				}
				Student student = new Student();
				student.setClazzNumbers(clazzNumbers);
				List<Student> students = studentService.findList(student);

				for (Student st : students) {
					StudentCourse sc = new StudentCourse();
					sc.setStudentNumber(st.getStudentNumber());
					sc.setStudentName(st.getName());
					list.add(sc);
				}
			} else {
				logger.info("选课模式");
				logger.info("导入选课学生信息");
				SelectCourse selectCourse = new SelectCourse();
				selectCourse.setCourse(course);
				List<SelectCourse> selectCourses = selectCourseService.findList(selectCourse);
				for (SelectCourse scc : selectCourses) {
					StudentCourse sc = new StudentCourse();
					sc.setStudentNumber(scc.getStudent().getNo());
					sc.setStudentName(scc.getStudent().getName());
					list.add(sc);
				}

			}
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

			Cell teacherLabelCell = row.createCell(6);
			teacherLabelCell.setCellValue("任课教师");
			Cell teacherValueCell = row.createCell(7);
			teacherValueCell.setCellValue(course.getTeacher().getTchrName());

			exportExcel.setHeader(headerList);

			exportExcel.setDataList(list).write(response, fileName).dispose();

		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/student/studentCourse/list?repage";
    }
	
}