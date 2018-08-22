package com.thinkgem.jeesite.modules.answering.home;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.CourseUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnswering;
import com.thinkgem.jeesite.modules.answering.admin.entity.AsAnsweringStudent;
import com.thinkgem.jeesite.modules.answering.admin.service.AsAnsweringService;
import com.thinkgem.jeesite.modules.answering.admin.service.AsAnsweringStudentService;
import com.thinkgem.jeesite.modules.answering.home.response.AnsweringResponse;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
@Controller
@RequestMapping(value = "/answering")
public class AnsweringController extends BaseController {
	@Autowired
	private AsAnsweringService asAnsweringService;
	@Autowired
	private AsAnsweringStudentService asAnsweringStudentService;
	@RequestMapping(value = {"index", ""})
	public String index(HttpServletRequest request,HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/answering/home/index";
	}
	@RequestMapping(value = "get")
	@ResponseBody
	public List<AnsweringResponse> get() {
		List<AsAnswering> asAnswerings = asAnsweringService.findList(new AsAnswering());
		List<AnsweringResponse> list = new LinkedList<AnsweringResponse>();
		for(AsAnswering asAnswering : asAnswerings) {
			String jxl = CourseUtil.jiaoxuelou(CourseUtil.GetTimeCol(asAnswering.getTimeAdd()).get("school"));
			String jiaoshi = CourseUtil.jiaoshi(CourseUtil.GetTimeCol(asAnswering.getTimeAdd()).get("school"));
			String school = jxl.concat(jiaoshi);
			AnsweringResponse answeringResponse = new AnsweringResponse();
			answeringResponse.setTitle(asAnswering.getTitle());
			answeringResponse.setSchool(school);
			AsAnsweringStudent asAnsweringStudent = new AsAnsweringStudent();
			asAnsweringStudent.setAsAnsweringId(asAnswering.getId());
			asAnsweringStudent.setStatus(AsAnsweringService.HAVE_IN_HAND);
			AsAnsweringStudent as = asAnsweringStudentService.get(asAnsweringStudent);
			if(StringUtils.isEmpty(as)) {
				asAnsweringStudent.setStatus(AsAnsweringService.READY);
				List<AsAnsweringStudent> ass = asAnsweringStudentService.findList(asAnsweringStudent);
				if(!CollectionUtils.isEmpty(ass)) {
					AsAnsweringStudent  haveInHand  = ass.get(0);
					haveInHand.setStatus(AsAnsweringService.HAVE_IN_HAND);
					asAnsweringStudentService.save(haveInHand);
					as = haveInHand;
				}
			}
			if(StringUtils.isEmpty(as)) {
				User current = UserUtils.getByLoginName(as.getStudentNumber());
				
				User next = null;
				asAnsweringStudent.setAsAnsweringId(asAnswering.getId());
				asAnsweringStudent.setStatus(AsAnsweringService.READY);
				List<AsAnsweringStudent> ass = asAnsweringStudentService.findList(asAnsweringStudent);
				if(!CollectionUtils.isEmpty(ass)) {
					next = UserUtils.getByLoginName(ass.get(0).getStudentNumber());
				}
	
				
				answeringResponse.setCurrent(current);
				answeringResponse.setNext(next);
			}
			AsAnsweringStudent cs = new AsAnsweringStudent();
			cs.setAsAnsweringId(asAnswering.getId());
			int total = asAnsweringStudentService.count(cs);
			asAnsweringStudent.setStatus(AsAnsweringService.COMPLETE);
			int completed = asAnsweringStudentService.count(cs);
			answeringResponse.setTotal(total);
			answeringResponse.setCompleted(completed);
			StringBuffer sb = new StringBuffer();
			String[] teacherIds = asAnswering.getTeacherIds().split(",");
			for(String teacherNumber:teacherIds) {
				User teacher = UserUtils.getByLoginName(teacherNumber);
				sb.append(teacher.getName());
				sb.append(",");
			}
			String teachers= "";
			if(!StringUtils.isEmpty(sb)) {
				teachers = sb.substring(0, sb.length()-1);
			}
			answeringResponse.setTeachers(teachers);
			list.add(answeringResponse);
		}
		
		return list;
	}
}
