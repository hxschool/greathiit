package com.thinkgem.jeesite.common.utils;

public class StudentUtil {

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
	}
}
