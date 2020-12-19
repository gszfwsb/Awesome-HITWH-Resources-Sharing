package messagewriter;
/*------------------------------------------------*/
/* THis class implements interface MessageLogger  */
/* This class provides a method called            */
/*            logMsg(String msg)                  */
/* to write a message into a file called log.txt  */
/*------------------------------------------------*/



import java.util.*;

import ioutil.FileUtil;

public class TxtFileWriter implements MessageWriter {

  public void logMsg(String lastNm, String firstNm, String code) throws Exception{
    FileUtil futil = new FileUtil();
    futil.writeToFile("Encrypted.txt",lastNm + " " + firstNm + " " +code+ " " + "\r\n", true, true); // "\r\n"����д������
  }
}
