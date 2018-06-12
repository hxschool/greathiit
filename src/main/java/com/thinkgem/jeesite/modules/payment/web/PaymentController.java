package com.thinkgem.jeesite.modules.payment.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.IPUtil;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.QRCodeKit;
import com.thinkgem.jeesite.common.utils.StudentUtil;
import com.thinkgem.jeesite.modules.pay.GlobalConstants;
import com.thinkgem.jeesite.modules.payment.entity.order.Order;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.order.OrderService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.DictService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/payment")
public class PaymentController {
	@Autowired
	private SystemService systemService;
	@Autowired
	private DictService dictService;
	
	@Autowired
	private OrderService orderService;
	
	@Value("${payment.qrcode.url}")
	private String qrCodeUrl;

	@RequestMapping(value = {"index", ""})
	public String index( HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PaymentEntity> list = new ArrayList<PaymentEntity>();

		Dict dict = new Dict();
		dict.setType("payment_type");
		List<Dict> dicts = dictService.findList(dict);
		
		User user = UserUtils.getUser();
		
		for(Dict d:dicts) {
			PaymentEntity paymentEntity = new PaymentEntity();
			paymentEntity.setId(d.getId());
			paymentEntity.setTitle(d.getLabel());
			paymentEntity.setAmount(d.getValue());
			paymentEntity.setRemarks(d.getRemarks());
			if(!StringUtils.isEmpty(d.getRemarks())&&d.getRemarks().equals("default")) {
				list.add(paymentEntity);
			}else if (!StringUtils.isEmpty(user.getNo())) {
				String year = StudentUtil.getCircles(user.getNo());
				if(year.equals(d.getRemarks())) {
					list.add(paymentEntity);
				}
			}
		}
		
		Order order = new Order();
		order.setStatus(GlobalConstants.TRADESTATUS_SUC);
		order.setUser(user);
		List<Order> orders = orderService.findList(order);
		//移除已缴费内容
		for(Order o:orders) {
			for(Iterator<PaymentEntity> it = list.iterator();it.hasNext();){
				if(it.next().getId().equals(o.getPayId())) {
					 it.remove();  
				}
	        }
		}
		
		model.addAttribute("list", list);
		return "modules/payment/paymentIndex";
	}
	
	@RequestMapping("pay.html")
	public String pay(String[] ids, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		User user = UserUtils.getUser();
		Dict dict = new Dict();
		dict.setType("payment_type");
		List<Dict> dicts = dictService.findList(dict);
		List<String> idsList = Arrays.asList(ids);

		List<Order> orders = new ArrayList<Order>();
		BigDecimal amount = BigDecimal.ZERO;
		StringBuilder sb = new StringBuilder();
		StringBuilder stringBuilder = new StringBuilder();
		
		String outTradeno = "T".concat(systemService.getSequence("serialNo14"));
		
		for(Dict d:dicts) {
			if(idsList.contains(d.getId())) {
				
				String payRemark = d.getDescription();
				stringBuilder.append(payRemark);
				stringBuilder.append("|");
				Order order = new Order();
				String orderId = "O".concat(systemService.getSequence("serialNo14"));
				order.setId(orderId);
				order.setPayId(d.getId());
				order.setPayTitle(d.getLabel());
				order.setPayRemark(payRemark);
				order.setPayAmount(d.getValue());
				order.setPayTime(new Date());
				order.setStatus(GlobalConstants.TRADESTATUS_PAY);
				order.setUser(user);
				orders.add(order);
				sb.append(d.getLabel());
				sb.append(",");
				BigDecimal paymentAmount=new BigDecimal(d.getValue());
				amount = amount.add(paymentAmount);
			}
		}
		String detail = stringBuilder.toString();
		if(detail.length()>0) {
			detail = detail.substring(0,detail.length()-1);
		}
		String payAmount = amount.toString();
		Traderecord traderecord = new Traderecord();
		traderecord.setStartTime(new Date());
		traderecord.setId(outTradeno);
		traderecord.setUser(user);
		traderecord.setIdCard(user.getLoginName());
		traderecord.setUserIp(IPUtil.getIpAddr(request));
		traderecord.setOrders(orders);
		traderecord.setDetail(detail);
		traderecord.setPayAmount(payAmount);
		String subject = sb.toString();
		if(StringUtils.isEmpty(subject)) {
			subject="哈尔滨信息工程学院,";
		}
		if(subject.length()>20) {
			subject = subject.substring(0,20);
		}else {
			subject=subject.substring(0,subject.length()-1);
		}
		traderecord.setSubject(subject);
		String key = UUID.randomUUID().toString();
		JedisUtils.setObject(key, traderecord, 1000*60*30);
		model.addAttribute("k", key);
		return "modules/payment/paymentPay";
	}
	
	@RequestMapping("qrcode")
	public void qrcode(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String key = request.getParameter("k");
		String data = qrCodeUrl + "payment/qrcode?k="+key;//"http://zhaojunfei.tunnel.qydev.com/
		BufferedImage image = QRCodeKit.createQRCodeWithLogo(data);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ImageIO.write(image, "png", servletOutputStream);
	}
	
	@RequestMapping("msg")
	@ResponseBody
	public String msg(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String key = request.getParameter("k");
		Traderecord traderecord = (Traderecord)JedisUtils.getObject(key);
		return traderecord.getPayAmount();
	}
}
