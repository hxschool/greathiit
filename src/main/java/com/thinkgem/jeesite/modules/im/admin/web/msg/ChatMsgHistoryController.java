/**
 * TCopyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.im.admin.web.msg;

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
import com.thinkgem.jeesite.modules.im.admin.entity.msg.ChatMsgHistory;
import com.thinkgem.jeesite.modules.im.admin.service.msg.ChatMsgHistoryService;

/**
 * 历史消息Controller
 * @author 赵俊飞
 * @version 2018-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/im/msg/chatMsgHistory")
public class ChatMsgHistoryController extends BaseController {

	@Autowired
	private ChatMsgHistoryService chatMsgHistoryService;
	
	@ModelAttribute
	public ChatMsgHistory get(@RequestParam(required=false) String id) {
		ChatMsgHistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = chatMsgHistoryService.get(id);
		}
		if (entity == null){
			entity = new ChatMsgHistory();
		}
		return entity;
	}
	
	@RequiresPermissions("im:msg:chatMsgHistory:view")
	@RequestMapping(value = {"list", ""})
	public String list(ChatMsgHistory chatMsgHistory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ChatMsgHistory> page = chatMsgHistoryService.findPage(new Page<ChatMsgHistory>(request, response), chatMsgHistory); 
		model.addAttribute("page", page);
		return "modules/im/msg/chatMsgHistoryList";
	}

	@RequiresPermissions("im:msg:chatMsgHistory:view")
	@RequestMapping(value = "form")
	public String form(ChatMsgHistory chatMsgHistory, Model model) {
		model.addAttribute("chatMsgHistory", chatMsgHistory);
		return "modules/im/msg/chatMsgHistoryForm";
	}

	@RequiresPermissions("im:msg:chatMsgHistory:edit")
	@RequestMapping(value = "save")
	public String save(ChatMsgHistory chatMsgHistory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, chatMsgHistory)){
			return form(chatMsgHistory, model);
		}
		chatMsgHistoryService.save(chatMsgHistory);
		addMessage(redirectAttributes, "保存历史消息成功");
		return "redirect:"+Global.getAdminPath()+"/im/msg/chatMsgHistory/?repage";
	}
	
	@RequiresPermissions("im:msg:chatMsgHistory:edit")
	@RequestMapping(value = "delete")
	public String delete(ChatMsgHistory chatMsgHistory, RedirectAttributes redirectAttributes) {
		chatMsgHistoryService.delete(chatMsgHistory);
		addMessage(redirectAttributes, "删除历史消息成功");
		return "redirect:"+Global.getAdminPath()+"/im/msg/chatMsgHistory/?repage";
	}

}