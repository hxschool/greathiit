package com.thinkgem.jeesite.modules.yingxin.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.recruit.entity.student.RecruitStudent;
import com.thinkgem.jeesite.modules.recruit.service.student.RecruitStudentService;
import com.thinkgem.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "/yingxin")
public class YingxinController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RecruitStudentService recruitStudentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@ModelAttribute
	public void initalCategory(Model model) {
		Link link = new Link();
		Category linkCategory = new Category();
		linkCategory.setId("2");
		link.setCategory(linkCategory);
		List<Link> links = linkService.findList(link);
		
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		List<Category> categorys = categoryService.findByParentId("2", site.getId());
		model.addAttribute("categorys", categorys);	
		model.addAttribute("links", links);
	}
	
	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(HttpServletRequest request ,Model model) {
		
		Article entity = new Article();
		Category category = new Category();
		category.setId("200005");
		entity.setCategory(category);
		List<Article> list = articleService.findList(entity);
		List<Article> articles = new ArrayList<Article>();
		for(Article article:list) {
			if(article.getWeight()>900) {
				articles.add(article);
			}
		}
		
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		model.addAttribute("articles", articles);
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/yingxin/themes/"+site.getTheme()+"/index";
	}
	
	@RequestMapping(value="login")
	public String login(HttpServletRequest request ,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/yingxin/themes/"+site.getTheme()+"/login";
	}
	
	
	@RequestMapping(value = "view-{categoryId}-{contentId}${urlSuffix}")
	public String view(@PathVariable String categoryId, @PathVariable String contentId, Model model) {
		Article article = articleService.get(contentId);
		article.setArticleData(articleDataService.get(article.getId()));
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("article", article);
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/yingxin/themes/"+site.getTheme()+"/view";
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
		return "modules/yingxin/themes/"+site.getTheme()+"/list";
	}

	@RequestMapping(value = "signin")
	public String signin(Model model) {
		return "modules/yingxin/themes/mobile/signin";
	}
	
	@RequestMapping(value = "checkSign")
	@ResponseBody
	public Map<String,String> checkSign(String phone,String pwd,  Model model) {
		Map<String,String> map = new HashMap<String,String>();
		RecruitStudent recruitStudent = new RecruitStudent();
		recruitStudent.setUsername(phone);
		recruitStudent.setIdCard(pwd);
		RecruitStudent pojo = recruitStudentService.getRecruitStudent(recruitStudent);
		if(StringUtils.isEmpty(pojo)) {
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "根据姓名和身份证号未查找到相关信息,请联系报考教师");
			map.put("responseCode", "0");
			map.put("responseMessage", "根据姓名和身份证号未查找到相关信息,请联系报考教师");
			return map;
		}
		if(pojo.getStatus().equals(RecruitStudentService.RECRUIT_STUDENT_STATUS_BAODAO_SUCCESS)) {
			map.put("responseCode", "1");
			map.put("responseMessage", "已签到,请勿重复签到");
			return map;
		}
		if (pojo.getStatus().equals(RecruitStudentService.RECRUIT_STUDENT_STATUS_BAODAO)) {
			pojo.setStatus(RecruitStudentService.RECRUIT_STUDENT_STATUS_BAODAO_SUCCESS);
			recruitStudentService.save(pojo);
		}
		map.put("responseCode", "2");
		map.put("responseMessage", "签到成功,请领取报道卡后进行缴费");
		return map;
	}
	
}
