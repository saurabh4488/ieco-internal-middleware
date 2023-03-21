package ieco.internal.middleware.util;

import ieco.internal.middleware.domain.response.AbstractResponse;
import ieco.internal.middleware.enums.ResponseCodeEnum;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricEncryptionUtil extends AbstractResponse {

	private static Cipher rsa;
	static String encryptResult;
	static String decryptResult;

	/**
	 * @return cipher instance
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
		if (rsa == null) {
			rsa = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
		}
		return rsa;
	}

	/**
	 * @param msg
	 * @param key
	 * @return encrypt string
	 */
	public static String encryptText(String msg, String key) {

		try {
			rsa = AsymmetricEncryptionUtil.getCipherInstance();
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
			KeyFactory keyFactory = KeyFactory.getInstance(DirectEquityConstants.RA);
			PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			encryptResult = Base64.getEncoder().encodeToString(rsa.doFinal(msg.getBytes(StandardCharsets.UTF_8)));

		} catch (Exception e) {
			responseError(DirectEquityConstants.ENCRYPTION_ERROR, ResponseCodeEnum.ENCRYPTION_ERROR);
		}

		return encryptResult;

	}

	/**
	 * @param msg
	 * @param key
	 * @return decrypt string
	 */
	public static String decryptText(String msg, PublicKey key) {

		try {
			rsa = AsymmetricEncryptionUtil.getCipherInstance();
			rsa.init(Cipher.DECRYPT_MODE, key);
			decryptResult = new String(rsa.doFinal(Base64.getDecoder().decode(msg)), StandardCharsets.UTF_8);

		} catch (Exception e) {
			responseError(DirectEquityConstants.DECRYPTION_ERROR, ResponseCodeEnum.DECRYPTION_ERROR);
		}
		return decryptResult;
	}

	private AsymmetricEncryptionUtil() {

	}
}