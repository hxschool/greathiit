package com.thinkgem.jeesite.modules.xuanke.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aliyuncs.http.HttpRequest;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdcardValidator;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.course.entity.CourseScheduleExt;
import com.thinkgem.jeesite.modules.course.entity.CourseTeachingMode;
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.course.service.CourseTeachingModeService;
import com.thinkgem.jeesite.modules.pay.GlobalConstants;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.UserOperationLog;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.service.UserOperationLogService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;
@Controller
@RequestMapping(value = "xuanke")
public class XuankeController extends BaseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseTeachingModeService courseTeachingModeService;
	@Autowired
	private CourseScheduleService courseScheduleService;
	@Autowired
	private SelectCourseService selectCourseService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private UserOperationLogService userOperationLogService;
	
	@ModelAttribute
	public void initalCategory(Model model) {
		Category category = categoryService.get("3");
		Article entity = new Article();
		entity.setCategory(category);
		List<Article> list = articleService.findList(entity);
		List<Article> articles = new ArrayList<Article>();
		for(Article article:list) {
			if(article.getWeight()>900) {
				articles.add(article);
			}
		}
		boolean ret = DateUtils.isEffectiveDate(DictUtils.getDictLabel("start","select_course_start", ""), DictUtils.getDictLabel("end","select_course_end", ""));
		model.addAttribute("ret", ret);	
		model.addAttribute("category", category);	
		model.addAttribute("articles", articles);
	}
	@RequestMapping(value = "view-{categoryId}-{contentId}${urlSuffix}")
	public String view(@PathVariable String categoryId, @PathVariable String contentId, Model model) {
		Article article = articleService.get(contentId);
		article.setArticleData(articleDataService.get(article.getId()));
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("article", article);
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/xuanke/themes/"+site.getTheme()+"/view";
	}
	
	
	@RequestMapping(value = "list-{categoryId}${urlSuffix}")
	public String list(@PathVariable String categoryId,  Model model) {
		Category category = categoryService.get(categoryId);
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		Article entity = new Article();
		entity.setCategory(category);
		List<Article> articles = articleService.findList(entity);
		model.addAttribute("category", category);
		model.addAttribute("articles", articles);
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/xuanke/themes/"+site.getTheme()+"/list";
	}
	/**
	 * 网站首页
	 */
	@RequestMapping(value = {"index", ""})
	public String index(Course course, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		boolean isIndex = true;
		if(StringUtils.isEmpty(course.getCursProperty())) {
			course.setCursProperty("20");
		}
		course.setCursYearTerm(String.valueOf(DateUtils.getTerm()));
		List<Course> courses = courseService.findList(course);
		List<SelectCourse> selectCourses = new ArrayList<SelectCourse>();
		
		Map<String,CourseScheduleExt> courseScheduleMap = new HashMap<String,CourseScheduleExt>();
		CourseScheduleExt cse = new CourseScheduleExt();
		cse.setCourseClass("00000000");
		List<CourseScheduleExt> courseScheduleExts = courseScheduleService.getCourseScheduleExt(cse);
		for(CourseScheduleExt courseScheduleExt:courseScheduleExts) {
			courseScheduleMap.put(courseScheduleExt.getCourseId(), courseScheduleExt);
		}
		User user = UserUtils.getUser();
		if(!StringUtils.isEmpty(user)&&!StringUtils.isEmpty(user.getNo())) {
			isIndex = false;
			SelectCourse selectCourse = new SelectCourse();
			selectCourse.setStudent(user);
			selectCourse.setCourse(course);
			selectCourses = selectCourseService.findList(selectCourse);
			
			for(Role r:user.getRoleList()) {
				int id = Integer.valueOf(r.getId());
				if (id >= 90) {
					isIndex = true;
				}
			}
		}

		List<CourseTeachingMode> courseTeachingModes = new ArrayList<CourseTeachingMode>();
		if(!StringUtils.isEmpty(user)) {
			String studentNumber = user.getNo();
			if(!StringUtils.isEmpty(studentNumber)) {
				Iterator<Course> it = courses.iterator();
				String benke = "B".toUpperCase();
				while(it.hasNext()){
					Course c = it.next();
					String courseId = c.getId();
					if(studentNumber.length()==10) {//本科
						courseTeachingModes.add(courseTeachingModeService.getCourseTeachingModeByCourse(courseId));
						if(!c.getCursNum().substring(0,1).toUpperCase().equals(benke)) {
							it.remove();
						}
					}else if(studentNumber.length()==7||studentNumber.length()==8){
						courseTeachingModes.add(courseTeachingModeService.getCourseTeachingModeByCourse(courseId));
						if(c.getCursNum().substring(0,1).toUpperCase().equals(benke)) {
							it.remove();
						}
					}
				}
			}
		}
		
		Collections.sort(courseTeachingModes, new Comparator<CourseTeachingMode>(){
			 public int compare(CourseTeachingMode p1, CourseTeachingMode p2) {
				 if(StringUtils.isEmpty(p1.getPeriod())) {
					 return 0;
				 }
				 if(Integer.valueOf(p1.getPeriod())>Integer.valueOf(p2.getPeriod())) {
					 return 1;
				 }else {
					 return -1;
				 }
				 
			 }
		});
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("courseScheduleMap", courseScheduleMap);
		model.addAttribute("site", site);
		model.addAttribute("isIndex", isIndex);
		model.addAttribute("courses", courses);
		model.addAttribute("selectCourses", selectCourses);
		
		Map<String, Object> map= model.asMap();
		if(!StringUtils.isEmpty(map.get("message"))) {
			model.addAttribute("message", map.get("message") + "," + getMessage(user));
		}else {
			model.addAttribute("message", "");
		}
		return "modules/xuanke/themes/"+site.getTheme()+"/index";
	}
	

	@RequestMapping("select")
	public String select(Course course, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		Course entity = courseService.get(course);
		if(StringUtils.isEmpty(entity)) {
			addMessage(redirectAttributes, "请求参数异常。请不要使用非法参数操作");
		}
		
		User user = UserUtils.getUser();
		if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(user.getNo())) {
			SelectCourse countCourse = new SelectCourse();
			countCourse.setCourse(course);
			int cnt = selectCourseService.count(countCourse);
			SelectCourse selectCourse = new SelectCourse();
			selectCourse.setStudent(user);
			selectCourse.setCourse(entity);
			SelectCourse selectCourseEntity = selectCourseService.get(selectCourse);
			//教学模式
			CourseTeachingMode courseTeachingMode = courseTeachingModeService.getCourseTeachingModeByCourse(entity.getId());
			
			if(!StringUtils.isEmpty(selectCourseEntity)) {
				
				if (!StringUtils.isEmpty(courseTeachingMode) && !courseTeachingMode.getPeriod().equals("0") &&  cnt > entity.getUpperLimit()) {
					entity.setCursStatus(Course.PAIKE_STATUS_WEI_PAIKE);
					courseService.save(entity);
				}
				selectCourseService.delete(selectCourseEntity);
				saveSelectCourseLog(request, entity,GlobalConstants.Global_FAL,user.getNo());
				addMessage(redirectAttributes, "退课成功");
			}else {
				if (!StringUtils.isEmpty(courseTeachingMode) &&  !courseTeachingMode.getPeriod().equals("0") && cnt > entity.getUpperLimit()) {
					entity.setCursStatus(Course.PAIKE_STATUS_OVER_PAIKE);
					courseService.save(entity);
					addMessage(redirectAttributes, "当前课程已满,请选择其他课程");
					return "redirect:/xuanke/index?repage";
				}
				selectCourseService.save(selectCourse);
				saveSelectCourseLog(request, entity,GlobalConstants.Global_SUC,user.getNo());
				addMessage(redirectAttributes, "选课成功");
			}
			
			return "redirect:/xuanke/index?repage";
		}
		addMessage(redirectAttributes, "当前用户未登录,请登陆后再操作");
		return "redirect:/xuanke/index?repage";
	}
	
	public String getMessage(User user) {
		if(StringUtils.isEmpty(user)) {
			return "";
		}
		SelectCourse selectedCourse = new SelectCourse();
		selectedCourse.setStudent(user);
		List<SelectCourse> selectedCourses = selectCourseService.findList(selectedCourse);
		List<Course> courses = new ArrayList<Course>();
		String benke = "B".toUpperCase();
		if(user.getNo().length()==10) {
			for(SelectCourse sc:selectedCourses) {
				Course c = courseService.get(sc.getCourse());
				CourseTeachingMode courseTeachingMode = courseTeachingModeService.getCourseTeachingModeByCourse(c.getId());
				if(c.getCursNum().substring(0,1).toUpperCase().equals(benke)) {
					if(!StringUtils.isEmpty(courseTeachingMode)) {
						if(!StringUtils.isEmpty(courseTeachingMode.getPeriod())&&courseTeachingMode.getPeriod().equals("0")) {
							courses.add(c);
						}
					}
					
				}
			}
			
			if(courses.size()<1) {
				 return  "本科学生必须选择一节 <b>课堂讲授 </b> 模式课程";
			}
		}
		return "";
	}
	private void saveSelectCourseLog(HttpServletRequest request, Course entity,String status,String userno) {
		UserOperationLog log = new UserOperationLog();
		log.setModule("course");
		log.setModuleId(entity.getId());
		log.setUserNumber(userno);
		log.setUserType("99");
		log.setStatus(status);;
		log.setRemoteAddr(com.thinkgem.jeesite.common.utils.StringUtils.getRemoteAddr(request));
		log.setUserAgent(request.getHeader("user-agent"));
		log.setRequestUri(request.getRequestURI());
		userOperationLogService.save(log);
	}
	
	
	@RequestMapping("kebiao")
	public String kebiao(@RequestParam(value="list",required=false) List<String> list,@RequestParam(value="courseClass",required=false) String courseClass,@RequestParam(value="teacherNumber",required=false) String teacherNumber, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		
		CourseScheduleExt courseScheduleExt = new CourseScheduleExt();
		courseScheduleExt.setList(list);
		courseScheduleExt.setCourseClass(courseClass);
		courseScheduleExt.setTeacherNumber(teacherNumber);
		List<CourseScheduleExt> courseScheduleExts = courseScheduleService.findCoursesByParam(courseScheduleExt);
		model.addAttribute("courseScheduleExts", courseScheduleExts);

		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/xuanke/themes/"+site.getTheme()+"/kebiao";
	}
	
	@ResponseBody
	@RequestMapping(value = "treeClassLink")
	public List<TreeLink> treeClassLink(@RequestParam(required=false,defaultValue="1") String parnetId, HttpRequest request, HttpServletResponse response) {
		return officeService.treeClassLink(parnetId);
	}
	@Autowired
	private SystemService systemService;
	@ResponseBody
	@RequestMapping(value = "checkPassword")
	public String checkPassword(User user,String loginname, HttpRequest request, HttpServletResponse response) {
		if(StringUtils.isEmpty(user.getLoginName())) {
			List<User> users = systemService.findAllList(user);
			for(User u:users) {
				if(!StringUtils.isEmpty(u.getLoginName())) {
					if(IdcardValidator.isValidatedAllIdcard(u.getLoginName())) {
						String password = org.apache.commons.lang3.StringUtils.right(u.getLoginName(), 6);
						systemService.updatePasswordById(u.getId(), u.getLoginName(), password);
					}
				}
			}
		}else {
			User entity = systemService.getCasByLoginName(loginname);
			if(!StringUtils.isEmpty(entity)) {
				if(IdcardValidator.isValidatedAllIdcard(entity.getLoginName())) {
					String password = org.apache.commons.lang3.StringUtils.right(entity.getLoginName(), 6);
					systemService.updatePasswordById(entity.getId(), entity.getLoginName(), password);
				}else {
					String password = "888888";
					systemService.updatePasswordById(entity.getId(), entity.getLoginName(), password);
				}
			}
		}
		return "成功";
	}
	
	public static void main(String[] args) {
		String startTime = "2018-09-04 16:00:00";
		String endTime = "2018-09-04 18:00:00";
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startDate = dateFormat.parse(startTime);
			Date endDate = dateFormat.parse(endTime);
			boolean ret = DateUtils.isEffectiveDate(new Date(), startDate, endDate);
			System.out.println("开始结束时间"+ret);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
