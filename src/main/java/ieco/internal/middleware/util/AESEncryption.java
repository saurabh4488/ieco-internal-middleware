package ieco.internal.middleware.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

public class AESEncryption {
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;
    private static final String GIVEN_KEY = "NDCdssAyat96Z8yyTKRAhuDy5750qd6wQbvYem2HaC8";
    public static String encrypt(String text) throws Exception {
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        Key secretKey = new SecretKeySpec(Base64.decodeBase64(GIVEN_KEY), "AES");
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        byte[] cipherText = cipher.doFinal(bytes);
        byte[] finalArray = new byte[cipherText.length + GCM_IV_LENGTH];
        System.arraycopy(iv, 0, finalArray, 0, GCM_IV_LENGTH);
        System.arraycopy(cipherText, 0, finalArray, GCM_IV_LENGTH, cipherText.length);
        return new String(Base64.encodeBase64URLSafe(finalArray), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception
    {
        /* Note that values in query String are URL encoded. */
        String queryString = "method=SendMessage&send_to=919XXXXXXXXX&msg=This%20is%20a%20test%20message&msg_type=TEXT&auth_scheme=plain&password=password&v=1.1&format=text ";
        System.out.println(AESEncryption.encrypt(queryString));
    }
}
