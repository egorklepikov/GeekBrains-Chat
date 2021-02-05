package com.geekbrains.practice;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class UserDataLoader {
  private User user;
  private String historyLocation;

  public UserDataLoader(User user) {
    this.user = user;
    this.historyLocation = "./src/localhistory/" + user.getPhoneNumber().replaceAll("\\s+", "") + user.getUserName().replaceAll("\\s+", "");
  }

  public void saveLocalHistory(String chatName) {
    if (prepareFolder()) {
      for (Chat chat : user.getChats()) {
        try (
          FileOutputStream fileOutputStream = new FileOutputStream("src/localhistory/" + user.getPhoneNumber().replaceAll("\\s+","") + user.getUserName().replaceAll("\\s+","") + "/" + chat.getChatName());
          ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))  {
          objectOutputStream.writeObject(chat);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private boolean prepareFolder() {
    try {
      File file = new File("src/localhistory/" + user.getPhoneNumber().replaceAll("\\s+","") + user.getUserName().replaceAll("\\s+",""));
      FileUtils.deleteDirectory(file);
      if (file.mkdir()) {
        return true;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
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
