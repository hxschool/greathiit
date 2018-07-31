package com.thinkgem.jeesite.modules.xuanke.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.course.entity.Course;
import com.thinkgem.jeesite.modules.xuanke.service.XuankeService;
@Controller
@RequestMapping(value = "xuanke")
public class XuankeController extends BaseController {
	@Autowired
	private XuankeService xuankeService;
	
	/**
	 * 网站首页
	 */
	@RequestMapping(value = {"index", ""})
	public String index(Course course, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Course> page = xuankeService.findPage(new Page<Course>(request, response), course); 
		model.addAttribute("page", page);
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/xuanke/themes/"+site.getTheme()+"/index";
	}
}
