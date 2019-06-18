/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.POIUtils;
import com.thinkgem.jeesite.modules.course.dao.CourseClassDao;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;
import com.thinkgem.jeesite.modules.course.dao.CourseScheduleDao;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.student.util.StudentCourseUtil;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.uc.student.dao.UcStudentDao;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 课程基本信息Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudService<CourseDao, Course> {
	DecimalFormat df = new DecimalFormat("#.00");
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private CourseScheduleDao courseScheduleDao;
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private UcStudentDao ucStudentDao;
	public Course getCourseByCourseId(String courseId) {
		Course course = new Course();
		course.setId(courseId);
		return courseDao.getCourse(course);
	}
	public Course getCourse(Course course) {
		return courseDao.getCourse(course);
	}
	
	public Course get(String id) {
		return super.get(id);
	}
	
	public List<Course> findCoursesByPaike(Course course) {
		return courseDao.findCoursesByPaike(course);
	}
	
	public List<Course> findList(Course course) {
		return super.findList(course);
	}
	
	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}
	
	@Transactional(readOnly = false)
	public Course importCourse(MultipartFile file) {
		logger.info("根据课程导入成绩");
		Course course = null;
		try {
			Workbook wb = null;
			InputStream is = file.getInputStream();
			String fileName = file.getOriginalFilename();
			if (StringUtils.isEmpty(fileName)){
				throw new RuntimeException("导入文档为空!");
			}else if(fileName.toLowerCase().endsWith("xls")){    
				wb = new HSSFWorkbook(is);    
	        }else if(fileName.toLowerCase().endsWith("xlsx")){  
	        	wb = new XSSFWorkbook(is);
	        }else{  
	        	throw new RuntimeException("文档格式不正确!");
	        }  
			Sheet clazzSheet = wb.getSheetAt(0);
			Row courseIdRow = clazzSheet.getRow(1);
			Cell courseIdCell = courseIdRow.getCell(0);
			course = courseDao.get(courseIdCell.getStringCellValue());
	
			for(int i=0;i<wb.getNumberOfSheets();i++) {
				Sheet sheet = wb.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();
				for(int rowIndex=14;rowIndex<=rowNum;rowIndex++ ) {
					Row row = sheet.getRow(rowIndex);
					String studentNumber = row.getCell(0).getStringCellValue();
					if(!StringUtils.isEmpty(studentNumber)) {
						String name = row.getCell(1).getStringCellValue();
						String classEvaValue = POIUtils.getCell(row.getCell(2));;
						
					
						String midEvaValue = POIUtils.getCell(row.getCell(3));
						String finEvaValue = POIUtils.getCell(row.getCell(4));
						StudentCourse studentCourse = new StudentCourse(); 
						studentCourse.setStudentNumber(studentNumber);
						studentCourse.setStudentName(name);
						studentCourse.setCourse(course);
						studentCourse.setTermYear(course.getCursYearTerm());
						
						studentCourse.setClassEvaValue(classEvaValue);
						studentCourse.setFinEvaValue(finEvaValue);
						if(!POIUtils.isNumeric(classEvaValue)) {
							classEvaValue = StudentCourseUtil.getPercentageSocre(classEvaValue);
						}
						if(!POIUtils.isNumeric(finEvaValue)) {
							finEvaValue = StudentCourseUtil.getPercentageSocre(finEvaValue);
						}
						String evaValue = "";
						//zhaojunfei
						switch (course.getCursType()) {
						case Course.COURSE_TYPE_EXA:
							//考试课：平时成绩*30%+期末成绩*70%=综合成绩
													
							evaValue = String.valueOf(Double.valueOf((Double.parseDouble(classEvaValue)*Double.parseDouble("0.30")+Double.parseDouble(finEvaValue)*Double.parseDouble("0.70"))).intValue());
							break;
						case Course.COURSE_TYPE_TEST:
							//考察课：平时成绩*40%+期末成绩*60%=综合成绩
							evaValue = String.valueOf(Double.valueOf((Double.parseDouble(classEvaValue)*Double.parseDouble("0.40")+Double.parseDouble(finEvaValue)*Double.parseDouble("0.60"))).intValue());
							break;
						case Course.COURSE_TYPE_OTHER:
							break;
						default:
							break;
						}
						String point = df.format((Double.valueOf(evaValue) - 60) * Double.valueOf("0.1"));
						studentCourse.setPoint(point);
						studentCourse.setEvaValue(evaValue);
						studentCourseService.save(studentCourse); 
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return course;
	}
	public void exportCourse(File file,Course course,OutputStream os) {
		logger.info("根据课程生成全部sheet班级信息");
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			CourseClass courseClass = new CourseClass();
			courseClass.setCourse(course);
			List<CourseClass> ccs = courseClassDao.findList(courseClass);
			for(CourseClass cc:ccs) {
				Office cls = cc.getCls();
				Office clazz = officeDao.get(cls);
				Office major = officeDao.get(clazz.getParent());
				Office school = officeDao.get(major.getParent());
				if(!StringUtils.isEmpty(clazz)) {
					HSSFSheet clazzSheet = wb.createSheet(clazz.getName());
					POIUtils.copySheet(wb, sheet , clazzSheet, true);
					Row schoolreportRow = clazzSheet.getRow(0);
					Cell courseNameCell = schoolreportRow.getCell(0);
				    CellStyle cellStyle = wb.createCellStyle();
				    cellStyle.setFont(POIUtils.getFont(wb));
				    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
					courseNameCell.setCellValue("  "+course.getCursName()+"   （科）成绩单");
					courseNameCell.setCellStyle(cellStyle);
					
					Row courseIdRow = clazzSheet.getRow(1);
					Cell courseIdCell = courseIdRow.getCell(0);
					courseIdCell.setCellValue(course.getId());

					Row departmentRow = clazzSheet.getRow(2);
					Cell departmentCell = departmentRow.getCell(0);
					
					departmentCell.setCellValue("院系 ："+school.getName()+"         专业："+major.getName()+"       班级："+clazz.getName());
					String[] ss = course.getCursYearTerm().split("-");
					String startYear = ss[0];
					String endYear = ss[1];
					String n = ss[2];
					Row yearTermRow = clazzSheet.getRow(3);
					Cell yearTermCell = yearTermRow.getCell(0);
					yearTermCell.setCellValue("    "+startYear+" —— "+endYear+" 学年度第"+n+"学期        ");
					
					UcStudent ucStudent = new UcStudent();
					ucStudent.setClassNumber(clazz.getName());
					//
					List<UcStudent> list = ucStudentDao.exportList(ucStudent);
					
					int rowIndex = 14;
					CellStyle style = POIUtils.formatCell(wb);
					for (UcStudent student : list) {
						Row studentRow = clazzSheet.createRow(rowIndex);
						studentRow.setHeight((short) 370);// 目的是想把行高设置成25px

						Cell studentNumberCell = studentRow.createCell(0);
						studentNumberCell.setCellValue(student.getStudentNumber());
						studentNumberCell.setCellStyle(style);
						Cell nameCell = studentRow.createCell(1);
						nameCell.setCellValue(student.getUsername());
						nameCell.setCellStyle(style);
						for (int j = 2; j < 9; j++) {
							Cell tempCell = studentRow.createCell(j);
							tempCell.setCellStyle(style);
						}
						rowIndex++;
					}
					
				}
				
			}
			wb.removeSheetAt(0);
			wb.write(os);
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	
	@Transactional(readOnly = false)
	public void delete(Course course) {
		CourseClass courseClass = new CourseClass();
		courseClass.setCourse(course);;
		courseClassDao.delete(courseClass);
		courseScheduleDao.deleteByCourse(course.getId());
		super.delete(course);
	}
	
}