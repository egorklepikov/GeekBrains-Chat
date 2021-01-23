package com.geekbrains.practice.listeners;

import com.geekbrains.practice.model.User;
import com.geekbrains.practice.services.HardcodedAuthService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UserLoaderListener implements IListener {
  private final Socket socket;
  private final FutureTask<User> future;

  public UserLoaderListener(Socket socket) {
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
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final HardcodedAuthService hardcodedAuthService;

    public UserLoader() {
      hardcodedAuthService = new HardcodedAuthService();
      try {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public User call() {
      if (!socket.isClosed()) {
        try {
          User user;
          String[] credentials = objectInputStream.readUTF().split("\\|");
          String phoneNumber = credentials[0];
          String userName = credentials[1];
          if (hardcodedAuthService.isUserExist(phoneNumber, userName)) {
            user = hardcodedAuthService.getUserByPhoneAndName(phoneNumber, userName);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            return user;
          } else {
            objectOutputStream.writeObject(null);
            objectOutputStream.flush();
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return null;
    }
  }
}