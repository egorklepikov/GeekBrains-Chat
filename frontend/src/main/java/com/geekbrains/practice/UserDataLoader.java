package com.geekbrains.practice;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;
import com.geekbrains.practice.network.UserController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class UserDataLoader {
  private final User user;
  private final String historyLocation;

  public UserDataLoader(User user) {
    this.user = user;
    this.historyLocation = "./src/localhistory/" + user.getPhoneNumber().replaceAll("\\s+", "") + user.getUserName().replaceAll("\\s+", "");
  }

  public void saveLocalHistory(String chatName) {
    if (prepareFolder(chatName)) {
      Chat chat = UserController.getInstance().getChatByName(chatName);
      try (
        FileOutputStream fileOutputStream = new FileOutputStream(historyLocation + "/" + chat.getChatName());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
        objectOutputStream.writeObject(chat);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean prepareFolder(String chatName) {
    return new File(historyLocation + "/" + chatName).delete();
  }

  public void loadLocalHistory() {
    File file = new File(historyLocation);
    if (!file.exists() || !file.isDirectory()) {
      return;
    }
    ArrayList<File> chatsHistory = (ArrayList<File>) Arrays.asList(Objects.requireNonNull(file.listFiles()));
    for (File chat : chatsHistory) {
      try (FileInputStream fileInputStream = new FileInputStream(chat); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
        user.getChats().add((Chat) objectInputStream.readObject());
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
