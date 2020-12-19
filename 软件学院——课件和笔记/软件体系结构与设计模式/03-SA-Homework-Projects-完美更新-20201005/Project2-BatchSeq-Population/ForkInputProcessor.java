
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ForkInputProcessor implements BatchProcessing{

     private JTextArea resultTxt;
     private BufferedReader input = null;
     private BufferedWriter output1 = null;
     private BufferedWriter output2 = null;


   public ForkInputProcessor(String inFile, String outFile1, String outFile2, JTextArea resultTxtA)throws IOException{
       input = new BufferedReader(new FileReader(inFile));
	   output1 = new BufferedWriter(new FileWriter(outFile1));
	   output2 = new BufferedWriter(new FileWriter(outFile2));
	   resultTxt=resultTxtA;
   }

   /*-------------------------------------------------------------*/
   /* This method simply reads input file line by line,          */
   /* and write each line read to one of the two output files,
   /* one is a file containing woman data and the other is a file
   /* containing man information. The information will not be
   /* accumulated in this class. Once a line is read, it is written
   /* immedietely to an output file                               */
   /*-------------------------------------------------------------*/
   public void processData(){
       try{
		    resultTxt.setText("Man has been dropped.\n\n");
            String line = input.readLine();
            while(line != null){ //not end of file
                String[] words = line.split("\\s");

                if (isAMan(words)==true){
   			        output1.write(line+"\n");
   		        }
   		        else if(isAWoman(words)==true)   {
   			        output2.write(line+"\n");
			        resultTxt.append(line+"\n");
   		        }
                line = input.readLine();
            }

            if (input != null){
   		         input.close();
   		    }
   		    if (output1 != null){
   		         output1.close();
            }
            if (output2 != null){
   		         output2.close();
            }
      }
      catch(IOException exc){
            exc.printStackTrace();
            System.err.println("Error: failed Fork input processor");
            System.exit(1);
         }
   }
    private boolean isAMan(String[] words){
		boolean flag = false;
		for(int k=0; k< words.length; k++){  //One line
		   if( words[k].compareTo("male")==0 ){
				 flag = true;
			}
		}
	   return flag;
    }

    private boolean isAWoman(String[] words){
		boolean flag = false;
		for(int k=0; k< words.length; k++){  //One line
			 if( words[k].compareTo("female")==0 ){
				 flag = true;
				 break;
			}
		}
	   return flag;
    }
}