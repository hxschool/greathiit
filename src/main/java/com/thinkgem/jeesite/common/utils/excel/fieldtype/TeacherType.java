package com.thinkgem.jeesite.common.utils.excel.fieldtype;

import java.util.List;

import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.teacher.entity.Teacher;
import com.thinkgem.jeesite.modules.teacher.service.TeacherService;

public class TeacherType {
	private static TeacherService teacherService = SpringContextHolder.getBean(TeacherService.class);

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		
		List<Teacher> allTeacherList = teacherService.findByParentIdsLike(new Teacher());
		for (String s : StringUtils.split(val, ",")) {
			for (Teacher e : allTeacherList) {
				if (StringUtils.trimToEmpty(s).equals(e.getTchrName())) {
					return e;
				}
			}
		}
		return null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null) {
			@SuppressWarnings("unchecked")
			List<Teacher> teacherList = (List<Teacher>) val;
			return Collections3.extractToString(teacherList, "tchrName", ", ");
		}
		return "";
	}
}
