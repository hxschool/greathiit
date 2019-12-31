package com.thinkgem.jeesite.modules.score.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.exception.GITException;
import com.thinkgem.jeesite.common.utils.DESUtil;
import com.thinkgem.jeesite.common.utils.IPUtil;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.QRCodeKit;
import com.thinkgem.jeesite.modules.pay.GlobalConstants;
import com.thinkgem.jeesite.modules.pay.enums.PayType;
import com.thinkgem.jeesite.modules.pay.strategy.StrategyContext;
import com.thinkgem.jeesite.modules.payment.entity.SysPayment;
import com.thinkgem.jeesite.modules.payment.entity.order.Order;
import com.thinkgem.jeesite.modules.payment.entity.trade.Traderecord;
import com.thinkgem.jeesite.modules.payment.service.SysPaymentService;
import com.thinkgem.jeesite.modules.payment.service.trade.TraderecordService;
import com.thinkgem.jeesite.modules.score.service.ScoreService;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

//https://www.chsi.com.cn/xlrz/report_gdjycjd.jsp
@Controller
@RequestMapping(value = { "score", "chengji" })
public class ScoreController {
	private static Logger logger = LoggerFactory.getLogger(ScoreController.class);
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private SysPaymentService sysPaymentService;
	@Autowired
	private TraderecordService traderecordService;
	@Value("${payment.qrcode.url}")
	private String qrCodeUrl;

	@RequestMapping(value = { "login", "" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			Model model) {

		return "modules/chengji/login";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			Model model) {
		request.getSession().setAttribute("user",null);
		return "redirect:/chengji/logout";
	}
	
	@RequestMapping("get")
	public void get(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(StringUtils.isEmpty(user)) {
			request.getRequestDispatcher("/chengji").forward(request, response);
		}
		String studentNumber = null;
		try {
			studentNumber = DESUtil.decrypt(user.getNo(), "A1B2C3D4E5F60708");
		}catch(Exception e) {
			throw new GITException("500","加密异常请求参数异常,请重试");
		}
		
		if(StringUtils.isEmpty(studentNumber)) {
			throw new GITException("404","请求参数异常,请重试");
		}
		session.setAttribute("userCode", "0");
		List<Order> orders = new ArrayList<Order>();
		String outTradeno = "T".concat(systemService.getSequence("serialNo14"));		
		SysPayment payment = sysPaymentService.get("3");
		Order order = new Order();
		String orderId = "O".concat(systemService.getSequence("serialNo14"));
		order.setId(orderId);
		order.setPayId(payment.getId());
		order.setPayTitle(payment.getTitle());
		order.setPayRemark(payment.getDescription());
		order.setPayAmount(payment.getAmount());
		order.setPayTime(new Date());
		order.setUser(user);
		order.setStatus(GlobalConstants.TRADESTATUS_PAY);
		orders.add(order);
		BigDecimal paymentAmount = new BigDecimal(payment.getAmount()).multiply(new BigDecimal(100));
		Traderecord traderecord = new Traderecord();
		traderecord.setStartTime(new Date());
		traderecord.setId(outTradeno);
		traderecord.setUserIp(IPUtil.getIpAddr(request));
		traderecord.setOrders(orders);
		traderecord.setDetail(payment.getDescription());
		traderecord.setPayAmount(paymentAmount.longValue());
		traderecord.setUser(user);
		traderecord.setSubject("哈尔滨信息工程学院-成绩单");
		String key = UUID.randomUUID().toString();
		JedisUtils.setObject(key, traderecord, 1000*60*30);
		String data = qrCodeUrl + "scan/check?k="+key;//"http://zhaojunfei.tunnel.qydev.com/
		BufferedImage image = QRCodeKit.createQRCodeWithLogo(data);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ImageIO.write(image, "png", servletOutputStream);
	}

	@RequestMapping("msg")
	@ResponseBody
	public Map<String,String> msg( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String userCode = (String)request.getSession().getAttribute("userCode");
		if(StringUtils.isEmpty(userCode)) {
			userCode = "0";
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", "00000000");
		map.put("msg", "获取成功");
		map.put("result", userCode);
		return map;
	}

	@RequestMapping("check")
	public String check( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		request.getSession().setAttribute("userCode","1");
		
		Map<String, Object> params = new HashMap<String, Object>();
		String ua = request.getHeader("User-Agent");
		
		String key = request.getParameter("k");
		if(StringUtils.isEmpty(key)) {
			throw new GITException("404","请求参数异常,请重试");
		}
		Traderecord traderecord = (Traderecord) JedisUtils.getObject(key);
		if(StringUtils.isEmpty(traderecord)) {
			throw new GITException("500","请求参数异常,请重试");
		}
		PayType payType = null;
		if (StringUtils.isEmpty(ua)) {
			throw new GITException("403","非法请求");
		} else {
			BigDecimal amount = new BigDecimal(traderecord.getPayAmount()).divide(new BigDecimal(100));
			if (ua.contains("Alipay")) {
				payType = PayType.ALIPAY_APP;
				params.put("outTradeNo", traderecord.getId());
				params.put("totalAmount", amount);
				params.put("subject", traderecord.getSubject());
				
			} else if (ua.contains("MicroMessenger")) {
				payType = PayType.WECHAT_APP;
				
				params.put("out_trade_no",traderecord.getId());
				params.put("total_fee", amount);
				params.put("body", traderecord.getSubject());
				params.put("spbill_create_ip", IPUtil.getIpAddr(request));
			}
		}
		
		traderecord.setChannel(String.valueOf(payType.value()));
		traderecordService.insertTraderecord(traderecord);
		StrategyContext strategyContext = new StrategyContext();
		String location = strategyContext.generatePayParams(payType, params);
		
		if(!StringUtils.isEmpty(location)) {
			return "redirect:".concat(location);
		}
		
		return "modules/payment/paymentError";
		
	}

	@RequestMapping(value = "view")
	public String login(String username, String idcard, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isEmpty(user)||StringUtils.isEmpty(user.getNo())) {
			if (org.springframework.util.StringUtils.isEmpty(username)
					|| org.springframework.util.StringUtils.isEmpty(idcard)) {
				model.addAttribute("message", "姓名或者身份证号为空");
				return "modules/chengji/login";
			}
			user = systemService.getCasByLoginName(idcard);
			if (StringUtils.isEmpty(user)||!user.getName().equals(com.thinkgem.jeesite.common.utils.StringUtils.trim(username))) {
				model.addAttribute("message", "用户信息不合法,请输入正确的身份证号和姓名");
				return "modules/chengji/login";
			}
		}
		request.getSession().setAttribute("user", user);
		logger.info("获取登录用户信息:{}",user);
		
		String studentNumber = user.getNo();
		String userinfo = request.getSession().getServletContext().getRealPath("userinfo");
		File file = new File(userinfo, studentNumber);
		if (file.exists()) {
			String[] chengjis = file.list();
			List<String> lcs = new ArrayList<String>();
			for (String cjs : chengjis) {
				lcs.add(studentNumber + "/" + cjs);
			}
			model.addAttribute("chengjis", lcs);
		}
		StudentCourse studentCourse = new StudentCourse();
		Student student = new Student();
		student.setStudentNumber(studentNumber);
		studentCourse.setStudent(student);
		List<String> termYears = studentCourseService.groupTermYear(studentCourse);
		Map<String, List<StudentCourse>> scs = new HashMap<String, List<StudentCourse>>();

		for (String termYear : termYears) {
			Student st = new Student();
			StudentCourse sc = new StudentCourse();
			st.setStudentNumber(studentNumber);
			sc.setStudent(st);
			sc.setTermYear(termYear);
			scs.put(termYear, studentCourseService.findByParentIdsLike(sc));
		}
		model.addAttribute("scs", scs);
		model.addAttribute("studentNumber", studentNumber);

		return "modules/chengji/chengji";
	}

	@RequestMapping(value = "print")
	@ResponseBody
	public String print(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			Model model) throws IOException {
		User user = UserUtils.getUser();
		String userinfo = request.getSession().getServletContext().getRealPath("userinfo");
		if (user.isAdmin()) {
			String op = request.getParameter("op");
			if (!StringUtils.isEmpty(op)) {
				if (op.equals("ALL")) {
					List<User> users = systemService.findAllList(new User());
					for (User u : users) {
						if (!StringUtils.isEmpty(u) && !StringUtils.isEmpty(u.getLoginName())
								&& u.getLoginName().length() == 18) {
							scoreService.write(u, userinfo);
						}

					}
					return "all";
				}
			}

		}

		String st = request.getParameter("st");
		if (!StringUtils.isEmpty(st)) {
			User u = systemService.getCasByLoginName(st);
			if (!StringUtils.isEmpty(u)) {
				scoreService.write(u, userinfo);
				return "student";
			}
		}

		scoreService.write(user, userinfo);

		return "ok";
	}

}
