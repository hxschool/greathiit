package com.thinkgem.jeesite.modules.student.util;
/**
 * 成绩计算器
 * @author Administrator
 *
 */
public class StudentCourseUtil {

	public static String getPercentageSocre(String str) {
		String value = "";
		switch (str) {
		case "优":
			value = "95";
			break;
		case "良":
			value = "85";
			break;
		case "中":
			value = "75";
			break;
		case "及格":
			value = "65";
			break;
		case "不及格":
			value = "0";
			break;
		default:
			value = "0";
			break;
		}
		return value;
	}

}
