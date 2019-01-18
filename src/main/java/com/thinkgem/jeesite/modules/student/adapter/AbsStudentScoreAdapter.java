package com.thinkgem.jeesite.modules.student.adapter;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public abstract class AbsStudentScoreAdapter<T> {
	private List<T> list;
	public abstract void execute(HSSFSheet sheet);
	protected void formatCell(HSSFWorkbook wb,Cell cell) {
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
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
