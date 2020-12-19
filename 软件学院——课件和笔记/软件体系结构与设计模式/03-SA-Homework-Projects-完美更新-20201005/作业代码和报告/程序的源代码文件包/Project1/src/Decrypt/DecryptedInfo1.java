package Decrypt;

import Util.TxtFileWriter;

import java.util.*;
import java.lang.*;
import javax.swing.*;

public class DecryptedInfo1 implements Decryption {
    private TxtFileWriter writer = new TxtFileWriter();
    public static final String FOLDING = "Folding";
    @Override
    public String decryptName(String inputStr) {
        return null;
    }
    @Override
    public String decryptCode(String code) {
        return null;
    }

    @Override
    public void log(JTextArea txt) {
        txt.append("DecryptCode Result: \n");
        Vector<String> vector = new Vector<String>();
        vector = writer.logMsgg(FOLDING);
        String[] array = vector.toArray(new String[vector.size()]);
        int i;
        for (i =0 ;i<array.length;i++){
                if (!array[i].equals("")){
                    String temp = array[i];
                    String[] strarr = temp.split(" ");
                    strarr[0] = encryptName(strarr[0]);
                    txt.append(strarr[0]+" ");
                    strarr[1] = encryptName(strarr[1]);
                    txt.append(strarr[1]+" ");
                    strarr[2] = encryptCode(strarr[2]);
                    txt.append(strarr[2]+'\n');
                }
        }
        txt.append("\n");
    }
    public String encryptName(String msg){
        char[] alphabet = getAlphabetArray();
        char[] chars = msg.toCharArray();
        for (int m = 0; m < chars.length; m++){
            for(int n = 0; n < 26; n++){
                if ( chars[m] == alphabet[n]){
                    chars[m] = alphabet[25-n];
                    break;
                }
                else if ( chars[m] == Character.toUpperCase(alphabet[n])){
                    chars[m] = Character.toUpperCase(alphabet[25-n]);
                    break;
                }
            }
        }
        return new String(chars);
    }

    public String encryptCode (String code) {
        char[] digitArr = getDigitArray();
        char[] codeChars = code.toCharArray();
        char[] alphabet = getAlphabetArray();

        if(codeChars.length != 12){
            System.out.println("Incorrect code length.");
        }
        else{
            for (int m = 0; m < codeChars.length; m++){
                for(int n = 0; n < 26; n++){
                    if ( codeChars[m] == alphabet[n]){
                        codeChars[m] = alphabet[25-n];
                        break;
                    }
                    else if ( codeChars[m] == Character.toUpperCase(alphabet[n]) ){
                        codeChars[m] = Character.toUpperCase(alphabet[25-n]);
                        break;
                    }
                }

                for(int j = 0; j < 10; j++){
                    if ( codeChars[m] == digitArr[j]){
                        codeChars[m] = digitArr[9-j];
                        break;
                    }
                }
            }
        }
        return new String(codeChars);
    }

    private char[] getAlphabetArray(){
        String str = "abcdefghijklmnopqrstuvwxyz";
        char[] chArray = str.toCharArray();
        return chArray;
    }

    private char[] getDigitArray(){
        String str = "0123456789";
        char[] chArray = str.toCharArray();
        return chArray;
    }
}
