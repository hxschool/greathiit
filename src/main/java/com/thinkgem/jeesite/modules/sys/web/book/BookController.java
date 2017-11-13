/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thinkgem.jeesite.common.utils.HttpClientUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/book")
public class BookController extends BaseController {

	@RequestMapping(value = {"list", ""})
	public String list(String bookname,String sortbookname, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!StringUtils.isEmpty(bookname)){
			String url = "http://book.greathiit.com/api/getBook?bookname="+bookname;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			List<Map<String,String>> booklist = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			model.addAttribute("bookname", bookname);
			model.addAttribute("booklist", booklist);
		}
		
		if(!StringUtils.isEmpty(sortbookname)){
			String url = "http://book.greathiit.com/api/getBook?bookname="+sortbookname;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			List<Map<String,String>> booklist = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			model.addAttribute("sortbookname", sortbookname);
			model.addAttribute("booklist", booklist);
		}
		
		return "modules/sys/bookList";
	}
	
	@RequestMapping("sortname")
	@ResponseBody
	public List<String> sortname(String sortbookname, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> list = new ArrayList<String>();
		if(!StringUtils.isEmpty(sortbookname)){
			String url = "http://book.greathiit.com/api/getSortBook?sortBookname="+sortbookname;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			List<Map<String,String>> booklist = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			for(Map<String,String> map:booklist){
				list.add(map.get("题名"));
			}
		}
		return list;
	}
	
	@RequestMapping("mybook")
	public String mybook( HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		String student_number = user.getNo();
		if(!StringUtils.isEmpty(student_number)){
			String url = "http://book.greathiit.com/api/getReaderByStudentNumber?studentNumber="+student_number;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			List<Map<String,String>> userinfos = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			model.addAttribute("userinfos", userinfos);
		}
		return "modules/sys/mybook";
	}
	@RequiresPermissions("sys:book:view")
	@RequestMapping("look")
	public String look(String name,String studentNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(name)){
			name = studentNumber;
		}
		
		if(!StringUtils.isEmpty(name)){
			String url = "http://book.greathiit.com/api/getReader?name="+name;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			List<Map<String,String>> userinfos = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			model.addAttribute("userinfos", userinfos);
		}
		
		return "modules/sys/look";
	}
	
	@RequiresPermissions("sys:book:view")
	@RequestMapping("reader")
	@ResponseBody
	public List<String> reader(String name,String studentNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> list = new ArrayList<String>();
		List<Map<String,String>> userinfos = new ArrayList<Map<String,String>>();
		if(!StringUtils.isEmpty(name)){
			String url = "http://book.greathiit.com/api/getReader?name="+name;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			userinfos = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
		}
		if(!StringUtils.isEmpty(studentNumber)){
			String url = "http://book.greathiit.com/api/getReaderByStudentNumber?studentNumber="+studentNumber;
			String result = HttpClientUtil.get(url);
			Gson gson = new Gson();
			userinfos = gson.fromJson(result, new TypeToken<List<Map<String,String>>>(){}.getType()); 
			
		}
		for(Map<String,String> map:userinfos){
			list.add(map.get("姓名"));
		}
		return list;
	}
	
}
