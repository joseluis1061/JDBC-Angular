package org.example.joseFrontend;

import org.example.joseFrontend.util.DatabaseConnection;

import java.sql.*;

public class Main {
  public static void main(String[] args) throws SQLException {

    String correoAEliminar = "correo5@correo.com";
    String sql = "DELETE FROM usuarios WHERE correo = '" + correoAEliminar + "'";


    try(
        Connection myConn = DatabaseConnection.getInstance();
        Statement myStat = myConn.createStatement();
        ResultSet myRes = myStat.executeQuery("SELECT * FROM usuarios");
        )
    {

      System.out.println("Se ha conectado la BD");
      //int rowsAffected = myStat.executeUpdate("UPDATE usuarios SET nickname='backend' WHERE user_id = '8'");
      while(myRes.next()){
        System.out.println(myRes.getString("user_id")+" "+myRes.getString("nickname")+" "+myRes.getString("email"));
      }


    }catch (Exception exception){
      System.out.println("Ha ocurrido un error: "+ exception);
    }
  }
}