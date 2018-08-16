/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.websocket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.im.admin.entity.group.ChatGroup;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSInit;
import com.thinkgem.jeesite.modules.im.websocket.data.SNSMember;
import com.thinkgem.jeesite.modules.im.websocket.service.LayIMService;

/**
 * 个人资料Controller
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Controller
@RequestMapping(value = "/im/api/group")
public class ImGroupController extends BaseController {
	@Autowired
	private LayIMService layIMService;

	//获取群组成员
	@RequestMapping(value = "/getSimpleMemberByGroupId")
	@ResponseBody
	public List<String> getSimpleMemberByGroupId(HttpServletRequest request,HttpServletResponse response) {
	        String gId = request.getParameter("id");
	       
	        return null;
	}
	//获取我的群组列表(加入的)
	@RequestMapping(value = "/getGroupByUserId")
	@ResponseBody
	public List<ChatGroup> getGroupByUserId(HttpServletRequest request,HttpServletResponse response) {
	       
	       
	        return null;
	}
	
	@RequestMapping(value = "/getByGroupId")
	public @ResponseBody SNSMember getByGroupId(String id){		
		return layIMService.GroupFriend(id);
	}

}