package com.thinkgem.jeesite.modules.student.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;



public class StudentReportUtil {
	public void oper(File file,Map<String,String> departmentMap,Map<String,String> dateMap,List<UcStudent> list,OutputStream os) throws FileNotFoundException, IOException {
		
		 POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));  
		 HSSFWorkbook  wb = new HSSFWorkbook(fs);  
		 HSSFSheet sheet = wb.getSheetAt(0);
		 Row schoolreportRow =   sheet.getRow(0);
		 Cell schoolreportCell = schoolreportRow.getCell(0);
		 
		 Row departmentRow =   sheet.getRow(2);
		 Cell departmentCell = departmentRow.getCell(0);
		 String departmentValue = departmentCell.getStringCellValue();
		 for (Map.Entry<String, String> entry : departmentMap.entrySet()) {  
		    departmentValue = departmentValue.replace(entry.getKey(),entry.getValue());
		 }  
				 
		 departmentCell.setCellValue(departmentValue);
		 
		 Row dateRow =   sheet.getRow(3);
		 Cell dateCell = dateRow.getCell(0);
		 String dateValue = dateCell.getStringCellValue();
		 for (Map.Entry<String, String> entry : dateMap.entrySet()) {  
			 dateValue = dateValue.replace(entry.getKey(),entry.getValue());
		 }  
		 dateCell.setCellValue(dateValue);
		 int rowIndex = 14;
		 for(UcStudent student:list) {
			Row studentRow = sheet.createRow(rowIndex);
			studentRow.setHeight((short) 370);//目的是想把行高设置成25px

			Cell studentNumberCell = studentRow.createCell(0);
			formatCell(wb,studentNumberCell);
			studentNumberCell.setCellValue(student.getStudentNumber());
			Cell nameCell = studentRow.createCell(1);
			nameCell.setCellValue(student.getUsername());
			formatCell(wb,nameCell);
			for(int j=2;j<9;j++) {
				Cell tempCell = studentRow.createCell(j);
				formatCell(wb,tempCell);
			}
			rowIndex++;
		 }
        wb.write(os);  
        os.flush();
        os.close();  
	}
	
	private void formatCell(HSSFWorkbook wb,Cell cell) {
		CellStyle style = wb.createCellStyle();  
		 HSSFFont hssfFont = wb.createFont();
		 hssfFont.setFontName("宋体");
		 style.setFont(hssfFont);
		 
		   style.setBorderBottom(CellStyle.BORDER_THIN);  
		   style.setBottomBorderColor(IndexedColors.BLACK.getIndex());  
		   style.setBorderLeft(CellStyle.BORDER_THIN);  
		   style.setLeftBorderColor(IndexedColors.BLACK.getIndex());  
		   style.setBorderRight(CellStyle.BORDER_THIN);  
		   style.setRightBorderColor(IndexedColors.BLACK.getIndex());  
		   style.setBorderTop(CellStyle.BORDER_THIN);  
		   style.setTopBorderColor(IndexedColors.BLACK.getIndex());  
		   style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
		   style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   cell.setCellStyle(style);  
	}
}
