package com.geekbrains.practice.network;

import com.geekbrains.practice.model.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class NetworkHandler implements INetworkHandler {
  private Socket socket;
  private Scanner scanner;
  private PrintWriter printWriter;

  public NetworkHandler() {
    try {
      socket = new Socket(NetworkProperties.IP_ADDRESS, NetworkProperties.PORT);
      scanner = new Scanner(new InputStreamReader(socket.getInputStream()));
      printWriter = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public User loadUserByPhone(String phoneNumber) throws IllegalStateException {
    printWriter.flush();
    printWriter.println(phoneNumber);
    return null;
  }

  @Override
  public void registerUser(User user) {
  }

  @Override
  public boolean isUserRegistered(String phoneNumber) {
    printWriter.flush();
    printWriter.println(phoneNumber);
    return scanner.nextLine().equalsIgnoreCase("true");
  }
}
