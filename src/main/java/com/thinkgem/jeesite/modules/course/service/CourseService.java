/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.course.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.POIUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
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

	public List<Course> findByParentIdsLike(Course course) {
		return super.findByParentIdsLike(course);
	}

	public Page<Course> findPage(Page<Course> page, Course course) {
		return super.findPage(page, course);
	}

	
	public HSSFWorkbook exportCourse(File file, Course course) {
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
			
			
			String  title = StringEscapeUtils.unescapeHtml4(course.getCursEduNum().concat("  ").concat(course.getCursName()).replace("★", "") );
			if (course.getCursProperty().equals(Course.COURSE_PROPERTY_SELECT)) {
				HSSFSheet clazzSheet = wb.createSheet(POIUtils.format(course.getCursName()));
				
				Row r = clazzSheet.getRow(1);
				if(!StringUtils.isEmpty(r)) {
					r.setHeight((short) 10);// 目的是想把行高设置成25px
				}
				
				Row r1 = clazzSheet.getRow(4);
				if(!StringUtils.isEmpty(r1)) {
					r1.setHeight((short) 10);// 目的是想把行高设置成25px
				}
				Row r2 = clazzSheet.getRow(11);
				if(!StringUtils.isEmpty(r2)) {
					r2.setHeight((short) 10);// 目的是想把行高设置成25px
				}
				
				Footer footer = clazzSheet.getFooter();
				footer.setLeft(
						"任课教师 ：          命题教师：              评分教师：                                                               \n"
								+ "录分人：             教研室主任：            录分日期：  年  月  日                                 ");
				POIUtils.copySheet(wb, sheet, clazzSheet, true);

				CellRangeAddress xf = new CellRangeAddress(12, 13, 6, 0);
				CellRangeAddress jd = new CellRangeAddress(12, 13, 7, 0);
				CellRangeAddress fz = new CellRangeAddress(12, 13, 8, 0);
				
				POIUtils.pullCellRangeAddress(xf, clazzSheet, wb);				
				POIUtils.pullCellRangeAddress(jd, clazzSheet, wb);
				POIUtils.pullCellRangeAddress(fz, clazzSheet, wb);
				
				Row schoolreportRow = clazzSheet.getRow(0);
				Cell courseNameCell = schoolreportRow.getCell(0);

				courseNameCell.setCellValue("  " + title + "   成绩单");
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
				String n = ss[2].equals("01")? "一" : "二";
				Row yearTermRow = clazzSheet.getRow(3);
				Cell yearTermCell = yearTermRow.getCell(0);
				yearTermCell.setCellValue("    " + startYear + "—" + endYear + " 学年度第" + n + "学期        ");
				logger.info("根据班级查找相关学生信息");
				//
				SelectCourse selectCourse = new SelectCourse();
				selectCourse.setCourse(course);
				List<SelectCourse> list = selectCourseDao.findList(selectCourse);
				Collections.sort(list, new Comparator<SelectCourse>() {

					@Override
					public int compare(SelectCourse o1, SelectCourse o2) {
						BigInteger a = new BigInteger(o1.getStudent().getStudentNumber());
						BigInteger b = new BigInteger(o2.getStudent().getStudentNumber());
						return a.compareTo(b);
					}
					
				});
				int rowIndex = 14;
				CellStyle style = POIUtils.formatCell(wb,9);

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
					studentRow.setHeight((short) 376);// 目的是想把行高设置成25px

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

						if (StringUtils.isEmpty(evaValue)) {
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
						} else {
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
						if(!StringUtils.isEmpty(studentCourse.getStatus())&&!studentCourse.getStatus().equals("0")) {
							remarkCell.setCellValue(DictUtils.getDictLabel(studentCourse.getStatus(), "student_course_result", ""));
						}else {
							remarkCell.setCellValue(remark);
						}
						//sk++;
					}

					rowIndex++;
				}
				// 进行成绩段值的填充

				//setCell(clazzSheet, 6, 0, yk);
				//setCell(clazzSheet, 8, 0, sk);
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
				//重复标题行
				
				clazzSheet.setMargin(Sheet.TopMargin, 1);// 页边距（上）   

				clazzSheet.setMargin(Sheet.BottomMargin, 1);// 页边距（下）   

				clazzSheet.setMargin(Sheet.LeftMargin,0.75);// 页边距（左）   

				clazzSheet.setMargin(Sheet.RightMargin, 0);// 页边距（右）
				
				HSSFPrintSetup printSetup = clazzSheet.getPrintSetup();
				printSetup.setPaperSize(HSSFPrintSetup.B5_PAPERSIZE); 
				printSetup.setHeaderMargin((double)0.6);  
				printSetup.setFooterMargin((double)0.6); 
				clazzSheet.setRepeatingRows(new CellRangeAddress(12,13,0,255));
				clazzSheet.setRepeatingColumns(new CellRangeAddress(12,13,0,255));
			} else {
				CourseClass courseClass = new CourseClass();
				courseClass.setCourse(course);
				List<CourseClass> ccs = courseClassDao.findList(courseClass);
				for (CourseClass cc : ccs) {
					
					Office clazz = officeDao.get(cc.getClassId());
					Office major = officeDao.get(clazz.getParent());
					Office school = officeDao.get(major.getParent());
					if (!StringUtils.isEmpty(clazz)) {
						HSSFSheet clazzSheet = wb.createSheet(clazz.getName());
						
						
						
						Footer footer = clazzSheet.getFooter();
						footer.setLeft(
								"任课教师 ：          命题教师：              评分教师：                                                               \n"
										+ "录分人：             教研室主任：            录分日期：  年  月  日                                 ");
						POIUtils.copySheet(wb, sheet, clazzSheet, true);
						
						CellRangeAddress xf = new CellRangeAddress(12, 13, 6, 0);
						CellRangeAddress cj = new CellRangeAddress(12, 13, 7, 0);
						CellRangeAddress jd = new CellRangeAddress(12, 13, 8, 0);
						
						POIUtils.pullCellRangeAddress(xf, clazzSheet, wb);
						POIUtils.pullCellRangeAddress(cj, clazzSheet, wb);
						POIUtils.pullCellRangeAddress(jd, clazzSheet, wb);
						
						Row r = clazzSheet.getRow(1);
						
						if(!StringUtils.isEmpty(r)) {
							r.setHeight((short) 10);// 目的是想把行高设置成25px
						}
						
						Row r1 = clazzSheet.getRow(4);
						if(!StringUtils.isEmpty(r1)) {
							r1.setHeight((short) 10);// 目的是想把行高设置成25px
						}
						Row r2 = clazzSheet.getRow(11);
						if(!StringUtils.isEmpty(r2)) {
							r2.setHeight((short) 10);// 目的是想把行高设置成25px
						}
						
						Row schoolreportRow = clazzSheet.getRow(0);
						Cell courseNameCell = schoolreportRow.getCell(0);

						courseNameCell.setCellValue("  " + title + "   成绩单");
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
						String n = ss[2].equals("01") ? "一" : "二";
						Row yearTermRow = clazzSheet.getRow(3);
						Cell yearTermCell = yearTermRow.getCell(0);
						yearTermCell.setCellValue("    " + startYear + " — " + endYear + " 学年度第" + n + "学期        ");
						logger.info("根据班级查找相关学生信息");
						//
						Student entity = new Student();
						entity.setClazz(clazz);
						entity.setStatus("1");
						List<Student> list = studentDao.findList(entity);
						
						int rowIndex = 14;
						CellStyle style = POIUtils.formatCell(wb,9);

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
						Collections.sort(list, new Comparator<Student>() {

							@Override
							public int compare(Student o1, Student o2) {
								BigInteger a = new BigInteger(o1.getStudentNumber());
								BigInteger b = new BigInteger(o2.getStudentNumber());
								return a.compareTo(b);
							}
							
						});
						for (Student student : list) {
							Row studentRow = clazzSheet.createRow(rowIndex);
							
							
							studentRow.setHeight((short) 290);// 目的是想把行高设置成25px

							Cell studentNumberCell = studentRow.createCell(0);
							studentNumberCell.setCellValue(student.getStudentNumber());
							studentNumberCell.setCellStyle(style);
							Cell nameCell = studentRow.createCell(1);
							nameCell.setCellValue(student.getName());
							nameCell.setCellStyle(style);
							StudentCourse scEntity = new StudentCourse();
							scEntity.setStudent(student);
							scEntity.setCourse(course);
							 StudentCourse studentCourse =
							 studentCourseService.getStudentCourseByStudentCourse(scEntity);

							// 判断成绩是否为空,如果是空那么进行创建空行
							if (StringUtils.isEmpty(studentCourse)) {
								for (int j = 2; j < 9; j++) {
									getCell(studentRow, style, j);
								}
							} else {
								Cell classEvaValueCell = getCell(studentRow, style, 2);
								String classEvaValue = studentCourse.getClassEvaValue();
								if (StringUtils.isEmpty(classEvaValue)) {
									classEvaValue = "0";
								}
								classEvaValueCell.setCellValue(classEvaValue);
								Cell midEvaValueCell = getCell(studentRow, style, 3);
								String midEvaValue = studentCourse.getMidEvaValue();

								midEvaValueCell.setCellValue(midEvaValue);
								Cell finEvaValueCell = getCell(studentRow, style, 4);
								String finEvaValue = studentCourse.getFinEvaValue();
								if (StringUtils.isEmpty(finEvaValue)) {
									finEvaValue = "0";
								}
								finEvaValueCell.setCellValue(finEvaValue);
								Cell evaValueCell = getCell(studentRow, style, 5);
								String evaValue = studentCourse.getEvaValue();
								if (StringUtils.isEmpty(evaValue)) {
									evaValue = "0";
								}
								evaValueCell.setCellValue(evaValue);

								Integer eva = Double.valueOf(evaValue).intValue();

								if (0 <= eva && eva < 10) {
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
								if(!StringUtils.isEmpty(studentCourse.getStatus())&&!studentCourse.getStatus().equals("0")) {
									remarkCell.setCellValue(DictUtils.getDictLabel(studentCourse.getStatus(), "student_course_result", ""));
								}else {
									remarkCell.setCellValue(remark);
								}
								
								sk++;
							}

							rowIndex++;
						}
						// 进行成绩段值的填充

						//setCell(clazzSheet, 6, 0, yk);
						//setCell(clazzSheet, 8, 0, sk);
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
						
						
						clazzSheet.setMargin(Sheet.TopMargin, 1);// 页边距（上）   

						clazzSheet.setMargin(Sheet.BottomMargin, 1);// 页边距（下）   

						clazzSheet.setMargin(Sheet.LeftMargin,0.75);// 页边距（左）   

						clazzSheet.setMargin(Sheet.RightMargin, 0);// 页边距（右）

						HSSFPrintSetup printSetup = clazzSheet.getPrintSetup();
						printSetup.setPaperSize(HSSFPrintSetup.B5_PAPERSIZE); 
						printSetup.setHeaderMargin((double)0.6);  
						printSetup.setFooterMargin((double)0.6); 
					}

				}
			}
			
			return wb;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Async
	public void selectCourse(File file, SelectCourse selectCourse) throws Exception {

		if (!file.exists()) {
			file.createNewFile();
		}
		List<SelectCourse> selectCourses = selectCourseDao.findList(selectCourse);
		ExportExcel exportExcel = new ExportExcel("选课报考数据", SelectCourse.class);
		exportExcel.setDataList(selectCourses);

		FileOutputStream os = new FileOutputStream(file);
		exportExcel.write(os);
		os.flush();
		os.close();
	}
	
	public void setCell(Sheet sheet, int row, int cell, int value) {
		Row tmpRow = sheet.getRow(row);
		Cell tempCell = tmpRow.getCell(cell);
		tempCell.setCellValue(value);
		CellStyle cellStyle = POIUtils.formatCell((HSSFWorkbook)sheet.getWorkbook(),"黑体",11);
		tempCell.setCellStyle(cellStyle);
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