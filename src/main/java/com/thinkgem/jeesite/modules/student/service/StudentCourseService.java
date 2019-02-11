/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.course.dao.CourseCompositionRulesDao;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;
import com.thinkgem.jeesite.modules.course.dao.CourseGpaDao;
import com.thinkgem.jeesite.modules.course.dao.CoursePointDao;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.entity.CourseGpa;
import com.thinkgem.jeesite.modules.course.entity.CoursePoint;
import com.thinkgem.jeesite.modules.student.dao.StudentCourseDao;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 学生成绩Service
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class StudentCourseService extends CrudService<StudentCourseDao, StudentCourse> {
	@Autowired
	private StudentCourseDao studentCourseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseCompositionRulesDao courseCompositionRulesDao;
	@Autowired
	private CoursePointDao coursePointDao; 
	@Autowired
	private CourseGpaDao courseGpaDao;

	
	public void gpa(List<StudentCourse> studentCourses) {
		List<CourseGpa> groupCourseGpas = courseGpaDao.groupList(new CourseGpa());
		for(CourseGpa entity:groupCourseGpas) {
			List<CourseGpa> courseGpas = courseGpaDao.findList(entity);
			for(CourseGpa courseGpa : courseGpas) {
				
			}
		}
	}
	
	public List<StudentCourse> findListByStudentCourseAndCourse(StudentCourse studentCourse){
		return studentCourseDao.findListByStudentCourseAndCourse(studentCourse);
	}

	
	public StudentCourse getStudentCourseByStudentCourse(StudentCourse studentCourse) {
		return studentCourseDao.getStudentCourseByStudentCourse(studentCourse);
	}
	
	public StudentCourse get(String id) {
		return super.get(id);
	}
	
	public List<StudentCourse> findList(StudentCourse studentCourse) {
		return super.findList(studentCourse);
	}
	

	public Page<StudentCourse> findPage(Page<StudentCourse> page, StudentCourse studentCourse) {
		return super.findPage(page, studentCourse);
	}
	
	@Transactional(readOnly = false)
	public void save(StudentCourse studentCourse) {
		super.save(studentCourse);
	}
	
	@Transactional(readOnly = false)
	public void delete(StudentCourse studentCourse) {
		super.delete(studentCourse);
	}
	@Transactional(readOnly = false)
	public String upload(File file) throws GITException{
		//缺少评分规则,详情参见~TeacherCourseService
		ImportExcel ei;
		try {
			ei = new ImportExcel(file, 1, 0);
			Row courseRow = ei.getRow(1);
			Cell courseCell = courseRow.getCell(0);   
			String courseId = courseCell.getStringCellValue();
			Course course = courseDao.get(courseId);
			if(StringUtils.isEmpty(course)) {
				throw new GITException("40000404","上传成绩文件异常,缺少课程编号");
			}
			Course entity = new Course();
			entity.setTeacher(UserUtils.getTeacher());
			List<Course> courses = courseDao.findList(entity);
			List<String> csList = new ArrayList<String>();
			for(Course cs : courses) {
				csList.add(cs.getId());
			}
			if(!csList.contains(courseId)) {
				throw new GITException("40000404","上传成绩不是当前任课教师课程,请检查上传文件内容");
			}
			Row termRow = ei.getRow(3);
			Cell termCell = termRow.getCell(0);
			String title = termCell.getStringCellValue().replaceAll("\\s*", "").replaceAll("学年度第.*学期", "");
			String[] arrays = title.split("——");
			String termYear = null;
			if(arrays.length==2) {
				String start = arrays[0];
				String end = arrays[1];
				termYear = start.concat("2");
				if(!start.equals(end)) {
					termYear = start.concat("1");
				}
			}

			if(StringUtils.isEmpty(termYear)) {
				String year = DateUtils.getYear();
				String month = DateUtils.getMonth();
				Integer y = Integer.valueOf(year);
				Integer m = Integer.valueOf(month);;
				if (m > 0b0011 && m < 0b1001) {
					y = y-1 ; 
					termYear = String.valueOf(y).concat("2");
				}else {
					termYear = year.concat("1");
				}
			}
			ei = new ImportExcel(file, 13, 0);
			List<StudentCourse> list = ei.getDataList(StudentCourse.class);
			int successNum = 0;
			int failureNum = 0;
			CourseCompositionRules rules = courseCompositionRulesDao.getCourseCompositionRulesByCourseId(course.getId());
			DecimalFormat df = new DecimalFormat("#.00");// 用于格式化Double类型数据，保留两位小数
			StringBuilder failureMsg = new StringBuilder();
			
			CoursePoint cp = new CoursePoint();
			cp.setCourse(course);;
			CoursePoint coursePoint = coursePointDao.getCoursePointByCourseId(cp);
			if(StringUtils.isEmpty(coursePoint)) {
				coursePoint = new CoursePoint();
				coursePoint.setPercentage("60");
				coursePoint.setPoint("0.1");
			}
			
			for (StudentCourse studentCourse : list) {
				if(!StringUtils.isEmpty(studentCourse.getStudentNumber())) {
					studentCourse.setTermYear(termYear);
					studentCourse.setCourse(course);
					studentCourse.setStatus("0");
					StudentCourse sc = studentCourseDao.getStudentCourseByStudentCourse(studentCourse);
					
					if(!StringUtils.isEmpty(rules)) {
						Double midEvaPer = Double.valueOf(rules.getMidTermPer()) / 100;// 期中成绩百分比
						Double finEvaPer = Double.valueOf(rules.getFinalExamper()) / 100;// 期末成绩百分比
						Double classEvaPer = Double.valueOf(rules.getClazzPer()) / 100;// 课堂表现百分比
						Double workEvaPer = Double.valueOf( rules.getHomeworkResultPer()) / 100;// 平时作业百分比
						Double expEvaPer = Double.valueOf( rules.getExpResultPer()) / 100;// 实验成绩百分比
						
						Double midEvaValue = Double.parseDouble(studentCourse.getMidEvaValue());// 期中成绩
						Double finEvaValue = Double.parseDouble(studentCourse.getFinEvaValue());// 期末成绩
						Double classEvaValue = Double.parseDouble(studentCourse.getClassEvaValue());// 课堂表现
						Double workEvaValue = Double.parseDouble(studentCourse.getWorkEvaValue());// 平时作业成绩
						Double expEvaValue = Double.parseDouble(studentCourse.getExpEvaValue());// 实验成绩
						
						
						Double evaValue = midEvaValue * midEvaPer + finEvaValue
								* finEvaPer + classEvaValue * classEvaPer
								+ workEvaValue * workEvaPer + expEvaValue
								* expEvaPer;

						evaValue = Double.valueOf(df.format(evaValue));
						studentCourse.setMidEvaValue(String.valueOf(midEvaValue));
						studentCourse.setFinEvaValue(String.valueOf(finEvaValue));
						studentCourse.setClassEvaValue(String.valueOf(classEvaValue));
						studentCourse.setWorkEvaValue(String.valueOf(workEvaValue));
						studentCourse.setExpEvaValue(String.valueOf(expEvaValue));
						studentCourse.setEvaValue(String.valueOf(evaValue));
						studentCourse.setPoint("1");
						this.save(studentCourse);
						failureNum++;
						continue;
					}
					
					String point = "0";
					if(isNumeric(studentCourse.getEvaValue())) {
						if (Double.parseDouble(studentCourse.getEvaValue()) - Double.parseDouble(coursePoint.getPercentage()) > 0) {
							Double value = Double.parseDouble(studentCourse.getEvaValue());
							Double percentage = Double.parseDouble(coursePoint.getPercentage());
							point = String.valueOf((value - percentage) * 0.1);
						}
					}
					if(!StringUtils.isEmpty(studentCourse.getPoint())) {
						point = studentCourse.getPoint();
					}
					try {
						if (StringUtils.isEmpty(sc)) {
							
							studentCourse.setPoint(point);
							this.save(studentCourse);
							successNum++;
						} else {
							if (StringUtils.isEmpty(sc.getClassEvaValue())) {
								sc.setClassEvaValue(studentCourse.getClassEvaValue());
							}
							if (StringUtils.isEmpty(sc.getEvaValue())) {
								sc.setEvaValue(studentCourse.getEvaValue());
							}
							if (StringUtils.isEmpty(sc.getExpEvaValue())) {
								sc.setExpEvaValue(studentCourse.getExpEvaValue());
							}
							if (StringUtils.isEmpty(sc.getFinEvaValue())) {
								sc.setFinEvaValue(studentCourse.getFinEvaValue());
							}
							if (StringUtils.isEmpty(sc.getMidEvaValue())) {
								sc.setMidEvaValue(studentCourse.getMidEvaValue());
							}
							if (StringUtils.isEmpty(sc.getWorkEvaValue())) {
								sc.setWorkEvaValue(studentCourse.getWorkEvaValue());
							}
							if (StringUtils.isEmpty(sc.getCredit())) {
								sc.setCredit(studentCourse.getCredit());
							}
							sc.setPoint(point);
							this.save(sc);
							failureNum++;
						}
					} catch (ConstraintViolationException ex) {
						failureMsg.append("\r\n姓名 " + studentCourse.getStudentName() + " 导入失败：");
						List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
						for (String message : messageList) {
							failureMsg.append(message + "; ");
							failureNum++;
						}
					} catch (Exception ex) {
						failureMsg.append("\r\n姓名 " + studentCourse.getStudentName() + " 导入失败：" + ex.getMessage());
					}
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			return "已成功导入 "+successNum+" 条成绩信息"+failureMsg;
		} catch (InvalidFormatException e) {
			throw new GITException("50000404","读取excel文件异常");
		} catch (IOException e) {
			throw new GITException("50000404","读取excel文件异常");
		} catch (InstantiationException e) {
			throw new GITException("50000404","系统异常,请联系管理员 : 18801029695");
		} catch (IllegalAccessException e) {
			throw new GITException("50000404","系统异常,请联系管理员 : 18801029695");
		}
		
	}
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static void main(String[] args) {
		System.out.println(((92*4+80*3+98*2+70*6+89*3)*4)/((4+3+2+6+3)*100));
	}
	
}