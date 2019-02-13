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
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping
	public String index(HttpServletRequest request,HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/answering/home/answeringIndex";
	}
	
	@RequestMapping(value = "list-{id}")
	public String list_id(@PathVariable("id") String id,Model model) {
		model.addAttribute("id", id);
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/answering/home/answeringList";
	}
	
	@RequestMapping(value = "list")
	@ResponseBody
	public List<AsAnsweringStudent> list(AsAnsweringStudent asAnsweringStudent) {
		asAnsweringStudent.setAsAnsweringId(asAnsweringStudent.getAsAnsweringId());
		asAnsweringStudent.setStatus(AsAnsweringService.HAVE_IN_HAND);
		List<AsAnsweringStudent> list = asAnsweringStudentService.findList(asAnsweringStudent);
		asAnsweringStudent.setStatus(AsAnsweringService.READY);
		list.addAll(asAnsweringStudentService.findList(asAnsweringStudent));
		return list;
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
				asAnsweringStudent.setAsAnsweringId(asAnswering.getId());
				asAnsweringStudent.setStatus(AsAnsweringService.READY);
				List<AsAnsweringStudent> ass = asAnsweringStudentService.findList(asAnsweringStudent);
				if(!CollectionUtils.isEmpty(ass)) {
					AsAnsweringStudent  haveInHand  = ass.get(0);
					haveInHand.setStatus(AsAnsweringService.HAVE_IN_HAND);
					asAnsweringStudentService.save(haveInHand);
					as = haveInHand;
				}
			}
			
			if(!StringUtils.isEmpty(as)) {
				User current = UserUtils.getCasByLoginName(as.getStudentNumber());
				answeringResponse.setCurrent(current);
				User next = null;
				asAnsweringStudent.setAsAnsweringId(asAnswering.getId());
				asAnsweringStudent.setStatus(AsAnsweringService.READY);
				List<AsAnsweringStudent> ass = asAnsweringStudentService.findList(asAnsweringStudent);
				if(!CollectionUtils.isEmpty(ass)) {
					next = UserUtils.getCasByLoginName(ass.get(0).getStudentNumber());
				}
				answeringResponse.setNext(next);
			}
			
			
			
			AsAnsweringStudent cs = new AsAnsweringStudent();
			cs.setAsAnsweringId(asAnswering.getId());
			int total = asAnsweringStudentService.count(cs);
			cs.setStatus(AsAnsweringService.COMPLETE);
			int completed = asAnsweringStudentService.count(cs);
			answeringResponse.setTotal(total);
			answeringResponse.setCompleted(completed);
			String teachers = UserUtils.getTeachers(asAnswering.getTeacherIds());
			answeringResponse.setAsAnsweringId(asAnswering.getId());
			answeringResponse.setTeachers(teachers);
			list.add(answeringResponse);
		}
		
		return list;
	}
}
