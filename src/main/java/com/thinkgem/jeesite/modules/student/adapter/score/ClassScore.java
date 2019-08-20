package com.thinkgem.jeesite.modules.student.adapter.score;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

public class ClassScore extends AbsStudentScoreAdapter<UcStudent> {

	@Override
	public void execute(HSSFSheet sheet) {
		int rowIndex = 14;
		HSSFWorkbook wb = sheet.getWorkbook();
		CellStyle style = formatCell(wb);
		for (UcStudent student : getList()) {
			Row studentRow = sheet.createRow(rowIndex);
			studentRow.setHeight((short) 370);// 目的是想把行高设置成25px

			Cell studentNumberCell = studentRow.createCell(0);
			studentNumberCell.setCellStyle(style);
			studentNumberCell.setCellValue(student.getStudentNumber());
			Cell nameCell = studentRow.createCell(1);
			nameCell.setCellValue(student.getName());
			
			nameCell.setCellStyle(style);
			for (int j = 2; j < 9; j++) {
				Cell tempCell = studentRow.createCell(j);
				tempCell.setCellStyle(style);
			}
			rowIndex++;
		}

	}

}
