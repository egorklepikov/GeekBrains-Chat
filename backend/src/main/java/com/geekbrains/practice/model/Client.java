package com.geekbrains.practice.model;

import com.geekbrains.practice.listeners.MessagesListener;
import com.geekbrains.practice.listeners.UserLoaderListener;

import java.net.Socket;

public class Client {
  private final Socket socket;
  private final UserLoaderListener userLoaderListener;
  private final MessagesListener messagesListener;

  public Client(Socket socket) {
    this.socket = socket;
    userLoaderListener = new UserLoaderListener(socket);
    userLoaderListener.startListening();
    messagesListener = new MessagesListener(socket);
    messagesListener.startListening();
  }

  public Socket getSocket() {
    return socket;
  }

  public UserLoaderListener getUserLoaderListener() {
    return userLoaderListener;
  }

  public MessagesListener getMessagesListener() {
    return messagesListener;
  }

  public User getUser() {
    return userLoaderListener.getUser();
  }
}
