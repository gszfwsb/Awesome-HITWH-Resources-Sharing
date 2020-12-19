package filter;
import java.io.IOException;

import pipe.Pipe;


public class DecrFilter3 extends Filter {

   public DecrFilter3(Pipe input, Pipe output){
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
   /*        0-->1, 1-->2,бн,8-->9 and 9-->0.
   /*        a-->b, b-->c; c-->d, d-->e; e-->f, бн,y-->z, z-->a
   /* Upper case letters are also encrypted the same way (Upper case letters
   /* are encrypted into Upper case letters)
   /* All encrypted chars should be written to the sink pipe char by char.
   /*------------------------------------------------------*/
   protected void processData(){

      try{
         int c = inPipe.read(); // Read a character from the source pipe

         while(c != -1){ //not end of file
            char f = (char)c;
			char e = decrypt(f); // to encrypt a character; shift 1 position
			outPipe.write(e);  // Write character 'e' to the sink pipe
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
   
   private char decrypt(char c) {
	   System.out.println("\n3 original char: "+ c);
	   if (Character.isLetter(c) || Character.isDigit(c)) {
		   switch(c) {
			   case 'a': c='z'; break;
			   case 'A': c='Z'; break;
			   case '0': c='9'; break;
			   default : c-=1;
		   }
	   }
	   System.out.println("3Decrypted char: " + c);
	   return c;
   }
}


