package Encrypt;

import javax.swing.*;

public interface Encryption {
    public abstract String encryptName(String inputStr);
    public abstract String encryptCode (String code);
    public abstract void log(String lastNm, String firstNm, String code, JTextArea txt);
}