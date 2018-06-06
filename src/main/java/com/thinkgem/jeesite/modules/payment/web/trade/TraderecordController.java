/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.payment.web.trade;

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
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;

/**
 * 交易信息Controller
 * @author 赵俊飞
 * @version 2018-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/payment/trade/traderecord")
public class TraderecordController extends BaseController {

	@Autowired
	private TraderecordService traderecordService;
	
	@ModelAttribute
	public Traderecord get(@RequestParam(required=false) String id) {
		Traderecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = traderecordService.get(id);
		}
		if (entity == null){
			entity = new Traderecord();
		}
		return entity;
	}
	
	@RequiresPermissions("payment:trade:traderecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(Traderecord traderecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Traderecord> page = traderecordService.findPage(new Page<Traderecord>(request, response), traderecord); 
		model.addAttribute("page", page);
		return "modules/payment/trade/traderecordList";
	}

	@RequiresPermissions("payment:trade:traderecord:view")
	@RequestMapping(value = "form")
	public String form(Traderecord traderecord, Model model) {
		model.addAttribute("traderecord", traderecord);
		return "modules/payment/trade/traderecordForm";
	}

	@RequiresPermissions("payment:trade:traderecord:edit")
	@RequestMapping(value = "save")
	public String save(Traderecord traderecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, traderecord)){
			return form(traderecord, model);
		}
		traderecordService.save(traderecord);
		addMessage(redirectAttributes, "保存交易信息成功");
		return "redirect:"+Global.getAdminPath()+"/payment/trade/traderecord/?repage";
	}
	
	@RequiresPermissions("payment:trade:traderecord:edit")
	@RequestMapping(value = "delete")
	public String delete(Traderecord traderecord, RedirectAttributes redirectAttributes) {
		traderecordService.delete(traderecord);
		addMessage(redirectAttributes, "删除交易信息成功");
		return "redirect:"+Global.getAdminPath()+"/payment/trade/traderecord/?repage";
	}

}