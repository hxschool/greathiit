package com.thinkgem.jeesite.modules.course.web.excel;

import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

public class CourseSelectExcel {
	private String id;
	private Office dept;
	private Office major;
	private Office cla;
	private User user;
	private Course course;
	private StudentCourse studentCourse;
	@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@ExcelField(title="学院", type=1, align=2, sort=2)
	public String getDeptName() {
		return dept.getName();
	}
	@ExcelField(title="专业", type=1, align=2, sort=3)
	public String getMajorName() {
		return major.getName();
	}
	@ExcelField(title="班级", type=1, align=2, sort=4)
	public String getClassName() {
		String classno = StudentUtil.getClassId(user.getNo());
		return StudentUtil.getClassName(classno);
	}
	@ExcelField(title="学号", type=1, align=2, sort=5)
	public String getStudentNumber() {
		return user.getNo();
	}
	@ExcelField(title="姓名", type=1, align=2, sort=6)
	public String getName() {
		return user.getName();
	}
	@ExcelField(title="学习模式", type=1, align=2, sort=7)
	public String getLearningModel() {
		return DictUtils.getDictLabel(course.getCursLearningModel(), "course_learning_model", "未知模式");
	}
	@ExcelField(title="选修课程", type=1, align=2, sort=8)
	public String getCourseName() {
		return course.getCursName();
	}
	@ExcelField(title="成绩", type=1, align=2, sort=9)
	
	public String getFinEvaValue() {
		return studentCourse.getFinEvaValue();
	}
	
	@ExcelField(title="学分", type=1, align=2, sort=10)
	public String getEvaValue() {
		return studentCourse.getEvaValue();
	}
	@ExcelField(title="签到", type=1, align=2, sort=11)
	public String getSignIn() {
		return "";
	}
	
	
	public Office getDept() {
		return dept;
	}
	public void setDept(Office dept) {
		this.dept = dept;
	}
	public Office getMajor() {
		return major;
	}
	public void setMajor(Office major) {
		this.major = major;
	}
	public Office getCla() {
		return cla;
	}
	public void setCla(Office cla) {
		this.cla = cla;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public StudentCourse getStudentCourse() {
		return studentCourse;
	}
	public void setStudentCourse(StudentCourse studentCourse) {
		this.studentCourse = studentCourse;
	} 
	
	
}
