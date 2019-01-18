package com.thinkgem.jeesite.modules.student.adapter.score;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.thinkgem.jeesite.modules.student.adapter.AbsStudentScoreAdapter;
import com.thinkgem.jeesite.modules.student.adapter.StudentScoreCourse;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

public class CourseScore extends AbsStudentScoreAdapter<StudentScoreCourse> {

	@Override
	public void execute(HSSFSheet sheet) {
		int rowIndex = 14;
		HSSFWorkbook wb = sheet.getWorkbook();
		for(StudentScoreCourse studentScoreCourse:getList()) {
			UcStudent student = studentScoreCourse.getUcStudent();
			List<StudentCourse> studentCourses = studentScoreCourse.getStudentCourses();
			for(StudentCourse sc : studentCourses) {
				Row studentRow = sheet.createRow(rowIndex);
				studentRow.setHeight((short) 370);//目的是想把行高设置成25px

				Cell studentNumberCell = studentRow.createCell(0);
				formatCell(wb,studentNumberCell);
				studentNumberCell.setCellValue(student.getStudentNumber());
				Cell nameCell = studentRow.createCell(1);
				nameCell.setCellValue(student.getUsername());
				formatCell(wb,nameCell);
				
				 Cell classEvaValueCell = studentRow.createCell(2);
				 
				 classEvaValueCell.setCellValue(sc.getClassEvaValue());
				 formatCell(wb,classEvaValueCell);
				 
					
				 Cell midEvaValueCell = studentRow.createCell(3);
				 
				 midEvaValueCell.setCellValue(sc.getMidEvaValue());
				 formatCell(wb,midEvaValueCell);
				 
				 Cell finEvaValueCell = studentRow.createCell(4);
				 
				 finEvaValueCell.setCellValue(sc.getFinEvaValue());
				 formatCell(wb,finEvaValueCell);
				 
				 Cell evaValueCell = studentRow.createCell(5);
				 evaValueCell.setCellValue(sc.getEvaValue());
				 formatCell(wb,evaValueCell);
		
				
				 Cell creditCell = studentRow.createCell(6);
				 
				 creditCell.setCellValue(sc.getCredit());
				 formatCell(wb,creditCell);
				 
				 Cell pointCell = studentRow.createCell(7);
				 
				 pointCell.setCellValue(sc.getPoint());
				 formatCell(wb,pointCell);
				 
				 Cell remarkCell = studentRow.createCell(8);
				 remarkCell.setCellValue(sc.getCourse().getCursName());
				 formatCell(wb,remarkCell);

				
				rowIndex++;
			}
			
		 }
		
	}

}
