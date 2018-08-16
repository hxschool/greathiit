/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.web.user;

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
import com.thinkgem.jeesite.modules.im.admin.entity.user.ChatUser;
import com.thinkgem.jeesite.modules.im.admin.service.user.ChatUserService;

/**
 * 个人资料Controller
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/im/user/chatUser")
public class ChatUserController extends BaseController {

	@Autowired
	private ChatUserService chatUserService;
	
	@ModelAttribute
	public ChatUser get(@RequestParam(required=false) String id) {
		ChatUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = chatUserService.get(id);
		}
		if (entity == null){
			entity = new ChatUser();
		}
		return entity;
	}
	
	@RequiresPermissions("im:user:chatUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ChatUser chatUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ChatUser> page = chatUserService.findPage(new Page<ChatUser>(request, response), chatUser); 
		model.addAttribute("page", page);
		return "modules/im/user/chatUserList";
	}

	@RequiresPermissions("im:user:chatUser:view")
	@RequestMapping(value = "form")
	public String form(ChatUser chatUser, Model model) {
		model.addAttribute("chatUser", chatUser);
		return "modules/im/user/chatUserForm";
	}

	@RequiresPermissions("im:user:chatUser:edit")
	@RequestMapping(value = "save")
	public String save(ChatUser chatUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, chatUser)){
			return form(chatUser, model);
		}
		chatUserService.save(chatUser);
		addMessage(redirectAttributes, "保存个人资料成功");
		return "redirect:"+Global.getAdminPath()+"/im/user/chatUser/?repage";
	}
	
	@RequiresPermissions("im:user:chatUser:edit")
	@RequestMapping(value = "delete")
	public String delete(ChatUser chatUser, RedirectAttributes redirectAttributes) {
		chatUserService.delete(chatUser);
		addMessage(redirectAttributes, "删除个人资料成功");
		return "redirect:"+Global.getAdminPath()+"/im/user/chatUser/?repage";
	}

}