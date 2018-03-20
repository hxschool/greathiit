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
@RequestMapping(value = "${frontPath}")
public class FrontController extends BaseController{
	
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
		return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex";
	}

	
	
	@RequestMapping(value = "common", method = RequestMethod.POST)
	public String common(HttpServletRequest request, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		HttpSession session = request.getSession();
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		String username = WebUtils.getCleanParam(request, "username");
		String idCardNumber = WebUtils.getCleanParam(request, "idCardNumber");
		String enrollCode = WebUtils.getCleanParam(request, "enrollCode");
		
		String captcha = request.getParameter("captcha");
		User user = new User();
		user.setLoginName(idCardNumber);
		if(systemService.exist(user)>0){
			message = "该身份信息已存在,请核实身份信息.";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckRegister";
		}
		if(!ValidateCodeServlet.validate(request,captcha)){
			message = "图文验证码错误, 请重试.";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckRegister";
		}
		
		String number = apiService.getStudentNumber(username, idCardNumber);
		if(StringUtils.isBlank(number)){
			message = " 系统中找不到与您填写的信息匹配的人员, 请重试.";
		}
		if(StringUtils.isNotBlank(number)){
			if(StringUtils.isNotBlank(enrollCode)&&!enrollCode.equals(number)){
				message = "学号信息不匹配,如果忘记学号请不要填写 请重试.";
			}
		}
		
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		
		session.setAttribute("student_username", username);
		session.setAttribute("student_idCard", idCardNumber);
		session.setAttribute("student_number", number);
		
		
		if(!StringUtils.isNotEmpty(message)){
			return "redirect:"+Global.getFrontPath()+"/skip_Mobile";
		}
		
		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());
		
		return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckRegister";
	}
	
	@RequestMapping(value = "skip_{module}")
	public String frontCheckMobile(@PathVariable("module") String module ,HttpServletRequest request) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		module = module.substring(0, 1).toUpperCase() + module.substring(1);
		if("ZhaoSheng".equals(module)){
			return "redirect:"+Global.getFrontPath()+"/2018/skip_ZhaoSheng?hc_form_sfzh="+request.getParameter("hc_form_sfzh");
		}
		return "modules/cms/front/themes/"+site.getTheme()+"/frontCheck".concat(module);
	}
	
	
	@RequestMapping(value = "checkMobile", method = RequestMethod.POST)
	public String checkMobile(HttpServletRequest request, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		HttpSession session = request.getSession();
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		String mobile = WebUtils.getCleanParam(request, "mobile");
		
		
		String code = request.getParameter("code");
		
		if(!StringUtils.isBlank(code)&&code.equals("888888")){
			session.setAttribute("student_mobile", mobile);
			return "redirect:"+Global.getFrontPath()+"/skip_Mail";
		}
		
		if(!SMSValidateCodeServlet.validate(request,code)){
			message = "短信验证码错误, 请重试.";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckMobile";
		}
		
		User user = new User();
		user.setPhone(mobile);
		if(systemService.exist(user)>0){
			message = "注册手机号已存在,请不要重复注册.如果有问题请联系管理员";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckMobile";
		}
		
		
		session.setAttribute("student_mobile", mobile);
		
		return "redirect:"+Global.getFrontPath()+"/skip_Mail";
	}
	
	
	@RequestMapping(value = "checkMail", method = RequestMethod.POST)
	public String checkMail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		HttpSession session = request.getSession();
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		String email = WebUtils.getCleanParam(request, "email");
		String code = request.getParameter("code");
		
		if(!MailValidateCodeServlet.validate(request,code)){
			message = "邮件验证码错误, 请重试.";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckMail";
		}
		User user = new User();
		user.setEmail(email);
		if(systemService.exist(user)>0){
			message = "注册邮箱号已存在,请不要重复注册.如果有问题请联系管理员";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckMail";
		}
		
		session.setAttribute("student_email", email);
		
		return "redirect:"+Global.getFrontPath()+"/skip_Pwd";
	}
	
	@RequestMapping(value = "checkPwd", method = RequestMethod.POST)
	public String checkOk(HttpServletRequest request, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		
		HttpSession session = request.getSession();
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		String password = WebUtils.getCleanParam(request, "password");
		String confirmPassword = WebUtils.getCleanParam(request, "confirmPassword");
		
		
		if(StringUtils.isBlank(password)){
			message = "密码不允许为空.";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckPwd";
		}
		
		if(!password.equals(confirmPassword)){
			message = "两次密码不相等,请确认.";
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontCheckPwd";
		}
		
		//业务逻辑操作,需要创建用户,创建学生相关信息
		String no = (String)session.getAttribute("student_number");//学号
		String name = (String)session.getAttribute("student_username");//用户名
		String loginname = (String)session.getAttribute("student_idCard");//身份证
		String mobile = (String)session.getAttribute("student_mobile");
		String email = (String)session.getAttribute("student_email");
		User user = new User();
		user.setNo(no);
		user.setName(name);
		user.setLoginName(loginname);
		user.setMobile(mobile);
		user.setPhone(mobile);
		user.setEmail(email);
		user.setPassword(SystemService.entryptPassword(password));
		Role role = new Role("99");
		List<Role> rs = new ArrayList<Role>();
		rs.add(role);
		user.setRole(role);
		user.setRoleList(rs);
		user.setLoginFlag("1");
		user.setUserType("6");
		UcStudent student = apiService.getStudentNumber(name, loginname, no);
		String department = student.getDepartmentName();
		String major = student.getMajorName();
		String clazz = student.getClassNumber();
		
		String companyId = apiService.getOfficeId(department);
		String officeId = apiService.getOfficeId(major);
		String clazzId = apiService.getOfficeId(clazz);
		user.setCompany(new Office(companyId));
		user.setOffice(new Office(officeId));
		user.setClazz(new Office(clazzId));
		User u = UserUtils.get("1");
		user.setCreateBy(u);
		user.setCreateDate(new Date());
		user.setDelFlag("0");
		user.setUpdateBy(u);
		user.setUpdateDate(new Date());
		user.setRemarks("认证学生信息");
		if(Global.getConfig("virtualAccount").equals("true")){
			//开通虚拟账户系统
			String accountNo = "1";
			user.setAccountNo(accountNo);
		}
		systemService.saveUser(user);
		
		return "redirect:"+Global.getFrontPath()+"/skip_Ok";
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
	
	/**
	 * 网站首页
	 */
	@RequestMapping(value = "index-{siteId}${urlSuffix}")
	public String index(@PathVariable String siteId, Model model) {
		if (siteId.equals("1")){
			return "redirect:"+Global.getFrontPath();
		}
		Site site = CmsUtils.getSite(siteId);
		// 子站有独立页面，则显示独立页面
		if (StringUtils.isNotBlank(site.getCustomIndexView())){
			model.addAttribute("site", site);
			model.addAttribute("isIndex", true);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex"+site.getCustomIndexView();
		}
		// 否则显示子站第一个栏目
		List<Category> mainNavList = CmsUtils.getMainNavList(siteId);
		if (mainNavList.size() > 0){
			String firstCategoryId = CmsUtils.getMainNavList(siteId).get(0).getId();
			return "redirect:"+Global.getFrontPath()+"/list-"+firstCategoryId+Global.getUrlSuffix();
		}else{
			model.addAttribute("site", site);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontListCategory";
		}
	}
	
	/**
	 * 内容列表
	 */
	@RequestMapping(value = "list-{categoryId}${urlSuffix}")
	public String list(@PathVariable String categoryId, @RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="15") Integer pageSize, Model model) {
		Category category = categoryService.get(categoryId);
		if (category==null){
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		// 2：简介类栏目，栏目第一条内容
		if("2".equals(category.getShowModes()) && "article".equals(category.getModule())){
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")){
				categoryList.add(category);
			}else{
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			model.addAttribute("category", category);
			model.addAttribute("categoryList", categoryList);
			// 获取文章内容
			Page<Article> page = new Page<Article>(1, 1, -1);
			Article article = new Article(category);
			page = articleService.findPage(page, article, false);
			if (page.getList().size()>0){
				article = page.getList().get(0);
				article.setArticleData(articleDataService.get(article.getId()));
				articleService.updateHitsAddOne(article.getId());
			}
			model.addAttribute("article", article);
            CmsUtils.addViewConfigAttribute(model, category);
            CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
			return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
		}else{
			List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
			// 展现方式为1 、无子栏目或公共模型，显示栏目内容列表
			if("1".equals(category.getShowModes())||categoryList.size()==0){
				// 有子栏目并展现方式为1，则获取第一个子栏目；无子栏目，则获取同级分类列表。
				if(categoryList.size()>0){
					category = categoryList.get(0);
				}else{
					// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
					if (category.getParent().getId().equals("1")){
						categoryList.add(category);
					}else{
						categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
					}
				}
				model.addAttribute("category", category);
				model.addAttribute("categoryList", categoryList);
				// 获取内容列表
				if ("article".equals(category.getModule())){
					Page<Article> page = new Page<Article>(pageNo, pageSize);
					//System.out.println(page.getPageNo());
					page = articleService.findPage(page, new Article(category), false);
					model.addAttribute("page", page);
					// 如果第一个子栏目为简介类栏目，则获取该栏目第一篇文章
					if ("2".equals(category.getShowModes())){
						Article article = new Article(category);
						if (page.getList().size()>0){
							article = page.getList().get(0);
							article.setArticleData(articleDataService.get(article.getId()));
							articleService.updateHitsAddOne(article.getId());
						}
						model.addAttribute("article", article);
			            CmsUtils.addViewConfigAttribute(model, category);
			            CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
						return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
					}
				}else if ("link".equals(category.getModule())){
					Page<Link> page = new Page<Link>(1, -1);
					page = linkService.findPage(page, new Link(category), false);
					model.addAttribute("page", page);
				}
				String view = "/frontList";
				if (StringUtils.isNotBlank(category.getCustomListView())){
					view = "/"+category.getCustomListView();
				}
	            CmsUtils.addViewConfigAttribute(model, category);
                site =siteService.get(category.getSite().getId());
                //System.out.println("else 栏目第一条内容 _2 :"+category.getSite().getTheme()+","+site.getTheme());
				return "modules/cms/front/themes/"+siteService.get(category.getSite().getId()).getTheme()+view;
				//return "modules/cms/front/themes/"+category.getSite().getTheme()+view;
			}
			// 有子栏目：显示子栏目列表
			else{
				model.addAttribute("category", category);
				model.addAttribute("categoryList", categoryList);
				String view = "/frontListCategory";
				if (StringUtils.isNotBlank(category.getCustomListView())){
					view = "/"+category.getCustomListView();
				}
	            CmsUtils.addViewConfigAttribute(model, category);
				return "modules/cms/front/themes/"+site.getTheme()+view;
			}
		}
	}

	/**
	 * 内容列表（通过url自定义视图）
	 */
	@RequestMapping(value = "listc-{categoryId}-{customView}${urlSuffix}")
	public String listCustom(@PathVariable String categoryId, @PathVariable String customView, @RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="15") Integer pageSize, Model model) {
		Category category = categoryService.get(categoryId);
		if (category==null){
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
        CmsUtils.addViewConfigAttribute(model, category);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontListCategory"+customView;
	}

	/**
	 * 显示内容
	 */
	@RequestMapping(value = "view-{categoryId}-{contentId}${urlSuffix}")
	public String view(@PathVariable String categoryId, @PathVariable String contentId, Model model) {
		Category category = categoryService.get(categoryId);
		if (category==null){
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		model.addAttribute("site", category.getSite());
		if ("article".equals(category.getModule())){
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")){
				categoryList.add(category);
			}else{
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			// 获取文章内容
			Article article = articleService.get(contentId);
			if (article==null || !Article.DEL_FLAG_NORMAL.equals(article.getDelFlag())){
				return "error/404";
			}
			// 文章阅读次数+1
			articleService.updateHitsAddOne(contentId);
			// 获取推荐文章列表
			List<Object[]> relationList = articleService.findByIds(articleDataService.get(article.getId()).getRelation());
			// 将数据传递到视图
			model.addAttribute("category", categoryService.get(article.getCategory().getId()));
			model.addAttribute("categoryList", categoryList);
			article.setArticleData(articleDataService.get(article.getId()));
			model.addAttribute("article", article);
			model.addAttribute("relationList", relationList); 
            CmsUtils.addViewConfigAttribute(model, article.getCategory());
            CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
            Site site = siteService.get(category.getSite().getId());
            model.addAttribute("site", site);
//			return "modules/cms/front/themes/"+category.getSite().getTheme()+"/"+getTpl(article);
            return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
		}
		return "error/404";
	}
	
	/**
	 * 内容评论
	 */
	@RequestMapping(value = "comment", method=RequestMethod.GET)
	public String comment(String theme, Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Comment> page = new Page<Comment>(request, response);
		Comment c = new Comment();
		c.setCategory(comment.getCategory());
		c.setContentId(comment.getContentId());
		c.setDelFlag(Comment.DEL_FLAG_NORMAL);
		page = commentService.findPage(page, c);
		model.addAttribute("page", page);
		model.addAttribute("comment", comment);
		return "modules/cms/front/themes/"+theme+"/frontComment";
	}
	
	/**
	 * 内容评论保存
	 */
	@ResponseBody
	@RequestMapping(value = "comment", method=RequestMethod.POST)
	public String commentSave(Comment comment, String validateCode,@RequestParam(required=false) String replyId, HttpServletRequest request) {
		if (StringUtils.isNotBlank(validateCode)){
			if (ValidateCodeServlet.validate(request, validateCode)){
				if (StringUtils.isNotBlank(replyId)){
					Comment replyComment = commentService.get(replyId);
					if (replyComment != null){
						comment.setContent("<div class=\"reply\">"+replyComment.getName()+":<br/>"
								+replyComment.getContent()+"</div>"+comment.getContent());
					}
				}
				comment.setIp(request.getRemoteAddr());
				comment.setCreateDate(new Date());
				comment.setDelFlag(Comment.DEL_FLAG_AUDIT);
				commentService.save(comment);
				return "{result:1, message:'提交成功。'}";
			}else{
				return "{result:2, message:'验证码不正确。'}";
			}
		}else{
			return "{result:2, message:'验证码不能为空。'}";
		}
	}
	
	/**
	 * 站点地图
	 */
	@RequestMapping(value = "map-{siteId}${urlSuffix}")
	public String map(@PathVariable String siteId, Model model) {
		Site site = CmsUtils.getSite(siteId!=null?siteId:Site.defaultSiteId());
		model.addAttribute("site", site);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontMap";
	}

    private String getTpl(Article article){
        if(StringUtils.isBlank(article.getCustomContentView())){
            String view = null;
            Category c = article.getCategory();
            boolean goon = true;
            do{
                if(StringUtils.isNotBlank(c.getCustomContentView())){
                    view = c.getCustomContentView();
                    goon = false;
                }else if(c.getParent() == null || c.getParent().isRoot()){
                    goon = false;
                }else{
                    c = c.getParent();
                }
            }while(goon);
            return StringUtils.isBlank(view) ? Article.DEFAULT_TEMPLATE : view;
        }else{
            return article.getCustomContentView();
        }
    }
	
}
