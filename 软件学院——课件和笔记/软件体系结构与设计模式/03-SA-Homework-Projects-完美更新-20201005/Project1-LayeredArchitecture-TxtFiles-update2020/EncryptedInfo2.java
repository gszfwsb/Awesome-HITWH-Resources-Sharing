
/*-------------------------------------------------*/
/* This is a subclass to implement interface       */
/* Encryption.                                     */
/* a) This class keeps a reference logger of       */
/*    MessageWriter.                               */
/* b) This logger is used to call logMsg in        */
/*    class MessageLogger                          */
/* c) The object is created from the Client        */
/*    file, like, logger = new FileLogger();       */
/*    Then logger is passed into THIS class        */
/*    TextMessage by                               */
/*    Message msg = new EncryptedMessage(logger);  */
/*-------------------------------------------------*/

import java.util.*;
import javax.swing.*;

public class EncryptedInfo2 implements Encryption{

  public static final String GROUPSWAP = "Group-swap";
  private TxtFileWriter writer = new TxtFileWriter();

  public void log(String lastNm, String firstNm, String code, JTextArea txt){
      String lName = encryptName(lastNm);
      String fName = encryptName(firstNm);
      String codeStr = encryptCode(code);
      txt.append("Encrypted agent first name: "+ fName +"\n");
      txt.append("Encrypted agent last name: "+ lName +"\n");
      txt.append("Encrypted agent code: "+ codeStr +"\n\n");

      writer.logMsg(GROUPSWAP, lName, fName, codeStr);
      txt.append("Data has been saved into file "+ GROUPSWAP +".txt"+"\n\n");
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
