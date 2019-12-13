package com.thinkgem.jeesite.modules.student.export;

public enum StudentAction {
	
	ucstudent("uc_student","学籍管理"),student("student","在籍管理"),tracked("tracked","分班管理"),imp("import","导入"),exp("import","导出");;
	
	private String code;
	private String name;
	
	private StudentAction(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
