package com.thinkgem.jeesite.modules.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.service.StudentService;

@Controller
@RequestMapping(value = "${adminPath}/student/complete")
public class StudentCompleteController extends BaseController {

	@Autowired
	private StudentService studentService;

	@ModelAttribute
	public Student get(@RequestParam(required = false) String id) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = studentService.get(id);
		}
		if (entity == null) {
			entity = new Student();
		}
		return entity;
	}

	@RequiresPermissions("student:student:complete")
	@RequestMapping(value = { "list", "" })
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		String op = request.getParameter("op");
		if (!org.springframework.util.StringUtils.isEmpty(op) && op.equals("search")) {
			Page<Student> page = studentService.completePage(new Page<Student>(request, response),student);
			model.addAttribute("page", page);
		}
		return "modules/student/complete/completeList";
	}
}
