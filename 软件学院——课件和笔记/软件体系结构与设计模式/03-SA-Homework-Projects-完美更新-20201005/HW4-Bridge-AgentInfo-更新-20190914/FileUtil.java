import java.io.*;
import java.util.*;


public class FileUtil {

  DataOutputStream dos;

  /*
  	 Utility method to write a given text to a file
  */
  public boolean writeToFile(String fileName, String dataLine,
      boolean isAppendMode, boolean isNewLine) {
    if (isNewLine) {
      dataLine = "\n" + dataLine;
    }

    try {
      File outFile = new File(fileName);
      if (isAppendMode) {
        dos = new DataOutputStream(
                new FileOutputStream(fileName, true));
      } else {
        dos = new DataOutputStream(
                new FileOutputStream(outFile));
      }

      dos.writeBytes(dataLine);
      dos.close();
    } catch (FileNotFoundException ex) {
      return (false);
    }
    catch (IOException ex) {
      return (false);
    }
    return (true);

  }

  /*
   	 Reads data from a given file
   */
  public String readFromFile(String fileName) {
    String DataLine = "";
    try {
      File inFile = new File(fileName);
      BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                              new FileInputStream(inFile)));

      DataLine = br.readLine();
      br.close();
    } catch (FileNotFoundException ex) {
      return (null);
    }
    catch (IOException ex) {
      return (null);
    }
    return (DataLine);

  }

  public boolean isFileExists(String fileName) {
    File file = new File(fileName);
    return file.exists();
  }

  public boolean deleteFile(String fileName) {
    File file = new File(fileName);
    return file.delete();
  }

  /*
   	 Reads data from a given file into a Vector
   */

  public Vector fileToVector(String fileName) {
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
    } // Try
    catch (FileNotFoundException ex) {
      //
    }
    catch (IOException ex) {
      //
    }
    return (v);
  }

  /*
   	 Writes data from an input vector to a given file
   */

  public void vectorToFile(Vector v, String fileName) {
    for (int i = 0; i < v.size(); i++) {
      writeToFile(fileName, (String) v.elementAt(i), true,
                  true);
    }
  }

  /*
   	 Copies unique rows from a source file to a destination file
   */

  public void copyUniqueElements(String sourceFile,
      String resultFile) {
    Vector v = fileToVector(sourceFile);
    v = MiscUtil.removeDuplicates(v);
    vectorToFile(v, resultFile);
  }

} // end FileUtil

