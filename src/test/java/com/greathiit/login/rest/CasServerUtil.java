package com.greathiit.login.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

/**
 * 首先客户端提交用户名、密码、及Service三个参数，
 * 如果验证成功便返回用户的TGT(Ticket Granting Ticket)至客户端, 
 * 然后客户端再根据 TGT 获取用户的 ST(Service Ticket)来进行验证登录。 
 * 故名思意，TGT是用于生成一个新的Ticket(ST)的Ticket，
 * 而ST则是提供给客户端用于登录的Ticket，两者最大的区别在于，
 * TGT是用户名密码验证成功之后所生成的Ticket，并且会保存在Server中及Cookie中，
 * 而ST则必须是是根据TGT来生成，主要用于登录，并且当登录成功之后 ST 则会失效。

 * 机能概要:
 */
public class CasServerUtil {

    //登录服务器地址
    private static final String  CAS_SERVER_PATH = "http://login.greathiit.com";

    //登录地址的token 
    private static final String  GET_TOKEN_URL = CAS_SERVER_PATH + "/v1/tickets";

    //目标返回的服务器的url， 同访问的地址必须完全一致，不然就会报错。
    private static final String TAGET_URL = "http://app.greathiit.com/hxci-admin/login.html";

    private static CasServerUtil utils = null;

    private CasServerUtil(){}

    public static CasServerUtil getInstance(){
        if(utils == null){
            synchronized (CasServerUtil.class) {
                if(utils == null){
                    utils = new CasServerUtil();
                }
            }
        }
        return utils;
    }
    /**
      * 机能概要: 先通过用户名密码，
      * 先生成tikect的 token，然后再通过token获取到id
      * @param args
      * @throws Exception
     */
    public static void main(String [] args) throws Exception {


        String username ="";
        String password ="";


        CasServerUtil utils = CasServerUtil.getInstance();

        String st = utils.getSt(username, password);
        System.out.println(st);
        check(st);
    }
    /**
     * 机能概要:通过用户名和密码来获取service ticket,通过这个可以免密码登录
     * @param username
     * @param password
     * @return
     */
    public String getSt(String username,String password){
        //先获取到 token generate ticket
        String tgt = utils.getTGT(username, password);

        if(StringUtils.isEmpty(tgt)){
            return "";
        }

        return utils.getST(tgt);
    }
    /**
     * 机能概要:获取到 （Tokent generate tiker ,token生成票据）tgt
     * @return
     */
    private String getTGT(String username,String password){
        String tgt = "";
        OutputStreamWriter out = null;
        BufferedWriter wirter  = null;
        HttpURLConnection conn = null;
        try {
            //第一步，获取到tgt
            conn = (HttpURLConnection) openConn(GET_TOKEN_URL);
            String param ="username=" + URLEncoder.encode(username, "UTF-8");
            param += "&password" + "=" + URLEncoder.encode(password, "UTF-8");

            out = new OutputStreamWriter(conn.getOutputStream());
            wirter = new BufferedWriter(out);
            //添加参数到目标服务器
            wirter.write(param);
            wirter.flush();
            wirter.close();
            out.close(); 

            //获取token
            tgt = conn.getHeaderField("location");
            //获取返回值
            if (tgt != null && conn.getResponseCode() == 201) {
                  tgt = tgt.substring(tgt.lastIndexOf("/") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn != null){
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tgt;
    }

    /**
     * 机能概要:根据票据生成工具，获取st
     * @param tgt
     * @return
     */
    private String getST(String tgt){
        String serviceTicket = "";
        OutputStreamWriter out = null;
        BufferedWriter wirter  = null;
        HttpURLConnection conn = null;
        try {

            //第一步，获取到tgt
            conn = (HttpURLConnection) openConn(GET_TOKEN_URL+"/"+tgt);

            //需要访问的目标网站
            String param ="service=" + URLEncoder.encode(TAGET_URL, "utf-8");

            out = new OutputStreamWriter(conn.getOutputStream());
            wirter = new BufferedWriter(out);
            //添加参数到目标服务器
            wirter.write(param);
            wirter.flush();
            wirter.close();
            out.close(); 

            //获取返回的ticket票据
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line ="";
              while ((line = in.readLine()) != null) {
                  serviceTicket = line;
              }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn != null){
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceTicket;
    }

    private URLConnection openConn(String urlk) throws Exception {
        URL url = new URL(urlk);
        HttpURLConnection hsu = (HttpURLConnection) url.openConnection();
        hsu.setDoInput(true);
        hsu.setDoOutput(true);
        hsu.setRequestMethod("POST");
        return hsu;
    }
    
    public static String check(String ticket)
			throws Exception {
		
		String serviceValidate = CAS_SERVER_PATH.concat("/serviceValidate?ticket="+ticket+"&service="+TAGET_URL);
		URL url = new URL(serviceValidate);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:26.0) Gecko/20100101 Firefox/26.0");
		connection.setRequestProperty("Content-Type",
				"plain/text; charset=UTF-8");
		connection.setRequestMethod("GET");
		connection.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		// 返回结果
		String line;
		StringBuilder sb = new StringBuilder();
		  while ((line = in.readLine()) != null) {
			  sb.append(line);
		    }
		System.out.println(sb.toString());
		in.close();
		connection.disconnect();
		return sb.toString();
	}
}