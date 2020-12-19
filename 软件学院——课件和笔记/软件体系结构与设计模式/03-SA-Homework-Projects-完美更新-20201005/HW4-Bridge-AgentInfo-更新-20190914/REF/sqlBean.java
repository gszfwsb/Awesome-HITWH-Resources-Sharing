//package project;

import java.sql.*;

public class sqlBean
{

    public Connection conn = null;

    public ResultSet rs = null;

    private String DatabaseDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
    private String DatabaseConnStr = "jdbc:odbc:work,'000000',000000";//DataSource 数据源名称DSN

    //定义方法
    /*setXxx用于设置属性值;getXxx用于得到属性值*/
    public void setDatabaseDriver(String Driver)
    {
        this.DatabaseDriver = Driver;
    }

    public String getDatabaseDriver()
    {
        return(this.DatabaseDriver);
    }

    public void setDatabaseConnStr(String ConnStr)
    {
        this.DatabaseConnStr = ConnStr;
    }

    public String getDatabaseConnStr()
    {
        return(this.DatabaseConnStr);
    }

    public sqlBean()//构造函数
    {
        try
        {
           Class.forName(DatabaseDriver);
        } catch(java.lang.ClassNotFoundException e)
        {
            System.err.println("加载驱动器有错误:" + e.getMessage());
            System.out.print("执行插入有错误:" + e.getMessage()); //输出到客户端
        }
    }


    public int executeInsert(String sql)
    {
        int num = 0;
        try
        {
            conn = DriverManager.getConnection("jdbc:odbc:work", "000000",
                   "000000");
            Statement stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);
        } catch(SQLException ex)
        {
            System.err.println("执行插入有错误:" + ex.getMessage());
            System.out.print("执行插入有错误:" + ex.getMessage()); //输出到客户端
        }

        CloseDataBase();
        return num;
    }


    public ResultSet executeQuery(String sql)// display data
    {
        rs = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:odbc:work", "000000",
                   "000000");
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch(SQLException ex)
        {
            System.err.println("执行查询有错误:" + ex.getMessage() + sql);
            System.out.print("执行查询有错误:" + ex.getMessage()); //输出到客户端
        }

        return rs;
    }

    public int executeDelete(String sql)// delete data
    {
        int num = 0;
        try
        {

            conn = DriverManager.getConnection("jdbc:odbc:work", "000000",
                   "000000");
            Statement stmt = conn.createStatement();
            num = stmt.executeUpdate(sql);
        } catch(SQLException ex)
        {
            System.err.println("执行删除有错误:" + ex.getMessage());
            System.out.print("执行删除有错误:" + ex.getMessage()); //输出到客户端
        }
        CloseDataBase();
        return num;
    }

    public void CloseDataBase()
    {
        try
        {
            conn.close();
        } catch(Exception end)
        {
            System.err.println("执行关闭Connection对象有错误：" + end.getMessage());
            System.out.print("执行执行关闭Connection对象有错误：有错误:" + end.getMessage()); //输出到客户端
        }
    }
}
