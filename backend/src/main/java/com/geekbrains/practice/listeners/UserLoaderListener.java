package com.geekbrains.practice.listeners;

import com.geekbrains.practice.model.User;
import com.geekbrains.practice.services.H2AuthService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UserLoaderListener implements IListener {
  private final FutureTask<User> future;
  private final ObjectInputStream objectInputStream;
  private final ObjectOutputStream objectOutputStream;
  private final Socket socket;

  public UserLoaderListener(Socket socket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
    this.objectOutputStream = objectOutputStream;
    this.objectInputStream = objectInputStream;
    this.socket = socket;
    UserLoader userLoader = new UserLoader();
    future = new FutureTask(userLoader);
  }

  public User getUser() {
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void startListening() {
    future.run();
  }

  private class UserLoader implements Callable<User> {
    private final H2AuthService h2AuthService;

    public UserLoader() {
      h2AuthService = new H2AuthService();
    }

    @Override
    public User call() {
      if (!socket.isClosed()) {
        try {
          User user;
          String[] credentials = objectInputStream.readUTF().split("\\|");
          String phoneNumber = credentials[0];
          String userName = credentials[1];
          user = h2AuthService.getUserByPhoneAndName(phoneNumber, userName);
          objectOutputStream.writeObject(user);
          objectOutputStream.flush();
          return user;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return null;
    }
  }
}