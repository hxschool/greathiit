package com.thinkgem.jeesite.common.utils;

import java.util.Arrays;
import java.util.List;

import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;

public class StudentUtil {
	private static OfficeService officeService = SpringContextHolder.getBean(OfficeService.class);
	private static SelectCourseService selectCourseService = SpringContextHolder.getBean(SelectCourseService.class);
	private static String[] majors = {"01","02","03","04","05","06","07"};
	
	public static String  assignClasses(String majorId,String classNo) {
		String year = DateUtils.getDate("yyyy");
		String classNumber = year.concat(majorId).concat(String.format("%02d", Integer.valueOf(classNo)));
		Office clazz = officeService.get(classNumber);
		if(!org.springframework.util.StringUtils.isEmpty(clazz)) {
			return assignClasses(majorId,String.valueOf(Integer.valueOf(classNo) + 1));
		}
		if(org.springframework.util.StringUtils.isEmpty(clazz)) {
			List<String> majorList = Arrays.asList(majors);
			Office parent = officeService.get(majorId);
			clazz = new Office();
			clazz.setId(classNumber);
			clazz.setParent(parent);
			Area area = new Area();
			area.setId("230100");
			clazz.setArea(area);
			clazz.setCode(classNo);
			clazz.setType("4");
			clazz.setGrade("4");
			clazz.setUseable("1");
			if(majorList.contains(majorId)) {
				String name = parent.getName().concat(year.substring(2)).concat(classNo);
				clazz.setName(name);
			}else {
				clazz.setName(classNumber);
			}
			clazz.setCode(classNumber);
			clazz.setIsNewRecord(true);
			officeService.save(clazz);
		}
		return classNumber;
	}
	
	public static Office  createClasses(String year,String majorId,String classNo) {
		String classNumber = year.concat(majorId).concat(String.format("%02d", Integer.valueOf(classNo)));
		Office clazz = officeService.get(classNumber);
		if(org.springframework.util.StringUtils.isEmpty(clazz)) {
			List<String> majorList = Arrays.asList(majors);
			Office parent = officeService.get(majorId);
			clazz = new Office();
			clazz.setId(classNumber);
			clazz.setParent(parent);
			Area area = new Area();
			area.setId("230100");
			clazz.setArea(area);
			clazz.setCode(classNo);
			clazz.setType("4");
			clazz.setGrade("4");
			clazz.setUseable("1");
			if(majorList.contains(majorId)) {
				String name = parent.getName().concat(year.substring(2)).concat(classNo);
				clazz.setName(name);
			}else {
				clazz.setName(classNumber);
			}
			clazz.setCode(classNumber);
			clazz.setIsNewRecord(true);
			officeService.save(clazz);
		}
		return clazz;
	}

	public static String getClassId(String studentNumber) {
		if(StringUtils.isEmpty(studentNumber)) {
			return "";
		}
		
		if(studentNumber.length()==7) {
			return "20" + studentNumber.substring(0, 4) + "0" + studentNumber.substring(4, 5);
		}
		
		if(studentNumber.length()==8) {
			return "20" + studentNumber.substring(0, 4) + studentNumber.substring(4, 6);
		}
		
		if(studentNumber.length()==10) {
			return studentNumber.substring(0, 8);
		}
		
		if(studentNumber.length()==12) {
			return studentNumber.substring(0, 4) + studentNumber.substring(6, 10);
		}
		
		return "";
	}
	
	public static String getClassName(String classno) {
		Office entity = officeService.get(classno);
		if (entity != null) {
			return entity.getName();
		}
		return "";
	}
	
	public static String getCircles(String studentNumber) {
		if(StringUtils.isEmpty(studentNumber)) {
			return "";
		}
		if(studentNumber.length()==6||studentNumber.length()==7||studentNumber.length()==8) {
			return "20" + studentNumber.substring(0, 2);
		}		
		if(studentNumber.length()>10) {
			return studentNumber.substring(0, 4);
		}
		return "";
	}
	public static int  countStudents(String courseId) {
		if(!StringUtils.isEmpty(courseId)) {
			Course course = new Course();
			course.setId(courseId);
			SelectCourse selectCourse = new SelectCourse();
			selectCourse.setCourse(course);
			return selectCourseService.count(selectCourse);
		}
		return 0;
	}
	public static void main(String[] args) {
		String s = getClassId("1551342");
		
	}
}
