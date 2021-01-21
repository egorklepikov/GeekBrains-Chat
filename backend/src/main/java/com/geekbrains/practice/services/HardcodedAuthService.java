package com.geekbrains.practice.services;

import com.geekbrains.practice.model.User;

import java.util.ArrayList;
import java.util.List;

public class HardcodedAuthService implements IAuthService {
  private final List<User> users;

  public HardcodedAuthService() {
    users = new ArrayList<>();
    users.add(HardcodedUsersFactory.createFirstUser());
    users.add(HardcodedUsersFactory.createSecondUser());
  }

  @Override
  public boolean isUserExist(String phoneNumber, String userName) {
    for (User user : users) {
      if (user.getPhoneNumber().equals(phoneNumber) && user.getUserName().equals(userName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public User getUserByPhoneAndName(String phoneNumber, String userName) {
    for (User user : users) {
      if (user.getPhoneNumber().equals(phoneNumber) && user.getUserName().equals(userName)) {
        return user;
      }
    }
    return null;
  }
}
