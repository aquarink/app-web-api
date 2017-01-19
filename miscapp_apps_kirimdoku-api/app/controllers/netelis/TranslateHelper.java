package controllers.netelis;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import play.libs.WS;
//import play.libs.F.Promise;
//import play.libs.WS.Response;
import play.Logger;

public class TranslateHelper {
	
	//dev
	private final String URL_TRANSLATE = "https://www.cashtochina.com/ctcDemo/translate2Chi.do";
	//local
	//private final String URL_TRANSLATE = "https://localhost/netelis/translate.php";
	//prod
	//private final String URL_TRANSLATE = "https://www.cashtochina.com/ctc/translate2Chi.do";
	private final int TIMEOUT_TRANSLATE = 70000;
	private static final char[] digits = "0123456789abcdef".toCharArray();
	
	public String doTranslate(String codeVal, String method) {
		try {
			String secureCodePattern = "{0}&{1}&{2}&{3}&{4}&{5}";
//			String companyId = "5039";
//			String username = "Warindo";
//			String securePwd = "warindo1";
			
			String companyId = "1000";
			String username = "testshop";
			String securePwd = "secure112233";
			
			String hashSecurePwd = "";
			try {
				hashSecurePwd = toHexMd5(securePwd).toLowerCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String ts=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			//String method = "PINYIN";
			//String codeVal = "a";
			
			String secureCode="";
			try {
				secureCode = toHexMd5(MessageFormat.format(secureCodePattern, new Object[] { companyId, username, method, codeVal, ts, hashSecurePwd}));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String paramPost = "";
			paramPost += "companyId="+companyId+"&";
			paramPost += "username="+username+"&";
			paramPost += "method="+method+"&";
			paramPost += "codeVal="+codeVal+"&";
			paramPost += "ts="+ts+"&";
			paramPost += "secureCode="+secureCode;
			
			System.out.println("Request : "+paramPost.toString());
			
	//		Promise<Response> result = WS.url(URL_TRANSLATE)
	//				.setHeader("Content-Type", "application/x-www-form-urlencoded")
	//				.setTimeout(TIMEOUT_TRANSLATE)
	//				.post(paramPost.toString());
			
			//HTTP URL CONNECTION
			URL url = new URL(URL_TRANSLATE);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(TIMEOUT_TRANSLATE);
			connection.setReadTimeout(TIMEOUT_TRANSLATE);
			connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        wr.write(paramPost);
	        wr.flush();
			String hasil = "";
	        if (connection.getResponseCode() == 200) {
	
	            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            String line;
	            while ((line = rd.readLine()) != null) {
	                hasil = hasil + line;
	            }
	            connection.disconnect();
	            Logger.debug("Result : "+hasil);
				return hasil;
	        }else{
	        	connection.disconnect();
	        	return "";
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// Method to generate MD5 Hash in HEX format
	public static String toHexMd5(String msg) throws Exception {
		return bytesToHexStr(MessageDigest.getInstance("md5").digest(
				msg.getBytes("utf-8")));
	}

	// Convert bytearray to HEX format
	public static String bytesToHexStr(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int b = bytes[i];
			if (b < 0)
				b = 256 + b;
			buffer.append(digits[b / 16]);
			buffer.append(digits[b % 16]);
		}
		return buffer.toString();
	}

	// Read inputstream as String in UTF-8 encoding
	public static String readInputStream(InputStream is) throws Exception {
		try {
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] bs = new byte[4096];
			int count = 0;
			while ((count = is.read(bs)) > 0) {
				out.write(bs, 0, count);
			}
			return new String(out.toByteArray(),"utf-8");
		} finally {
			is.close();
		}
	}
	
}
