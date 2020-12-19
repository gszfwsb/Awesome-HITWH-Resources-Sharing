
import java.io.IOException;
import javax.swing.*;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;


public class OutFilter extends Filter {
   private JTextArea resultTextArea;
   String inputFileNM;

   public OutFilter(Pipe input, JTextArea resultTxtA, String inFile) {
      super(input, null);
      resultTextArea = resultTxtA;
      inputFileNM = inFile;
   }

   /*--------------------------------------------------*/
   /* Read chars from the input pipe, and then write   */
   /* the resultant data into a file and console       */
   /*--------------------------------------------------*/
   protected void processData() {
      resultTextArea.append("\n");
      try {
		 //String file = "UpdatedFiles/"+ "New" + inputFileNM;
		 String file = "EncryptedFiles/"+ "Encrypted" + inputFileNM;
		 PrintWriter prWriter
			     = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)));

         int c = inPipe.read(); //Read a character from source pipe
	     while(c != -1){
	         prWriter.print((char)c); //write a character to the "file"
		     resultTextArea.append(""+(char)c); //write a character to the screen
		     c = inPipe.read();
	     }
	     resultTextArea.append("\n");
	     prWriter.close();
         inPipe.closePipedInputStream();
      }
      catch(IOException exc){
         exc.printStackTrace();
         System.err.println("Output Error");
         System.exit(1);
      }
   }//end transform method

}
