/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.web.friend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.im.admin.entity.friend.ChatFriend;
import com.thinkgem.jeesite.modules.im.admin.service.friend.ChatFriendService;

/**
 * 我的好友Controller
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/im/friend/chatFriend")
public class ChatFriendController extends BaseController {

	@Autowired
	private ChatFriendService chatFriendService;
	
	@ModelAttribute
	public ChatFriend get(@RequestParam(required=false) String id) {
		ChatFriend entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = chatFriendService.get(id);
		}
		if (entity == null){
			entity = new ChatFriend();
		}
		return entity;
	}
	
	@RequiresPermissions("im:friend:chatFriend:view")
	@RequestMapping(value = {"list", ""})
	public String list(ChatFriend chatFriend, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ChatFriend> page = chatFriendService.findPage(new Page<ChatFriend>(request, response), chatFriend); 
		model.addAttribute("page", page);
		return "modules/im/friend/chatFriendList";
	}

	@RequiresPermissions("im:friend:chatFriend:view")
	@RequestMapping(value = "form")
	public String form(ChatFriend chatFriend, Model model) {
		model.addAttribute("chatFriend", chatFriend);
		return "modules/im/friend/chatFriendForm";
	}

	@RequiresPermissions("im:friend:chatFriend:edit")
	@RequestMapping(value = "save")
	public String save(ChatFriend chatFriend, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, chatFriend)){
			return form(chatFriend, model);
		}
		chatFriendService.save(chatFriend);
		addMessage(redirectAttributes, "保存我的好友成功");
		return "redirect:"+Global.getAdminPath()+"/im/friend/chatFriend/?repage";
	}
	
	@RequiresPermissions("im:friend:chatFriend:edit")
	@RequestMapping(value = "delete")
	public String delete(ChatFriend chatFriend, RedirectAttributes redirectAttributes) {
		chatFriendService.delete(chatFriend);
		addMessage(redirectAttributes, "删除我的好友成功");
		return "redirect:"+Global.getAdminPath()+"/im/friend/chatFriend/?repage";
	}

}