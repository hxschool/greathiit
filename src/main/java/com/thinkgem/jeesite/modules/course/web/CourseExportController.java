/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.calendar.entity.CourseCalendar;
import com.thinkgem.jeesite.modules.calendar.service.CourseCalendarService;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.entity.CourseYearTerm;
import com.thinkgem.jeesite.modules.course.service.CourseCompositionRulesService;
import com.thinkgem.jeesite.modules.course.service.CourseMaterialService;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseSpecificContentService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingtargetService;
import com.thinkgem.jeesite.modules.course.service.CourseYearTermService;
import com.thinkgem.jeesite.modules.course.web.param.Lesson;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.service.TeacherService;

/**
 * 课程基本信息Controller
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Controller
@RequestMapping(value = "${adminPath}/course/export")
public class CourseExportController extends BaseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseYearTermService courseYearTermService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private CourseMaterialService courseMaterialService;
	@Autowired
	private CourseCompositionRulesService courseCompositionRulesService;
	@Autowired
	private CourseTeachingtargetService courseTeachingtargetService;
	@Autowired
	private CourseTeachingModeService courseTeachingModeService;
	@Autowired
	private CourseSpecificContentService courseSpecificContentService;
	@Autowired
	private CourseCalendarService courseCalendarService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TeacherService teacherService;
	
	private SXSSFWorkbook  wb;
	private Sheet sheet;
	private Map<String, CellStyle> styles;
	
	private static Map<String,String> schoolRootMap = new HashMap();
	{
		schoolRootMap.put("01", "A栋");
		schoolRootMap.put("02", "B栋");
		schoolRootMap.put("03", "C栋");
	}
	@ModelAttribute
	public Course get(@RequestParam(required=false) String id) {
		Course entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseService.get(id);
		}
		if (entity == null){
			entity = new Course();
		}
		return entity;
	}
	
	
	@RequestMapping(value = {"export", ""})
	public String list(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = courseService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		return "modules/course/courseList";
	}
	
	@RequestMapping(value = "allCourse")
	public void allCourse(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException,InvalidFormatException {
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm = courseYearTerm.getYearTerm();
		String[] $date = {yearTerm.substring(0, 4),yearTerm.substring(4)};
		String $file_name = $date[0] + "年度第" + $date[1] + "学期课程表.xls";
		response.setContentType("application/octet-stream;charset=utf-8"); 
		response.setHeader("Content-Disposition","attachment;filename=" + new String($file_name.getBytes(),"iso-8859-1")); 
		OutputStream os = response.getOutputStream();
		String fs = request.getSession().getServletContext().getRealPath("/resources/student/教师课表.xls");  
	
	 
	    HSSFWorkbook  wb = new HSSFWorkbook(new FileInputStream(fs)); 
		
		List<Teacher> teachers = teacherService.findListByYearTerm(yearTerm);
		Sheet sheet = wb.getSheetAt(0);
		styles = createStyles(wb);
		//从零开始默认第二行开始设置列Cell
		Row row = sheet.getRow(1);
		Map<String,Integer> indexMap = new HashMap<String,Integer>();
		for(int i=0;i<teachers.size();i++) {
			Teacher teacher  = teachers.get(i);
			int celIndex = 3+i;
			addCell(row, celIndex, teacher.getTchrName(), 3);
			indexMap.put(teacher.getTeacher().getNo(),celIndex);
		}
		
		for(int i=2;i<37;i++) {
			Row rRow = sheet.getRow(i);
			rRow.setHeight((short) 900);
		}
		
		List<CourseSchedule> courseSchedules = courseScheduleService.getCourseScheduleByYearTerm(yearTerm);
		for(CourseSchedule courseSchedule:courseSchedules) {
			
			String corseId = courseSchedule.getCourseId();
			
			Course course = courseService.get(corseId);
			if(!org.springframework.util.StringUtils.isEmpty(course)&&indexMap.containsKey(course.getTeacher().getNo())) {
				
				int cIndex = indexMap.get(course.getTeacher().getNo());
				Map<String,String> $col_a = GetTimeCol(courseSchedule.getTimeAdd());
				String zhou = $col_a.get("zhou");
				String jie  = $col_a.get("jie");
				//设定索引行
				int rowIndex = (Integer.valueOf(zhou) - 1) * 5 + 2 + Integer.valueOf(jie) - 1;
				//获取到行信息
				Row rRow = sheet.getRow(rowIndex);
				rRow.setHeight((short) 900);
				sheet.setColumnWidth((short) cIndex, (short) (6000));
				
				String root = courseSchedule.getTimeAdd().substring(7, 10);
				String buildRootKey = courseSchedule.getTimeAdd().substring(5, 7);
				addCell(rRow, cIndex, course.getCursName() + "(" +course.getCursClassHour()+ " 学时) \r\n" + schoolRootMap.get(buildRootKey) + " " + root  + "\r\n" +  courseSchedule.getCourseClass(), 4);
			}	
		}
		
		wb.write(os);
		os.flush();
		os.close();
		
	}


	@RequestMapping(value = "teacher")
	public void teacher(String teacherNumber, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		User user = new User();
		user.setNo(teacherNumber);
		User teacher = UserUtils.getUser(user);
		if(org.springframework.util.StringUtils.isEmpty(teacher)) {
			teacher = UserUtils.getUser();
		}
		PrintWriter pw = response.getWriter();
		
		if(org.springframework.util.StringUtils.isEmpty(teacher)) {
			pw.write("<script>alert(\"抱歉，数据库中没有查到该老师姓名\")</script>");
			pw.flush();
			pw.close();
		}
		CourseYearTerm courseYearTerm = courseYearTermService.systemConfig();
		String yearTerm = courseYearTerm.getYearTerm();
		String[] $date = {yearTerm.substring(0, 4),yearTerm.substring(4)};

		String $file_name = teacher.getName() + "-" + $date[0] + "年度第" + $date[1] + "学期课程表.xls";
		response.setContentType("application/octet-stream;charset=utf-8"); 
		response.setHeader("Content-Disposition","attachment;filename=" + new String($file_name.getBytes(),"iso-8859-1")); 
		OutputStream os = response.getOutputStream();
		List<CourseSchedule> courseSchedules = courseScheduleService.getCourseScheduleByYearTermAndTeacherNumber(yearTerm, teacherNumber);
		SXSSFWorkbook  wb = new SXSSFWorkbook();
		Sheet sheet = wb.createSheet("Export");
		Row row = sheet.createRow(0);
		styles = createStyles(wb);
		//姓名
		addCell(row, 0,"姓名", 4);
		addCell(row, 1,teacher.getName(), 2);
		//标题相关
		CellRangeAddress cra = new CellRangeAddress(0, 0, 2, 4);
		sheet.addMergedRegion(cra);  
		
		addCell(row, 2,$date[0] + "年度第"+  $date[1] + "学期", 2);
		
		Row row1 = sheet.createRow(1);
		addCell(row1, 0,"时间", 2);
		addCell(row1, 1,"地点", 2);
		addCell(row1, 2,"课程", 2);
		addCell(row1, 3,"班级", 2);
		addCell(row1, 4,"备注", 2);
		int $i=2;
		
		List<Lesson> lessons = new ArrayList<Lesson>();
		for(CourseSchedule courseSchedule:courseSchedules) {
			Row r = sheet.createRow($i);
			Map<String,String> $col_a = GetTimeCol(courseSchedule.getTimeAdd());
			int date = (Integer.valueOf($col_a.get("week")) - 1) * 7 + Integer.valueOf($col_a.get("zhou"));
			addCell(r, 0,'第' + $col_a.get("week") + "周  " + addDate(date) + " ("+ zhou($col_a.get("zhou")) + ") " +  jie($col_a.get("jie")), 2);
			String buildRootKey = courseSchedule.getTimeAdd().substring(5, 7);
			String root = courseSchedule.getTimeAdd().substring(7, 10);
			addCell(r, 1,schoolRootMap.get(buildRootKey) + " " + root , 2);
			
			String courseClass = courseSchedule.getCourseClass();
			
			Course course = courseService.get(courseSchedule.getCourseId());
			if(org.springframework.util.StringUtils.isEmpty(course)) {
				System.out.println(courseSchedule.getCourseId());
			}
			addCell(r, 2,course.getCursName(), 2);
			addCell(r, 3,courseClass, 2);
			addCell(r, 4,courseSchedule.getTips(), 2);
			//添加相关数据信息
			lessons.add(new Lesson($col_a.get("week"), $col_a.get("zhou"),$col_a.get("jie"), schoolRootMap.get(buildRootKey) + " " + root, course.getCursName()))  ;
			
			$i++;
		}
		$i = $i+2;
		Row kc = sheet.createRow($i);
		addCell(kc, 0,teacher.getName() , 2);
		addCell(kc, 1,"星期一" , 2);
		addCell(kc, 2,"星期二" , 2);
		addCell(kc, 3,"星期三" , 2);
		addCell(kc, 4,"星期四" , 2);
		addCell(kc, 5,"星期五" , 2);
		addCell(kc, 6,"星期六" , 2);
		addCell(kc, 7,"星期日" , 2);
		//构建左侧列
		++$i;
		int  $lesson_sheet_base_row = $i;
		
		for(int i=1;i<=5;i++) {
			int k = i*2;
			Row kj = sheet.createRow($i);
			addCell(kj, 0, (k - 1) + "-" + k + "节", 2);
			$i++;
		}
		
		for(Lesson lesson :lessons ) {
			int rownum = Integer.valueOf(lesson.getJie()) + $lesson_sheet_base_row - 1;
			Row r = sheet.getRow(rownum);
			addCell(r, Integer.valueOf(lesson.getZhou()),lesson.getCourse() , 2);
		}
		wb.write(os);
		os.flush();
		os.close();
	}
	
	
	
	
	public String jie(String $j)
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
	public String zhou(String $z)
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
	public Map<String,String> GetTimeCol(String $time_add){
	    Map<String,String> $time = new HashMap<String,String>();
	    $time.put("week", $time_add.substring(10, 12));
	    $time.put("jie", $time_add.substring(12, 13));
	    $time.put("zhou", $time_add.substring(13, 14));
	    return $time;
	}
	
	public static void main(String[] args) {
		 String today = "2013-12-14";  
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
		 calendar.add(Calendar.DATE, 1);
		 System.out.println(format.format(calendar.getTime()));
		 
		 System.out.println(Integer.valueOf("01"));
	}
	
	
	public String addDate(int day) {
		 CourseCalendar courseCalendar = courseCalendarService.systemConfig();
		 String today = courseCalendar.getCalendarYear() + "-" + courseCalendar.getCalendarMonth() + "-" +courseCalendar.getCalendarDay();
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
	
	
	
	
	public Cell addCell(Row row, int column, Object val, int align){
		Cell cell = row.createCell(column);
		CellStyle style = styles.get("data"+(align>=1&&align<=4?align:""));
		try {
			if (val == null){
				cell.setCellValue("");
			} else if (val instanceof String) {
				cell.setCellValue((String) val);
			} else if (val instanceof Integer) {
				cell.setCellValue((Integer) val);
			} else if (val instanceof Long) {
				cell.setCellValue((Long) val);
			} else if (val instanceof Double) {
				cell.setCellValue((Double) val);
			} else if (val instanceof Float) {
				cell.setCellValue((Float) val);
			} else if (val instanceof Date) {
				DataFormat format = wb.createDataFormat();
	            style.setDataFormat(format.getFormat("yyyy-MM-dd"));
				cell.setCellValue((Date) val);
			} else {
				cell.setCellValue((String) val);
			}
		} catch (Exception ex) {
			logger.info("Set cell value ["+row.getRowNum()+","+column+"] error: " + ex.toString());
			cell.setCellValue(val.toString());
		}
		style.setFillBackgroundColor(HSSFColor.AQUA.index);
		cell.setCellStyle(style);
		return cell;
	}



	/**
	 * 创建表格样式
	 * @param wb 工作薄对象
	 * @return 样式列表
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 12);
		style.setFont(dataFont);
		styles.put("data", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data2", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);

	  //设置背景颜色
	    style.setFillForegroundColor(HSSFColor.ROSE.index);
	    //solid 填充  foreground  前景色
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    dataFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    dataFont.setFontHeightInPoints((short) 10);
	   
		style.setFont(dataFont);
		styles.put("data3", style);
		
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 10);
		  
		//style.setFillPattern(CellStyle.SOLID_FOREGROUND);  
		style.setBorderBottom(CellStyle.BORDER_DOTTED); // 下边框  
		style.setBorderLeft(CellStyle.BORDER_DOTTED);// 左边框  
		style.setBorderTop(CellStyle.BORDER_DOTTED);// 上边框  
		style.setBorderRight(CellStyle.BORDER_DOTTED);// 右边框  
		
		style.setWrapText(true);
		style.setFont(titleFont);
		styles.put("data4", style);

		return styles;
	}
	
	@RequestMapping(value = "student")
	public String student(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/courseForm";
	}
	
	
	@RequestMapping(value = "school")
	public String school(Course course, Model model) {
		model.addAttribute("course", course);
		return "modules/course/courseForm";
	}
	

}