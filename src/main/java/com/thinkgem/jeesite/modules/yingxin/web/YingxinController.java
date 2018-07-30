package com.thinkgem.jeesite.modules.yingxin.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;

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

	@ModelAttribute
	public void initalCategory(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		List<Category> categorys = categoryService.findByParentId("2", site.getId());
		model.addAttribute("categorys", categorys);	
	}
	
	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(HttpServletRequest request ,Model model) {
		
		Article entity = new Article();
		Category category = new Category();
		category.setId("5");
		entity.setCategory(category);
		List<Article> list = articleService.findList(entity);
		List<Article> articles = new ArrayList<Article>();
		for(Article article:list) {
			if(article.getWeight()>900) {
				articles.add(article);
			}
		}
		Link link = new Link();
		Category linkCategory = new Category();
		linkCategory.setId("20");
		link.setCategory(linkCategory);
		List<Link> links = linkService.findList(link);
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		model.addAttribute("articles", articles);
		model.addAttribute("links", links);
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

	
	
}
