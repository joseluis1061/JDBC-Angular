package org.example.joseFrontend;

import java.sql.*;

public class Main {
  public static void main(String[] args) {
    Connection myConn = null;
    Statement myStat = null;
    PreparedStatement myPrep = null;
    ResultSet myRes = null;
    String url = "jdbc:mysql://localhost:3306/masterblog";
    String user = "root";
    String password = "1061ivalostia";

    String correoAEliminar = "correo5@correo.com";
    String sql = "DELETE FROM usuarios WHERE correo = '" + correoAEliminar + "'";
    

    try {
      myConn = DriverManager.getConnection(url, user, password);
      System.out.println("Se ha conectado la BD");
      myStat = myConn.createStatement();

      myStat.executeUpdate("DELETE FROM usuarios WHERE user_id = '" + "10" + "'");

      myRes = myStat.executeQuery("SELECT * FROM usuarios");

      while(myRes.next()){
        System.out.println(myRes.getString("user_id")+" "+myRes.getString("nickname")+" "+myRes.getString("email"));
      }


    }catch (Exception exception){
      System.out.println("Ha ocurrido un error: "+ exception);
    }

  }
}