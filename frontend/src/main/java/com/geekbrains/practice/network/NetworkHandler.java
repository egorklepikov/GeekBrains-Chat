package com.geekbrains.practice.network;

import com.geekbrains.practice.model.User;

import java.io.*;
import java.net.Socket;

public class NetworkHandler {
  private ObjectInputStream objectInputStream;
  private ObjectOutputStream objectOutputStream;

  public NetworkHandler() {
    try {
      Socket socket = new Socket(NetworkProperties.IP_ADDRESS, NetworkProperties.PORT);
      objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectInputStream = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public User loadUserByPhoneAndName(String phoneNumber, String userName) {
    try {
      objectOutputStream.writeUTF(phoneNumber + "|" + userName);
      objectOutputStream.flush();
      return (User) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void sendMessage(String senderName, String senderPhoneNumber, String message, String readerName, String readerPhoneNumber) {
    try {
      objectOutputStream.writeUTF(senderName + "|" + senderPhoneNumber + "|" + message + "|" + readerName + "|" + readerPhoneNumber);
      objectOutputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
