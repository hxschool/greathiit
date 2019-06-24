/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.POIUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportResult;
import com.thinkgem.jeesite.modules.course.dao.CourseClassDao;
import com.thinkgem.jeesite.modules.course.dao.CourseDao;
import com.thinkgem.jeesite.modules.course.dao.CourseScheduleDao;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseClass;
import com.thinkgem.jeesite.modules.select.dao.SelectCourseDao;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.student.dao.StudentDao;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.student.util.StudentCourseUtil;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 课程基本信息Service
 * 
 * @author 赵俊飞
 * @version 2017-12-13
 */
@Service
@Transactional(readOnly = true)
public class CourseService extends CrudService<CourseDao, Course> {
	DecimalFormat df = new DecimalFormat("#.00");
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private CourseScheduleDao courseScheduleDao;
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SelectCourseDao selectCourseDao;
	@Transactional(readOnly = false)
	public void submit(Course course) {
		 courseDao.submit(course);
	}
	public Course getCourseByCourseId(String courseId) {
		Course course = new Course();
		course.setId(courseId);
		return courseDao.getCourse(course);
	}

	public Course getCourse(Course course) {
		return courseDao.getCourse(course);
	}

	public Course get(String id) {
		return super.get(id);
	}

	public List<Course> findCoursesByPaike(Course course) {
		return courseDao.findCoursesByPaike(course);
	}

	public List<Course> findList(Course course) {
		return super.findList(course);
	}

	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}

	@Transactional(readOnly = false)
	public ImportResult<Course> importCourse(MultipartFile file) {
		logger.info("根据课程导入成绩");
		int successNum = 0;
		int failureNum = 0;
		StringBuilder failureMsg = new StringBuilder();
		Course course = null;
		ImportResult<Course> importResult = new ImportResult<Course>();
		try {
			Workbook wb = null;
			InputStream is = file.getInputStream();
			String fileName = file.getOriginalFilename();
			if (StringUtils.isEmpty(fileName)) {
				throw new RuntimeException("导入文档为空!");
			} else if (fileName.toLowerCase().endsWith("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (fileName.toLowerCase().endsWith("xlsx")) {
				wb = new XSSFWorkbook(is);
			} else {
				throw new RuntimeException("文档格式不正确!");
			}
			Sheet clazzSheet = wb.getSheetAt(0);
			Row courseIdRow = clazzSheet.getRow(1);
			Cell courseIdCell = courseIdRow.getCell(0);
			course = courseDao.get(courseIdCell.getStringCellValue());
			
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();
				for (int rowIndex = 14; rowIndex <= rowNum; rowIndex++) {
					Row row = sheet.getRow(rowIndex);
					String studentNumber = row.getCell(0).getStringCellValue();
					if (!StringUtils.isEmpty(studentNumber)) {
						StudentCourse sc = new StudentCourse();
						sc.setCourse(course);
						Student student = new Student();
						student.setStudentNumber(studentNumber);
						sc.setStudent(student);
						StudentCourse score = studentCourseService.getStudentCourseByStudentCourse(sc);
						if(!StringUtils.isEmpty(score)) {
							logger.info("当前课程,当前学号成绩已存在");
							failureNum++;
							failureMsg.append("<br/>学号: "+studentNumber+" 当前课程成绩已存在");
							continue;
						}
						String name = row.getCell(1).getStringCellValue();
						String classEvaValue = POIUtils.getCell(row.getCell(2));
						

						String midEvaValue = POIUtils.getCell(row.getCell(3));
						String finEvaValue = POIUtils.getCell(row.getCell(4));
						StudentCourse studentCourse = new StudentCourse();
						studentCourse.setStudentNumber(studentNumber);
						studentCourse.setStudentName(name);
						studentCourse.setCourse(course);

						studentCourse.setClassEvaValue(classEvaValue);
						studentCourse.setFinEvaValue(finEvaValue);
						if (!POIUtils.isNumeric(classEvaValue)) {
							classEvaValue = StudentCourseUtil.getPercentageSocre(classEvaValue);
						}
						if (!POIUtils.isNumeric(finEvaValue)) {
							finEvaValue = StudentCourseUtil.getPercentageSocre(finEvaValue);
						}
						if(StringUtils.isEmpty(classEvaValue)) {
							classEvaValue = "0";
						}
						if(StringUtils.isEmpty(finEvaValue)) {
							finEvaValue = "0";
						}
						String evaValue = "0";
						// zhaojunfei
						switch (course.getCursType()) {
						case Course.COURSE_TYPE_EXA:
							// 考试课：平时成绩*30%+期末成绩*70%=综合成绩

							evaValue = String.valueOf(Double
									.valueOf((Double.parseDouble(classEvaValue) * Double.parseDouble("0.30")
											+ Double.parseDouble(finEvaValue) * Double.parseDouble("0.70")))
									.intValue());
							break;
						case Course.COURSE_TYPE_TEST:
							// 考察课：平时成绩*40%+期末成绩*60%=综合成绩
							evaValue = String.valueOf(Double
									.valueOf((Double.parseDouble(classEvaValue) * Double.parseDouble("0.40")
											+ Double.parseDouble(finEvaValue) * Double.parseDouble("0.60")))
									.intValue());
							break;
						case Course.COURSE_TYPE_OTHER:
							break;
						default:
							break;
						}
						String point = df.format((Double.valueOf(evaValue) - 60) * Double.valueOf("0.1"));
						studentCourse.setPoint(point);
						studentCourse.setEvaValue(evaValue);
						studentCourseService.save(studentCourse);
						successNum ++;
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		importResult.setFailureNum(failureNum);
		importResult.setFailureMsg(failureMsg);
		importResult.setObject(course);
		importResult.setSuccessNum(successNum);
		return importResult;
	}

	public void exportCourse(File file, Course course, OutputStream os) {
		logger.info("根据课程生成全部sheet班级信息");
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			CellStyle cellCourseNameStyle = wb.createCellStyle();
			cellCourseNameStyle.setFont(POIUtils.getFont(wb));
			cellCourseNameStyle.setAlignment(CellStyle.ALIGN_CENTER);

			CellStyle cellCourseIdStyle = wb.createCellStyle();
			Font courseIdFont = wb.createFont();
			courseIdFont.setColor(HSSFColor.WHITE.index);
			cellCourseIdStyle.setFont(courseIdFont);
			cellCourseIdStyle.setAlignment(CellStyle.ALIGN_CENTER);
			if (course.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
				HSSFSheet clazzSheet = wb.createSheet(POIUtils.format(course.getCursName()));

				Footer footer = clazzSheet.getFooter();
				footer.setLeft(
						"任课教师 ：          命题教师：              评分教师：                                                               \n"
								+ "录分人：             教研室主任：            录分日期：  年  月  日                                 ");
				POIUtils.copySheet(wb, sheet, clazzSheet, true);
				Row schoolreportRow = clazzSheet.getRow(0);
				Cell courseNameCell = schoolreportRow.getCell(0);

				courseNameCell.setCellValue("  " + StringEscapeUtils.unescapeHtml4(course.getCursName()) + "   （科）成绩单");
				courseNameCell.setCellStyle(cellCourseNameStyle);

				Row courseIdRow = clazzSheet.getRow(1);
				Cell courseIdCell = courseIdRow.getCell(0);

				courseIdCell.setCellValue(course.getId());
				courseIdCell.setCellStyle(cellCourseIdStyle);

				Row departmentRow = clazzSheet.getRow(2);
				Cell departmentCell = departmentRow.getCell(0);

				departmentCell.setCellValue("院系 ：         专业：       班级：");
				String[] ss = course.getCursYearTerm().split("-");
				String startYear = ss[0];
				String endYear = ss[1];
				String n = ss[2]=="01"?"一":"二";
				Row yearTermRow = clazzSheet.getRow(3);
				Cell yearTermCell = yearTermRow.getCell(0);
				yearTermCell.setCellValue("    " + startYear + " —— " + endYear + " 学年度第" + n + "学期        ");
				logger.info("根据班级查找相关学生信息");
				//
				SelectCourse selectCourse = new SelectCourse();
				selectCourse.setCourse(course);
				List<SelectCourse> list = selectCourseDao.findList(selectCourse);

				int rowIndex = 14;
				CellStyle style = POIUtils.formatCell(wb);

				int yk = list.size();// 应考
				int sk = 0;// 实考
				// 分数段
				int p7d = 0;
				int p7e = 0;
				int p7f = 0;
				int p7g = 0;
				int p7h = 0;

				int p9d = 0;
				int p9e = 0;
				int p9f = 0;
				int p9g = 0;
				int p9h = 0;

				int p11d = 0;
				int p11e = 0;
				int p11f = 0;
				int p11g = 0;
				int p11h = 0;

				for (SelectCourse sc : list) {
					String studentNumber = sc.getStudent().getStudentNumber();
					Student student = studentDao.getStudentByStudentNumber(studentNumber);
					Row studentRow = clazzSheet.createRow(rowIndex);
					studentRow.setHeight((short) 370);// 目的是想把行高设置成25px

					Cell studentNumberCell = studentRow.createCell(0);
					studentNumberCell.setCellValue(student.getStudentNumber());
					studentNumberCell.setCellStyle(style);
					Cell nameCell = studentRow.createCell(1);
					nameCell.setCellValue(student.getName());
					nameCell.setCellStyle(style);
					StudentCourse scEntity = new StudentCourse();
					scEntity.setStudent(student);
					scEntity.setCourse(course);
					StudentCourse studentCourse = studentCourseService.getStudentCourseByStudentCourse(scEntity);
					// 判断成绩是否为空,如果是空那么进行创建空行
					if (StringUtils.isEmpty(studentCourse)) {
						for (int j = 2; j < 9; j++) {
							getCell(studentRow, style, j);
						}
					} else {
						Cell classEvaValueCell = getCell(studentRow, style, 2);
						String classEvaValue = studentCourse.getClassEvaValue();
						classEvaValueCell.setCellValue(classEvaValue);
						Cell midEvaValueCell = getCell(studentRow, style, 3);
						String midEvaValue = studentCourse.getMidEvaValue();
						midEvaValueCell.setCellValue(midEvaValue);
						Cell finEvaValueCell = getCell(studentRow, style, 4);
						String finEvaValue = studentCourse.getFinEvaValue();
						finEvaValueCell.setCellValue(finEvaValue);
						Cell evaValueCell = getCell(studentRow, style, 5);
						String evaValue = studentCourse.getEvaValue();
						
						evaValueCell.setCellValue(evaValue);
						
						if(StringUtils.isEmpty(evaValue)) {
							evaValue = "不及格";
						}
						
						if (evaValue.equals("优") || evaValue.equals("优秀")) {
							p11d++;
						} else if (evaValue.equals("良") || evaValue.equals("良好")) {
							p11e++;
						} else if (evaValue.equals("中") || evaValue.equals("中等")) {
							p11f++;
						} else if (evaValue.equals("及格")) {
							p11g++;
						} else  {
							p11h++;
						}

						Cell creditCell = getCell(studentRow, style, 6);

						String credit = studentCourse.getCredit();
						creditCell.setCellValue(credit);

						Cell pointCell = getCell(studentRow, style, 7);
						String point = studentCourse.getPoint();
						pointCell.setCellValue(point);
						Cell remarkCell = getCell(studentRow, style, 8);
						String remark = studentCourse.getRemarks();
						remarkCell.setCellValue(remark);
						sk++;
					}

					rowIndex++;
				}
				// 进行成绩段值的填充

				setCell(clazzSheet, 6, 0, yk);
				setCell(clazzSheet, 8, 0, sk);
				setCell(clazzSheet, 6, 3, p7d);
				setCell(clazzSheet, 6, 4, p7e);
				setCell(clazzSheet, 6, 5, p7f);
				setCell(clazzSheet, 6, 6, p7g);
				setCell(clazzSheet, 6, 7, p7h);
				setCell(clazzSheet, 8, 3, p9d);
				setCell(clazzSheet, 8, 4, p9e);
				setCell(clazzSheet, 8, 5, p9f);
				setCell(clazzSheet, 8, 6, p9g);
				setCell(clazzSheet, 8, 7, p9h);
				setCell(clazzSheet, 10, 3, p11d);
				setCell(clazzSheet, 10, 4, p11e);
				setCell(clazzSheet, 10, 5, p11f);
				setCell(clazzSheet, 10, 6, p11g);
				setCell(clazzSheet, 10, 7, p11h);
			} else {
				CourseClass courseClass = new CourseClass();
				courseClass.setCourse(course);
				List<CourseClass> ccs = courseClassDao.findList(courseClass);
				for (CourseClass cc : ccs) {
					Office cls = cc.getCls();
					Office clazz = officeDao.get(cls);
					Office major = officeDao.get(clazz.getParent());
					Office school = officeDao.get(major.getParent());
					if (!StringUtils.isEmpty(clazz)) {
						HSSFSheet clazzSheet = wb.createSheet(clazz.getName());

						Footer footer = clazzSheet.getFooter();
						footer.setLeft(
								"任课教师 ：          命题教师：              评分教师：                                                               \n"
										+ "录分人：             教研室主任：            录分日期：  年  月  日                                 ");
						POIUtils.copySheet(wb, sheet, clazzSheet, true);
						Row schoolreportRow = clazzSheet.getRow(0);
						Cell courseNameCell = schoolreportRow.getCell(0);

						courseNameCell.setCellValue("  " + StringEscapeUtils.unescapeHtml4(course.getCursName()) + "   （科）成绩单");
						courseNameCell.setCellStyle(cellCourseNameStyle);

						Row courseIdRow = clazzSheet.getRow(1);
						Cell courseIdCell = courseIdRow.getCell(0);

						courseIdCell.setCellValue(course.getId());
						courseIdCell.setCellStyle(cellCourseIdStyle);

						Row departmentRow = clazzSheet.getRow(2);
						Cell departmentCell = departmentRow.getCell(0);

						departmentCell.setCellValue("院系 ：" + school.getName() + "         专业：" + major.getName()
								+ "       班级：" + clazz.getName());
						String[] ss = course.getCursYearTerm().split("-");
						String startYear = ss[0];
						String endYear = ss[1];
						String n = ss[2]=="01"?"一":"二";
						Row yearTermRow = clazzSheet.getRow(3);
						Cell yearTermCell = yearTermRow.getCell(0);
						yearTermCell.setCellValue("    " + startYear + " —— " + endYear + " 学年度第" + n + "学期        ");
						logger.info("根据班级查找相关学生信息");
						//
						Student entity = new Student();
						entity.setClazz(cls);
						List<Student> list = studentDao.findList(entity);

						int rowIndex = 14;
						CellStyle style = POIUtils.formatCell(wb);

						int yk = list.size();// 应考
						int sk = 0;// 实考
						// 分数段
						int p7d = 0;
						int p7e = 0;
						int p7f = 0;
						int p7g = 0;
						int p7h = 0;

						int p9d = 0;
						int p9e = 0;
						int p9f = 0;
						int p9g = 0;
						int p9h = 0;

						int p11d = 0;
						int p11e = 0;
						int p11f = 0;
						int p11g = 0;
						int p11h = 0;

						for (Student student : list) {
							Row studentRow = clazzSheet.createRow(rowIndex);
							studentRow.setHeight((short) 370);// 目的是想把行高设置成25px

							Cell studentNumberCell = studentRow.createCell(0);
							studentNumberCell.setCellValue(student.getStudentNumber());
							studentNumberCell.setCellStyle(style);
							Cell nameCell = studentRow.createCell(1);
							nameCell.setCellValue(student.getName());
							nameCell.setCellStyle(style);
							StudentCourse scEntity = new StudentCourse();
							scEntity.setStudent(student);
							scEntity.setCourse(course);
							//StudentCourse studentCourse = studentCourseService.getStudentCourseByStudentCourse(scEntity);
							List<StudentCourse> scs = studentCourseService.findList(scEntity);
							for(StudentCourse studentCourse : scs){
								// 判断成绩是否为空,如果是空那么进行创建空行
								if (StringUtils.isEmpty(studentCourse)) {
									for (int j = 2; j < 9; j++) {
										getCell(studentRow, style, j);
									}
								} else {
									Cell classEvaValueCell = getCell(studentRow, style, 2);
									String classEvaValue = studentCourse.getClassEvaValue();
									if(StringUtils.isEmpty(classEvaValue)) {
										classEvaValue = "0";
									}
									classEvaValueCell.setCellValue(classEvaValue);
									Cell midEvaValueCell = getCell(studentRow, style, 3);
									String midEvaValue = studentCourse.getMidEvaValue();
									
									midEvaValueCell.setCellValue(midEvaValue);
									Cell finEvaValueCell = getCell(studentRow, style, 4);
									String finEvaValue = studentCourse.getFinEvaValue();
									if(StringUtils.isEmpty(finEvaValue)) {
										finEvaValue = "0";
									}
									finEvaValueCell.setCellValue(finEvaValue);
									Cell evaValueCell = getCell(studentRow, style, 5);
									String evaValue = studentCourse.getEvaValue();
									if(StringUtils.isEmpty(evaValue)) {
										evaValue = "0";
									}
									evaValueCell.setCellValue(evaValue);
									
									Integer eva = Double.valueOf(evaValue).intValue();
									
									if (0 <= eva && eva <10) {
										p7d++;
									} else if (10 <= eva && eva < 20) {
										p7e++;
									} else if (20 <= eva && eva < 30) {
										p7f++;
									} else if (30 <= eva && eva < 40) {
										p7g++;
									} else if (40 <= eva && eva < 50) {
										p7h++;
									} else if (50 <= eva && eva < 60) {
										p9d++;
									} else if (60 <= eva && eva < 70) {
										p9e++;
									} else if (70 <= eva && eva < 80) {
										p9f++;
									} else if (80 <= eva && eva < 90) {
										p9g++;
									} else if (90 <= eva && eva <= 100) {
										p9h++;
									}
	
									Cell creditCell = getCell(studentRow, style, 6);
	
									String credit = studentCourse.getCredit();
									creditCell.setCellValue(credit);
	
									Cell pointCell = getCell(studentRow, style, 7);
									String point = studentCourse.getPoint();
									pointCell.setCellValue(point);
									Cell remarkCell = getCell(studentRow, style, 8);
									String remark = studentCourse.getRemarks();
									
									remarkCell.setCellValue(DictUtils.getDictLabel(studentCourse.getStatus(), "student_course_result", ""));
									sk++;
								}
							}
							rowIndex++;
						}
						// 进行成绩段值的填充

						setCell(clazzSheet, 6, 0, yk);
						setCell(clazzSheet, 8, 0, sk);
						setCell(clazzSheet, 6, 3, p7d);
						setCell(clazzSheet, 6, 4, p7e);
						setCell(clazzSheet, 6, 5, p7f);
						setCell(clazzSheet, 6, 6, p7g);
						setCell(clazzSheet, 6, 7, p7h);
						setCell(clazzSheet, 8, 3, p9d);
						setCell(clazzSheet, 8, 4, p9e);
						setCell(clazzSheet, 8, 5, p9f);
						setCell(clazzSheet, 8, 6, p9g);
						setCell(clazzSheet, 8, 7, p9h);
						setCell(clazzSheet, 10, 3, p11d);
						setCell(clazzSheet, 10, 4, p11e);
						setCell(clazzSheet, 10, 5, p11f);
						setCell(clazzSheet, 10, 6, p11g);
						setCell(clazzSheet, 10, 7, p11h);

					}

				}
			}
			wb.removeSheetAt(0);
			wb.write(os);
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCell(Sheet sheet, int row, int cell, int value) {
		Row tmpRow = sheet.getRow(row);
		Cell tempCell = tmpRow.getCell(cell);
		tempCell.setCellValue(value);
	}

	public Cell getCell(Row row, CellStyle style, int index) {
		Cell tempCell = row.createCell(index);
		tempCell.setCellStyle(style);
		return tempCell;
	}

	@Transactional(readOnly = false)
	public void delete(Course course) {
		CourseClass courseClass = new CourseClass();
		courseClass.setCourse(course);
		;
		courseClassDao.delete(courseClass);
		courseScheduleDao.deleteByCourse(course.getId());
		super.delete(course);
	}

}