package com.geekbrains.practice.entry;

import com.geekbrains.practice.listeners.UserCheckerListener;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerInitializer {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8189);
      new UserCheckerListener(serverSocket.accept()).startListening();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
