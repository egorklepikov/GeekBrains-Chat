package com.geekbrains.practice.entry;

import com.geekbrains.practice.database.DatabaseInitializer;
import com.geekbrains.practice.listeners.ConnectivityListener;
import com.geekbrains.practice.services.ThreadsExecutionService;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerInitializer {
  public static void main(String[] args) {
    try {
      DatabaseInitializer.getInstance().initialize();
      ServerSocket serverSocket = new ServerSocket(8189);
      ConnectivityListener connectivityListener = new ConnectivityListener(serverSocket);
      ThreadsExecutionService.getInstance().addTaskToExecutionsQueue(connectivityListener);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
