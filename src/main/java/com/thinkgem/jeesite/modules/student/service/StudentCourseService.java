/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.thinkgem.jeesite.common.utils.RegexUtils;
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
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.teacher.dao.TeacherClassDao;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;

/**
 * 学生成绩Service
 * 
 * @author 赵俊飞
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class StudentCourseService extends CrudService<StudentCourseDao, StudentCourse> {
	
	private static Map<String,String> fiveMap = new HashMap<String,String>();
	private static Map<String,String> fivePointMap = new HashMap<String,String>();
	static {
		fiveMap.put("优秀优秀","优秀");
		fiveMap.put("优优","优秀");
		fiveMap.put("优秀良好","优秀");
		fiveMap.put("优良","优秀");
		fiveMap.put("优秀中等","中等");
		fiveMap.put("优中","中等");
		fiveMap.put("优秀及格","中等");
		fiveMap.put("优及格","中等");

		fiveMap.put("良好优秀","良好");
		fiveMap.put("好优","良好");
		fiveMap.put("良好良好","良好");
		fiveMap.put("良良","良好");
		fiveMap.put("良好中等","良好");
		fiveMap.put("良中","良好");
		fiveMap.put("良好及格","及格");
		fiveMap.put("良及格","及格");

		fiveMap.put("中等优秀","良好");
		fiveMap.put("中优","良好");
		fiveMap.put("中等良好","中等");
		fiveMap.put("中良","中等");
		fiveMap.put("中等中等","中等");
		fiveMap.put("中中","中等");
		fiveMap.put("中等及格","及格");
		fiveMap.put("中及格","及格");

		fiveMap.put("及格优秀","中等");
		fiveMap.put("及格优","中等");
		fiveMap.put("及格良好","中等");
		fiveMap.put("及格良","中等");
		fiveMap.put("及格中等","及格");
		fiveMap.put("及格中","及格");
		fiveMap.put("及格及格","及格");
		
		fivePointMap.put("优秀", "3.5");
		fivePointMap.put("良好", "2.5");
		fivePointMap.put("中等", "1.5");
		fivePointMap.put("及格", "0.5");
		fivePointMap.put("不及格", "0");
	}
	
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
				if(!StringUtils.isEmpty(cc.getClassId())) {
					clazzIds.add(cc.getClassId());
				}
			}
			Student student = new Student();
			student.setClazzNumbers(clazzIds);
			student.setStatus("1");
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

	public List<StudentCourse> findByParentIdsLike(StudentCourse studentCourse) {
		return super.findByParentIdsLike(studentCourse);
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
			for (StudentCourse studentCourse : list) {
				Student student = studentCourse.getStudent();
				if (!StringUtils.isEmpty(student) && !StringUtils.isEmpty(student.getStudentNumber())) {
					studentCourse.setCourse(course);
					String status = studentCourse.getStatus();
					studentCourse.setStatus(null);
					StudentCourse sc = studentCourseDao.getStudentCourseByStudentCourse(studentCourse);
					if (!StringUtils.isEmpty(sc)) {
						failureNum++;
						failureMsg.append("当前学生:"+studentCourse.getStudentName() +"课程:"+studentCourse.getCourse().getCursName() +"成绩已存在，成绩状态:"+DictUtils.getDictLabel(sc.getStatus(), "student_course_result", "未知状态"));
						continue;
					}
					if (StringUtils.isEmpty(sc)) {
						logger.info("当前课程模式:{},{}",course.getCursProperty(),DictUtils.getDictLabel(course.getCursProperty(), "course_property", "未知"));
						String classEvaValue = studentCourse.getClassEvaValue();// 课堂成绩
						String finEvaValue = studentCourse.getFinEvaValue();// 期末成绩
			
						
						if(course.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
							if(RegexUtils.isContainChinese(classEvaValue) && RegexUtils.isContainChinese(finEvaValue)) {
								String evaValue = "不及格";
								String point = "0";
								studentCourse.setCredit("0");
								evaValue = fiveMap.get(classEvaValue.concat(finEvaValue));
								point = fivePointMap.get(evaValue);
								studentCourse.setCredit(course.getCursCredit());
								studentCourse.setPoint(point);
								studentCourse.setEvaValue(evaValue);
							}else {
								failureNum++;
								failureMsg.append("当前学生:"+studentCourse.getStudentName() +"选课成绩非五分制");
								break;
							}
						}else{
							// 判断课程类型
							if(RegexUtils.isContainChinese(classEvaValue) || RegexUtils.isContainChinese(finEvaValue)) {
								failureNum++;
								failureMsg.append("当前课程:"+studentCourse.getCourse().getCursName() +"非选课模式,不允许使用五分制模式");
								break;
							}
							if (StringUtils.isEmpty(classEvaValue)) {
								classEvaValue = "0";
							}
							if (StringUtils.isEmpty(finEvaValue)) {
								finEvaValue = "0";
							}
							studentCourse.setClassEvaValue(String.valueOf(Double.valueOf(classEvaValue).intValue()));
							studentCourse.setFinEvaValue(String.valueOf(Double.valueOf(finEvaValue).intValue()));
	
								
							Double dd = new BigDecimal(classEvaValue).multiply(new BigDecimal(courseCompositionRules.getClazzPer())).add(new BigDecimal(finEvaValue).multiply(new BigDecimal(courseCompositionRules.getFinalExamper()))).doubleValue();
							
							Integer retValue =  new Long(Math.round(dd)).intValue(); //new Double(Math.ceil(dd)).intValue();

							String point = "0";
							studentCourse.setCredit("0");
							if(retValue>=60) {
								studentCourse.setCredit(course.getCursCredit());
								if(retValue>60) {
								point = String.format("%.1f",((dd - Double.valueOf(coursePoint.getPercentage()))
										* Double.valueOf(coursePoint.getPoint())));
								}
							}
							studentCourse.setPoint(point);
							studentCourse.setEvaValue(String.valueOf(retValue));
						}
						
						
						studentCourse.setTermYear(course.getCursYearTerm());
						if(StringUtils.isEmpty(status)) {
							logger.info("如果状态未设置,认为状态正常");
							status = "0";
						}
						studentCourse.setStatus(status);
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