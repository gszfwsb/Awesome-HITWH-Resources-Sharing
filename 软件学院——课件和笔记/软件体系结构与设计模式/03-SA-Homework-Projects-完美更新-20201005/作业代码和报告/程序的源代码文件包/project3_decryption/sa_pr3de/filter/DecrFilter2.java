package filter;

import java.io.IOException;
import java.lang.Character;

import pipe.Pipe;


public class DecrFilter2 extends Filter{

   public DecrFilter2(Pipe input, Pipe output){
      super(input, output);
   }

   /*-------------------------------------------------------
   /* Read from the input pipe, character by character.
   /* Encrypt the chars by using Encryption Algorithm 2 (·Ö×é»¥»»Ëã·¨).
   /* Precondition: a text file to be encrypted contains only
   /* English characters and digital numbers. The algorithm is:
   /*       a<-->b, c<-->d, e<-->f, ,..., y<-->z
   /*       0<-->1, 2<-->3, ...8<-->9
   /* Upper case letters are also encrypted the same way
   /* (Upper case letters are encrypted into Upper case letters) .
   /* The encrypted chars are written to the sink pipe char by char
   /*------------------------------------------------------*/
   protected void processData(){

      try{
         int c = inPipe.read(); // Read a character from the source pipe

         while(c != -1){ //not end of file
            char f = (char)c;
			char e = decrypt(f); // to encrypt a character
			outPipe.write(e);  // Write character 'e' to the sink pipe
            c = inPipe.read();
         }
         outPipe.closePipedOutputStream();
         inPipe.closePipedInputStream();
      }
      catch(IOException exc){
         exc.printStackTrace();
         System.err.println("Error: failed encryption 2");
         System.exit(1);
      }
   }
   
   private char decrypt(char c) {
	   System.out.println("\n2 original char: "+ c);
	   if(Character.isLowerCase(c)){
		   c = shiftChar('a', c);
	   }
	   else if (Character.isUpperCase(c)) {
		   c = shiftChar('A', c);
	   }
	   else if (Character.isDigit(c)){
		   c = shiftChar('0', c);
	   }
	   System.out.println("2Decrypted char: " + c);
	   return c;
   }
   
   private char shiftChar(char base, char c) {
	   int a = c - base;
	   a += a%2==1 ? -1 : 1;
	   c = (char)(a + base);
	   return c;
   }
}


