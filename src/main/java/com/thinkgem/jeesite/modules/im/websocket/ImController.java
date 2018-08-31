/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.websocket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSInit;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSMember;
import com.thinkgem.jeesite.modules.im.websocket.service.LayIMService;

/**
 * 个人资料Controller
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Controller
@RequestMapping(value = "/im/api")
public class ImController extends BaseController {
	@Autowired
	private LayIMService layIMService;
	@RequestMapping(value = "/base")
	@ResponseBody
	public SNSInit base(HttpServletRequest request,HttpServletResponse response) {
	        String userId = request.getParameter("id");
	        SNSInit SNSInit = layIMService.getInitList(userId);
	        return SNSInit;
	}
	
	@RequestMapping(value = "/nickname")
	@ResponseBody
	public SNSMember nickname(HttpServletRequest request,HttpServletResponse response) {
	        String uid = request.getParameter("uid");
	        String nickname = request.getParameter("nickname");
	        SNSMember SNSMember = layIMService.nickname(uid,nickname);
	        return SNSMember;
	}

	@RequestMapping(value = "/sign")
	@ResponseBody
	public SNSMember sign(HttpServletRequest request,HttpServletResponse response) {
	        String uid = request.getParameter("uid");
	        String sign = request.getParameter("sign");
	        SNSMember SNSMember = layIMService.sign(uid,sign);
	        return SNSMember;
	}

	
	@RequestMapping(value = "/head")
	@ResponseBody
	public SNSMember head(HttpServletRequest request,HttpServletResponse response) {
	        String uid = request.getParameter("uid");
	        String imageBase64= request.getParameter("imageBase64");
	        SNSMember SNSMember = layIMService.head(uid,imageBase64);
	        return SNSMember;
	}
	
}