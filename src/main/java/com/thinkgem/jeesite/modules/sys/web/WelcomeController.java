package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.dorm.entity.UcDormRepair;
import com.thinkgem.jeesite.modules.dorm.service.UcDormRepairService;
import com.thinkgem.jeesite.modules.oa.entity.OaNotify;
import com.thinkgem.jeesite.modules.oa.service.OaNotifyService;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysAppconfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SysAppconfigService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
@Controller
@RequestMapping(value = "${adminPath}/welcome")
public class WelcomeController extends BaseController {
	@Autowired
	private SysAppconfigService sysAppconfigService;
	@Autowired
	private StudentCourseService studentCourseService;

	@Autowired
	private OaNotifyService oaNotifyService;
	
	@Autowired
	private UcDormRepairService ucDormRepairService;
	
	@RequestMapping(value = {"index", ""})
	public String index(SysAppconfig sysAppconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SysAppconfig> list = sysAppconfigService.findList(sysAppconfig);
        model.addAttribute("list", list);
		return "modules/sys/welcome";
	}

	@RequestMapping(value = "student")
	public String student(SysAppconfig sysAppconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		//应用系统
		User user = UserUtils.getUser();
        List<SysAppconfig> list = sysAppconfigService.findList(sysAppconfig);
        
        //成绩
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudentNumber(user.getNo());
        List<StudentCourse>  studentCourses = studentCourseService.findList(studentCourse);
       
        //公告~
        OaNotify oaNotify = new OaNotify();
        oaNotify.setSelf(true);
        List<OaNotify> oaNotifys = oaNotifyService.findList(oaNotify);
        model.addAttribute("oaNotifys", oaNotifys);
        
        //我的报修
        UcDormRepair ucDormRepair = new UcDormRepair();
        ucDormRepair.setUser(user);
        List<UcDormRepair> ucDormRepairs = ucDormRepairService.findList(ucDormRepair);
		model.addAttribute("ucDormRepairs", ucDormRepairs);
		
		/*
		if(!StringUtils.isEmpty(user.getNo())){
			String url = "http://book.greathiit.com/api/getReaderByStudentNumber?studentNumber="+user.getNo();
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			List<Map<String,String>> books = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			model.addAttribute("books", books);
		}
		*/
		
		
        model.addAttribute("studentCourses", studentCourses);
        model.addAttribute("list", list);
		return "modules/sys/student";
	}
	
	@RequestMapping(value = "teacher")
	public String teacher(SysAppconfig sysAppconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<SysAppconfig> list = sysAppconfigService.findList(sysAppconfig);
        model.addAttribute("list", list);
		return "modules/sys/teacher";
	}
	
}
