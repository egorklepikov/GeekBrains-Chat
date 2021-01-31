package com.geekbrains.practice.services;

import com.geekbrains.practice.database.H2DatabaseConnectivity;
import com.geekbrains.practice.model.User;

import java.sql.SQLException;
import java.sql.Statement;

public class H2AuthService implements IAuthService {
  private final H2DatabaseConnectivity h2DatabaseConnectivity;
  private Statement statement;

  public H2AuthService() {
    h2DatabaseConnectivity = new H2DatabaseConnectivity();
    try {
      h2DatabaseConnectivity.connect();
      statement = h2DatabaseConnectivity.getConnection().createStatement();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean isUserExist(String phoneNumber, String userName) {
    return false;
  }

  @Override
  public User getUserByPhoneAndName(String phoneNumber, String userName) {
    return null;
  }
}
