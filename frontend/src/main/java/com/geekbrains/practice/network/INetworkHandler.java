package com.geekbrains.practice.network;

import com.geekbrains.practice.model.User;

public interface INetworkHandler {
  User loadUserByPhoneAndName(String phoneNumber, String userName);
  void registerUser(User user);
  boolean isUserRegistered(String phoneNumber);
}
