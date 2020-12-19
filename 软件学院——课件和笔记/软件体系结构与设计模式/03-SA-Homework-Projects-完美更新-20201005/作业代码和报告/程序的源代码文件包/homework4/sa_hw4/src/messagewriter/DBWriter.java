package messagewriter;
/*------------------------------------------------*/
/* THis class implements interface MessageLogger  */
/* This class provides a method called            */
/*            logMsg(String msg)                  */
/* to write a message into a file called log.txt  */
/*------------------------------------------------*/

import java.util.*;

public class DBWriter implements MessageWriter {
   private String lastName;
   private String firstName;
   private String codeNum;

   private static final String DBDRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
   private static final String DBCONNSTR = "jdbc:odbc:AgentInfo";
   private static final String USER = "mikesun";
   private static final String PWORD = "17371";

  public void logMsg(String lastNm, String firstNm, String code) throws Exception
  {
	 firstName = firstNm;
	 lastName = lastNm;
	 codeNum = code;

	 String sql = "INSERT INTO Agent " + "values("  + "'"
	                                   + lastName + "','"
	                                   + firstName + "','"
	                                   + codeNum + "')";

	 DBApplication db = new DBApplication(DBDRIVER, DBCONNSTR, USER, PWORD);
     db.executeInsert(sql);
  }
}
