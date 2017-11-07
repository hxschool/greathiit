/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thinkgem.jeesite.common.annotation.SameUrlData;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.out.rs.entity.RsStudent;
import com.thinkgem.jeesite.modules.out.rs.service.RsStudentService;
import com.thinkgem.jeesite.modules.out.system.entity.SystemStudent;
import com.thinkgem.jeesite.modules.out.system.service.SystemStudentService;
import com.thinkgem.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 网站Controller
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}/2018")
public class FrontZhaoShengController extends BaseController{
	
	@Autowired
	private RsStudentService rsStudentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SystemStudentService systemStudentService;
	/**
	 * 网站首页
	 */
	@RequestMapping(value = {"index", ""})
	public String index(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/index";
	}

	
	@RequestMapping(value = "skip_{module}")
	public String frontCheckMobile(@PathVariable("module") String module,String hc_form_sfzh,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		module = module.substring(0, 1).toUpperCase() + module.substring(1);
		
		SystemStudent systemStudent = systemStudentService.getByIdCard(hc_form_sfzh);
		if(!StringUtils.isEmpty(systemStudent)){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "该用户已注册,请输入身份证号码进行相关数据操作.");
			return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckJieguo";
		}
		
		return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheck".concat(module);
	}
	
	
	
	
	@SameUrlData
	@RequestMapping(value = "zhaosheng", method = RequestMethod.POST)
	public String checkZhaosheng(RsStudent rsStudent, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		if(StringUtils.isEmpty(rsStudent.getId())){
			String no = systemService.getRsStudentId();
			//报考顺序号
			rsStudent.setHc_form_area(no);
			model.addAttribute("no", "报专业顺序号"+no+"，考生需牢记。");
		}
		try{
			rsStudentService.save(rsStudent);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckSuccess";
	}
	
	
	@SameUrlData
	@RequestMapping(value = "jieguo", method = RequestMethod.POST)
	public String checkJieguo(String username, String idCardNumber, HttpServletRequest request,HttpServletResponse response, Model model) {
		
		String captcha = request.getParameter("captcha");
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		if(!ValidateCodeServlet.validate(request,captcha)){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "图文验证码错误, 请重试.");
			return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckJieguo";
		}
		if(username==null||username.equals("")){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "姓名不允许为空");
			return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckJieguo";
		}
		
		if(idCardNumber==null||idCardNumber.equals("")){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "身份证件号不允许为空");
			return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckJieguo";
		}
		
		
		
		
		SystemStudent systemStudent = systemStudentService.getByUsernameAndIdCard(username, idCardNumber);
		
		if(StringUtils.isEmpty(systemStudent)){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, "根据姓名和身份证号未查找到相关信息,请联系报考教师");
			return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckJieguo";
		}
	
		String ret = "0";
		
		if(!StringUtils.isEmpty(systemStudent.getHcFormZhuangtai())){
			ret = systemStudent.getHcFormZhuangtai().equals("1")?"1":"2";
		}
		
		if(ret.equals("1")){
			model.addAttribute("message", "查询登记成功");
		}else if (ret.equals("2")){
			model.addAttribute("message", "报考失败,请联系报考教师");
		}else{
			model.addAttribute("systemStudent", systemStudent);
			model.addAttribute("message", "信息审核中");
			return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckZhaoSheng";
		}
		
		return "modules/cms/front/themes/"+site.getTheme()+"/zhaosheng/frontCheckJieguoOk";
	}
	
}
