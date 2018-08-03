package com.thinkgem.jeesite.modules.xuanke.web;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.modules.course.service.CourseScheduleService;
import com.thinkgem.jeesite.modules.course.service.CourseService;
import com.thinkgem.jeesite.modules.select.entity.SelectCourse;
import com.thinkgem.jeesite.modules.select.service.SelectCourseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.TreeLink;
@Controller
@RequestMapping(value = "xuanke")
public class XuankeController extends BaseController {
	@Autowired
	private CourseService courseService;
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
	public String index(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Course> courses = courseService.findList(course);
		List<SelectCourse> selectCourses = new ArrayList<SelectCourse>();
		User user = UserUtils.getUser();
		if(!StringUtils.isEmpty(user)&&!StringUtils.isEmpty(user.getNo())) {
			SelectCourse selectCourse = new SelectCourse();
			String studentNumber = user.getNo();
			selectCourse.setStudentNumber(studentNumber);
			selectCourses = selectCourseService.findList(selectCourse);
		}
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		model.addAttribute("courses", courses);
		model.addAttribute("selectCourses", selectCourses);
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
			
			SelectCourse selectCourse = new SelectCourse();
			String studentNumber = user.getNo();
			selectCourse.setStudentNumber(studentNumber);
			selectCourse.setCourse(entity);
			SelectCourse selectCourseEntity = selectCourseService.get(selectCourse);
			if(!StringUtils.isEmpty(selectCourseEntity)) {
				selectCourseService.delete(selectCourseEntity);
				addMessage(redirectAttributes, "退课成功");
			}else {
				selectCourseService.save(selectCourse);
				addMessage(redirectAttributes, "选课成功");
			}
			
			return "redirect:/xuanke/index?repage";
		}
		addMessage(redirectAttributes, "当前用户未登录,请登陆后再操作");
		return "redirect:/xuanke/index?repage";
	}
	
	
	@RequestMapping("kebiao")
	public String kebiao(@RequestParam(value="list",required=false) List<String> list,@RequestParam(value="courseClass",required=false) String courseClass,@RequestParam(value="teacherNumber",required=false) String teacherNumber, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		List<CourseScheduleExt> courseScheduleExts = courseScheduleService.findCoursesByParam(list,courseClass,teacherNumber);
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
}
