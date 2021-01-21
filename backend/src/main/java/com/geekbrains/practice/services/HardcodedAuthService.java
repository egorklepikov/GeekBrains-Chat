package com.geekbrains.practice.services;

import java.util.ArrayList;
import java.util.List;

public class HardcodedAuthService implements IAuthService {
  private class UserData {
    private final String phoneNumber;
    private final String userName;

    public UserData(String phoneNumber, String userName) {
      this.phoneNumber = phoneNumber;
      this.userName = userName;
    }
  }

  private final List<UserData> users;

  public HardcodedAuthService() {
    users = new ArrayList<>();
    users.add(new UserData("+ 7 913 021 22 37", "Egor"));
    for (int i = 0; i < 10; i++) {
      users.add(new UserData("Test" + i, "Test" + i));
    }
  }

  @Override
  public String isUserExist(String phoneNumber) {
    for (UserData user : users) {
      if (user.phoneNumber.equals(phoneNumber)) {
        return "true";
      }
    }
    return "false";
  }
}
