package org.example.joseFrontend.repository;

import org.example.joseFrontend.model.User;
import org.example.joseFrontend.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

  private Connection getConnection() throws SQLException {
    return DatabaseConnection.getInstance();
  }

  @Override
  public List<User> findAll() throws SQLException {
    List<User> user = new ArrayList<>();
    try(
        Connection myConn = getConnection();
        Statement myStat = myConn.createStatement();
        ResultSet myRes = myStat.executeQuery("SELECT * FROM usuarios");
    ){
      while (myRes.next()){
        User e = getUser(myRes);
        user.add(e);
      }
    }
    return user;
  }



  @Override
  public User getByUid(Integer id) throws SQLException {
    User user = null;
    try(
        Connection myConn = getConnection();
        PreparedStatement myPrep = myConn.prepareStatement("SELECT * FROM usuarios WHERE user_id = ?");
        ) {
      myPrep.setInt(1, id);
      try(
          ResultSet myRes = myPrep.executeQuery();
          ){
        while (myRes.next()){
          user = getUser(myRes);
        }
      }
    }
    return user;
  }

  @Override
  public void save(User user) {

  }

  @Override
  public void delete(Integer id) {

  }

  private User getUser(ResultSet myRes) throws SQLException {
    User e = new User();
    e.setUser_id(myRes.getInt("user_id"));
    e.setLogin(myRes.getString("login"));
    e.setPassword(myRes.getString("password"));
    e.setNickname(myRes.getString("nickname"));
    e.setEmail(myRes.getString("email"));
    return e;
  }
}
