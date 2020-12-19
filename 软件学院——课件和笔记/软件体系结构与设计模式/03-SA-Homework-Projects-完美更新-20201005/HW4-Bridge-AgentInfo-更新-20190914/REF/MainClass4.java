import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Example of using PBE with a PBEParameterSpec
 */
public class MainClass4 {
  public static void main(String[] args) throws Exception {
    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());        
    byte[] input = "www.java2s.com".getBytes();
    byte[] keyBytes = new byte[] { 0x73, 0x2f, 0x2d, 0x33, (byte) 0xc8, 0x01, 0x73, 0x2b, 0x72,
        0x06, 0x75, 0x6c, (byte) 0xbd, 0x44, (byte) 0xf9, (byte) 0xc1, (byte) 0xc1, 0x03,
        (byte) 0xdd, (byte) 0xd9, 0x7c, 0x7c, (byte) 0xbe, (byte) 0x8e };
    byte[] ivBytes = new byte[] { (byte) 0xb0, 0x7b, (byte) 0xf5, 0x22, (byte) 0xc8, (byte) 0xd6,
        0x08, (byte) 0xb8 };

    // encrypt the data using precalculated keys

    Cipher cEnc = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC");

    cEnc.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "DESede"), new IvParameterSpec(
        ivBytes));

    byte[] out = cEnc.doFinal(input);

    // decrypt the data using PBE

    char[] password = "password".toCharArray();
    byte[] salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02, (byte) 0xe9, (byte) 0xe0, (byte) 0xae };
    int iterationCount = 2048;
    PBEKeySpec pbeSpec = new PBEKeySpec(password);
    SecretKeyFactory keyFact = SecretKeyFactory.getInstance("PBEWithSHAAnd3KeyTripleDES", "BC");

    Cipher cDec = Cipher.getInstance("PBEWithSHAAnd3KeyTripleDES", "BC");
    Key sKey = keyFact.generateSecret(pbeSpec);

    cDec.init(Cipher.DECRYPT_MODE, sKey, new PBEParameterSpec(salt, iterationCount));

    System.out.println("cipher : " + new String(out));
    System.out.println("gen key: " + new String(sKey.getEncoded()));
    System.out.println("gen iv : " + new String(cDec.getIV()));
    System.out.println("plain  : " + new String(cDec.doFinal(out)));
  }
}
