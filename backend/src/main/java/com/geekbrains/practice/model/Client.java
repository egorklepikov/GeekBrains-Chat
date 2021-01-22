package com.geekbrains.practice.model;

import com.geekbrains.practice.listeners.UserLoaderListener;

import java.net.Socket;

public class Client {
  private final Socket socket;
  private final UserLoaderListener userLoaderListener;

  public Client(Socket socket) {
    this.socket = socket;
    userLoaderListener = new UserLoaderListener(socket);
    userLoaderListener.startListening();
  }

  public Socket getSocket() {
    return socket;
  }

  public UserLoaderListener getUserLoaderListener() {
    return userLoaderListener;
  }
}
