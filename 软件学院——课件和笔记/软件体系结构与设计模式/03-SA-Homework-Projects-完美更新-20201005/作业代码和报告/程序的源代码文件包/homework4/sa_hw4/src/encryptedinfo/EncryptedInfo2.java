package encryptedinfo;
/*---------------------------------------------------------------------------------------------------------------*/
/* This is a subclass to implement interface Encryption.
/* a) This class keeps a reference logger of  MessageWriter.
/* b) This logger is used to call logMsg in class MessageLogger
/* c) The object is created from the ClientGUI file, like, logger = new FileLogger();
/*     Then logger is passed into THIS class TextMessage by Message
/*                         msg = new EncryptedMessage(logger);
/* d) Agent names must be formed by English characters, and no other forms are allowed;
/*     Agent Code can be formed by English characters mixed with digital numbers. THe length
/*     of Agent code must be exactly 12
/*  Encryption Algorithm:
/*  a-->b, b-->a; c-->d, d-->c; e-->f, f-->e;, ..., y-->z, z-->y
/*  0-->1, 1-->0; 2-->3, 3-->2; ...8-->9, 9-->8
/*----------------------------------------------------------------------------------------------------------------*/
import java.util.*;
import messagewriter.*;
import clientGui.*;
public class EncryptedInfo2 extends AgentInfo {
  private MessageWriter writer;
  private char[] chars;
  private char[][] coupledLowerCaseChar = new char[13][2];
  private char[][] coupledUpperCaseChar = new char[13][2];

  public EncryptedInfo2(MessageWriter w) {
       writer = w;
       coupledLowerCaseChar = createCoupledCharArr("lowerCase");
       coupledUpperCaseChar = createCoupledCharArr("upperCase");
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
  private String encryptName(String inputStr){

       chars = inputStr.toCharArray();
       int length = chars.length;

       for (int i = 0; i <  length; i++) {
		   char c = shiftChar(chars[i]);
		   chars[i] = c;
       }
       return new String(chars);
  }

   //This method can be used to encrypt the agent code
   private String encryptCode (String code) {

       char[ ] codeChars = code.toCharArray();

	   if(codeChars.length != 12){
	       System.out.println("Incorrect code length.");
	   }
	   else{
	       for (int m = 0; m < codeChars.length; m++){
			   if(Character.isLetter(codeChars[m]) == true){
			 		char c = shiftChar(codeChars[m]);
			 		codeChars[m] = c;
		       }
		       else if (Character.isDigit(codeChars[m]) == true){
					char c = shiftNum(codeChars[m]);
					codeChars[m] = c;
	           }
	     }
	   }
	   return new String(codeChars);
   }
   //create a special char array to encapsulate all the lower case english alphabets
   //or the upper case english alphabets
   private char[][] createCoupledCharArr(String style){
	    String lowerStr = "abcdefghijklmnopqrstuvwxyz";
	    String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    char[] lowerCharArr = lowerStr.toCharArray();
	    char[] upperCharArr = upperStr.toCharArray();
	    char[][] coupledCharArr = new char[13][2];

	    if(style.equals("lowerCase") ) {
	    	for(int m=0; m<13; m++){
				coupledCharArr[m][0] = lowerCharArr[2*m];
				coupledCharArr[m][1] = lowerCharArr[2*m+1];
			}
	    }
	    if(style.equals("upperCase") ) {
			for(int m=0; m<13; m++){
				coupledCharArr[m][0] = upperCharArr[2*m];
				coupledCharArr[m][1] = upperCharArr[2*m+1];
			}
	    }
	    return coupledCharArr;
   }
  //Shift a character according to rule a<-->b, b-->a; c-->d, d-->c;...y-->z, z-->y
  private char shiftChar(char aChar){
	    char inChar = aChar;
        char outChar = 'a';

	    if(Character.isLowerCase(inChar)==true){
	    	for(int m=0; m<13; m++){
				if( coupledLowerCaseChar[m][0] == inChar)
				    outChar = coupledLowerCaseChar[m][1];
				else if(coupledLowerCaseChar[m][1] == inChar )
				    outChar = coupledLowerCaseChar[m][0];
			}
		}
		if(Character.isUpperCase(inChar)==true){
			for(int m=0; m<13; m++){
				if( coupledUpperCaseChar[m][0] == inChar)
					outChar = coupledUpperCaseChar[m][1];
				else if(coupledUpperCaseChar[m][1] == inChar )
					outChar = coupledUpperCaseChar[m][0];
			}
		}
		return outChar;
   }
   //shif a char type number based on the rule 0-->1, 1-->0; 2-->3, 3-->2; ...8-->9, 9-->8
   private char shiftNum(char intputNum){
        char[][] num = new char[5][2];
        num[0] [0]= '0';
        num[0] [1]= '1';
        num[1] [0]= '2';
        num[1] [1]= '3';
        num[2] [0]= '4';
        num[2] [1]= '5';
        num[3] [0]= '6';
        num[3] [1]= '7';
        num[4] [0]= '8';
        num[4] [1]= '9';

        char shiftedNum = '1';
        for(int m=0; m<5; m++){
          if( intputNum == num[m][0] )
              shiftedNum = num[m][1];
          else if( intputNum == num[m][1] )
              shiftedNum = num[m][0];
   	 }
   	 return shiftedNum;
   }
}
