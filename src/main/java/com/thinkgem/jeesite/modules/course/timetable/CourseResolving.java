package com.thinkgem.jeesite.modules.course.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.utils.RegexUtils;



public class CourseResolving {
	private String cursName;
	private List<String> cs;
	public String getCursName() {
		return cursName;
	}
	public void setCursName(String cursName) {
		this.cursName = cursName;
	}
	public List<String> getCs() {
		return cs;
	}
	public void setCs(List<String> cs) {
		this.cs = cs;
	}
	
	public static CourseResolving courseResolving(String firstLine) throws Exception{
		firstLine = firstLine.replace("(", "（");
		firstLine = firstLine.replace(")", "）");
		String cursName = firstLine.substring(0,firstLine.indexOf("（"));
		String start = firstLine.substring(firstLine.indexOf("（")+1,firstLine.indexOf("）"));
		List<String> cs = new ArrayList<String>();
		start = RegexUtils.removeChinese(start).trim().replace("，", ",").replace("、", ",");
		String[] ccs = start.split(",");
		for(String cc : ccs) {
			String[] cis = cc.split("-");
			if(cis.length==2) {
				for (int i = Integer.valueOf(cis[0]); i <= Integer.valueOf(cis[1]); i++) {
					if (i < 10) {
						cs.add("0"+ i);
					}else {
						cs.add(""+ i);
					}
				}
			}
		}
		CourseResolving courseResolving = new CourseResolving();
		courseResolving.setCursName(cursName);
		courseResolving.setCs(cs);
		return courseResolving;
	}
}
