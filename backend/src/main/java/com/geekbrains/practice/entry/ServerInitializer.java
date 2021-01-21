package com.geekbrains.practice.entry;

import com.geekbrains.practice.listeners.UserCheckerListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitializer {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(8189);
      Socket socket = serverSocket.accept();
      new UserCheckerListener(socket).startListening();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
