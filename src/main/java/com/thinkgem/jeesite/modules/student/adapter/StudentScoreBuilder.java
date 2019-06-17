package com.thinkgem.jeesite.modules.student.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.Region;

import com.thinkgem.jeesite.common.utils.POIUtils;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class StudentScoreBuilder {
	
	public void oper(File file, Map<String, String> courseNameMap, Map<String, String> courseIdMap,
			Map<String, String> departmentMap, Map<String, String> dateMap, OutputStream os,
			AbsStudentScoreAdapter studentCourseAdapter) throws FileNotFoundException, IOException {

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		Row schoolreportRow = sheet.getRow(0);
		Cell courseNameCell = schoolreportRow.getCell(0);
		String courseNameValue = courseNameCell.getStringCellValue();

		for (Map.Entry<String, String> entry : courseNameMap.entrySet()) {
			courseNameValue = courseNameValue.replace(entry.getKey(), entry.getValue());
		}
		courseNameCell.setCellValue(courseNameValue);

		Row courseIdRow = sheet.getRow(1);
		Cell courseIdCell = courseIdRow.getCell(0);
		String courseIdValue = courseIdCell.getStringCellValue();

		for (Map.Entry<String, String> entry : courseIdMap.entrySet()) {
			courseIdValue = courseIdValue.replace(entry.getKey(), entry.getValue());
		}
		courseIdCell.setCellValue(courseIdValue);

		Row departmentRow = sheet.getRow(2);
		Cell departmentCell = departmentRow.getCell(0);
		String departmentValue = departmentCell.getStringCellValue();
		for (Map.Entry<String, String> entry : departmentMap.entrySet()) {
			departmentValue = departmentValue.replace(entry.getKey(), entry.getValue());
		}

		departmentCell.setCellValue(departmentValue);

		Row dateRow = sheet.getRow(3);
		Cell dateCell = dateRow.getCell(0);
		String dateValue = dateCell.getStringCellValue();
		for (Map.Entry<String, String> entry : dateMap.entrySet()) {
			dateValue = dateValue.replace(entry.getKey(), entry.getValue());
		}
		dateCell.setCellValue(dateValue);
		studentCourseAdapter.execute(sheet);

		wb.write(os);
		os.flush();
		os.close();
	}
	
	
	public static void main(String[] args) {
		File file = new File("C:\\Users\\Administrator\\git\\jeesite\\src\\main\\webapp\\resources\\student\\成绩单模版.xls");
		POIFSFileSystem fs;
		try {
			fs = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			for(int i=0;i<10;i++) {
				HSSFSheet clazzSheet = wb.createSheet("aa"+i);
				POIUtils.copySheet(wb, sheet , clazzSheet, true);
				Row schoolreportRow = clazzSheet.getRow(0);
				Cell courseNameCell = schoolreportRow.getCell(0);
				courseNameCell.setCellValue("ii");
			}
			wb.removeSheetAt(0);
			FileOutputStream out = new FileOutputStream("D:/aa.xls");
			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void copySheet(HSSFWorkbook wb,HSSFSheet fromSheet, HSSFSheet toSheet,
			boolean copyValueFlag) {
		//合并区域处理
		mergerRegion(fromSheet, toSheet);
		for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
			HSSFRow tmpRow = (HSSFRow) rowIt.next();
			HSSFRow newRow = toSheet.createRow(tmpRow.getRowNum());
			copyRow(wb,tmpRow,newRow,copyValueFlag);
		}
	}
	/**
	 * 行复制功能
	 * @param fromRow
	 * @param toRow
	 */
	public static void copyRow(HSSFWorkbook wb,HSSFRow fromRow,HSSFRow toRow,boolean copyValueFlag){
		for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
			HSSFCell tmpCell = (HSSFCell) cellIt.next();
			HSSFCell newCell = toRow.createCell(tmpCell.getCellNum());
			copyCell(wb,tmpCell, newCell, copyValueFlag);
		}
	}
	/**
	* 复制原有sheet的合并单元格到新创建的sheet
	* 
	* @param sheetCreat 新创建sheet
	* @param sheet      原有的sheet
	*/
	public static void mergerRegion(HSSFSheet fromSheet, HSSFSheet toSheet) {
	   int sheetMergerCount = fromSheet.getNumMergedRegions();
	   for (int i = 0; i < sheetMergerCount; i++) {
	    Region mergedRegionAt = fromSheet.getMergedRegionAt(i);
	    toSheet.addMergedRegion(mergedRegionAt);
	   }
	}
	/**
	 * 复制单元格
	 * 
	 * @param srcCell
	 * @param distCell
	 * @param copyValueFlag
	 *            true则连同cell的内容一起复制
	 */
	public static void copyCell(HSSFWorkbook wb,HSSFCell srcCell, HSSFCell distCell,
			boolean copyValueFlag) {
		HSSFCellStyle newstyle=wb.createCellStyle();
		copyCellStyle(srcCell.getCellStyle(), newstyle);
		
		//样式
		distCell.setCellStyle(newstyle);
		//评论
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		// 不同数据类型处理
		int srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);
		if (copyValueFlag) {
			if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
					distCell.setCellValue(srcCell.getDateCellValue());
				} else {
					distCell.setCellValue(srcCell.getNumericCellValue());
				}
			} else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
				distCell.setCellValue(srcCell.getRichStringCellValue());
			} else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
				// nothing21
			} else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
				distCell.setCellValue(srcCell.getBooleanCellValue());
			} else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
				distCell.setCellErrorValue(srcCell.getErrorCellValue());
			} else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
				distCell.setCellFormula(srcCell.getCellFormula());
			} else {
				distCell.setCellValue(srcCell.getStringCellValue());
			}
		}
	}
	
	public static void copyCellStyle(HSSFCellStyle fromStyle,
			HSSFCellStyle toStyle) {
		toStyle.setAlignment(fromStyle.getAlignment());
		//边框和边框颜色
		toStyle.setBorderBottom(fromStyle.getBorderBottom());
		toStyle.setBorderLeft(fromStyle.getBorderLeft());
		toStyle.setBorderRight(fromStyle.getBorderRight());
		toStyle.setBorderTop(fromStyle.getBorderTop());
		toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
		toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
		toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
		toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
		
		//背景和前景
		toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
		toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
		
		toStyle.setDataFormat(fromStyle.getDataFormat());
		toStyle.setFillPattern(fromStyle.getFillPattern());
//		toStyle.setFont(fromStyle.getFont(null));
		toStyle.setHidden(fromStyle.getHidden());
		toStyle.setIndention(fromStyle.getIndention());//首行缩进
		toStyle.setLocked(fromStyle.getLocked());
		toStyle.setRotation(fromStyle.getRotation());//旋转
		toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
		toStyle.setWrapText(fromStyle.getWrapText());
		
	}
}
