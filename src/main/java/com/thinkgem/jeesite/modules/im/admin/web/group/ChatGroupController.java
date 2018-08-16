/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.web.group;

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
import com.thinkgem.jeesite.modules.im.admin.entity.group.ChatGroup;
import com.thinkgem.jeesite.modules.im.admin.service.group.ChatGroupService;

/**
 * 好友分组Controller
 * @author 赵俊飞
 * @version 2018-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/im/group/chatGroup")
public class ChatGroupController extends BaseController {

	@Autowired
	private ChatGroupService chatGroupService;
	
	@ModelAttribute
	public ChatGroup get(@RequestParam(required=false) String id) {
		ChatGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = chatGroupService.get(id);
		}
		if (entity == null){
			entity = new ChatGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("im:group:chatGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(ChatGroup chatGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ChatGroup> page = chatGroupService.findPage(new Page<ChatGroup>(request, response), chatGroup); 
		model.addAttribute("page", page);
		return "modules/im/group/chatGroupList";
	}

	@RequiresPermissions("im:group:chatGroup:view")
	@RequestMapping(value = "form")
	public String form(ChatGroup chatGroup, Model model) {
		model.addAttribute("chatGroup", chatGroup);
		return "modules/im/group/chatGroupForm";
	}

	@RequiresPermissions("im:group:chatGroup:edit")
	@RequestMapping(value = "save")
	public String save(ChatGroup chatGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, chatGroup)){
			return form(chatGroup, model);
		}
		chatGroupService.save(chatGroup);
		addMessage(redirectAttributes, "保存好友分组成功");
		return "redirect:"+Global.getAdminPath()+"/im/group/chatGroup/?repage";
	}
	
	@RequiresPermissions("im:group:chatGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(ChatGroup chatGroup, RedirectAttributes redirectAttributes) {
		chatGroupService.delete(chatGroup);
		addMessage(redirectAttributes, "删除好友分组成功");
		return "redirect:"+Global.getAdminPath()+"/im/group/chatGroup/?repage";
	}

}