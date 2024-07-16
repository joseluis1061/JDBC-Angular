package org.example.joseFrontend;

import org.example.joseFrontend.model.User;
import org.example.joseFrontend.repository.Repository;
import org.example.joseFrontend.repository.UserRepository;
import org.example.joseFrontend.util.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class Main {
  public static void main(String[] args) throws SQLException {
    try(Connection myConn = DatabaseConnection.getInstance()){
      if(myConn.getAutoCommit()){
        myConn.setAutoCommit(false);
      }
      User user1 = new User();
      user1.setNickname("Architect");
      user1.setPassword("admin123");
      user1.setLogin("logead");
      user1.setEmail("correo8@correo.com");
      user1.setNickname("DevOps");

      User user2 = new User();
      user2.setNickname("AWS");
      user2.setPassword("admin123");
      user2.setLogin("logead");
      user2.setEmail("correo8@correo.com");
      user2.setNickname("DevOps");
      List<User> usersList;
      try{
        Repository<User> userRepository = new UserRepository(myConn);
        System.out.println("--------------Antes Cambio 1----------------");
        usersList = userRepository.findAll();
        usersList.forEach(System.out::println);
        userRepository.save(user1);
        System.out.println("--------------Después Cambio 1----------------");
        usersList = userRepository.findAll();
        usersList.forEach(System.out::println);
        System.out.println("--------------Antes Cambio 2----------------");
        userRepository.save(user2);
        System.out.println("--------------Después Cambio 2----------------");
        usersList = userRepository.findAll();
        usersList.forEach(System.out::println);
        System.out.println("-------------------------------------");

        myConn.commit();
      }catch (SQLException exception){
        myConn.rollback();
      }
    }
  }
}