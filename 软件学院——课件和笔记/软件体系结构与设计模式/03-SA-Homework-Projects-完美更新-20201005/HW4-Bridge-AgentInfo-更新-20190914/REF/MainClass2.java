import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Basic symmetric encryption example with CTR using DES
 */
public class MainClass2 {
  public static void main(String[] args) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    byte[] input = "www.java2s.com".getBytes();
    byte[] keyBytes = new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd,
        (byte) 0xef };
    byte[] ivBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01 };

    SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
    IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
    Cipher cipher = Cipher.getInstance("DES/CTR/NoPadding", "BC");
    System.out.println("input : " + new String(input));

    // encryption pass
    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
    int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
    ctLength += cipher.doFinal(cipherText, ctLength);
    System.out.println("cipher: " + new String(cipherText) + " bytes: " + ctLength);

    // decryption pass
    cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    byte[] plainText = new byte[cipher.getOutputSize(ctLength)];
    int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
    ptLength += cipher.doFinal(plainText, ptLength);
    System.out.println("plain : " + new String(plainText) + " bytes: " + ptLength);
  }
}

