package doku.kirimdoku.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.User;
import org.codehaus.jackson.JsonNode;
import controllers.helpers.EncryptionHelper;
import controllers.helpers.KirimDokuHttpConnection;
import controllers.tokens.AgentToken;
import play.Configuration;
import play.Logger;
import play.libs.Json;

public class CommonUtil {
	
	public static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfDateFileName = new SimpleDateFormat("ddMMyyyy");
	public static SimpleDateFormat sdfDateTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static final Configuration CONFIG_API = play.Play.application().configuration().getConfig("api");
	public static final String URL_API_INQUIRY_BATCH = CONFIG_API.getString("base_url") + "/cashin/inquirybatch";
	public static final int INQUIRY_TIMEOUT_API = 75000;
	public static final String URL_API_REMIT_BATCH = CONFIG_API.getString("base_url") + "/cashin/remitbatch";
	public static final int REMIT_TIMEOUT_API = 75000;
	public static final String UPLOAD_PATH = play.Play.application().configuration().getConfig("upload").getString("path.batch");
	
	public static Boolean pathCSV(String PathFile) {
        try {
            File cekDir = new File(PathFile);
            if (!cekDir.exists() || !cekDir.isDirectory()) {
                cekDir.mkdir();
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
	
	public static boolean validString(String input, String regexPattern) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[a-zA-Z0-9.\\-\\/+=_ ]*$";

            if (regexPattern != null && !regexPattern.trim().equals("")) {
                INPUT_PATTERN = regexPattern;
            }

            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validAN(String input, String regexPattern, int min, int max) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[a-zA-Z0-9]{"+min+","+max+"}$";

            if (regexPattern != null && !regexPattern.trim().equals("")) {
                INPUT_PATTERN = regexPattern;
            }

            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	public static boolean validNumeric(String input, String regexPattern, int min, int max) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9]{"+min+","+max+"}$";

            if (regexPattern != null && !regexPattern.trim().equals("")) {
                INPUT_PATTERN = regexPattern;
            }

            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validMoney(String input, String regexPattern) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[\\d]{1,12}|[.][\\d]{1,6}*$";

            if (regexPattern != null && !regexPattern.trim().equals("")) {
                INPUT_PATTERN = regexPattern;
            }

            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validPhone(String input, String regexPattern) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9.-+ ]*$";

            if (regexPattern != null && !regexPattern.trim().equals("")) {
                INPUT_PATTERN = regexPattern;
            }

            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	
	
	/* TODO VALIDATE PARAMETER KIRIMDOKU */
	
	public static boolean validChannelCode(String input, Map<String, String> channelCodeList) {
		boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9]{2}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && channelCodeList.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validCountryCode(String input, Map<String, String> countryCodeList) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[A-Z]{2}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && countryCodeList.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validCurrencyCode(String input, Map<String, String> currencyCodeList) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[A-Z]{3}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && currencyCodeList.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validBankId(String input, Map<String, String> bankIdList) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9]{1,3}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && bankIdList.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validBankSwiftCode(String input, Map<String, String> bankSwiftCodeList) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[A-Z0-9]{1,12}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && bankSwiftCodeList.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validBankAccountNumber(String input) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9]{1,50}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validDokuWalletId(String input) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9]{1,50}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validAmount(String input) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9.]{1,16}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validSendType(String input, Map<String, Integer> map) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^(S|B){1}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && map.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validPersonalIdType(String input, Map<String, String> map) {
		boolean status = false;
        try {
            String INPUT_PATTERN = "^[A-Z]*$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches() && map.containsKey(input);
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validCustomerId(String input) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^(CID)[0-9]{7}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validName(String input) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[a-zA-Z. 0-9]{1,64}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validPhoneNumber(String input) {
        boolean status = false;
        try {
            String INPUT_PATTERN = "^[0-9-+() ]{1,20}$";
            Pattern pattern = Pattern.compile(INPUT_PATTERN);
            Matcher matcher = pattern.matcher(input);
            status = matcher.matches();
        } catch (Exception e) {
            
        }
        return status;
    }
	
	public static boolean validDate(String input) {
        boolean status = false;
        try {
        	sdfDate.setLenient(false);
        	sdfDate.parse(input);
        	status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
	}
	
	public static boolean validDateFileName(String input) {
        boolean status = false;
        try {
        	sdfDateFileName.setLenient(false);
        	sdfDateFileName.parse(input);
        	status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
	}
	
	public static String postHttp(String parameter, User user, String url, int timeOut) {
		try {
			//HTTP URL CONNECTION
			KirimDokuHttpConnection connection = httpConnection(parameter, url, timeOut, "application/json", user);
			Logger.debug("Http Response Code     : "+connection.responseCode);
            if (connection.responseCode == 200) {
                Logger.debug("Result                 : "+connection.responseMessage);
    			JsonNode resultJson = Json.parse(connection.responseMessage);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+resultJson);
    			return connection.responseMessage;
            } else {
            	Logger.debug("Http Response Code "+connection.responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static KirimDokuHttpConnection httpConnection(String parameter, String urls, int timeout, String contentType, User user) {
		KirimDokuHttpConnection result = new KirimDokuHttpConnection();
		try {
			//HTTP URL CONNECTION
            Logger.debug("--------------------------------------------------");
            Logger.debug("URL                    : "+urls);
            Logger.debug("Connection Timeout     : "+timeout);
            Logger.debug("==================================================");
			URL url = new URL(urls);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.addRequestProperty("Content-Type", contentType != null ? contentType : "application/json");
			
			AgentToken agentToken = AgentToken.fromUser(user);
			String requestId = UUID.randomUUID().toString();
			String signature;
			signature = EncryptionHelper.encrypt(agentToken.toString()+requestId, user.corporate.encryptionKey);
			connection.addRequestProperty("agentKey", agentToken.toString());
			connection.addRequestProperty("requestId", requestId);
			connection.addRequestProperty("signature", signature);
			
			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(parameter);
            wr.flush();
			String hasil = "";
			Logger.debug("Http Response Code     : "+connection.getResponseCode());
			result.responseCode = connection.getResponseCode();
            if (connection.getResponseCode() == 200) {

                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    hasil = hasil + line;
                }
                
                Logger.debug("Result                 : "+hasil);
    			Logger.debug("--------------------------------------------------");
    			Logger.debug("Fund Transfer transaction Post result (json) "+hasil);
    			connection.disconnect();
    			result.responseMessage = hasil;
    			result.status = true;
    			return result;
            } else {
            	Logger.debug("Http Response Code "+connection.getResponseCode());
			}
		} catch (ConnectException e) {
			e.printStackTrace();
			Logger.debug("Failed - Connection refused");
			result.responseMessage = "Failed - Connection refused";
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			Logger.debug("Failed - Read timed out");
			result.responseMessage = "Failed - Connection refused";
		} catch (Exception e) {
			e.printStackTrace();
			result.responseMessage = "Failed - "+e.getMessage();
		}
		
		return result;
	}
	
}
