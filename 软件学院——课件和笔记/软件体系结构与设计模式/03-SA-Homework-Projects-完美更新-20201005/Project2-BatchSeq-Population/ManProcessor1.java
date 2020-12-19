
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ManProcessor1 implements BatchProcessing{
   private JTextArea resultTxt;
   private BufferedReader input = null;
   private BufferedWriter output1 = null;
   private BufferedWriter output2 = null;
   //String file = "UpdatedFiles/Man18-.txt";

   public ManProcessor1(String inFile, String outFile1, String outFile2, JTextArea resultTxtA)throws IOException{
      input = new BufferedReader(new FileReader(inFile));
	  output1 = new BufferedWriter(new FileWriter(outFile1));
	  output2 = new BufferedWriter(new FileWriter(outFile2));
	  resultTxt=resultTxtA;
   }

   /*---------------------------------------------------------------------*/
   /* This method simply reads input file (Man) line by line, and  */
   /* write each line read to one of the two output files, one is
   /* a file containing men whose ages are below 18, and the other
   /* is a file containing men whose ages are above 18. the data
   /* will not be accumulated in this class. Once a line is read,
   /* it is written immedietely to an output file                      */
   /*------------------------------------------------------------------*/
   public void processData(){
	  resultTxt.setText("25 < Women < 18 have been dropped.\n");
	  resultTxt.append("Men < 18 have been dropped.\n\n");
	 }
}


