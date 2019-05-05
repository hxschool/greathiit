package com.thinkgem.jeesite.modules.export;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.SysConfig;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SysConfigService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

@Controller
@RequestMapping(value = "${adminPath}/export")
public class ExportController extends BaseController {
	
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SysConfigService sysConfigService;
	private SysConfig config;


	@ModelAttribute
	public void config(Model model) {
		config = sysConfigService.getModule(Global.SYSCONFIG_EXPORT);
		model.addAttribute("config", config);
	}
	
	//数据统计-导出报表
	@RequestMapping(value = "search")
	public String exportView(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/export/search";
	}
	
	@RequestMapping(value = "ajaxReport")
	@ResponseBody
	public Map<String,Integer> ajaxReport(String classno, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		User user = new User();
		if(!org.springframework.util.StringUtils.isEmpty(classno)) {
			
			user.setClazz(officeService.get(classno));
			
		}
		
		List<User> users = systemService.findAllList(user);
		int m = 0;
		int f = 0;
		int o = 0;
		for(User u : users) {
			String idCard = u.getLoginName();
			if(!org.springframework.util.StringUtils.isEmpty(idCard)) {
				String s = IdcardUtils.getGenderByIdCard(idCard);
				if(s.equals("1")) {
					m++;
				}else if (s.equals("2")) {
					f++;
				}else {
					o++;
				}
			}
			
		}
		map.put("M", m);
		map.put("F", f);
		map.put("O", o);
		map.put("T",users.size());
		return map;

	}
	//导出学生数据明细~点名册
    @RequestMapping(value = "rollcall")
    public String exportFile(String classno, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			StringBuffer sb = new StringBuffer();
			if(org.springframework.util.StringUtils.isEmpty(classno)) {
        		throw new IllegalArgumentException("数据异常,[classno]不允许为空");
        	}
            if(!org.springframework.util.StringUtils.isEmpty(classno)) {
            	
            	Office entity = officeService.get(classno);
            	if(org.springframework.util.StringUtils.isEmpty(entity)) {
            		throw new RuntimeException("数据异常,根据班号:"+classno + "未查找到对应的班级信息");
            	}
            	String majorName = entity.getParent().getName();
            	Office major = officeService.getOfficeByName(majorName);
            	String departmentName = major.getParent().getName();
				sb.append(departmentName + "-" + majorName + "-" + entity.getName());
            
				String fileName = "班级信息-"+ classno +"-"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            ExportExcel ee = new ExportExcel();
	            String[] headers = {"序号","学号","姓名","备注"}; 
	            List<String> headerList = Arrays.asList(headers);
	            ee.init(sb.toString(), headerList);
	            ee.setHeader(headerList);
	            
	            User user = new User();
	            Office clazz = new Office();
	            clazz.setId(classno);
	            user.setClazz(clazz);
	            List<User> list = systemService.findAllList(user);
	            Collections.sort(list, new Comparator<User>(){
	              
	                public int compare(User p1, User p2) {
	                    if(Integer.valueOf(p1.getNo()) >Integer.valueOf(p2.getNo()) ){
	                        return 1;
	                    }
	                    if(Integer.valueOf(p1.getNo()) == Integer.valueOf(p2.getNo())){
	                        return 0;
	                    }
	                    return -1;
	                }
	            });
	            int i = 1;
	            for(User u : list) {
	            	Row row = ee.addRow();
	            	ee.addCell(row, 0, i);
	            	ee.addCell(row, 1, u.getNo());
	            	ee.addCell(row, 2, u.getName());
	            	ee.addCell(row, 3, "");
	            	i++;
	            }
	    		ee.write(response, fileName).dispose();
            }
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出班级信息,失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/export/search?repage";
    }
}
