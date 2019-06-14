package com.thinkgem.jeesite.modules.student.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

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

}
