/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.teacher.service;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.course.dao.CourseCompositionRulesDao;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseCompositionRules;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.util.ImportExecl;

/**
 * 教师信息Service
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class TeacherCourseService {
	
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseCompositionRulesDao courseCompositionRulesDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentCourseService studentCourseService;

	
	@Transactional(readOnly = false)
	public boolean handleExcel(String path) throws Exception {
		DecimalFormat df = new DecimalFormat("#.00");// 用于格式化Double类型数据，保留两位小数
		List<List<String>> result = new LinkedList<List<String>>();
		List<StudentCourse> stuCurs = new LinkedList<StudentCourse>();
		ImportExecl importExecl = new ImportExecl();
		result = importExecl.read(path);
		String studentNumber = "";
		String cursName = "";
		String cursCurrTerm = "";
		String cursClazz = "";
		Student student = null;
		Course course = null;
		List<Office> clazzs = new LinkedList<Office>();
		// 从路径中截取文件名，再截取课程学期及课程名，班级，找到课程对象、班级对象，为空则抛异常
		path = path.replaceAll("\\\\","/");
		String fileName = path.substring(path.lastIndexOf("/") + 1,path.length());// 截取文件名
		cursCurrTerm = fileName.substring(0, fileName.indexOf("_"));// 截取课程学期
		cursName = fileName.substring(fileName.indexOf("_") + 1,fileName.lastIndexOf("_"));// 截取课程名
		cursClazz = fileName.substring(fileName.lastIndexOf("_") + 1,
				fileName.lastIndexOf("."));
		String[] clazzArray = null;
		clazzArray = cursClazz.split(",");
		if ((!cursName.trim().equals("")) && (!cursCurrTerm.trim().equals(""))
				&& (!cursClazz.equals(""))) {
			Course courseQuery = new Course();
			courseQuery.setCursYearTerm(cursCurrTerm);
			courseQuery.setCursName(cursName);
			courseQuery.setTeacher(UserUtils.getTeacher());
			course = courseDao.getCourse(courseQuery);
			for (int i = 0; i < clazzArray.length; i++) {
				Office clazz = null;
				clazz = officeDao.get(clazzArray[i].trim());
				clazzs.add(clazz);
			}
			if (course == null) {
				throw new Exception("课程不存在");
			}
			if (clazzs.size() == 0) {
				throw new Exception("班级不存在");
			}
		}

		// 找到该课程的评分规则
		CourseCompositionRules rules = courseCompositionRulesDao.getCourseCompositionRulesByCourseId(course.getId());
		if (rules == null) {
			throw new Exception("未找到课程评分规则，请检查课程信息！");
		}
		Double midEvaPer = Double.valueOf(rules.getMidTermPer()) / 100;// 期中成绩百分比
		Double finEvaPer = Double.valueOf(rules.getFinalExamper()) / 100;// 期末成绩百分比
		Double classEvaPer = Double.valueOf(rules.getClazzPer()) / 100;// 课堂表现百分比
		Double workEvaPer = Double.valueOf( rules.getHomeworkResultPer()) / 100;// 平时作业百分比
		Double expEvaPer = Double.valueOf( rules.getExpResultPer()) / 100;// 实验成绩百分比

		if (result != null) {
			List<String> firstLine = new LinkedList<String>();// 第一行公共信息（包括学期、班级、课程）
			firstLine = result.get(0);
			for(int i=0;i<clazzs.size();i++){
				String clazzName = clazzs.get(i).getId();
				boolean isContain = firstLine.get(3).contains(clazzName);
				if(!isContain){
					throw new Exception("班级选择错误");
				}
			}
			if (!firstLine.get(5).equals(course.getCursName())) {
				throw new Exception("课程选择错误");
			}
			// List<String> notExistStudent = new LinkedList<String>();//
			// 表格中有而数据库中不存在的学生
			for (int i = 2; i < result.size(); i++) { // i从2开始意味着去掉表头和公共信息
				StudentCourse studentCourse = new StudentCourse();
				List<String> cellList = result.get(i);
				// 判断有无该学生，有则获取学生id
				studentNumber = cellList.get(0).toString();
				if (!studentNumber.equals("")) {
					student = studentDao.getStudentByStudentNumber(studentNumber);
					if (student != null) {
						studentCourse.setStudentNumber(student.getStudentNumber());
						//studentCourse.setStudentNumber(studentNumber);
					} else {
						throw new Exception("学号为" + studentNumber
								+ "的学生不存在");
					}

					studentCourse.setCourse(course);

					StudentCourse sc = studentCourseService.findStudentCourseByStudentNumberAndCourseId(course.getId(), studentNumber);
					if (sc != null) {
						studentCourseService.delete(sc);
					}

					String midString = cellList.get(2);
					String finString = cellList.get(3);
					String classString = cellList.get(4);
					String workString = cellList.get(5);
					String expString = cellList.get(6);
					// 若为空，则置为0
					if (midString.trim().length() == 0) {
						midString = "0";
					}
					if (finString.trim().length() == 0) {
						finString = "0";
					}
					if (classString.trim().length() == 0) {
						classString = "0";
					}
					if (workString.trim().length() == 0) {
						workString = "0";
					}
					if (expString.trim().length() == 0) {
						expString = "0";
					}

					Double midEvaValue = Double.parseDouble(midString);// 期中成绩
					Double finEvaValue = Double.parseDouble(finString);// 期末成绩
					Double classEvaValue = Double.parseDouble(classString);// 课堂表现
					Double workEvaValue = Double.parseDouble(workString);// 平时作业成绩
					Double expEvaValue = Double.parseDouble(expString);// 实验成绩

					// 计算单个学生的综合成绩
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
					studentCourse.setTermYear(cursCurrTerm);
					studentCourse.setStatus("0");
					stuCurs.add(studentCourse);
				}
				else continue;
			}

		}
		else{
			throw new Exception("未找到相应成绩");
		}

		if (stuCurs != null) {
			for (int i = 0; i < stuCurs.size(); i++) {
				// 单个学生成绩写入数据库
				studentCourseService.save(stuCurs.get(i));
			}
		}

		return true;
	}
}