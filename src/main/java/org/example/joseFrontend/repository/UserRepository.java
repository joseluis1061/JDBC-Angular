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
        Statement myStat = getConnection().createStatement();
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
        PreparedStatement myPrep = getConnection().prepareStatement("SELECT * FROM usuarios WHERE user_id = ?");
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
    if(user.getUser_id() == null && user.getUser_id()<=0){
      String sql = "INSERT INTO usuarios (login, password, nickname, email) VALUES (?,?,?,?)";
      try(
          PreparedStatement myPrep = getConnection().prepareStatement(sql);
      ) {
        myPrep.setString(1, user.getLogin());
        myPrep.setString(2, user.getPassword());
        myPrep.setString(3, user.getNickname());
        myPrep.setString(4, user.getEmail());
        myPrep.executeUpdate();
      }
      return;
    }
    String sql = "UPDATE usuarios SET login= ?, password= ?, nickname= ?, email= ? WHERE user_id = ?";
    try(
        PreparedStatement myPrep = getConnection().prepareStatement(sql);
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
        PreparedStatement myStat = getConnection().prepareStatement("DELETE FROM usuarios WHERE user_id=?")
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
