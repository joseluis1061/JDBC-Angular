package org.example.joseFrontend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static String url = "jdbc:mysql://localhost:3306/masterblog";
  private static String user = "root";
  private static String password = "1061ivalostia";
  private static Connection myConn;

  public static Connection getInstance() throws SQLException {
    if(myConn==null){
      myConn = DriverManager.getConnection(url, user, password);
    }
    return myConn;
  }
}
