import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Basic symmetric encryption example
 */
public class MainClass {
  public static void main(String[] args) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());        
    byte[] input = " www.java2s.com ".getBytes();
    byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
        0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };

    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
    System.out.println("input text : " + new String(input));

    // encryption pass

    byte[] cipherText = new byte[input.length];
    cipher.init(Cipher.ENCRYPT_MODE, key);
    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
    ctLength += cipher.doFinal(cipherText, ctLength);
    System.out.println("cipher text: " + new String(cipherText) + " bytes: " + ctLength);

    // decryption pass

    byte[] plainText = new byte[ctLength];
    cipher.init(Cipher.DECRYPT_MODE, key);
    int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
    ptLength += cipher.doFinal(plainText, ptLength);
    System.out.println("plain text : " + new String(plainText) + " bytes: " + ptLength);
  }
}
