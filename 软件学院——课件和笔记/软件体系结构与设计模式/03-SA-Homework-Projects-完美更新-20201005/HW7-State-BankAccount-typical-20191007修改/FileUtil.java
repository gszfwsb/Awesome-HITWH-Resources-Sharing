import java.io.*;
import java.util.*;


public class FileUtil {

  DataOutputStream dos;
  String fileName = null;
  File outFile = null;   //new File(fileName);

  public FileUtil(String fileName){
	  this.fileName = fileName;
  }

  // Utility method to append a String to a file
  public boolean appendToFile(String dataLine,boolean isNewLine) {

      if (isNewLine) {
        dataLine = dataLine + "\n";
      }

      try {
          dos = new DataOutputStream(
                  new FileOutputStream(fileName, true));

          dos.writeBytes(dataLine);
          dos.close();
      } catch (FileNotFoundException ex) {
  		   ex.printStackTrace();
           return (false);
      }
      catch (IOException ex) {
  	  ex.printStackTrace();
           return (false);
      }
      return (true);
  }

// Utility method to clear and write a String to a file
public boolean overwriteToFile(String dataLine, boolean isNewLine) {

    if (isNewLine) {
      dataLine = dataLine + "\n";
    }

    try {
      File outFile = new File(fileName);
      dos = new DataOutputStream(
              new FileOutputStream(outFile));

      dos.writeBytes(dataLine);
      dos.close();
    } catch (FileNotFoundException ex) {
		 ex.printStackTrace();
         return (false);
    }
    catch (IOException ex) {
	  ex.printStackTrace();
      return (false);
    }
    return (true);
  }

  /*
   	 Reads a line of data from a given file
   */
  public String readFromFile() {
    String DataLine = "";
    try {
      File inFile = new File(fileName);
      BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                              new FileInputStream(inFile)));

      DataLine = br.readLine();
      br.close();
    }
    catch (FileNotFoundException ex) {
	  ex.printStackTrace();
      return (null);
    }
    catch (IOException ex) {
	  ex.printStackTrace();
      return (null);
    }
    return (DataLine);
  }


  // Reads data from a given file into a Vector
  public Vector readFile() {
    Vector v = new Vector();
    String inputLine;
    try {
      File inFile = new File(fileName);
      BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                              new FileInputStream(inFile)));

      while ((inputLine = br.readLine()) != null) {
        v.addElement(inputLine.trim());
      }
      br.close();
    }
    catch (FileNotFoundException ex) {
       ex.printStackTrace();
    }
    catch (IOException ex) {
       ex.printStackTrace();
    }
    return (v);
  }

  // Clear contents of a given file, and then write data
  // from an input vector to the given file

  public boolean writeVectorToFile(Vector v) {
	  try {
	        File outFile = new File(fileName);
	        dos = new DataOutputStream(
	                new FileOutputStream(outFile));

	        for (int i = 0; i < v.size(); i++) {
			     dos.writeBytes((String)v.elementAt(i) + "\n");
            }
	        dos.close();
	  } catch (FileNotFoundException ex) {
	  		 ex.printStackTrace();
	           return (false);
	  }
	  catch (IOException ex) {
	  	  ex.printStackTrace();
	        return (false);
      }
      return true;
  }
} // end FileUtil

