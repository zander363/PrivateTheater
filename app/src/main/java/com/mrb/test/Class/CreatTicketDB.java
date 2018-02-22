package com.mrb.test.Class;
import java.sql.*;

public class CreatTicketDB
{
  public static void InitializeDB()
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:Ticket.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "CREATE TABLE TICKET " +
                   "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                   " MOVIENAME           TEXT    NOT NULL, " + 
                   " STARTTIME            TIME     NOT NULL, " + 
                   " ROW        CHAR(1), " + 
                   " SEATNUMBER         INT)"; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }
}
