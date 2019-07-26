import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.utils.CourseUtil;

public class SchoolTimetable {
	
	
	private String cursName;//课程名称
	private String tchrName;//教师名称


	public static void main(String[] args) throws IOException {
		
		String s = "网页设计 （3-17周）                单晓光                     A417";
		
		String termYear = "2019-2020-01";
		String str = "分布式程序设计（1-10,17-18周）\r\n" + 
				"A602\r\n" + 
				"杨威";
		
		BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
		LineNumberReader lnr = new LineNumberReader(new StringReader(str));
		lnr.skip(Long.MAX_VALUE);
		int lineNo = lnr.getLineNumber() + 1;
		lnr.close();
		String firstLine = bufferedReader.readLine();
		CourseResolving courseResolving = courseResolving(firstLine);
		String twoLine = bufferedReader.readLine();
		String schoolBuild = null;
		Matcher m = Pattern.compile("[a-zA-Z]").matcher(twoLine);
		while (m.find()) {
			schoolBuild = m.group();
		}
		String classRoom = null;
		if(!StringUtils.isEmpty(schoolBuild)) {
			classRoom = twoLine.substring(twoLine.indexOf(schoolBuild)+1);
		}
		String tchrName = null;
		switch (lineNo) {
		case 2: 
			{
				tchrName = twoLine.substring(0, twoLine.indexOf(schoolBuild));
			}
			break;
		case 3:
			{
				tchrName = bufferedReader.readLine();
			}
			break;
		case 4:
			{
				tchrName = bufferedReader.readLine();
				String four = bufferedReader.readLine();
				CourseResolving resolving = courseResolving(four);
			}
			break;
		default:
			break;
		}
		List<String> cs = courseResolving.getCs();
		int line = 5;
		String jie = String.valueOf(line%5);
		
		if(line%5==0) {
			jie = String.valueOf(5);
		}
		
		String zhou = String.valueOf(BigDecimal.valueOf(Math.floor(line/5)).intValue());
		
		for(String c:cs) {
			String timeAdd = termYear.concat(CourseUtil.schoolRootMap.get(schoolBuild).concat(classRoom)).concat(c).concat(jie).concat(zhou);
			System.out.println(timeAdd);
		}
	
	}
	
	static class CourseResolving{
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
		
	}
	
	public static CourseResolving courseResolving(String firstLine){
		String reg = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(reg);  
		String cursName = firstLine.substring(0,firstLine.indexOf("（"));
		String start = firstLine.substring(firstLine.indexOf("（")+1,firstLine.indexOf("）"));
		Matcher matcher = pat.matcher(start);
		List<String> cs = new ArrayList<String>();
		start = matcher.replaceAll("");
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
	
	public static String  scheduleResolver() {
		return "";
	}
}
