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
      System.out.println("----------------BEFORE--------------------");
      userRepository.findAll().forEach(System.out::println);
      User newUser = new User();
      newUser.setUser_id(1);
      newUser.setLogin("logead");
      newUser.setPassword("admin123");
      newUser.setNickname("master");
      newUser.setEmail("correo1@correo.com");
      userRepository.save(newUser);
      System.out.println("------------------AFTER------------------");
      userRepository.findAll().forEach(System.out::println);
      System.out.println("------------------DELTE------------------");
      userRepository.delete(12);
      userRepository.findAll().forEach(System.out::println);
    }
  }
}