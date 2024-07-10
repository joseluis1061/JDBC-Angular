package org.example.joseFrontend.model;

public class User {
  Integer user_id;
  String login;
  String password;
  String nickname;
  String email;

  public User() {
  }

  public User(String email, String login, String nickname, String password, Integer user_id) {
    this.email = email;
    this.login = login;
    this.nickname = nickname;
    this.password = password;
    this.user_id = user_id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", user_id='" + user_id + '\'' +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", nickname='" + nickname + '\'' +
        '}';
  }
}
