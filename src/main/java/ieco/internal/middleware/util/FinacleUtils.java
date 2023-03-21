package ieco.internal.middleware.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class FinacleUtils {

	private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

	public static String encrypt(String message, String key)
			throws GeneralSecurityException, UnsupportedEncodingException {
		if (message == null || key == null) {
			throw new IllegalArgumentException("text to be encrypted and key should not be null");
		}
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		byte[] messageArr = message.getBytes();
		byte[] keyparam = key.getBytes();
		SecretKeySpec keySpec = new SecretKeySpec(keyparam, "AES");
		byte[] ivParams = new byte[16];
		byte[] encoded = new byte[messageArr.length + 16];
		System.arraycopy(ivParams, 0, encoded, 0, 16);
		System.arraycopy(messageArr, 0, encoded, 16, messageArr.length);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivParams));
		byte[] encryptedBytes = cipher.doFinal(encoded);
		encryptedBytes = Base64.getEncoder().encode(encryptedBytes);
		return new String(encryptedBytes);
	}

	public static String decrypt(String encryptedStr, String key)
			throws GeneralSecurityException, UnsupportedEncodingException {
		if (encryptedStr == null || key == null) {
			throw new IllegalArgumentException("text to be decrypted and key should not be null");
		}
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		byte[] keyparam = key.getBytes();
		SecretKeySpec keySpec = new SecretKeySpec(keyparam, "AES");
		byte[] encoded = encryptedStr.getBytes();
		encoded = Base64.getDecoder().decode(encoded);
		byte[] decodedEncrypted = new byte[encoded.length - 16];
		System.arraycopy(encoded, 16, decodedEncrypted, 0, encoded.length - 16);
		byte[] ivParams = new byte[16];
		System.arraycopy(encoded, 0, ivParams, 0, ivParams.length);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivParams));
		byte[] decryptedBytes = cipher.doFinal(decodedEncrypted);
		return new String(decryptedBytes);
	}

	public static String encrypt1(String message, String key)
			throws GeneralSecurityException, UnsupportedEncodingException {
		if (message == null || key == null) {
			throw new IllegalArgumentException("text to be encrypted and key should not be null");
		}
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		byte[] messageArr = message.getBytes();
		SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
		byte[] ivParams = new byte[16];
		byte[] encoded = new byte[messageArr.length + 16];
		System.arraycopy(ivParams, 0, encoded, 0, 16);
		System.arraycopy(messageArr, 0, encoded, 16, messageArr.length);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivParams));
		byte[] encryptedBytes = cipher.doFinal(encoded);
		encryptedBytes = Base64.getEncoder().encode(encryptedBytes);
		return new String(encryptedBytes);
	}

	public static String decrypt1(String encryptedStr, String key)
			throws GeneralSecurityException, UnsupportedEncodingException {
		if (encryptedStr == null || key == null) {
			throw new IllegalArgumentException("text to be decrypted and key should not be null");
		}
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
		byte[] encoded = encryptedStr.getBytes();
		encoded = Base64.getDecoder().decode(encoded);
		byte[] decodedEncrypted = new byte[encoded.length - 16];
		System.arraycopy(encoded, 16, decodedEncrypted, 0, encoded.length - 16);
		byte[] ivParams = new byte[16];
		System.arraycopy(encoded, 0, ivParams, 0, ivParams.length);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivParams));
		byte[] decryptedBytes = cipher.doFinal(decodedEncrypted);
		return new String(decryptedBytes);
	}

	public static void main(String[] args) throws GeneralSecurityException, IOException {
        String encrypted = "v/QYSewL2nC1QQP19DOB9Fuj/f3WCPVR95nFzxrU+nlf2chf0sJ49OY4p6Ggz1Mnu6WBfPZW0Mq/wkJ785TRZC0JoOVx904r2qVHUwPOiNqiEBxQe3VUjv38ocY0dJXJQiqRN7ufOwVN1o8OzjpWJsT9lLZRTu28RM/iYDiuM0L6KlCkK7nH/9Mlz9f0YYUJLagN1UpFNVnGh/wBgWh7H6jYfzbgiC6kHZ8Mxegn1RHKPW5hYzlqIKJrz5Y6b8qokI1pMklCrTgkbdop830x7GRLCoGRxmtQswOFL6hxo+gYiTzfp+Ku3SWZinZjQSWGNJ3h0kbIfwuvJubv/sx8zvj2y3yU2XnzV48J/OrQNNdYJz7CUAfV/+LGyhf/MvnDh3Sxro42nsELHvSf1r5cFihqfdU7kGyIdbgv0NBYeSE48KAiwAfeiZQix2Gb4zVpk4Stfy4bBqBp4ig/kvL/N2f1qgMHLxygLNTs4+5Ymkiz/GniCIfDyqnOT4Col/vA";
        System.out.println(decrypt(encrypted,"a97d90a4cf744e7c95993bbb1849420e"));
       String singleline="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><kmblRoot><Input><MTI>CVV2GENREQ</MTI><CRN>4594532220000034</CRN><Dateandtimelocaltransaction>40920201547</Dateandtimelocaltransaction><RRN>24815155929</RRN><Expirydate>1224</Expirydate><ChannelID>MB</ChannelID></Input></kmblRoot>";
               
       System.out.println("singleline->"+encrypt(singleline,"a97d90a4cf744e7c95993bbb1849420e"));
       String actualRequest ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
               "<kmblRoot>\r\n" + 
               "    <Input>\r\n" + 
               "        <MTI>CVV2GENREQ</MTI>\r\n" + 
               "        <CRN>4594532220000034</CRN>\r\n" + 
               "        <Dateandtimelocaltransaction>40920201547</Dateandtimelocaltransaction>\r\n" + 
               "        <RRN>24815155929</RRN>\r\n" + 
               "        <Expirydate>1224</Expirydate>\r\n" + 
               "        <ChannelID>MB</ChannelID>\r\n" + 
               "    </Input>\r\n" + 
               "</kmblRoot>\r\n" + 
               "";
       System.out.println("ogRequest->"+encrypt(actualRequest,"a97d90a4cf744e7c95993bbb1849420e"));
        
        
	}
}
