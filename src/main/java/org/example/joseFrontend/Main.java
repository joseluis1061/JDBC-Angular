package org.example.joseFrontend;

import org.example.joseFrontend.model.User;
import org.example.joseFrontend.repository.Repository;
import org.example.joseFrontend.repository.UserRepository;
import org.example.joseFrontend.util.DatabaseConnection;
import view.SwingApp;

import java.sql.*;

public class Main {
  public static void main(String[] args) throws SQLException {
    SwingApp app = new SwingApp();
    app.setVisible(true);
  }
}