
import java.io.IOException;
import java.lang.Character;


public class EncrFilter2 extends Filter{

   public EncrFilter2(Pipe input, Pipe output){
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
			//char e = encrypt(f); // to encrypt a character
			outPipe.write(f);  // Write character 'e' to the sink pipe
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
}


