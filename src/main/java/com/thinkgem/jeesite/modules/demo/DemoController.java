package com.thinkgem.jeesite.modules.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.CostGreeNode;
import com.thinkgem.jeesite.common.persistence.GreeNode;
import com.thinkgem.jeesite.common.utils.GreeHelper;
import com.thinkgem.jeesite.modules.api.service.ApiService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.uc.student.entity.UcStudent;
import com.thinkgem.jeesite.modules.uc.student.service.UcStudentService;
import com.thinkgem.jeesite.modules.visitor.entity.TmVisitor;

@Controller
@RequestMapping(value = "${adminPath}/init/userinfo")
public class DemoController {
	private static Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired
	private SystemService systemService;
	@Autowired
	private ApiService apiService;
	@Autowired
	private UcStudentService ucStudentService;
	@Autowired
	private OfficeService officeService;
 
	@RequestMapping
	@ResponseBody
	public String list(TmVisitor tmVisitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List<UcStudent> list = ucStudentService.findByParentIdsLike(new UcStudent());
		String companyId = null;
		String officeId = null;
		String clazzId = null;
		String loginname = null;
		String department = null;
		String major = null;
		String clazz  = null;
		for (UcStudent ucStudent : list) {
			try {

				String no = ucStudent.getStudentNumber();
				String name = ucStudent.getUsername();
				loginname = ucStudent.getIdCard();
				String password = "000000";
				if (loginname.length() > 6) {
					password = loginname.substring(loginname.length() - 6);
				}

				User user = new User();
				user.setNo(no);
				user.setName(name);
				user.setLoginName(loginname);
				// user.setMobile(mobile);
				// user.setPhone(mobile);
				// user.setEmail(email);
				user.setPassword(SystemService.entryptPassword(password));
				Role role = new Role("6");
				List<Role> rs = new ArrayList<Role>();
				rs.add(role);
				user.setRole(role);
				user.setRoleList(rs);
				user.setLoginFlag("1");
				user.setUserType("6");
				UcStudent student = apiService.getStudentNumber(name, loginname, no);
				department = student.getDepartmentName();
				major = student.getMajorName();
				clazz = student.getClassNumber();

				companyId = apiService.getOfficeId(department);
				officeId = apiService.getOfficeId(major);

				clazzId = apiService.getClazzName(officeId, clazz);

				if (StringUtils.isEmpty(companyId) && !StringUtils.isEmpty(officeId)) {
					Office office = officeService.get(officeId);
					companyId = office.getParent().getId();
				}
				logger.info("department:{},companyId:{},major:{},clazzId:{}", department, companyId, major, officeId);

				user.setCompany(new Office(companyId));
				user.setOffice(new Office(officeId));
				user.setClazz(new Office(clazzId));
				User u = UserUtils.get("1");
				user.setCreateBy(u);
				user.setCreateDate(new Date());
				user.setDelFlag("0");
				user.setUpdateBy(u);
				user.setUpdateDate(new Date());
				user.setRemarks("认证学生信息");
				if (Global.getConfig("virtualAccount").equals("true")) {
					// 开通虚拟账户系统
					String accountNo = "1";
					user.setAccountNo(accountNo);
				}
				systemService.saveUser(user);
			} catch (Exception e) {
				System.out.println("----------------------------------------------------------------------");
				System.out.println("companyId:" + companyId + " major "+ major +" officeId :" + officeId + " clazz:" + clazzId);
				System.out.println("当前身份证号:" + loginname);
				System.out.println("----------------------------------------------------------------------");
			}
		}
		
		
		
		return "ok";
	}
	@RequestMapping("node")
	@ResponseBody
	public GreeNode list( HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		List<Office> list = officeService.findAll();
		Map<String,GreeNode> greeNodes = new HashMap<String,GreeNode>();
		for(Office office:list) {
			CostGreeNode node = new CostGreeNode();
			node.setId(office.getId());
			node.setName(office.getName());
			node.setParentId(office.getParentId());
			greeNodes.put(node.getId(), node);
		}
		GreeHelper.generateTree(greeNodes);
		return greeNodes.get("1");
	}
	public static void main(String[] args) {
		String aa = "230302198402175312";
		System.out.println(aa.substring(aa.length()-6));
	}
}
