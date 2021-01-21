package com.geekbrains.practice.network;

import com.geekbrains.practice.model.User;

import java.io.*;
import java.net.Socket;

public class NetworkHandler implements INetworkHandler {
  private Socket socket;
  private ObjectInputStream objectInputStream;
  private ObjectOutputStream objectOutputStream;

  public NetworkHandler() {
    try {
      socket = new Socket(NetworkProperties.IP_ADDRESS, NetworkProperties.PORT);
      objectInputStream = new ObjectInputStream(socket.getInputStream());
      objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public User loadUserByPhoneAndName(String phoneNumber, String userName) throws IllegalStateException {
    try {
      objectOutputStream.writeUTF(phoneNumber + "|" + userName);
      return (User) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void registerUser(User user) {
  }

  @Override
  public boolean isUserRegistered(String phoneNumber) {
    //TODO implement if will be needed
    return false;
  }
}
