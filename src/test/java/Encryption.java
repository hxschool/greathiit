
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

class Encryption {
	public static String generateSignature(String method, String reqUrl, String date, String secret) {
		StringBuilder sign = new StringBuilder();
		sign.append(method);
		sign.append("");
		sign.append(reqUrl);
		sign.append("\n");
		sign.append(date);
		byte[] sha1 = hmac_sha1(sign.toString(), secret);
		String signature;
		try {
			signature = new String(Base64.encodeBase64(sha1), "UTF-8");
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
	
	public static void main(String[] agrs) {
		String method="POST";
		String reqUrl="/multiplesupplier/public/search/currency_rate";
		String date= String.valueOf(new Date());
		System.out.println(date);
		String secret="qinggongyeschool";
		String str = generateSignature(method, reqUrl, date, secret);
		System.out.println(str);
		//
		
	}
}