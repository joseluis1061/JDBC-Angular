package org.example.joseFrontend;

import org.example.joseFrontend.model.User;
import org.example.joseFrontend.repository.Repository;
import org.example.joseFrontend.repository.UserRepository;
import org.example.joseFrontend.util.DatabaseConnection;

import java.sql.*;

public class Main {
  public static void main(String[] args) throws SQLException {

    try(
        Connection myConn = DatabaseConnection.getInstance();
        )
    {
      Repository<User> userRepository = new UserRepository();
      userRepository.findAll().forEach(System.out::println);
    }catch (Exception exception){
      System.out.println("Ha ocurrido un error: "+ exception);
    }
  }
}