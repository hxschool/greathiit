package com.thinkgem.jeesite.modules.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.echart.ItemStyle;
import com.thinkgem.jeesite.common.persistence.CostGreeNode;
import com.thinkgem.jeesite.common.persistence.GreeNode;
import com.thinkgem.jeesite.common.utils.RandomColorUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.school.entity.SchoolRoot;
import com.thinkgem.jeesite.modules.school.service.SchoolRootService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
/**
 * 统计分析
 * @author Team
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/information")
public class InformationController extends BaseController {
	@Autowired
	private OfficeService officeService;
	@Autowired
	private SchoolRootService schoolRootService;
	/**
	 * 学院专业班级树状统计
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("greathiit/graph")
	public String graph(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/information/greathiit/index";
	}
	@RequestMapping("greathiit/tree")
	public String tree(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/information/greathiit/tree";
	}
	@RequestMapping("greathiit/greathiit.json")
	@ResponseBody
	public GreeNode flare( HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Office entity = new Office();
		entity.setId("1");
		Office office = officeService.get(entity);
		CostGreeNode node = new CostGreeNode();
		node.setId(office.getId());
		node.setName(office.getName());
		node.setParentId(office.getParentId());
		List<Office> collegeOffices = officeService.findByParentId(office.getId());
		List<GreeNode> collegeNodes = new ArrayList<GreeNode>();
		for(Office college:collegeOffices) {
			List<Office> majorOffices = officeService.findByParentId(college.getId());
			List<GreeNode> majorNodes = new ArrayList<GreeNode>();
			for(Office major:majorOffices) {
				List<Office> clazzOffices = officeService.findByParentId(major.getId());
				List<GreeNode> clazzNodes = new ArrayList<GreeNode>();
				for(Office clazz:clazzOffices) {
					CostGreeNode clazzNode = new CostGreeNode();
					clazzNode.setId(clazz.getId());
					clazzNode.setName(clazz.getName());
					clazzNode.setParentId(clazz.getParentId());
					clazzNode.setValue(1);
					clazzNode.setItemStyle(new ItemStyle(RandomColorUtils.getRandomColor()));
					clazzNodes.add(clazzNode);
				}
				CostGreeNode majorNode = new CostGreeNode();
				majorNode.setId(major.getId());
				majorNode.setName(major.getName());
				majorNode.setParentId(major.getParentId());
				majorNode.setChildren(clazzNodes);
				majorNode.setItemStyle(new ItemStyle(RandomColorUtils.getRandomColor()));
				majorNodes.add(majorNode);
			}
			CostGreeNode collegeNode = new CostGreeNode();
			collegeNode.setId(college.getId());
			collegeNode.setName(college.getName());
			collegeNode.setParentId(college.getParentId());
			collegeNode.setChildren(majorNodes);
			collegeNode.setItemStyle(new ItemStyle(RandomColorUtils.getRandomColor()));
			collegeNodes.add(collegeNode);
		}
		node.setChildren(collegeNodes);
		node.setItemStyle(new ItemStyle(RandomColorUtils.getRandomColor()));
		return node;
	}
	@RequestMapping("schoolroot/tree")
	public String schoolroot(HttpServletRequest request, HttpServletResponse response) {
		return "modules/information/schoolroot/tree";
	}
	@RequestMapping("schoolroot/schoolroot.json")
	@ResponseBody
	public GreeNode schoolroot( HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
	
		CostGreeNode node = new CostGreeNode();
		node.setId("0");
		node.setName("哈尔滨信息工程学院");
		node.setParentId("-1");
		List<SchoolRoot> list1 = schoolRootService.findByParentId("1");
		List<SchoolRoot> list2 = schoolRootService.findByParentId("2");
		List<SchoolRoot> list3 = schoolRootService.findByParentId("3");
		CostGreeNode node1 = new CostGreeNode();
		node1.setId("1");
		node1.setName("A栋");
		node1.setParentId("0");
		node1.setChildren(handler(list1));
		
		CostGreeNode node2 = new CostGreeNode();
		node2.setId("2");
		node2.setName("B栋");
		node2.setParentId("0");
		node2.setChildren(handler(list2));
		CostGreeNode node3 = new CostGreeNode();
		node3.setId("3");
		node3.setName("C栋");
		node3.setParentId("0");
		
		node3.setChildren(handler(list3));
		List<GreeNode> nodes = new ArrayList<GreeNode>();
		
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		node.setChildren(nodes);
		return node;
	}
	
	private List<GreeNode> handler(List<SchoolRoot> schoolroots){
		List<GreeNode> greenodes = new ArrayList<GreeNode>();
		for(SchoolRoot schoolRoot:schoolroots){
			CostGreeNode node3 = new CostGreeNode();
			node3.setId(schoolRoot.getId());
			node3.setName(schoolRoot.getValue());
			node3.setParentId(schoolRoot.getParent().getId());
			greenodes.add(node3);
		}
		return greenodes;
	}
}
