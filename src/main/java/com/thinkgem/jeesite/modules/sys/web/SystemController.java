/**
 * Copyright &copy; 2018-2025 <a href="http://www.greathiit.com">哈尔滨信息工程学院</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 登录Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
@RequestMapping(value = "${adminPath}/system")
public class SystemController extends BaseController{
	
	@RequestMapping(value = "clazz")
	public String clazz() {
		return "modules/system/clazzDialog";
	}
	
	@RequestMapping(value = "major")
	public String major() {
		return "modules/system/majorDialog";
	}
	
}
