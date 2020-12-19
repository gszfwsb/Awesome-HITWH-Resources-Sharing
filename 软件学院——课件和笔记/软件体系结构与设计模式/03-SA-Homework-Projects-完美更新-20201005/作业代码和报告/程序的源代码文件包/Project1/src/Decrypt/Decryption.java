package Decrypt;

import javax.swing.*;

public interface Decryption {
    public abstract String decryptName(String inputStr);
    public abstract String decryptCode (String code);
    public abstract void log(JTextArea txt);
//    String lastNm, String firstNm, String code, JTextArea txt
}
