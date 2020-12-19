package filter;
import java.io.IOException;
//import java.io.CharArrayWriter;
import java.lang.Character;

import pipe.*;
public class EncrFilter3 extends Filter {

   public EncrFilter3(Pipe input, Pipe output){
      super(input, output);
   }

   /*-------------------------------------------------------
   /* Read from the input pipe, character by character.
   /* Encryption Algorithm 3 (Caesar cipher, rotation 1):
   /* Precondition: a text file to be encrypted contains only English
   /* characters and digital numbers. The algorithm is: for the 26
   /* English characters, you replace the letter a with b, b with c,
   /* and so on, up to z, which is replaced by a. When a digital number
   /* between 0 and 9 is encountered, the rule to rotate is:
   /*        0-->1, 1-->2,��,8-->9 and 9-->0.
   /*        a-->b, b-->c; c-->d, d-->e; e-->f, ��,y-->z, z-->a
   /* Upper case letters are also encrypted the same way (Upper case letters
   /* are encrypted into Upper case letters)
   /* All encrypted chars should be written to the sink pipe char by char.
   /*------------------------------------------------------*/
   protected void processData(){

      try{
         int c = inPipe.read(); // Read a character from the source pipe

         while(c != -1){ //not end of file
            char f = (char)c;
			//char e = encrypt(f, 1); // to encrypt a character; shift 1 position
			f = encrypt(f);
            outPipe.write(f);  // Write character 'e' to the sink pipe
            c = inPipe.read();
         }
         outPipe.closePipedOutputStream();
         inPipe.closePipedInputStream();
      }
      catch(IOException exc){
         exc.printStackTrace();
         System.err.println("Error: failed encryption 3");
         System.exit(1);
      }
   }

   private char encrypt(char c) {
      System.out.println("\n original char: "+ c);
      char[] digitArr = getDigitArray();
      char[] alphabet = getAlphabetArray();

      for(int n = 0; n < 26; n++){
         if ( c == alphabet[n]){
            c = alphabet[(n+1)%26];
            break;
         }
         else if ( c == Character.toUpperCase(alphabet[n]) ){
            c = Character.toUpperCase(alphabet[(n+1)%26]);
            break;
         }
      }
      for(int j = 0; j < 10; j++){
         if ( c == digitArr[j]){
            c = digitArr[(j+1)%9];
            break;
         }
      }
      System.out.println("Encrypted char: " + c);
      return c;
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


