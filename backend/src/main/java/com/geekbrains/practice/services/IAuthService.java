package com.geekbrains.practice.services;

import com.geekbrains.practice.model.User;

public interface IAuthService {
  boolean isUserExist(String phoneNumber, String userName);
  User getUserByPhoneAndName(String phoneNumber, String userName);
}
