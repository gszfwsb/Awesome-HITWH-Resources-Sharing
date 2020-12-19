
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class WomanProcessor2 implements BatchProcessing{
   private JTextArea resultTxt;
   private BufferedReader input = null;
   private BufferedWriter output1 = null;
   private BufferedWriter output2 = null;


   public WomanProcessor2(String inFile, String outFile1, String outFile2, JTextArea resultTxtA)throws IOException{

      input = new BufferedReader(new FileReader(inFile));
	  output1 = new BufferedWriter(new FileWriter(outFile1));
	  output2 = new BufferedWriter(new FileWriter(outFile2));
	  resultTxt=resultTxtA;
   }

   /*------------------------------------------------------------------------*/
   /* This method simply reads input file (Woman18+) line by line, and       */
   /* write each line read to one of the two output files, one is
   /* a file containing women whose ages are between 18 to 25, and the other
   /* is a file containing women whose ages are above 25. the data
   /* will not be accumulated in this class. Once a line is read,
   /* it is written immedietely to an output file                            */
   /*------------------------------------------------------------------------*/
   public void processData(){

   }
}
