package com.geekbrains.practice.network;

import com.geekbrains.practice.model.User;

import java.io.*;
import java.net.Socket;

public class NetworkHandler {
  private Socket socket;
  private ObjectInputStream objectInputStream;
  private ObjectOutputStream objectOutputStream;

  public NetworkHandler() {
    try {
      socket = new Socket(NetworkProperties.IP_ADDRESS, NetworkProperties.PORT);
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
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.
        append(senderName).append("|").
        append(senderPhoneNumber).append("|").
        append(message).append("|").
        append(readerName).append("|").
        append(readerPhoneNumber).append("|");
      objectOutputStream.writeUTF(stringBuilder.toString());
      objectOutputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
