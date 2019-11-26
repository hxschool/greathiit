package com.thinkgem.jeesite.modules.student.export;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class StudentTrackedExport {
	@ExcelField(title="学号", align=2, sort=1)
	private String studentNumber;//返回json处理~
	@ExcelField(title="姓名", align=2, sort=2)
	private String name;		// 姓名
	@ExcelField(title="班级", align=2, sort=3)
	private Office clazz;		// 班级编号
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Office getClazz() {
		return clazz;
	}
	public void setClazz(Office clazz) {
		this.clazz = clazz;
	}
	
}
