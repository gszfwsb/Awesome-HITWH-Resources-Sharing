import java.io.IOException;
import java.lang.Character;


public class EncrFilter1 extends Filter {

   public EncrFilter1(Pipe input, Pipe output){
      super(input, output);
   }

   /*-------------------------------------------------------
   /* Read from the source pipe, character by character.
   /* And then encrypt the chars by using the Encryption Algorithm 1 (ÕÛµþËã·¨).
   /* Precondition: a text file to be encrypted contains only English
   /* characters and digital numbers. The algorithm is:
   /*      a<-->z, b<-->y, ...m<-->n, y<-->b, z<-->a
   /*      0<-->9, 1<-->8, 2<-->7, 3<-->6, 4<-->5, 5<-->4, 6<-->3, 7<-->2, 8<-->1, 9<-->0
   /* Upper case letters are also encrypted the same way (Upper case letters */
   /* are encrypted into Upper case letters). */
   /* All the encrypted chars should be written to the sink pipe char by char.
   /*-------------------------------------------------------*/

   protected void processData(){

      try{
         int c = inPipe.read(); // Read a character from the source pipe

         while(c != -1){ //not end of file
            char f = (char)c;
		    char e = encrypt(f); // to encrypt a character
		    outPipe.write(e);  // Write character 'e' to the sink pipe
            c = inPipe.read();
         }
         outPipe.closePipedOutputStream();
         inPipe.closePipedInputStream();
      }
      catch(IOException exc){
         exc.printStackTrace();
         System.err.println("Error: failed encryption 1");
         System.exit(1);
      }
   }

   private char encrypt(char c) {
      System.out.println("\n original char: "+ c);
      char[] digitArr = getDigitArray();
	  char[] alphabet = getAlphabetArray();

	  for(int n = 0; n < 26; n++){
	           if ( c == alphabet[n]){
	              c = alphabet[25-n];
	        	  break;
	           }
	           else if ( c == Character.toUpperCase(alphabet[n]) ){
			      c = Character.toUpperCase(alphabet[25-n]);
				  break;
	           }
	        }
            for(int j = 0; j < 10; j++){
	           if ( c == digitArr[j]){
	              c = digitArr[9-j];
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


