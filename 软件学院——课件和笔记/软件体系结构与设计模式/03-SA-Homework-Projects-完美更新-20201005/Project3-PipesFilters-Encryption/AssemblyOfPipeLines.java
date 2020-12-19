import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;


public class AssemblyOfPipeLines {

   public void assembly(String inFile, JTextArea orig, JTextArea destn) throws IOException {

	     try{
            //Create 4 pipe objects
            Pipe p1 = new Pipe();
      	    Pipe p2 = new Pipe();
      	    Pipe p3 = new Pipe();
      	    Pipe p4 = new Pipe();

      	    //Input from source stream
      	    FileInputStream in = new FileInputStream(PipeFilterTestGui.ORIGINALFILES + inFile);

            //Create 5 filter objects
            Filter input = new InFilter(in, p1,orig);
            Filter encrypt1 = new EncrFilter1(p1, p2);
            Filter encrypt2= new EncrFilter2(p2, p3 );
            Filter encrypt3= new EncrFilter3(p3, p4 );

            //Filter output = new OutFilter(p4, destn,inFile);
            Filter output = new OutFilter(p4, destn,inFile);

            //Start all the filter threads
            input.start();
            encrypt1.start();
            encrypt2.start();
            encrypt3.start();
            output.start();
         }
         catch(IOException exc) {
            exc.printStackTrace();
         }
   }
}
