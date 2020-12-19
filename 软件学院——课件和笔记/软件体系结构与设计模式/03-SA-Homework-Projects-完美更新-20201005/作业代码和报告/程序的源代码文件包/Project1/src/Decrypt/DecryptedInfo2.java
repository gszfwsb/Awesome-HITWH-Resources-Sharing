package Decrypt;

import Util.TxtFileWriter;

import java.util.*;
import java.lang.*;
import javax.swing.*;

public class DecryptedInfo2 implements Decryption{
    private TxtFileWriter writer = new TxtFileWriter();
    public static final String GROUP = "Group-swap";
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
        vector = writer.logMsgg(GROUP);
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
    public String encryptName(String name){
        char[] nameArr = name.toCharArray();
        char[] evenCharArr = getEvenCharArray();
        char[] oddCharArr = getOddCharArray();
        char[] evenUpperCharArr = getEvenUpperCharArray();
        char[] oddUpperCharArr = getOddUpperCharArray();
        int length = nameArr.length;
        int len = 13;

        for (int i = 0; i < length; i++){
            for (int j=0; j<len; j++){
                if(nameArr[i] == evenCharArr[j] )
                    nameArr[i] = oddCharArr[j];
                else if(nameArr[i] == oddCharArr[j] )
                    nameArr[i] = evenCharArr[j];
                else if(nameArr[i] == evenUpperCharArr[j] )
                    nameArr[i] = oddUpperCharArr[j];
                else if(nameArr[i] == oddUpperCharArr[j] )
                    nameArr[i] = evenUpperCharArr[j];
            }
        }
        return new String(nameArr);
    }

    public String encryptCode (String code) {
        char[] codeCharArr = code.toCharArray();
        char[] evenCharArr = getEvenCharArray();
        char[] oddCharArr = getOddCharArray();
        char[] evenUpperCharArr = getEvenUpperCharArray();
        char[] oddUpperCharArr = getOddUpperCharArray();
        char[] enenNumArr = getEvenNumArray();
        char[] oddNumArr = getOddNumArray();
        int length = codeCharArr.length; //length of code
        int len = 13; // half of the length of array of 26 English character arrays
        int numLen = 5; //half of the length of array of 10 digital numbers

        if(length != 12){
            System.out.println("Incorrect code length.");
        }
        else{
            for (int i = 0; i < length; i++){
                for (int j=0; j<len; j++){
                    if(codeCharArr[i] == evenCharArr[j] )
                        codeCharArr[i] = oddCharArr[j];
                    else if(codeCharArr[i] == oddCharArr[j] )
                        codeCharArr[i] = evenCharArr[j];
                    else if(codeCharArr[i] == evenUpperCharArr[j] )
                        codeCharArr[i] = oddUpperCharArr[j];
                    else if(codeCharArr[i] == oddUpperCharArr[j] )
                        codeCharArr[i] = evenUpperCharArr[j];
                }
                for (int j=0; j<numLen; j++){
                    if(codeCharArr[i] == enenNumArr[j] )
                        codeCharArr[i] = oddNumArr[j];
                    else if(codeCharArr[i] == oddNumArr[j] )
                        codeCharArr[i] = enenNumArr[j];
                }
            }
        }
        return new String(codeCharArr);
    }

    private char[] getEvenCharArray(){
        String str = "acegikmoqsuwy";
        char[] chArray = str.toCharArray();
        return chArray;
    }
    private char[] getOddCharArray(){
        String str = "bdfhjlnprtvxz";
        char[] chArray = str.toCharArray();
        return chArray;
    }
    private char[] getEvenUpperCharArray(){
        String str = "ACEGIKMOQSUWY";
        char[] chArray = str.toCharArray();
        return chArray;
    }
    private char[] getOddUpperCharArray(){
        String str = "BDFHJLNPRTVXZ";
        char[] chArray = str.toCharArray();
        return chArray;
    }
    private char[] getEvenNumArray(){
        String str = "02468";
        char[] chArray = str.toCharArray();
        return chArray;
    }
    private char[] getOddNumArray(){
        String str = "13579";
        char[] chArray = str.toCharArray();
        return chArray;
    }
}
