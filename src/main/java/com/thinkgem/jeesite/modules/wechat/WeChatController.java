package com.thinkgem.jeesite.modules.wechat;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sword.wechat4j.user.UserManager;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.wechat.entity.WeixinUser;
import com.thinkgem.jeesite.modules.wechat.service.WeixinUserService;
//微信相关操作
@Controller
@RequestMapping("/wechat")
public class WeChatController extends BaseController {
	@Autowired
	private WeixinUserService weixinUserService;
	@Autowired
	private SystemService systemService;
	private final static String pwd = "c4aa67d4775697681c83a66cd451891b6a2f7db57efb283efd30105a";
	@RequestMapping
	public void execute(HttpServletRequest request, HttpServletResponse response){
		WechatHandler wechat = new WechatHandler(request);
		String result = wechat.execute();
		try {
			ServletOutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("auth")
	public String auth(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		 String service = request.getParameter("service");
	       String code = request.getParameter("code");
           logger.info("静默授权获取请求CODE:{}",code);
           UserManager userManager = new UserManager();
   		   String openid = userManager.getOpenId(code);	
   		   openid = "9999";
   		   WeixinUser  weixinUser = weixinUserService.get(openid);
   		   if(!StringUtils.isEmpty(weixinUser)) {
   			   String userno = weixinUser.getUserNo();
   			   return "redirect:http://login.greathiit.com/cas/login?action=auto&user="+userno+"&pwd="+pwd+"&service="+service;
   		   }
   		  
   		   session.setAttribute("service", service);
   		   session.setAttribute("openid", openid);
		   return "modules/wechat/bind";
	}
	@RequestMapping("bind")
	public String bind(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		String service = (String)session.getAttribute("service");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User entity = systemService.getCasByLoginName(username);
		boolean ret = systemService.validatePassword(password, entity.getPassword());
		if(ret) {
			if(!StringUtils.isEmpty(entity)) {
				String userno = entity.getNo();
				String openid = (String)session.getAttribute("openid");
				WeixinUser weixinUser = new WeixinUser();
				weixinUser.setOpenid(openid);
				weixinUser.setUserNo(userno);
				weixinUserService.save(weixinUser);
			}
			return "redirect:http://login.greathiit.com/cas/login?action=auto&user="+username+"&pwd="+password+"&service="+service;
		}
		
		return "modules/wechat/error";
	}
	
	
}
