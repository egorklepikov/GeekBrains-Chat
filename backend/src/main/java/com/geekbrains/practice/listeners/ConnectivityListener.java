package com.geekbrains.practice.listeners;

import com.geekbrains.practice.core.ClientsHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class ConnectivityListener implements Runnable {
  private final ServerSocket serverSocket;

  public ConnectivityListener(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  @Override
  public void run() {
    while (!serverSocket.isClosed()) {
      try {
        ClientsHandler.getInstance().addClient(serverSocket.accept());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
