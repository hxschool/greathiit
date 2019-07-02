/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.select.entity;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 选课信息表Entity
 * @author 赵俊飞
 * @version 2018-07-31
 */
public class SelectCourse extends DataEntity<SelectCourse>  implements Comparable<SelectCourse>{
	
	private static final long serialVersionUID = 1L;
	private Course course;		// 课程编号
	private Student student;		// 学号
	private List<Course> courses;//选择学期课程

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public SelectCourse() {
		super();
	}

	public SelectCourse(String id){
		super(id);
	}

	
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	@ExcelField(title="班级", align=2, sort=1)
	public String getClassname() {
		if(!StringUtils.isEmpty(student)) {
			if(!StringUtils.isEmpty(student.getStudentNumber())) {
				String classId = StudentUtil.getClassId(student.getStudentNumber());
				User st = UserUtils.getCasByLoginName(student.getStudentNumber());
				if(!StringUtils.isEmpty(st.getClazz())) {
					Office clazz = st.getClazz();
					if(!StringUtils.isEmpty(clazz)) {
						classId = clazz.getId();
					}
				}
				return StudentUtil.getClassName(classId);
			}
		}
		return "";
	}
	@ExcelField(title="学号", align=2, sort=2)
	public String getStudentNumber() {
		if(!StringUtils.isEmpty(student)) {
			return student.getStudentNumber();
		}
		return "";
	}
	@ExcelField(title="姓名", align=2, sort=3)
	public String getStudentname() {
		if(!StringUtils.isEmpty(student)) {
			return student.getName();
		}
		return "";
	}
	
	@ExcelField(title="课程名称", align=2, sort=4)
	public String getCoursename() {
		if(!StringUtils.isEmpty(course)) {
			return StringEscapeUtils.unescapeHtml4(course.getCursName());
		}
		return "";
	}
	
	@ExcelField(title="学期", align=2, sort=5)
	public String getCursYearTerm() {
		if(!StringUtils.isEmpty(course)) {
			String[] ss = course.getCursYearTerm().split("-");
			String startYear = ss[0];
			String endYear = ss[1];
			String n = ss[2].equals("01")? "一" : "二";
			return startYear + " —— " + endYear + " 学年度第" + n + "学期";
		}
		return "";
	}
	
	@ExcelField(title="任课教师", align=2, sort=6)
	public String getTeachername() {
		if(!StringUtils.isEmpty(course)) {
			if(!StringUtils.isEmpty(course.getTeacher())) {
				User teacher = UserUtils.getCasByLoginName(course.getTeacher().getTeacherNumber());
				if(!StringUtils.isEmpty(teacher)) {
					return teacher.getName();
				}
			}
		}
		return "";
	}
	
	@ExcelField(title="备注", align=2, sort=7)
	public String getRemarks() {
		return "";
	}

	@Override
	public int compareTo(SelectCourse selectCourse) {
		BigInteger a = new BigInteger(student.getStudentNumber());
		BigInteger b = new BigInteger(selectCourse.getStudent().getStudentNumber());
		return a.compareTo(b);
	}
}