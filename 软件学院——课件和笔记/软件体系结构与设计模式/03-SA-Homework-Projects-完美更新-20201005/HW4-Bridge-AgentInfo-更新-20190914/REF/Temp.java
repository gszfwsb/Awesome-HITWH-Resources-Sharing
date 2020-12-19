

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Temp
{

   public static void main(String args[])
   {
	   Connection con;
       // The connection to the database.
       // The following code can throw errors, so they must be caught.
	   try
       {
		   // First, tell Java what driver to use and where to find it.

           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

           // Next, create a connection to your data source.
           // Specify that you are using the ODBC-JDBC Bridge.
           // And specify the data source from ODBC.

           //con = DriverManager.getConnection("jdbc:odbc:work");

           con = DriverManager.getConnection("jdbc:odbc:work", "000000",
                   "000000");

           // Create an SQL statement.

           Statement stmt = con.createStatement();

          // Execute some SQL to create a table in your database.
           // If the table already exists, an exception is thrown!

           stmt.executeUpdate("CREATE TABLE CUSTOMER " +
           "(ID integer, fname VARCHAR(32), lname VARCHAR(32), ccnum integer)");


           int ID=264;
           String firstName="Micheal";
           String lastName="Sun";
           int cardNum=123;


       //String sql = "update enrol " +
       //                     "set score='" + score + "' " +
       //                     "where stu_id='" + stu_id + "' " +
       //                     "and class_id='" + class_id + "' ";

      String sql = "INSERT INTO  CUSTOMER " + "values(" + ID + ",'" + firstName + "','" +
      lastName + "'," + cardNum + ")";


        sqlBean db = new sqlBean();
        db.executeInsert(sql);







 //stmt.executeUpdate(
	//  stmt.executeInsert(
 //     "INSERT INTO  CUSTOMER " +
 //     "values(" + ID + ",'" + firstName + "','" +
 //     lastName + "'," + cardNum + ")");


 //stmt.executeUpdate(
 //      "Insert into Customer values (111,'aaaa','vvvv',22);");


//System.err.println("Insert into Customer " +"values(" + ID + ",'" + firstName + "','" +lastName + "'," + cardNum + ");");
//con.commit();
//stmt.executeUpdate(
 //     "Insert into Customer values(ID,firstName, lastName, cardNum);");


}

// Catch any exceptions that are thrown.
catch(ClassNotFoundException e){

System.out.println(e.toString());
}
catch(SQLException e){

System.out.println(e.toString());
}

}

}

