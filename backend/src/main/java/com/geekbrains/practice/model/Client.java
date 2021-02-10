package com.geekbrains.practice.model;

import com.geekbrains.practice.listeners.MessagesListener;
import com.geekbrains.practice.services.ThreadsExecutionService;
import com.geekbrains.practice.listeners.UserLoaderListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
  private final Socket socket;
  private ObjectInputStream objectInputStream;
  private ObjectOutputStream objectOutputStream;
  private UserLoaderListener userLoaderListener;
  private MessagesListener messagesListener;

  public Client(Socket socket) {
    this.socket = socket;
    try {
      objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectInputStream = new ObjectInputStream(socket.getInputStream());
      userLoaderListener = new UserLoaderListener(socket, objectOutputStream, objectInputStream);
      messagesListener = new MessagesListener(socket, objectOutputStream, objectInputStream);
      ThreadsExecutionService.getInstance().addTaskToExecutionsQueue(messagesListener);
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  public ObjectInputStream getObjectInputStream() {
    return objectInputStream;
  }

  public ObjectOutputStream getObjectOutputStream() {
    return objectOutputStream;
  }
}
