/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.out.sc.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.out.sc.entity.RsStudentResult;
import com.thinkgem.jeesite.modules.out.sc.service.RsStudentResultService;

/**
 * 省成绩Controller
 * @author 赵俊飞
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/out/sc/rsStudentResult")
public class RsStudentResultController extends BaseController {

	@Autowired
	private RsStudentResultService rsStudentResultService;
	
	@ModelAttribute
	public RsStudentResult get(@RequestParam(required=false) String id) {
		RsStudentResult entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rsStudentResultService.get(id);
		}
		if (entity == null){
			entity = new RsStudentResult();
		}
		return entity;
	}
	
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(RsStudentResult rsStudentResult, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "省成绩数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<RsStudentResult> page = rsStudentResultService.findPage(new Page<RsStudentResult>(request, response, -1),rsStudentResult);
    		new ExportExcel("省成绩数据", RsStudentResult.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/out/sc/rsStudentResult/list?repage";
    }
	
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public void importFile(MultipartFile file,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<RsStudentResult> list = ei.getDataList(RsStudentResult.class);
			pw.write("<a href='/a/out/sc/rsStudentResult'>点击返回</a>");
			for(RsStudentResult rsStudentResult:list) {
				RsStudentResult entity = rsStudentResultService.get(rsStudentResult);
				
				Integer cj = Integer.valueOf(rsStudentResult.getHcFormYuwen()) + Integer.valueOf(rsStudentResult.getHcFormShuxue()) + Integer.valueOf(rsStudentResult.getHcFormZonghe());
				if(org.springframework.util.StringUtils.isEmpty(entity)) {
					
					pw.write(rsStudentResult.getHcFormBkh() + "," + rsStudentResult.getHcFormXm() + "," + rsStudentResult.getHcFormSfzh() + ","
							+ rsStudentResult.getHcFormYuwen() + "," + rsStudentResult.getHcFormShuxue() + ","
							+ rsStudentResult.getHcFormZonghe() + "," + cj);
					
					rsStudentResult.setHcFormCj(String.valueOf(cj));
					rsStudentResultService.save(rsStudentResult);
				}else {
					pw.write("数据对比结果:"+entity.getHcFormBkh() + "," + entity.getHcFormXm() + "," + entity.getHcFormSfzh() + ","
							+ entity.getHcFormYuwen() + (entity.getHcFormYuwen().equals(rsStudentResult.getHcFormYuwen())?"":"["+rsStudentResult.getHcFormYuwen()+","+entity.getHcFormYuwen()+"]<font color=\"RED\">X</font>") + "," + entity.getHcFormShuxue() + (entity.getHcFormShuxue().equals(rsStudentResult.getHcFormShuxue())?"":"["+rsStudentResult.getHcFormShuxue()+","+entity.getHcFormShuxue()+"]<font color=\"RED\">X</font>") + ","
							+ entity.getHcFormZonghe() + (entity.getHcFormZonghe().equals(rsStudentResult.getHcFormZonghe())?"":"["+rsStudentResult.getHcFormZonghe()+","+entity.getHcFormZonghe()+"]<font color=\"RED\">X</font>")  + "," + entity.getHcFormCj() +  (entity.getHcFormCj().equals(String.valueOf(cj))?"":"["+String.valueOf(cj)+","+entity.getHcFormCj()+"]<font color=\"RED\">X</font>"));
					
				}
				pw.write("<br>");
			}
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		pw.write("<a href='/a/out/sc/rsStudentResult'>点击返回</a>");
		pw.flush();
		pw.close();

	}
	
	//@RequiresPermissions("out:sc:rsStudentResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(RsStudentResult rsStudentResult, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RsStudentResult> page = rsStudentResultService.findPage(new Page<RsStudentResult>(request, response), rsStudentResult); 
		model.addAttribute("page", page);
		return "modules/out/sc/rsStudentResultList";
	}

	//@RequiresPermissions("out:sc:rsStudentResult:view")
	@RequestMapping(value = "form")
	public String form(RsStudentResult rsStudentResult, Model model) {
		model.addAttribute("rsStudentResult", rsStudentResult);
		return "modules/out/sc/rsStudentResultForm";
	}

	@RequiresPermissions("out:sc:rsStudentResult:edit")
	@RequestMapping(value = "save")
	public String save(RsStudentResult rsStudentResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rsStudentResult)){
			return form(rsStudentResult, model);
		}
		rsStudentResultService.save(rsStudentResult);
		addMessage(redirectAttributes, "保存省成绩成功");
		return "redirect:"+Global.getAdminPath()+"/out/sc/rsStudentResult/?repage";
	}
	
	@RequiresPermissions("out:sc:rsStudentResult:edit")
	@RequestMapping(value = "delete")
	public String delete(RsStudentResult rsStudentResult, RedirectAttributes redirectAttributes) {
		rsStudentResultService.delete(rsStudentResult);
		addMessage(redirectAttributes, "删除省成绩成功");
		return "redirect:"+Global.getAdminPath()+"/out/sc/rsStudentResult/?repage";
	}

}