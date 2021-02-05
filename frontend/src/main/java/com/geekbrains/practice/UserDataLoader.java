package com.geekbrains.practice;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;
import com.geekbrains.practice.network.UserController;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class UserDataLoader {
  private final User user;
  private final String historyLocation;

  public UserDataLoader(User user) {
    this.user = user;
    this.historyLocation = ".\\frontend\\src\\localhistory\\" + user.getPhoneNumber().replaceAll("\\s+", "") + user.getUserName().replaceAll("\\s+", "");
  }

  public void saveLocalHistory(String chatName) {
    if (prepareFolder(chatName.replaceAll("\\s+", ""))) {
      Chat chat = UserController.getInstance().getChatByName(chatName);
      try (
        FileOutputStream fileOutputStream = new FileOutputStream(historyLocation + "\\" + chat.getChatName().replaceAll("\\s+", ""));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
        objectOutputStream.writeObject(chat);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean prepareFolder(String chatName) {
    createUserFolderIfNotExist();
    File file = new File(historyLocation + "\\" + chatName.replaceAll("\\s+", ""));
    if (!file.exists()) {
      return true;
    } else {
      return file.delete();
    }
  }

  private void createUserFolderIfNotExist() {
    File file = new File(historyLocation);
    if (!file.exists()) {
      file.mkdir();
    }
  }

  public void loadLocalHistory() {
    File file = new File(historyLocation);
    if (!file.exists() || !file.isDirectory()) {
      return;
    }
    File[] chatsHistory = file.listFiles();
    for (File chat : Objects.requireNonNull(chatsHistory)) {
      try (FileInputStream fileInputStream = new FileInputStream(chat); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
        Chat loadedChat = (Chat) objectInputStream.readObject();
        updateUserChat(loadedChat);
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  private void updateUserChat(Chat loadedChat) {
    for (int chatIndex = 0; chatIndex < user.getChats().size(); chatIndex++) {
      if (loadedChat.getChatName().equals(user.getChats().get(chatIndex).getChatName())) {
        user.getChats().set(chatIndex, loadedChat);
      }
    }
  }
}
