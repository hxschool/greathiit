package com.thinkgem.jeesite.modules.student.adapter.score;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;

public class CourseScore extends AbsStudentScoreAdapter<StudentCourse> {

	@Override
	public void execute(HSSFSheet sheet) {
		int rowIndex = 14;
		HSSFWorkbook wb = sheet.getWorkbook();
		CellStyle style = formatCell(wb);
		for (StudentCourse sc : getList()) {
			Row studentRow = sheet.createRow(rowIndex);
			studentRow.setHeight((short) 370);// 目的是想把行高设置成25px

			Cell studentNumberCell = studentRow.createCell(0);
			studentNumberCell.setCellStyle(style);
			studentNumberCell.setCellValue(sc.getStudentNumber());
			Cell nameCell = studentRow.createCell(1);
			nameCell.setCellValue(sc.getStudentName());
			nameCell.setCellStyle(style);
			Cell classEvaValueCell = studentRow.createCell(2);

			classEvaValueCell.setCellValue(sc.getClassEvaValue());
			classEvaValueCell.setCellStyle(style);
			Cell midEvaValueCell = studentRow.createCell(3);

			midEvaValueCell.setCellValue(sc.getMidEvaValue());
			midEvaValueCell.setCellStyle(style);
			Cell finEvaValueCell = studentRow.createCell(4);

			finEvaValueCell.setCellValue(sc.getFinEvaValue());
			finEvaValueCell.setCellStyle(style);
			Cell evaValueCell = studentRow.createCell(5);
			evaValueCell.setCellValue(sc.getEvaValue());
			evaValueCell.setCellStyle(style);
			Cell creditCell = studentRow.createCell(6);

			creditCell.setCellValue(sc.getCredit());
			creditCell.setCellStyle(style);
			Cell pointCell = studentRow.createCell(7);

			pointCell.setCellValue(sc.getPoint());
			pointCell.setCellStyle(style);

			Cell remarkCell = studentRow.createCell(8);
			remarkCell.setCellValue("");
			remarkCell.setCellStyle(style);

			rowIndex++;
		}

	}

}
