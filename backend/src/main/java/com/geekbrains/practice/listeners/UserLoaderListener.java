package com.geekbrains.practice.listeners;

import com.geekbrains.practice.services.HardcodedAuthService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserLoaderListener extends Thread implements IListener {
  private final Socket socket;
  private ObjectInputStream objectInputStream;
  private ObjectOutputStream objectOutputStream;
  private final HardcodedAuthService hardcodedAuthService;

  public UserLoaderListener(Socket socket) {
    hardcodedAuthService = new HardcodedAuthService();
    this.socket = socket;
    try {
      objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectInputStream = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void startListening() {
    start();
  }

  @Override
  public void run() {
    while (!socket.isClosed()) {
      try {
        String[] credentials = objectInputStream.readUTF().split("\\|");
        String phoneNumber = credentials[0];
        String userName = credentials[1];
        if (hardcodedAuthService.isUserExist(phoneNumber, userName)) {
          objectOutputStream.writeObject(hardcodedAuthService.getUserByPhoneAndName(phoneNumber, userName));
        } else {
          objectOutputStream.writeObject(null);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
