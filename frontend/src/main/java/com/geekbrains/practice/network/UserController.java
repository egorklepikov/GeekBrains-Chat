package com.geekbrains.practice.network;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;
import javafx.fxml.FXMLLoader;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserController {
  private static UserController userController;
  private User user;
  private final NetworkHandler networkHandler;

  private UserController() {
    networkHandler = new NetworkHandler();
  }

  public static UserController getInstance() {
    if (userController == null) {
      userController = new UserController();
    }
    return userController;
  }

  public boolean loadUser(String phoneNumber, String userName) {
    user = networkHandler.loadUserByPhoneAndName(phoneNumber, userName);
    if (user == null) {
      return false;
    }
    loadLocalHistory();
    initialeFXMLLoaders();
    return true;
  }

  private void initialeFXMLLoaders() {
    for (Chat chat : user.getChats()) {
      chat.setFxmlLoader(new FXMLLoader(getClass().getResource("/com/geekbrains/practice/ui/chat_fragment.fxml")));
    }
  }

  public User getUser() throws IllegalArgumentException {
    if (user == null) {
      throw new IllegalArgumentException("User is not loaded! Call `loadUser` first.");
    }
    return user;
  }

  public Chat addNewChat(String phoneNumber) throws IllegalArgumentException {
    if (user == null) {
      throw new IllegalArgumentException("User is not loaded! Call `loadUser` first.");
    }
    Chat chat = new Chat(
      phoneNumber,
      null,
      new ArrayList<>(),
      new FXMLLoader(getClass().getResource("/com/geekbrains/practice/ui/chat_fragment.fxml"))
    );
    user.getChats().add(chat);
    return chat;
  }

  public void setSelectedChatIndex(int selectedChatIndex) throws IllegalArgumentException {
    if (user == null) {
      throw new IllegalArgumentException("User is not loaded! Call `loadUser` first.");
    }
    user.setSelectedChatIndex(selectedChatIndex);
  }

  public Chat getSelectedChat() throws IllegalArgumentException {
    if (user == null) {
      throw new IllegalArgumentException("User is not loaded! Call `loadUser` first.");
    }
    return user.getSelectedChat();
  }

  public CopyOnWriteArrayList<Chat> getChats() {
    if (user == null) {
      throw new IllegalArgumentException("User is not loaded! Call `loadUser` first.");
    }
    return user.getChats();
  }

  public void sendMessage(String senderName, String senderPhoneNumber, String message, String readerName, String readerPhoneNumber) {
    networkHandler.sendMessage(senderName, senderPhoneNumber, message, readerName, readerPhoneNumber);
    saveLocalHistory();
  }

  public String getMessage() {
    return networkHandler.getMessage();
  }

  public void saveLocalHistory() {
    if (prepareFolder()) {
      for (Chat chat : user.getChats()) {
        try (
          FileOutputStream fileOutputStream = new FileOutputStream("localhistory/" + user.getPhoneNumber().replaceAll("\\s+","") + user.getUserName().replaceAll("\\s+","") + "/" + chat.getChatName());
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
      File file = new File("localhistory/" + user.getPhoneNumber().replaceAll("\\s+","") + user.getUserName().replaceAll("\\s+",""));
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
    File file = new File("localhistory/" + user.getPhoneNumber().replaceAll("\\s+","") + user.getUserName().replaceAll("\\s+",""));
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
