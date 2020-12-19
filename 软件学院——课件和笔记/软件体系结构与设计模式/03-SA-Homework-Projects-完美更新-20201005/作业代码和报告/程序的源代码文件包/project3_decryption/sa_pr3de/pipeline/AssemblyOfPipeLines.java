package pipeline;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;

import filter.DecrFilter1;
import filter.DecrFilter2;
import filter.DecrFilter3;
import filter.Filter;
import filter.InFilter;
import filter.OutFilter;
import gui.PipeFilterTestGui;
import pipe.Pipe;


public class AssemblyOfPipeLines {

   public void assembly(String inFile, JTextArea orig, JTextArea destn) throws IOException {

	     try{
            //Create 4 pipe objects
            Pipe p1 = new Pipe();
      	    Pipe p2 = new Pipe();
      	    Pipe p3 = new Pipe();
      	    Pipe p4 = new Pipe();

      	    //Input from source stream
      	    //FileInputStream in = new FileInputStream(PipeFilterTestGui.ORIGINALFILES + inFile);
      	    FileInputStream in = new FileInputStream(PipeFilterTestGui.ENCRYPTEDFILES + inFile);

            //Create 5 filter objects
            Filter input = new InFilter(in, p1,orig);
            /*
            Filter encrypt1 = new EncrFilter1(p1, p2);
            Filter encrypt2= new EncrFilter2(p2, p3 );
            Filter encrypt3= new EncrFilter3(p3, p4 );
            */
            Filter decrypt3= new DecrFilter3(p1, p2 );
            Filter decrypt2= new DecrFilter2(p2, p3 );
            Filter decrypt1 = new DecrFilter1(p3, p4);

            //Filter output = new OutFilter(p4, destn,inFile);
            Filter output = new OutFilter(p4, destn,inFile);

            //Start all the filter threads
            input.start();
            /*
            encrypt1.start();
            encrypt2.start();
            encrypt3.start();
            */
            decrypt3.start();
            decrypt2.start();
            decrypt1.start();
            output.start();
         }
         catch(IOException exc) {
            exc.printStackTrace();
         }
   }
}
