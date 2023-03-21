package ieco.internal.middleware.util;


import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Component
public class AESCBCPKCS5Encryption {

  private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
  public static String encrypt(String message, String key) throws GeneralSecurityException, UnsupportedEncodingException {
    if(message == null || key == null){
      throw new IllegalArgumentException("text to be encrypted and key should not be null");
    }
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    byte[] messageArr = message.getBytes();
    byte[] keyparam=key.getBytes();
    SecretKeySpec keySpec = new SecretKeySpec(keyparam, "AES");
    byte[] ivParams = new byte[16];
    byte[] encoded = new byte[messageArr.length + 16];
    System.arraycopy(ivParams,0,encoded,0,16);
    System.arraycopy(messageArr, 0, encoded, 16, messageArr.length);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivParams));
    byte[] encryptedBytes = cipher.doFinal(encoded);
    encryptedBytes = Base64.getEncoder().encode(encryptedBytes);
    return new String(encryptedBytes);
  }
  public static String decrypt(String encryptedStr, String key) throws GeneralSecurityException, UnsupportedEncodingException {
    if(encryptedStr == null || key == null){
      throw new IllegalArgumentException("text to be decrypted and key should not be null");
    }
   Cipher cipher = Cipher.getInstance(ALGORITHM);
   byte[] keyparam=key.getBytes();
    SecretKeySpec keySpec = new SecretKeySpec(keyparam, "AES");
    byte[] encoded = encryptedStr.getBytes();
    encoded = Base64.getDecoder().decode(encoded);
    byte[] decodedEncrypted = new byte[encoded.length-16];
    System.arraycopy(encoded, 16, decodedEncrypted, 0,encoded.length-16);
    byte[] ivParams = new byte[16];
    System.arraycopy(encoded,0, ivParams,0, ivParams.length);
    cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivParams));
    byte[] decryptedBytes = cipher.doFinal(decodedEncrypted);
    return new String(decryptedBytes);
  }

  public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException {
    String str ="{\"Login_ID\": \"ZOHO\",\"Login_Pwd\": \"Wk9ITzEyMw==\",\"Client_IP\": \"\",\"Channel_ID\": \"\"}";
    String key ="6143a9c607a441979dbf5d6f841d20f4";
    String enc = encrypt(str, key);
    System.out.println(enc);
    /*String dec = decrypt("IiJIj6Mxfk6CCAkvB8k1ngZ35HlvQl9mjMAxZBKjKCcsepEyOU44/OCojX+WY2X6xgnCXMggrFizbmxJSgf9ww5gKApDXs/BA0xMLJE5KfpnHp0SSXcnFqn8TqWIJX0+PQ/EAyuPXas3ClQ+ouyehWsn5psJlK4QsHeA3/gVrNEHwoW+o0qTbcSB7MDFIl6U7B7ZrEZV7zReUOpUwkk82YuNKMc+04bpMtZxPw0M9kjTj+mjKHRMKlpdJpF4RyYg2waYy4LysrqzY81l647BimfR5BlKOTPororkefYbSWxW7lSmqRAVgA59Whx3z7i/tSyqjDwemsyVtfOwOl1om3Q7dV9wywFUh/5MovOuWqdB2pgVk2rX1PHgemFl3jj2jYhVaQtZiT24xAUO6L5DALn/LjI6QHkYCvnpXWg6Htp+JkzP4UzXYgsLKVoK5rUH7Bl1PVJ2ssd+tsv++Y5SCuob08zAXD0IdcTlGB09cSKpiyvNw3MkSJmWaKrfvUiu/3V3zldwlbPYt5N/WgqE7H98Y6bfcCyTFBqprjgxrog=", key);

System.out.println(dec);*/
  }
}
