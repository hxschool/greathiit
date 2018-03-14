package com.thinkgem.jeesite.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;

public class CourseUtil {
	
	public static Map<String,String> schoolRootMap = new HashMap<String,String>();
	
	static{
		schoolRootMap.put("01", "A栋");
		schoolRootMap.put("02", "B栋");
		schoolRootMap.put("03", "C栋");
	}
	
	public static String jie(String $j)
	{
	        if($j.equals("1"))
	                return "1-2节";
	        if($j.equals("2"))
	                return "3-4节";
	        if($j.equals("3"))
	                return "5-6节";
	        if($j.equals("4"))
	                return "7-8节";
	        if($j.equals("5"))
	                return "9-10节";
			return $j;
	}
	//返回周
	public static String zhou(String $z)
	{
	        if($z.equals("1"))
	                return "周一";
	        if($z.equals("2"))
	                return "周二";
	        if($z.equals("3"))
	                return "周三";
	        if($z.equals("4"))
	                return "周四";
	        if($z.equals("5"))
	                return "周五";
	        if($z.equals("6"))
	                return "周六";
	        if($z.equals("7"))
	                return "周日";
			return $z;
	}
	
	
	
	public static Map<String,String> GetTimeCol(String $time_add){
	    Map<String,String> $time = new HashMap<String,String>();
	    $time.put("week", $time_add.substring(10, 12));
	    $time.put("jie", $time_add.substring(12, 13));
	    $time.put("zhou", $time_add.substring(13, 14));
	    return $time;
	}
	
	public static void main(String[] args) {
		String time_add = "20181023020146";
		int w = 20;
		
		int s = Integer.valueOf( CourseUtil.GetTimeCol(time_add).get("week"));
		if(StringUtils.isEmpty(w)||w==0) {
			w = s;
		}
		
		for(;s<=w;s++) {
			String week="";
			if(s<=9) {
				week = "0".concat(String.valueOf(s));
			}else {
				week =  String.valueOf(s);
			}
			String zhou = time_add.substring(12);
			
			time_add = time_add.substring(0,10).concat(week).concat(zhou);
			System.out.println(time_add);
			System.out.println(week);
		}
	}
	
	public static String addDate(String today,int day) {
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		 Date date = null;  
		 try {  
		     date = format.parse(today);  
		 } catch (ParseException e) {  
		     // TODO Auto-generated catch block  
		     e.printStackTrace();  
		 }  
		   
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE, day - 1);
		 return format.format(calendar.getTime());
	}
}
