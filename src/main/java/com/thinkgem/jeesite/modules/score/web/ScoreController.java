package com.thinkgem.jeesite.modules.score.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.modules.course.entity.Course;
//https://www.chsi.com.cn/xlrz/report_gdjycjd.jsp
@Controller
@RequestMapping(value = {"score", "chengji"})
public class ScoreController {

	@RequestMapping(value = {"index", ""})
	public String index(Course course, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		
		return "";
	}
}
