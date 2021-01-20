package com.geekbrains.practice.network;

import com.geekbrains.practice.model.User;

public interface INetworkHandler {
  User loadUserByPhone(String phoneNumber);
  void registerUser(User user);
  boolean isUserRegistered(String phoneNumber);
}
