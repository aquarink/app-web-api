package controllers.helpers;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptionHelper {
	private static final String ALGORITHM = "AES";

	public static String encrypt(String valueToEnc) throws Exception {
		return encrypt(valueToEnc, play.Play.application().configuration().getString("application.secret").substring(0, 16));
	}

	public static String decrypt(String valueToEnc) throws Exception {
		return decrypt(valueToEnc, play.Play.application().configuration().getString("application.secret").substring(0, 16));
	}

	public static String encrypt(String valueToEnc, String encKey) throws Exception {
		Key key = new SecretKeySpec(encKey.getBytes(), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		return new String(Base64.encodeBase64(encValue));
	}

	public static String decrypt(String encryptedValue, String encKey) throws Exception {
		Key key = new SecretKeySpec(encKey.getBytes(), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedValue.getBytes());
		byte[] decValue = c.doFinal(decordedValue);
		return new String(decValue);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("SIGNATURE : "+encrypt("A40043"+"e456c688-d915-1004-8997-ed635d04aba1","4mgibmc2pdjei1bd"));
	}
	

	public static String encryptHex(String valueToEnc) throws Exception {
		return encryptHex(valueToEnc, play.Play.application().configuration().getString("application.secret").substring(0, 16));
	}

	public static String decryptHex(String valueToEnc) throws Exception {
		return decryptHex(valueToEnc, play.Play.application().configuration().getString("application.secret").substring(0, 16));
	}

	public static String encryptHex(String valueToEnc, String encKey) throws Exception {
		Key key = new SecretKeySpec(encKey.getBytes(), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		return convertToHex(Base64.encodeBase64(encValue));
	}

	public static String decryptHex(String encryptedValue, String encKey) throws Exception {
		Key key = new SecretKeySpec(encKey.getBytes(), ALGORITHM);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedValue.getBytes());
		byte[] decValue = c.doFinal(decordedValue);
		return convertToHex(decValue);
	}
	
	public static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
	
}
