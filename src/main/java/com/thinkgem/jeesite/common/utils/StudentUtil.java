package com.thinkgem.jeesite.common.utils;

import java.util.Arrays;
import java.util.List;

import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class StudentUtil {
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);
	
	private static String[] majors = {"01","02","03","04","05","06","07"};
	
	public static String  assignClasses(String majorId,String classNo) {
		String year = DateUtils.getDate("yyyy");
		String classNumber = year.concat(majorId).concat(String.format("%02d", classNo));
		Office clazz = officeDao.get(classNumber);
		if(org.springframework.util.StringUtils.isEmpty(clazz)) {
			List<String> majorList = Arrays.asList(majors);
			Office parent = officeDao.get(majorId);
			clazz = new Office();
			clazz.setId(classNumber);
			clazz.setParent(parent);
			if(majorList.contains(majorId)) {
				String name = parent.getName().concat(year.substring(2)).concat(classNo);
				clazz.setName(name);
			}else {
				clazz.setName(classNumber);
			}
			clazz.setCode(classNumber);
			officeDao.insert(clazz);
		}
		return classNumber;
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
	
	public static String getCircles(String studentNumber) {
		if(StringUtils.isEmpty(studentNumber)) {
			return "";
		}
		if(studentNumber.length()==6) {
			return "20" + studentNumber.substring(0, 2);
		}
		if(studentNumber.length()==7) {
			return "20" + studentNumber.substring(0, 2);
		}
		
		if(studentNumber.length()==8) {
			return "20" + studentNumber.substring(0, 2) ;
		}
		
		if(studentNumber.length()==10) {
			return studentNumber.substring(0, 4);
		}
		
		if(studentNumber.length()==12) {
			return studentNumber.substring(0, 4);
		}
		if(studentNumber.length()==13) {
			return studentNumber.substring(0, 4);
		}
		return "";
	}
	
	public static void main(String[] args) {
		String s = getCircles("15211001");
		System.out.println("s:"+s);
		String year = DateUtils.getDate("yyyy");
		System.out.print(	year.substring(2));
	}
}
