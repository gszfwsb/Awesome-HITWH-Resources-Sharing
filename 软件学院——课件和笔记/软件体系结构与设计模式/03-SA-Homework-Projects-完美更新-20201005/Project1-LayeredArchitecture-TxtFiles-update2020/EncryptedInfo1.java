
/*-------------------------------------------------*/
/* This is a subclass to implement interface       */
/* Mesage.                                         */
/* a) This class keeps a reference logger of       */
/*    MessageLogger.                               */
/* b) This logger is used to call logMsg in        */
/*    class MessageLogger                          */
/* c) The object is created from the Client        */
/*    file, like, logger = new FileLogger();       */
/*    Then logger is passed into THIS class        */
/*    TextMessage by                               */
/*    Message msg = new EncryptedMessage(logger);  */
/* d) The algorithm is that a-->z, b-->y, ...m-->n */
/*    y-->b,z-->a, and uppercase lettet remins     */
/*    unchanged                                    */
/*-------------------------------------------------*/

import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;


public class EncryptedInfo1 implements Encryption{

   private TxtFileWriter writer = new TxtFileWriter();
   public static final String FOLDING = "Folding";

   public void log(String lastNm, String firstNm, String code, JTextArea txt){
      String lName = encryptName(lastNm);
      String fName = encryptName(firstNm);
      String codeStr = encryptCode(code);
      txt.append("Encrypted agent first name: "+ fName +"\n");
      txt.append("Encrypted agent last name: "+ lName +"\n");
      txt.append("Encrypted agent code: "+ codeStr +"\n\n");

      writer.logMsg(FOLDING, lName, fName, codeStr);
      txt.append("Data has been saved into file "+ FOLDING +".txt"+"\n\n");
   }

   public String encryptName(String msg){
	  System.out.println(msg);
	  char[] alphabet = getAlphabetArray();
	  char[] chars = msg.toCharArray();

	  System.out.println("CharLen"+chars.length);

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
