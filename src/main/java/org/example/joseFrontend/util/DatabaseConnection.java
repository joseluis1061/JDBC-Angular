package org.example.joseFrontend.util;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
  private static String url = "jdbc:mysql://localhost:3306/masterblog";
  private static String user = "root";
  private static String password = "1061ivalostia";
  private static BasicDataSource myPoolConn;

  public static BasicDataSource getInstance() throws SQLException {
    if(myPoolConn==null){
      myPoolConn = new BasicDataSource();
      myPoolConn.setUrl(url);
      myPoolConn.setUsername(user);
      myPoolConn.setPassword(password);
      // Establecer tamaño del pool
      myPoolConn.setInitialSize(3);  //# de conexiones
      myPoolConn.setMaxTotal(10);  //# de conexiones activas a la vez
      myPoolConn.setMaxIdle(10);  //# de conexiones inactivas
      myPoolConn.setMinIdle(3);  //# de conexiones inactivas minimo que deben haber

    }
    return myPoolConn;
  }

  public static Connection getConnection() throws SQLException {
    return getInstance().getConnection();
  }
}

