package org.example.joseFrontend.repository;

import org.example.joseFrontend.model.User;
import org.example.joseFrontend.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

  private Connection getConnection() throws SQLException {
    return DatabaseConnection.getConnection();
  }


  @Override
  public List<User> findAll() throws SQLException {
    List<User> users = new ArrayList<>();
    try(
        Connection myConn = getConnection();
        Statement myStat = myConn.createStatement();
        ResultSet myRes = myStat.executeQuery("SELECT * FROM usuarios");
    ){
      while (myRes.next()){
        User e = getUser(myRes);
        users.add(e);
      }
    }
    return users;
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
  public void save(User user) throws SQLException {
    if(user.getUser_id() == null){
      String sql = "INSERT INTO usuarios (login, password, nickname, email) VALUES (?,?,?,?)";
      try(
          Connection myConn = getConnection();
          PreparedStatement myPrep = myConn.prepareStatement(sql);
      ) {
        myPrep.setString(1, user.getLogin());
        myPrep.setString(2, user.getPassword());
        myPrep.setString(3, user.getNickname());
        myPrep.setString(4, user.getEmail());
        myPrep.executeUpdate();
      }
      return;
    }
    System.out.println("Actualizar");
    String sql = "UPDATE usuarios SET login= ?, password= ?, nickname= ?, email= ? WHERE user_id = ?";
    try(
        Connection myConn = getConnection();
        PreparedStatement myPrep = myConn.prepareStatement(sql);
    ) {
      myPrep.setString(1, user.getLogin());
      myPrep.setString(2, user.getPassword());
      myPrep.setString(3, user.getNickname());
      myPrep.setString(4, user.getEmail());
      myPrep.setInt(5, user.getUser_id());
      myPrep.executeUpdate();
    }
    return;

  }

  @Override
  public void delete(Integer id) throws SQLException {
    try(
        Connection myConn = getConnection();
        PreparedStatement myStat = myConn.prepareStatement("DELETE FROM usuarios WHERE user_id=?")
        ){
      myStat.setInt(1, id);
      myStat.executeUpdate();
    }
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
