/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportResult;
import com.thinkgem.jeesite.modules.course.dao.CourseClassDao;
import com.thinkgem.jeesite.modules.course.dao.CourseCompositionRulesDao;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;
import com.thinkgem.jeesite.modules.course.dao.CourseGpaDao;
import com.thinkgem.jeesite.modules.course.dao.CoursePointDao;
import com.thinkgem.jeesite.modules.course.dao.CourseScheduleDao;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.course.entity.CourseGpa;
import com.thinkgem.jeesite.modules.course.entity.CoursePoint;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.select.dao.SelectCourseDao;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.student.dao.StudentCourseDao;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.dao.TeacherClassDao;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;
import com.thinkgem.jeesite.modules.uc.student.dao.UcStudentDao;

/**
 * 学生成绩Service
 * 
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class StudentCourseService extends CrudService<StudentCourseDao, StudentCourse> {
	DecimalFormat df = new DecimalFormat("#.00");
	@Autowired
	private StudentCourseDao studentCourseDao;
	@Autowired
	private CourseCompositionRulesDao courseCompositionRulesDao;
	@Autowired
	private CoursePointDao coursePointDao;
	@Autowired
	private CourseGpaDao courseGpaDao;
	@Autowired
	private CourseScheduleDao courseScheduleDao;
	@Autowired
	private TeacherClassDao teacherClassDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private SelectCourseDao selectCourseDao;
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private UcStudentDao ucStudentDao;
	@Autowired
	private CourseDao courseDao;

	public List<StudentCourse> getStudentCourses(Course course) {
		List<StudentCourse> list = Lists.newArrayList();

		CourseClass entity = new CourseClass();
		entity.setCourse(course);
		List<CourseClass> cls = courseClassDao.findList(entity);
		if (!CollectionUtils.isEmpty(cls)) {
			logger.info("已设置班级,根据课程对应班级进行相关操作");
			List<String> clazzIds = Lists.newArrayList();
			for (CourseClass cc : cls) {
				clazzIds.add(cc.getCls().getId());
			}
			Student student = new Student();
			student.setClazzNumbers(clazzIds);
			List<Student> students = studentDao.findList(student);
			for (Student st : students) {
				StudentCourse sc = new StudentCourse();
				sc.setStudentNumber(st.getStudentNumber());
				sc.setStudentName(st.getName());
				sc.setCourse(course);
				list.add(sc);
			}
			return list;
		}

		if (!course.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
			// 判断是否排课,如果排课再班级里面获取学生信息
			CourseSchedule courseSchedule = new CourseSchedule();
			courseSchedule.setCourseId(course.getId());
			List<CourseSchedule> courseSchedules = courseScheduleDao.findList(courseSchedule);
			if (!CollectionUtils.isEmpty(courseSchedules)) {
				logger.info("已设置排课,优先排课模式");
				for (CourseSchedule cs : courseSchedules) {
					String courseClass = cs.getCourseClass();
					if (!StringUtils.isEmpty(courseClass) && courseClass.equals("00000000")) {
						String[] arrayIds = courseClass.split(",");
						List<String> clazzIds = Arrays.asList(arrayIds);
						Student student = new Student();
						student.setClazzNumbers(clazzIds);
						List<Student> students = studentDao.findList(student);
						for (Student st : students) {
							StudentCourse sc = new StudentCourse();
							sc.setStudentNumber(st.getStudentNumber());
							sc.setStudentName(st.getName());
							sc.setCourse(course);
							list.add(sc);
						}
					}
				}
			} else {
				logger.info("普通课模式");
				TeacherClass teacherClass = new TeacherClass();
				teacherClass.setTeacherNumber(course.getTeacher().getTeacherNumber());
				List<TeacherClass> teacherList = teacherClassDao.findList(teacherClass);
				List<String> clazzIds = Lists.newArrayList();
				logger.info("导入班级学生信息");
				for (TeacherClass tc : teacherList) {
					clazzIds.add(tc.getClazz().getId());
				}
				if (CollectionUtils.isEmpty(clazzIds)) {
					throw new GITException("40400000", "当前教师未设置班级信息");
				}
				Student student = new Student();
				student.setClazzNumbers(clazzIds);
				List<Student> students = studentDao.findList(student);
				for (Student st : students) {
					StudentCourse sc = new StudentCourse();
					sc.setStudentNumber(st.getStudentNumber());
					sc.setStudentName(st.getName());
					sc.setCourse(course);
					list.add(sc);
				}
			}

		} else {
			logger.info("选课模式");
			logger.info("导入选课学生信息");
			SelectCourse selectCourse = new SelectCourse();
			selectCourse.setCourse(course);
			List<SelectCourse> selectCourses = selectCourseDao.findList(selectCourse);
			for (SelectCourse scc : selectCourses) {
				StudentCourse sc = new StudentCourse();
				sc.setStudentNumber(scc.getStudent().getStudentNumber());
				sc.setStudentName(scc.getStudent().getName());
				sc.setCourse(course);
				list.add(sc);
			}
		}
		return list;
	}

	public void gpa(List<StudentCourse> studentCourses) {
		List<CourseGpa> groupCourseGpas = courseGpaDao.groupList(new CourseGpa());
		for (CourseGpa entity : groupCourseGpas) {
			List<CourseGpa> courseGpas = courseGpaDao.findList(entity);
			for (CourseGpa courseGpa : courseGpas) {

			}
		}
	}

	public List<StudentCourse> findListByStudentCourseAndCourse(StudentCourse studentCourse) {
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
	public ImportResult<Course> importStudentCourse(MultipartFile file) throws GITException {

		ImportResult<Course> importResult = new ImportResult<Course>();
		int successNum = 0;
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		Course course = null;
		try {

			ImportExcel ei = new ImportExcel(file, 1, 0);
			Row courseRow = ei.getRow(1);
			Cell courseIdCell = courseRow.getCell(5);   
			String courseId = courseIdCell.getStringCellValue();
			if (StringUtils.isEmpty(courseId)) {
				throw new GITException("50000404", "上传成绩课程编码不允许为空");
			}
			course = courseDao.get(courseId);
			if (StringUtils.isEmpty(course)) {
				throw new GITException("50000404", "根据课程编码未查找到相关课程信息");
			}
			if (StringUtils.isEmpty(course.getExamTime())) {
				throw new GITException("50000404", "当前课程开始时间为空,请设置考试时间");
			}
			if (StringUtils.isEmpty(course.getCategory())) {
				throw new GITException("50000404", "当前课程成绩类别为空,请重新设置成绩类别");
			}
			if (StringUtils.isEmpty(course.getPropositioner())) {
				throw new GITException("50000404", "当前课程命题人为空,请设置命题人");
			}
			if (StringUtils.isEmpty(course.getRater())) {
				throw new GITException("50000404", "当前课程成绩评分人,请重新设置评分人");
			}
			//设置录入人信息
			CourseCompositionRules courseCompositionRules = courseCompositionRulesDao
					.getCourseCompositionRulesByCourseId(course.getId());
			if (StringUtils.isEmpty(courseCompositionRules)) {
				switch (course.getCursType()) {
				case Course.COURSE_TYPE_EXA:
					courseCompositionRules = courseCompositionRulesDao.getCourseCompositionRulesByCourseId("99999999");
					break;
				case Course.COURSE_TYPE_TEST:
					courseCompositionRules = courseCompositionRulesDao.getCourseCompositionRulesByCourseId("00000000");
					break;
				case Course.COURSE_TYPE_OTHER:
					break;
				default:
					break;
				}

			}
			// 用于格式化Double类型数据，保留两位小数

			CoursePoint cp = new CoursePoint();
			cp.setCourse(course);
			CoursePoint coursePoint = coursePointDao.getCoursePointByCourseId(cp);
			if (StringUtils.isEmpty(coursePoint)) {
				Course c = new Course();
				c.setId("00000000");
				cp.setCourse(c);
				coursePoint = coursePointDao.getCoursePointByCourseId(cp);
			}

			ei = new ImportExcel(file, 3, 0);
			List<StudentCourse> list = ei.getDataList(StudentCourse.class);
			Collections.sort(list);
			for (StudentCourse studentCourse : list) {
				Student student = studentCourse.getStudent();
				if (!StringUtils.isEmpty(student) && !StringUtils.isEmpty(student.getStudentNumber())) {
					studentCourse.setCourse(course);
					if(StringUtils.isEmpty(studentCourse.getStatus())) {
						logger.info("如果状态未设置,认为状态正常");
						studentCourse.setStatus("0");
					}
					StudentCourse sc = studentCourseDao.getStudentCourseByStudentCourse(studentCourse);
					if (StringUtils.isEmpty(sc)) {
						if(!course.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
							// 判断课程类型
							String classEvaValue = studentCourse.getClassEvaValue();// 课堂成绩
							String finEvaValue = studentCourse.getFinEvaValue();// 期末成绩
							if (StringUtils.isEmpty(classEvaValue)) {
								classEvaValue = "0";
							}
							if (StringUtils.isEmpty(finEvaValue)) {
								finEvaValue = "0";
							}
							studentCourse.setClassEvaValue(String.valueOf(Double.valueOf(classEvaValue).intValue()));
							studentCourse.setFinEvaValue(String.valueOf(Double.valueOf(finEvaValue).intValue()));
							/*
							if (!POIUtils.isNumeric(classEvaValue)) {
								classEvaValue = StudentCourseUtil.getPercentageSocre(classEvaValue);
							}
							if (!POIUtils.isNumeric(finEvaValue)) {
								finEvaValue = StudentCourseUtil.getPercentageSocre(finEvaValue);
							}
							*/
	
							String evaValue = String.valueOf(Double.valueOf(Double
									.valueOf((Double.parseDouble(classEvaValue)
											* Double.parseDouble(courseCompositionRules.getClazzPer())
											+ Double.parseDouble(finEvaValue)
													* Double.parseDouble(courseCompositionRules.getFinalExamper()))).intValue())
									.intValue());
							String point = "0";
							if(Integer.valueOf(evaValue)>60) {
								point = String.format("%.1f",((Double.valueOf(evaValue) - Double.valueOf(coursePoint.getPercentage()))
										* Double.valueOf(coursePoint.getPoint())));;
							}
							 
							
							studentCourse.setPoint(point);
							studentCourse.setEvaValue(evaValue);
						}
						studentCourse.setCredit(course.getCursCredit());
						studentCourse.setTermYear(course.getCursYearTerm());
						this.save(studentCourse);
						successNum++;
					}
					/*
					 * if(!StringUtils.isEmpty(rules)) { Double midEvaPer =
					 * Double.valueOf(rules.getMidTermPer()) / 100;// 期中成绩百分比 Double finEvaPer =
					 * Double.valueOf(rules.getFinalExamper()) / 100;// 期末成绩百分比 Double classEvaPer =
					 * Double.valueOf(rules.getClazzPer()) / 100;// 课堂表现百分比 Double workEvaPer =
					 * Double.valueOf( rules.getHomeworkResultPer()) / 100;// 平时作业百分比 Double
					 * expEvaPer = Double.valueOf( rules.getExpResultPer()) / 100;// 实验成绩百分比
					 * 
					 * Double midEvaValue = Double.parseDouble(studentCourse.getMidEvaValue());//
					 * 期中成绩 Double finEvaValue =
					 * Double.parseDouble(studentCourse.getFinEvaValue());// 期末成绩 Double
					 * classEvaValue = Double.parseDouble(studentCourse.getClassEvaValue());// 课堂表现
					 * Double workEvaValue = Double.parseDouble(studentCourse.getWorkEvaValue());//
					 * 平时作业成绩 Double expEvaValue =
					 * Double.parseDouble(studentCourse.getExpEvaValue());// 实验成绩
					 * 
					 * 
					 * Double evaValue = midEvaValue * midEvaPer + finEvaValue finEvaPer +
					 * classEvaValue * classEvaPer + workEvaValue * workEvaPer + expEvaValue
					 * expEvaPer;
					 * 
					 * evaValue = Double.valueOf(df.format(evaValue));
					 * studentCourse.setMidEvaValue(String.valueOf(midEvaValue));
					 * studentCourse.setFinEvaValue(String.valueOf(finEvaValue));
					 * studentCourse.setClassEvaValue(String.valueOf(classEvaValue));
					 * studentCourse.setWorkEvaValue(String.valueOf(workEvaValue));
					 * studentCourse.setExpEvaValue(String.valueOf(expEvaValue));
					 * studentCourse.setEvaValue(String.valueOf(evaValue));
					 * studentCourse.setPoint("1"); this.save(studentCourse); failureNum++;
					 * continue; }
					 * 
					 * String point = "0"; if(isNumeric(studentCourse.getEvaValue())) { if
					 * (Double.parseDouble(studentCourse.getEvaValue()) -
					 * Double.parseDouble(coursePoint.getPercentage()) > 0) { Double value =
					 * Double.parseDouble(studentCourse.getEvaValue()); Double percentage =
					 * Double.parseDouble(coursePoint.getPercentage()); point =
					 * String.valueOf((value - percentage) * 0.1); } }
					 * if(!StringUtils.isEmpty(studentCourse.getPoint())) { point =
					 * studentCourse.getPoint(); } try { if (StringUtils.isEmpty(sc)) {
					 * 
					 * studentCourse.setPoint(point); this.save(studentCourse); successNum++; } else
					 * { if (StringUtils.isEmpty(sc.getClassEvaValue())) {
					 * sc.setClassEvaValue(studentCourse.getClassEvaValue()); } if
					 * (StringUtils.isEmpty(sc.getEvaValue())) {
					 * sc.setEvaValue(studentCourse.getEvaValue()); } if
					 * (StringUtils.isEmpty(sc.getExpEvaValue())) {
					 * sc.setExpEvaValue(studentCourse.getExpEvaValue()); } if
					 * (StringUtils.isEmpty(sc.getFinEvaValue())) {
					 * sc.setFinEvaValue(studentCourse.getFinEvaValue()); } if
					 * (StringUtils.isEmpty(sc.getMidEvaValue())) {
					 * sc.setMidEvaValue(studentCourse.getMidEvaValue()); } if
					 * (StringUtils.isEmpty(sc.getWorkEvaValue())) {
					 * sc.setWorkEvaValue(studentCourse.getWorkEvaValue()); } if
					 * (StringUtils.isEmpty(sc.getCredit())) {
					 * sc.setCredit(studentCourse.getCredit()); } sc.setPoint(point); this.save(sc);
					 * failureNum++; } } catch (ConstraintViolationException ex) {
					 * failureMsg.append("\r\n姓名 " + studentCourse.getStudentName() + " 导入失败：");
					 * List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex,
					 * ": "); for (String message : messageList) { failureMsg.append(message +
					 * "; "); failureNum++; }
					 * 
					 * } catch (Exception ex) { failureMsg.append("\r\n姓名 " +
					 * studentCourse.getStudentName() + " 导入失败：" + ex.getMessage()); }
					 */
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户，导入信息如下：");
			}
		} catch (Exception e) {
			throw new GITException("50000404", e.getMessage());
		}
		importResult.setSuccessNum(successNum);
		importResult.setFailureNum(failureNum);
		importResult.setFailureMsg(failureMsg);
		importResult.setObject(course);
		return importResult;
	}

	public List<String> groupTermYear(StudentCourse studentCourse) {
		return studentCourseDao.groupTermYear(studentCourse);
	}

	public static void main(String[] args) {
		System.out.println(((92 * 4 + 80 * 3 + 98 * 2 + 70 * 6 + 89 * 3) * 4) / ((4 + 3 + 2 + 6 + 3) * 100));
	}

}