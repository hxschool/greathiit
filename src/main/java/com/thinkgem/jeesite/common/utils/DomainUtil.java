package com.thinkgem.jeesite.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainUtil {

	// 一级域名提取
	private static final String RE_TOP1 = "(\\w*\\.?){1}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

	// 二级域名提取
	private static final String RE_TOP2 = "(\\w*\\.?){2}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";

	// 三级域名提取
	private static final String RE_TOP3 = "(\\w*\\.?){3}\\.(com.cn|net.cn|gov.cn|org\\.nz|org.cn|com|net|org|gov|cc|biz|info|cn|co)$";
	

	public static String getTopDomain(String url) {
		String result = url;
		try {
			Matcher matcher = Pattern.compile(RE_TOP1, Pattern.CASE_INSENSITIVE).matcher(url);
			if(matcher.find()) {
				result = matcher.group();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getSecondDomain(String url) {
		String result = url;
		try {
			Matcher matcher = Pattern.compile(RE_TOP2, Pattern.CASE_INSENSITIVE).matcher(url);
			if(matcher.find()) {
				result = matcher.group();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		// 示例
		String url = "http://www.greathiit.com/123123";
		String res1 = getTopDomain(url);
		System.out.println(url + " ==> " + res1);

		url = "http://www.greathiit.com/123123";
		String res2 = getSecondDomain(url);
		System.out.println(url + " ==> " + res2.substring(0, res2.length()-res1.length()- 1 ));
		
		String bb = getCookieDomain("http://oms.greathiit.com");
		System.out.println("bb:" +  bb);

	}
	 public static String PATTERN_L2DOMAIN = "\\w*\\.\\w*:";
	 public static String PATTERN_IP = "(\\d*\\.){3}\\d*";
	
	public static String getCookieDomain(String url) {
	      /* 以IP形式访问时，返回IP */
	     Pattern ipPattern = Pattern.compile(PATTERN_IP);
	     Matcher matcher = ipPattern.matcher(url);
	     if (matcher.find()) {
	         System.out.println("[HttpUtil][getCookieDomain] match ip.");
	         return matcher.group();
	     }

	     /* 以域名访问时，返回二级域名 */
	     Pattern pattern = Pattern.compile(PATTERN_L2DOMAIN);
	     matcher = pattern.matcher(url);
	     if (matcher.find()) {
	         System.out.println("[HttpUtil][getCookieDomain] match domain.");
	         String domain =  matcher.group();
	         /* 裁剪一下是因为连着冒号也匹配进去了，唉~ */
	         return domain.substring(0, domain.length() - 1);
	     }

	     return null;
	 }
}
