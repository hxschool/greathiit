/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.common.web.BaseController;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/book")
public class BookController extends BaseController {

	@RequestMapping(value = {"list", ""})
	public String list(String bookname, HttpServletRequest request, HttpServletResponse response, Model model) {
		String url = "http://book.greathiit.com/api/getBook?bookname="+bookname;
		String result = HttpClientUtil.get(url);
		Gson gson = new Gson();
		List<Map<String,String>> booklist = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
		Set<String> set = new HashSet<String>();
		
		for(Map<String,String> map:booklist){
			for (Map.Entry<String, String> entry : map.entrySet()) {  
				set.add(entry.getKey()); 
			}
		}
		
		model.addAttribute("header", set);
		model.addAttribute("body", booklist);
		return "modules/sys/bookList";
	}
}
