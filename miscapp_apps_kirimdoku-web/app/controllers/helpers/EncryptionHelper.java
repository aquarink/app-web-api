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
}
