package com.thinkgem.jeesite.modules.score.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdcardUtils;
import com.thinkgem.jeesite.modules.student.entity.Student;
import com.thinkgem.jeesite.modules.student.entity.StudentCourse;
import com.thinkgem.jeesite.modules.student.service.StudentCourseService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
//https://www.chsi.com.cn/xlrz/report_gdjycjd.jsp
@Controller
@RequestMapping(value = {"score", "chengji"})
public class ScoreController {
	@Autowired
	private StudentCourseService studentCourseService;
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value = {"login", ""},method=RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		
		return "modules/chengji/login";
	}
	
	@RequestMapping(value = {"login", ""},method=RequestMethod.POST)
	public String login(String username,String idcard, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) {
		
		if(org.springframework.util.StringUtils.isEmpty(username)||org.springframework.util.StringUtils.isEmpty(idcard)) {
			model.addAttribute("message", "姓名或者身份证号为空");
			return "modules/chengji/login";
		}
		User user = systemService.getCasByLoginName(idcard);
		String studentNumber = user.getNo();
		String userinfo = request.getSession().getServletContext().getRealPath("userinfo");
		File file = new File(userinfo,studentNumber);
		if(file.exists()) {
			String[] chengjis = file.list();
			List<String> lcs = new ArrayList<String>();
			for(String cjs : chengjis) {
				lcs.add( studentNumber + "/" +cjs);
			}
			model.addAttribute("chengjis", lcs);
		}
		StudentCourse studentCourse = new StudentCourse();
		Student student = new Student();
		student.setStudentNumber(studentNumber);
		studentCourse.setStudent(student);
		List<String> termYears = studentCourseService.groupTermYear(studentCourse);
		Map<String,List<StudentCourse>> scs = new HashMap<String,List<StudentCourse>>();
		
		for(String termYear : termYears) {
			Student st = new Student();
			StudentCourse sc = new StudentCourse();
			st.setStudentNumber(studentNumber);
			sc.setStudent(st);
			sc.setTermYear(termYear);
			scs.put(termYear, studentCourseService.findList(sc));
		}
		model.addAttribute("scs", scs);
		
		return "modules/chengji/chengji";
	}
	
	
	
	@RequestMapping(value ="print")
	public String print(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model) throws IOException {
		User user = UserUtils.getUser();
		String userinfo = request.getSession().getServletContext().getRealPath("userinfo");
		String studentNumber = user.getNo();
		File file = new File(userinfo,studentNumber);
		if(!file.exists()) {
			file.mkdirs();
		}
		StudentCourse studentCourse = new StudentCourse();
		Student student = new Student();
		student.setStudentNumber(studentNumber);
		studentCourse.setStudent(student);
		List<String> termYears = studentCourseService.groupTermYear(studentCourse);
		List<String> defaults = new ArrayList<String>();
		List<String> others = new ArrayList<String>();
		
		for (int i = 0; i < termYears.size(); i++) {
			String termYear = termYears.get(i);
			if (i < 2) {
				defaults.add(termYear);
			} else {
				others.add(termYear);
			}
		}
		BufferedImage image =  ImageIO.read(ScoreController.class.getResource("/chengji_default.png"));
		Color color = Color.BLACK;
		Font font = new Font("宋体",Font.PLAIN,12);
		image = draw(image, color, font, 152, 810, user + "-" + studentNumber);
		
		//姓名
		image = draw(image, color, font, 155, 235,user.getName());
		//编号
		image = draw(image, color, font, 480, 235, user.getNo());
		//性别
		image = draw(image, color, font, 155, 260, IdcardUtils.getGender(user.getLoginName()));
		//打印日期
		image = draw(image, color, font, 480, 260, DateUtils.getDate());
		//身份证号
		image = draw(image, color, font, 155, 285, user.getLoginName());
		//报告页码
		image = draw(image, color, font, 480, 285, "1/2");
		
		image = draw(image, color, font, 155, 308, "哈尔滨信息工程学院");
		String xl = "高职/专科";
		if(studentNumber.length()==10 || studentNumber.length()==12 ) {
			xl = "本科";
		}
		image = draw(image, color, font, 155, 332,xl);
		String xy = "未设置学院信息";
		Office company = user.getCompany();
		if(!org.springframework.util.StringUtils.isEmpty(company)) {
			xy=company.getName();
		}
		String zy = "未设置专业信息";
		Office office = user.getCompany();
		if(!org.springframework.util.StringUtils.isEmpty(office)) {
			zy=office.getName();
		}
		
		image = draw(image, color, font, 155, 355, xy+ "-" + zy);
		image = draw(image, color, font, 155, 380, "3.0");
		int defaultIndex = 0;
		for(String termYear : defaults) {
			StudentCourse sc = new StudentCourse();
			if(defaultIndex==0) {
				image = draw(image, color, font, 88, 462,termYear);
				Student st = new Student();
				st.setStudentNumber(studentNumber);
				sc.setStudent(st);
				sc.setTermYear(termYear);
				List<StudentCourse> scs = studentCourseService.findList(sc);
				int y1 = 482;
				for(StudentCourse isc:scs) {
					String courseName = ellipsis(isc.getCourse().getCursName(),10);
						image = draw(image, color, font, 88, y1,courseName);
						image = draw(image, color, font, 252, y1,isc.getEvaValue());
						image = draw(image, color, font, 292, y1,isc.getPoint());
						y1 = y1 + 18;
				}
			}else {
				image = draw(image, color, font, 335, 462,termYear);
				Student st = new Student();
				st.setStudentNumber(studentNumber);
				sc.setStudent(st);
				sc.setTermYear(termYear);
				List<StudentCourse> scs = studentCourseService.findList(sc);
				int y1 = 482;
				for(StudentCourse isc:scs) {
						String courseName = ellipsis(isc.getCourse().getCursName(),10);
						image = draw(image, color, font, 335, y1,courseName);
						image = draw(image, color, font, 498, y1,isc.getEvaValue());
						image = draw(image, color, font, 548, y1,isc.getPoint());
						y1 = y1 + 18;
				}
			}
			defaultIndex++;
		}
		
		FileOutputStream out=new FileOutputStream(new File(file,studentNumber + "_default.jpg"));
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image); 
		param.setQuality(100, true);  //
		encoder.encode(image, param); 
		out.close(); 
		
		
		BufferedImage img =  ImageIO.read(ScoreController.class.getResource("/chengji_other.png"));

		img = draw(img, color, font, 152, 810,studentNumber);
		boolean ret = true;
		int x1 = 210;
		int x2 = 210;
		int y1 = 228;
		int y2 = 228;
		for(String termYear : others) {
			
			StudentCourse sc = new StudentCourse();
			if(ret) {
				img = draw(img, color, font, 88, x1,termYear);
				Student st = new Student();
				st.setStudentNumber(studentNumber);
				sc.setStudent(st);
				sc.setTermYear(termYear);
				List<StudentCourse> scs = studentCourseService.findList(sc);
				
				for(StudentCourse isc:scs) {
					String courseName = ellipsis(isc.getCourse().getCursName(),10);
					img = draw(img, color, font, 88, y1,courseName);
					img = draw(img, color, font, 252, y1,isc.getEvaValue());
					img = draw(img, color, font, 292, y1,isc.getPoint());
						y1 = y1 + 18;
				}
				y1 = y1+18;
				x1 = y1-18;
			}else {
				img = draw(img, color, font, 335, x2,termYear);
				Student st = new Student();
				st.setStudentNumber(studentNumber);
				sc.setStudent(st);
				sc.setTermYear(termYear);
				List<StudentCourse> scs = studentCourseService.findList(sc);
				
				for(StudentCourse isc:scs) {
					String courseName = ellipsis(isc.getCourse().getCursName(),10);
					img = draw(img, color, font, 335, y2,courseName);
					img = draw(img, color, font, 498, y2,isc.getEvaValue());
					img = draw(img, color, font, 548, y2,isc.getPoint());
						y2 = y2 + 18;
				}
				y2 = y2+18;
				x2 = y2-18;
			}
			ret=!ret;
		}
		
		FileOutputStream otherFos=new FileOutputStream(new File(file,studentNumber + "_other.jpg"));
		JPEGImageEncoder otherEncoder = JPEGCodec.createJPEGEncoder(otherFos); 
		JPEGEncodeParam otherParam = otherEncoder.getDefaultJPEGEncodeParam(img); 
		otherParam.setQuality(100, true);  //
		otherEncoder.encode(img, otherParam); 
		otherFos.close(); 
		
		return "";
	}
	
	private String ellipsis(String str, int len) {
		if(org.springframework.util.StringUtils.isEmpty(str)) {
			return "";
		}
		if (str.length() > len) {
			return str.substring(0, len).concat("...");
		} else {
			return str;
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedImage image =  ImageIO.read(new File("D:/chengji.png"));
			Color color = Color.BLACK;
			Font font = new Font("宋体",Font.PLAIN,12);
			//姓名
			image = draw(image, color, font, 155, 235, "赵俊飞");
			//编号
			image = draw(image, color, font, 480, 235, "20190328");
			//性别
			image = draw(image, color, font, 155, 260, "男");
			//打印日期
			image = draw(image, color, font, 480, 260, "20190328");
			//身份证号
			image = draw(image, color, font, 155, 285, "230302198402175312");
			//报告页码
			image = draw(image, color, font, 480, 285, "1/2");
			
			image = draw(image, color, font, 155, 308, "哈尔滨信息工程学院");
			image = draw(image, color, font, 155, 332, "本科");
			image = draw(image, color, font, 155, 355, "软件工程");
			image = draw(image, color, font, 155, 380, "3.0");
			
			
			image = draw(image, color, font, 88, 462,"2018-2019-01");
			image = draw(image, color, font, 88, 482,StringUtils.rightPad("人际沟通与交往礼仪*",19," ") + StringUtils.rightPad("中",5," ") + "1.5");
			image = draw(image, color, font, 88, 502,StringUtils.rightPad("人际沟通与交往礼仪*",19," ") + StringUtils.rightPad("中",5," ") + "1.5");
			
			image = draw(image, color, font, 335, 462,"2018-2019-02");
			image = draw(image, color, font, 335, 482,StringUtils.rightPad("人际沟通与交往礼仪*",19," ") + StringUtils.rightPad("中",5," ") + "1.5");
			image = draw(image, color, font, 335, 502,StringUtils.rightPad("人际沟通与交往礼仪*",19," ") + StringUtils.rightPad("中",5," ") + "1.5");
			
			FileOutputStream out=new FileOutputStream("D:/chengji1.jpg"); //先用一个特定的输出文件名 
			JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out); 
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image); 
			param.setQuality(100, true);  //
			encoder.encode(image, param); 
			out.close(); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		int[] arr1 ={1,2,3,4,5};
		int[] dest = {};
		System.arraycopy(arr1, 0, dest, 2, 2);
		for(int i:dest) {
			System.out.println(i);
		}
	}
	
	public static BufferedImage draw(BufferedImage image,Color color,Font font,int w,int h,String content) {
		int height = image.getHeight();
		int width = image.getWidth();
		BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB); 
		Graphics2D g = bufferedImage.createGraphics();
        g.setColor(color); 
		g.drawImage(image, 0, 0, null ); 
		g.setFont(font);
		g.drawString(content,w,h);
		g.dispose(); 
		return bufferedImage;
	}
}
