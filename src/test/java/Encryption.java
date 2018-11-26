


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Encryption {
	
	private final static String $aic_id = "taketourstest";	
	private final static String $secret_key = "102d63d1cKD5d6f03361#5a2H60434";
	private final static String $url = "https://testopenapi.aichotels-service.com";
	
	public static String generateSignature(String method, String path_only, String date, String secret) {
	
		String[] urlArray = path_only.split("[?]",1000);
		if(urlArray.length > 0){
			path_only = urlArray[0];
		}
		StringBuilder sign = new StringBuilder();
		sign.append(method);
		sign.append(" ");
		sign.append(path_only);
		sign.append("\n");
		sign.append(date);
		
		
		byte[] sha1 = hmac_sha1(sign.toString(), secret);
		String signature;
		try {
			signature = new String(Base64.encodeBase64(sha1), "UTF-8");
			System.out.println(signature);
			return signature;
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	
	

	private static byte[] hmac_sha1(String value, String key) {
		try {
			byte[] keyBytes = key.getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);
			return mac.doFinal(value.getBytes());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String post(String path_only,String json) {
		String date = getDate();
		
		String token = generateSignature("POST",path_only,date,$secret_key);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("APIClientKey", $aic_id);
		headers.set("APIClientToken", token);
		headers.set("DateTime", date);
		RestTemplate restTemplate = new RestTemplate();
                HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> result = restTemplate.postForEntity($url.concat(path_only), entity, String.class);
		return result.getBody();
	}
	
	public static String get(String path_only,String json) {
		String date = getDate();
		String token = generateSignature("GET",path_only,date,$secret_key);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("APIClientKey", $aic_id);
		headers.set("APIClientToken", token);
		headers.set("DateTime", date);
		RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> result = restTemplate.postForEntity($url.concat(path_only), entity, String.class);
		
		
		return result.getBody();
	}
	
	public static void main(String[] agrs) {
		String path_only = "/rate/public/ping";
		
		String json = "{\"message\":\"Connect Successfully.恭喜连接成功！\"}";
		//String json = "{\"hotel_ids\":[],\"latitude\":\"40.70958800\",\"longitude\":\"-74.18888100\",\"radius\":10,\"number\":100}";
		String str = post(path_only,json);
		System.out.println(str);
	}
	public static void main1(String[] agrs) {
		String path_only = "/content/public/multi_hotels?locale=en_US";
		
		//String json = "{\"message\":\"Connect Successfully.恭喜连接成功！\"}";
		String json = "{\"hotel_ids\":[],\"latitude\":\"40.70958800\",\"longitude\":\"-74.18888100\",\"radius\":10,\"number\":100}";
		String str = post(path_only,json);
		System.out.println(str);
	}
	
	private static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE,d MMM Y HH:mm:ss 'GMT'",Locale.UK);
		Date date = new Date();
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(calendar.HOUR,-8);
		date=calendar.getTime();
		return  sdf.format(date);
	}
}