/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.annotation.SameUrlData;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.servlet.MailValidateCodeServlet;
import com.thinkgem.jeesite.common.servlet.SMSValidateCodeServlet;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.service.ApiService;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.out.rs.entity.RsStudent;
import com.thinkgem.jeesite.modules.out.rs.service.RsStudentService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;

/**
 * 网站Controller
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/zs")
public class FrontZhaoShengController extends BaseController{
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private ApiService apiService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RsStudentService rsStudentService;
	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/index";
	}

	
	@RequestMapping(value = "skip_{module}")
	public String frontCheckMobile(@PathVariable("module") String module) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		module = module.substring(0, 1).toUpperCase() + module.substring(1);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontCheck".concat(module);
	}
	
	
	
	
	@SameUrlData
	@RequestMapping(value = "zhaosheng", method = RequestMethod.POST)
	public String checkZhaosheng(RsStudent rsStudent, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		rsStudentService.save(rsStudent);
	
		return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckSuccess";
	}
	
	
	@SameUrlData
	@RequestMapping(value = "jieguo", method = RequestMethod.POST)
	public String checkJieguo(String username, String idCardNumber, HttpServletRequest request,HttpServletResponse response, Model model) {
		
		String captcha = request.getParameter("captcha");
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		if(!ValidateCodeServlet.validate(request,captcha)){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "图文验证码错误, 请重试.");
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckJieguo";
		}
		if(username==null||username.equals("")){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "姓名不允许为空");
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckJieguo";
		}
		
		if(idCardNumber==null||idCardNumber.equals("")){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "身份证件号不允许为空");
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckJieguo";
		}
		
		
		
		RsStudent rsStudent = new RsStudent();
		rsStudent.setHc_form_xm(username);
		rsStudent.setHc_form_sfzh(idCardNumber);
		List<RsStudent> list = rsStudentService.findList(rsStudent);
		
		if(list==null||list.size()==0){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "根据姓名和身份证号未查找到相关信息,请联系报考教师");
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckJieguo";
		}
	
		String ret = "0";
		for(RsStudent rs : list){
			if (rs.getHc_form_zhuangtai() != null && rs.getHc_form_zhuangtai().equals("1")) {
				ret = "1";
				break;
			}
			
			if (rs.getHc_form_zhuangtai() != null && rs.getHc_form_zhuangtai().equals("2")) {
				ret = "2";
				break;
			}
			
		}
		
		if(ret.equals("1")){
			model.addAttribute("message", "查询登记成功");
		}else if (ret.equals("2")){
			model.addAttribute("message", "报考失败,请联系报考教师");
		}else{
			model.addAttribute("message", "信息审核中");
		}
		
		return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckJieguoOk";
	}
	
}
