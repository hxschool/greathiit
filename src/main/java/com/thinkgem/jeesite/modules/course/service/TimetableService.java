package com.thinkgem.jeesite.modules.course.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.CourseUtil;
import com.thinkgem.jeesite.common.utils.RegexUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportResult;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;
import com.thinkgem.jeesite.modules.course.entity.CourseEducational;
import com.thinkgem.jeesite.modules.course.entity.CourseSchedule;
import com.thinkgem.jeesite.modules.course.timetable.CourseResolving;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.entity.TeacherClass;
import com.thinkgem.jeesite.modules.teacher.service.TeacherClassService;

@Service
public class TimetableService {

	private static Logger logger = LoggerFactory.getLogger(TimetableService.class);
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseEducationalService courseEducationalService;
	@Autowired
	private TeacherClassService teacherClassService;
	@Autowired
	private CourseClassService courseClassService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	
	public ImportResult importFile(MultipartFile file) {
		SysConfig sysConfig = sysConfigService.getModule(Global.SYSCONFIG_COURSE);
		String firstLine = null;
		ImportResult importResult = new ImportResult();
		int successNum = 0;
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		try {
			String termYear = sysConfig.getTermYear();
			
			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();
			Workbook wb = null;
			if (StringUtils.isBlank(fileName)){
				throw new RuntimeException("导入文档为空!");
			}else if(fileName.toLowerCase().endsWith("xls")){    
				wb = new HSSFWorkbook(is);    
	        }else if(fileName.toLowerCase().endsWith("xlsx")){  
	        	wb = new XSSFWorkbook(is);
	        }else{  
	        	throw new RuntimeException("文档格式不正确!");
	        }  
			if (wb.getNumberOfSheets()<0){
				throw new RuntimeException("文档中没有工作表!");
			}
			int sheetNumber = wb.getNumberOfSheets();
			for (int index = 0; index < sheetNumber; index++) {
				Sheet sheet = wb.getSheetAt(index);
				Row firstRow = sheet.getRow(1);
				int lastCellNum = firstRow.getLastCellNum();
				// 获取班级头信息
				int lastRowNum = sheet.getLastRowNum();
				for (int rowNum = 2; rowNum < lastRowNum; rowNum++) {
					for (int cellNum = 2; cellNum < lastCellNum; cellNum++) {
						Cell firstCell = firstRow.getCell(cellNum);
						String className = firstCell.getStringCellValue();
						String edu = RegexUtils.isContainChinese(className)==true?"B":"G";
						Row row = sheet.getRow(rowNum);
						Cell cell = row.getCell(cellNum);
						String str = cell.getStringCellValue();
						if (org.springframework.util.StringUtils.isEmpty(str)) {
							continue;
						}
						if (str.equals("公  选") || str.equals("公选") || str.equals("公共选课")) {
							continue;
						}
						BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
						LineNumberReader lnr = new LineNumberReader(new StringReader(str));
						lnr.skip(Long.MAX_VALUE);
						int lineNo = lnr.getLineNumber() + 1;
						lnr.close();
						firstLine = bufferedReader.readLine();
						if (StringUtils.isEmpty(firstLine)) {
							logger.info(firstLine);
							continue;
						}
						CourseResolving courseResolving = CourseResolving.courseResolving(firstLine);
						String cursName = courseResolving.getCursName();
						//判断当前教师导入的班级是本科,还是专科
						CourseEducational courseEducational = new CourseEducational();
						courseEducational.setCursName(cursName);
						String cursNum = null;
						List<CourseEducational> courseEducationals = courseEducationalService.findList(courseEducational);
						if(CollectionUtils.isEmpty(courseEducationals)) {
							logger.info("当前课程为在教务处留任何相关信息,未查找到相关课程信息");
							cursNum = edu.concat("99999999");
							failureMsg.append("当前课程"+cursName+"为在教务处留任何相关信息,未查找到相关课程信息.执行导入操作.请注意修改课程编号并且联系教务处添加课程基础信息");
						}
						
						for(CourseEducational ce : courseEducationals) {
							if(ce.getCursNum().substring(0,1).equals(edu)) {
								cursNum = ce.getCursNum();
								break;
							}
						}
						
						String twoLine = bufferedReader.readLine();
						String schoolBuild = null;
						String classRoom = null;
						if (!org.springframework.util.StringUtils.isEmpty(twoLine)) {
							if (!org.springframework.util.StringUtils.isEmpty(twoLine)&&RegexUtils.isZh(twoLine)) {
								logger.info("中文体育馆待处理");
								continue;
							}
							Matcher m = Pattern.compile("[a-zA-Z]").matcher(twoLine);
							while (m.find()) {
								schoolBuild = m.group();
							}
							if (!StringUtils.isEmpty(schoolBuild)) {
								classRoom = twoLine.substring(twoLine.indexOf(schoolBuild) + 1);
							}
							if (org.springframework.util.StringUtils.isEmpty(classRoom)) {
								logger.info(twoLine);
								continue;
							}
						}
						
						String tchrName = null;
						switch (lineNo) {
							case 1:{//单行数据处理
								//网页设计 （3-17周）                单晓光                     A417
								String firstTxt = firstLine;
								firstTxt = firstTxt.substring(firstTxt.lastIndexOf("）")+1);
								tchrName = RegexUtils.getChineseExt(firstTxt);
								twoLine = firstTxt.replace(tchrName, "");
								if (!org.springframework.util.StringUtils.isEmpty(twoLine)&&RegexUtils.isZh(twoLine)) {
									logger.info("中文教室待处理");
									continue;
								}
								Matcher m = Pattern.compile("[a-zA-Z]").matcher(twoLine);
								while (m.find()) {
									schoolBuild = m.group();
								}
								if (!StringUtils.isEmpty(schoolBuild)) {
									classRoom = twoLine.substring(twoLine.indexOf(schoolBuild) + 1);
								}
								if (org.springframework.util.StringUtils.isEmpty(classRoom)) {
									logger.info(twoLine);
									continue;
								}
							}
								break;
							case 2: {
								tchrName = twoLine.substring(0, twoLine.indexOf(schoolBuild));
							}
								break;
							case 3: {
								tchrName = bufferedReader.readLine();
							}
								break;
							case 4: {
								tchrName = bufferedReader.readLine();
								String four = bufferedReader.readLine();
								if (org.springframework.util.StringUtils.isEmpty(four)) {
									continue;
								}
								CourseResolving resolving = CourseResolving.courseResolving(four);
							}
								break;
							default:
								break;
						}
						
						User user = systemService.isExisUser(tchrName);
						
						Teacher teacher = new Teacher();
						teacher.setTeacherNumber(user.getNo());
						
						
						Office cl = officeService.getOfficeByName(className);
						if(org.springframework.util.StringUtils.isEmpty(cl)) {
							failureMsg.append("<br/>数据"+firstLine+" 导入失败,班级信息不存在："+ className);
							failureNum++;
							continue;
						}
						TeacherClass teacherClass = new TeacherClass();
						teacherClass.setTeacherNumber(user.getNo());
						teacherClass.setClazz(cl);
						TeacherClass entityTeacherClass = teacherClassService.get(teacherClass);
						if (org.springframework.util.StringUtils.isEmpty(entityTeacherClass)) {
							teacherClassService.save(teacherClass);
						}
						
						List<String> cs = courseResolving.getCs();
						String classHour = String.valueOf(cs.size()*2);
						String courseId = systemService.getSequence("serialNo10");
						Course course = new Course();
						course.setIsNewRecord(true);
						course.setId(courseId);
						course.setCursName(cursName);
						course.setCursNum(cursNum);
						course.setCursEduNum(cursNum);
						course.setCursEngName(cursName);
						course.setCursYearTerm(termYear);
						course.setTeacher(teacher);
						course.setCursClassHour(classHour);
						courseService.save(course);
						String classId = cl.getId();
						if(!org.springframework.util.StringUtils.isEmpty(classId)) {
							CourseClass courseClass = new CourseClass();
							courseClass.setCourse(course);
							Office cls = new Office();
							cls.setId(classId);
							courseClass.setCls(cls);
							courseClassService.save(courseClass);
						}
						
						int line = 5;
						String jie = String.valueOf(line % 5);

						if (line % 5 == 0) {
							jie = String.valueOf(5);
						}

						String zhou = String.valueOf(BigDecimal.valueOf(Math.floor(line / 5)).intValue());

						for (String w : cs) {
							String time_add = termYear.concat(CourseUtil.schoolRootMap.get(schoolBuild).concat(classRoom)).concat(w).concat(jie).concat(zhou);
							CourseSchedule courseSchedule = courseScheduleService.getByAddTime(time_add);
							if(!org.springframework.util.StringUtils.isEmpty(courseSchedule)&&courseSchedule.getScLock().equals("1")) {
								courseSchedule.setScLock("2");
								courseSchedule.setCourseClass(classId);
								courseSchedule.setCourseId(courseId);
								courseSchedule.setTips("课程表导入计划");
								courseScheduleService.save(courseSchedule);
							}
						}

					}
				}
			}
		} catch ( Exception e) {
			failureMsg.append("<br/>课程名 "+firstLine+" 导入失败："+ e.getMessage());
			failureNum++;
		}
		importResult.setSuccessNum(successNum);
		importResult.setFailureNum(failureNum);
		importResult.setFailureMsg(failureMsg);
		return importResult;
	}
}
