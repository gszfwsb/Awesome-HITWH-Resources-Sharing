import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.IOException;


public class PipeLineBuilder{
   private BatchProcessing inputProcessor = null;
   private BatchProcessing womanProcessor1 = null;
   private BatchProcessing womanProcessor2 = null;
   private BatchProcessing womanProcessor3 = null;
   private BatchProcessing manProcessor1 = null;
   private BatchProcessing manProcessor2 = null;
   private BatchProcessing outputProcessor = null;
   private ArrayList ftrs;
   private JTextArea processorInfoTxt;
   private JTextArea[] txtInfo = new JTextArea[5];
   private static final String POPULATIONFILES = "PopulationFiles/";
   private static final String PROCESSEDFILES = "ProcessedFiles/";


   public PipeLineBuilder(ArrayList ftrStr, JTextArea[] txt, JTextArea infoTxt) throws IOException{
	  txtInfo = txt;
	  ftrs = ftrStr;
	  processorInfoTxt = infoTxt;
   }

   public void buildAndRunPipeFilters(String inFile) throws IOException{
      String originFile = POPULATIONFILES + inFile;
      String womanFile = PROCESSEDFILES + "Woman.txt";
      String manFile = PROCESSEDFILES + "Man.txt";
      String womanUnder18File = PROCESSEDFILES + "Woman18-.txt";
      String womanAbove18File = PROCESSEDFILES + "Woman18+.txt";
      String womanAbove25File = PROCESSEDFILES + "Woman25+.txt";
      String womanBetween18And25File = PROCESSEDFILES + "Woman18-25.txt";
      String manUnder18File = PROCESSEDFILES + "Man18-.txt";
      String manAbove18File = PROCESSEDFILES + "Man18+.txt";
      String manBetween18And25File = PROCESSEDFILES + "Man18-25.txt";
      String manAbove25File = PROCESSEDFILES + "Man25+.txt";

      try{
         inputProcessor = new ForkInputProcessor(originFile, manFile, womanFile, txtInfo[0]);
         womanProcessor1 = new WomanProcessor1(womanFile, womanUnder18File, womanAbove18File, txtInfo[1]);
         womanProcessor2 = new WomanProcessor2(womanAbove18File, womanAbove25File, womanBetween18And25File, txtInfo[2]);
         manProcessor1 = new ManProcessor1(manFile, manUnder18File, manAbove18File, txtInfo[3]);
         manProcessor2 = new ManProcessor2(manAbove18File, manAbove25File, manBetween18And25File, txtInfo[4]);
         inputProcessor.processData();
	     womanProcessor1.processData();
	     womanProcessor2.processData();
	     manProcessor1.processData();
	     manProcessor2.processData();
      }
      catch(IOException exc) {
         exc.printStackTrace();
         System.err.println("Error: failed Pipeline builder");
      }
   }
}
