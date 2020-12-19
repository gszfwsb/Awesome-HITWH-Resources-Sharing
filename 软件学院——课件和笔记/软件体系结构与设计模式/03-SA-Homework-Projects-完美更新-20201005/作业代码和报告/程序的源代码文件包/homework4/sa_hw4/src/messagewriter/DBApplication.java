package messagewriter;

import java.sql.*;

public class DBApplication{
   public Connection conn = null;
   public ResultSet rs = null;

   private String databaseDriver;
   private String databaseConnStr;
   private String userName;
   private String passWord;

   public DBApplication(String dBDriver,String dBConnStr, String user, String passWd ){
      databaseDriver = dBDriver;
	  databaseConnStr = dBConnStr;
	  userName = user;
	  passWord = passWd;

      try{
         Class.forName(databaseDriver);
      }
      catch(java.lang.ClassNotFoundException e){
         System.err.println("�����������д���:" + e.getMessage());
         System.out.print("ִ�в����д���:" + e.getMessage()); //������ͻ���
      }
    }

    public int executeInsert(String sql){
       int num = 0;
       try{
          conn = DriverManager.getConnection(databaseConnStr,userName, passWord);
          Statement stmt = conn.createStatement();
          num = stmt.executeUpdate(sql);
       }
       catch(SQLException ex){
          System.err.println("ִ�в����д���:" + ex.getMessage());
          System.out.print("ִ�в����д���:" + ex.getMessage()); //������ͻ���
       }

       closeDataBase();
       return num;
    }


    public ResultSet executeQuery(String sql){
       rs = null;
       try{
          conn = DriverManager.getConnection(databaseConnStr,userName, passWord);
          Statement stmt = conn.createStatement();
          rs = stmt.executeQuery(sql);
       }
       catch(SQLException ex){
          System.err.println("ִ�в�ѯ�д���:" + ex.getMessage() + sql);
          System.out.print("ִ�в�ѯ�д���:" + ex.getMessage()); //������ͻ���
       }

       return rs;
    }

    public int executeDelete(String sql) {
       int num = 0;
       try{
          conn = DriverManager.getConnection(databaseConnStr,userName, passWord);
          Statement stmt = conn.createStatement();
          num = stmt.executeUpdate(sql);
       }
       catch(SQLException ex){
          System.err.println("ִ��ɾ���д���:" + ex.getMessage());
          System.out.print("ִ��ɾ���д���:" + ex.getMessage()); //������ͻ���
       }

       closeDataBase();
       return num;
    }

    public void closeDataBase(){
       try{
          conn.close();
       }
       catch(Exception end){
          System.err.println("ִ�йر�Connection�����д���" + end.getMessage());
          System.out.print("ִ��ִ�йر�Connection�����д����д���:" + end.getMessage()); //������ͻ���
       }
    }
}
