/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.TeacherType;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;

/**
 * 课程基本信息Entity
 * @author 赵俊飞
 * @version 2017-12-13
 */
public class Course extends DataEntity<Course> {
	
	public static final String COURSE_PROPERTY_SELECT = "50";
	
	public static final String COURSE_TYPE_EXA="10";
	public static final String COURSE_TYPE_TEST="20";
	public static final String COURSE_TYPE_OTHER="99";
	
	public static final String PAIKE_STATUS_WEI_PAIKE="00";
	public static final String PAIKE_STATUS_YI_PAIKE="10";
	public static final String PAIKE_STATUS_OVER_PAIKE="90";
	
	private static final long serialVersionUID = 1L;
	private String cursNum;		// 课程编号
	private String cursEduNum;		// 课程编号
	private String cursName;		// 课程名称
	private String cursEngName;		// 英文名称
	private String cursMajor;		// 专业
	private String cursWeekTotal;		// 周数
	private String cursWeekHour;		// 周学时
	private String cursClassHour;		// 学时
	private String cursCredit;		// 学分
	private String cursYearTerm;		// 
	private String cursIntro;		// 课程简介
	private String cursNote1;		// 与相关课程的分工衔接
	private String cursNote2;		// 其他说明
	private String cursPreCourses;		// 先修课程
	private String cursProperty;		// 课程性质
	private String cursSelectCourseType;//功课选课类型
	private String cursType;		// 	类型
	private String cursTotal;
	private String cursForm;
	private String cursStatus;
	private Integer upperLimit;//班额上限
	private Integer lowerLimit;//班额下限
	private Teacher teacher;		// 教师号
	private CourseTeachingMode courseTeachingMode;//教学模式
	private CourseCompositionRules courseCompositionRules;//规则
	private String teachingMode;
	private List<String> item;
	private String examTime;//考试时间
	private String propositioner;//命题人
	private String rater;//评分人
	private String category;//成绩类别
	private String enter;//录入人
	
	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getPropositioner() {
		return propositioner;
	}

	public void setPropositioner(String propositioner) {
		this.propositioner = propositioner;
	}

	public String getRater() {
		return rater;
	}

	public void setRater(String rater) {
		this.rater = rater;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getItem() {
		return item;
	}

	public void setItem(List<String> item) {
		this.item = item;
	}

	public Course() {
		super();
	}

	public Course(String id){
		super(id);
	}

	@Length(min=1, max=255, message="课程编号长度必须介于 1 和 255 之间")
	@ExcelField(title="课程编码",  align=2, sort=3)
	public String getCursNum() {
		return cursNum;
	}

	public void setCursNum(String cursNum) {
		this.cursNum = cursNum;
	}
	
	@Length(min=1, max=255, message="课程名称长度必须介于 1 和 255 之间")
	@ExcelField(title="课程名称",  align=2, sort=4)
	public String getCursName() {
		return cursName;
	}

	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	
	@Length(min=1, max=255, message="英文名称长度必须介于 1 和 255 之间")
	public String getCursEngName() {
		return cursEngName;
	}

	public void setCursEngName(String cursEngName) {
		this.cursEngName = cursEngName;
	}
	
	@Length(min=0, max=255, message="专业长度必须介于 0 和 255 之间")
	public String getCursMajor() {
		return cursMajor;
	}

	public void setCursMajor(String cursMajor) {
		this.cursMajor = cursMajor;
	}
	
	@Length(min=0, max=255, message="学时长度必须介于 0 和 255 之间")
	@ExcelField(title="学时",  align=2, sort=6)
	public String getCursClassHour() {
		return cursClassHour;
	}

	public void setCursClassHour(String cursClassHour) {
		this.cursClassHour = cursClassHour;
	}
	@ExcelField(title="学分",  align=2, sort=5)
	public String getCursCredit() {
		return cursCredit;
	}

	public void setCursCredit(String cursCredit) {
		this.cursCredit = cursCredit;
	}
	
	@Length(min=0, max=255, message="学期长度必须介于 0 和 255 之间")
	@ExcelField(title="学期",  align=2, sort=1)
	public String getCursYearTerm() {
		return cursYearTerm;
	}

	public void setCursYearTerm(String cursYearTerm) {
		this.cursYearTerm = cursYearTerm;
	}
	
	@Length(min=0, max=255, message="课程简介长度必须介于 0 和 2000 之间")
	public String getCursIntro() {
		return cursIntro;
	}

	public void setCursIntro(String cursIntro) {
		this.cursIntro = cursIntro;
	}
	
	@Length(min=0, max=255, message="与相关课程的分工衔接长度必须介于 0 和 255 之间")
	public String getCursNote1() {
		return cursNote1;
	}

	public void setCursNote1(String cursNote1) {
		this.cursNote1 = cursNote1;
	}
	
	@Length(min=0, max=255, message="其他说明长度必须介于 0 和 255 之间")
	public String getCursNote2() {
		return cursNote2;
	}

	public void setCursNote2(String cursNote2) {
		this.cursNote2 = cursNote2;
	}
	
	@Length(min=0, max=255, message="先修课程长度必须介于 0 和 255 之间")
	public String getCursPreCourses() {
		return cursPreCourses;
	}

	public void setCursPreCourses(String cursPreCourses) {
		this.cursPreCourses = cursPreCourses;
	}
	
	@Length(min=0, max=255, message="课程性质长度必须介于 0 和 255 之间")
	@ExcelField(title="课程性质",  align=2, sort=2,dictType="course_property")
	public String getCursProperty() {
		return cursProperty;
	}

	public void setCursProperty(String cursProperty) {
		this.cursProperty = cursProperty;
	}
	

	@Length(min=0, max=255, message="课程类型长度必须介于 0 和 255 之间")
	@ExcelField(title="课程类型",  align=2, sort=7,dictType="course_curs_type")
	public String getCursType() {
		return cursType;
	}

	public void setCursType(String cursType) {
		this.cursType = cursType;
	}
	
	@ExcelField(title="考核形式",  align=2, sort=8,dictType="course_curs_form")
	public String getCursForm() {
		return cursForm;
	}
	


	public void setCursForm(String cursForm) {
		this.cursForm = cursForm;
	}
	@ExcelField(title="教师姓名", align=2, sort=9,fieldType=TeacherType.class)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getCursWeekTotal() {
		return cursWeekTotal;
	}

	public void setCursWeekTotal(String cursWeekTotal) {
		this.cursWeekTotal = cursWeekTotal;
	}

	public String getCursWeekHour() {
		return cursWeekHour;
	}

	public void setCursWeekHour(String cursWeekHour) {
		this.cursWeekHour = cursWeekHour;
	}

	public String getCursTotal() {
		return cursTotal;
	}

	public void setCursTotal(String cursTotal) {
		this.cursTotal = cursTotal;
	}

	public String getCursStatus() {
		return cursStatus;
	}

	public void setCursStatus(String cursStatus) {
		this.cursStatus = cursStatus;
	}

	public String getCursSelectCourseType() {
		return cursSelectCourseType;
	}

	public void setCursSelectCourseType(String cursSelectCourseType) {
		this.cursSelectCourseType = cursSelectCourseType;
	}

	public String getCursEduNum() {
		return cursEduNum;
	}

	public void setCursEduNum(String cursEduNum) {
		this.cursEduNum = cursEduNum;
	}
	@ExcelField(title="班额上限",  align=2, sort=11)
	public Integer getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}
	@ExcelField(title="班额下限",  align=2, sort=12)
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	
	
	@ExcelField(title="教学模式",  align=2, sort=10,dictType="teac_method")
	public String getTeachingMode() {
		return teachingMode;
	}

	public void setTeachingMode(String teachingMode) {
		this.teachingMode = teachingMode;
	}


	public CourseTeachingMode getCourseTeachingMode() {
		return courseTeachingMode;
	}

	public void setCourseTeachingMode(CourseTeachingMode courseTeachingMode) {
		this.courseTeachingMode = courseTeachingMode;
	}
	
	public CourseCompositionRules getCourseCompositionRules() {
		return courseCompositionRules;
	}

	public void setCourseCompositionRules(CourseCompositionRules courseCompositionRules) {
		this.courseCompositionRules = courseCompositionRules;
	}

	@Override
	public String toString() {
		return cursName;
	}
}