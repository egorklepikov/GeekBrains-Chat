package com.geekbrains.practice.services;

import com.geekbrains.practice.model.User;

import java.sql.Statement;

public class H2AuthService implements IAuthService {
  private Statement statement;

  public H2AuthService() {
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
