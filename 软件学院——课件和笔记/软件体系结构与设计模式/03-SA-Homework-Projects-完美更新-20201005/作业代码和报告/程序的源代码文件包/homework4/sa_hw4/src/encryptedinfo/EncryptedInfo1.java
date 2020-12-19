package encryptedinfo;
/*------------------------------------------------------------------------------------------------------------------*/
/* This is a subclass to implement interface Mesage.
/* a) This class keeps a reference logger of  MessageLogger.
/* b) This logger is used to call logMsg in class MessageLogger
/* c) The object is created from the ClientGUI file, like, logger = new FileLogger();
/*     Then logger is passed into THIS class TextMessage by  Message
/*                            msg = new EncryptedMessage(logger);
/* d) Agent names must be formed by English characters, and no other forms are allowed;
/*     Agent Code can be formed by English characters mixed with digital numbers. THe length
/*     of Agent code must be exactly 12
/*  The algorithm for both encrypting agent name and is that a-->z, b-->y, ...m-->n, y-->b,z-->a.
/*  Upper case letters are also shifted the same way. Numbers are shifted the following way:
/*          0-->9, 1-->8, 2-->7, 3-->6, 4-->5, 5-->4, 6-->3, 7-->2, 8-->1, 9-->0
/*-----------------------------------------------------------------------------------------------------------------*/
import java.util.*;
import java.io.*;
import java.lang.*;
import messagewriter.*;
import clientGui.*;
public class EncryptedInfo1 extends AgentInfo{
   private MessageWriter writer;
   public EncryptedInfo1(MessageWriter l){
       writer = l;
   }
   public void log(String lastNm, String firstNm, String code){
	   if( isValidAgentName(lastNm) == false ||  isValidAgentName(firstNm) == false    ){
	   		    System.out.println("Invalid agent name. Only English characters are allowed.");
	   	        System.exit(1);
		}
		if( isValidCode(code) == false){
			    System.out.println("Invalid agent code. Only English characters and numbers are allowed.");
			   	System.exit(1);
		}

      String fName = encryptName(firstNm);
      ClientGUI.resultTxt.append("Encrypted agent first name: "+ fName +"\n");
      String lName = encryptName(lastNm);
      ClientGUI.resultTxt.append("Encrypted agent last name: "+ lName +"\n");

      String codeStr = encryptCode(code);
      ClientGUI.resultTxt.append("Encrypted agent code: "+ codeStr +"\n\n");

      try{
          writer.logMsg(lName, fName, codeStr);
      }
      catch (Exception e) {
	      e.printStackTrace();
      }

   }
  //This method can be used to encrypt the first name or the last name
   private String encryptName(String msg){

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
   //This method can be used to encrypt the agent code
   private String encryptCode (String code) {

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
