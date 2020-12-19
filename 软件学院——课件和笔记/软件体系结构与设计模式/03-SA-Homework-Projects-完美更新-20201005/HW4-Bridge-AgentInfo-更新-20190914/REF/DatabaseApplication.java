




 //import java.sql.*;
 //import java.sql.ResultSet;
 //import java.sql.SQLException;

 public class DatabaseApplication
 {
    public static void main(String args[])
   {
 		int ID=264;
 		String firstName="Micheal";
 		String lastName="Sun";
 		int cardNum=123;

		String sql = "INSERT INTO  CUSTOMER " + "values(" + ID + ",'" + firstName + "','" +
      				  lastName + "'," + cardNum + ")";

        sqlBean db = new sqlBean();
        db.executeInsert(sql);
    }
}