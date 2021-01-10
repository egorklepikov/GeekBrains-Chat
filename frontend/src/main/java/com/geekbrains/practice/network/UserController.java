package com.geekbrains.practice.network;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;
import javafx.fxml.FXMLLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserController {
  private static UserController userController;
  private User user;

  private UserController() {
  }

  public static UserController getInstance() {
    if (userController == null) {
      userController = new UserController();
    }
    return userController;
  }

  public boolean loadUser() {
    String phoneNumber = loadUserPhoneNumber();
    return false;
  }

  public void initializeUser(String phoneNumber, String userName) {
    user = new User(phoneNumber, userName, new CopyOnWriteArrayList<>());
    saveUserPhoneNumber();
    //TODO: some network staff
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

  private void saveUserPhoneNumber() {
    try {
      FileOutputStream fileOutputStream = new FileOutputStream("user_info.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(user);
      objectOutputStream.flush();
      objectOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String loadUserPhoneNumber() {
    try {
      FileInputStream fileInputStream = new FileInputStream("user_info.txt");
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      User user = (User) objectInputStream.readObject();
      objectInputStream.close();
      return user.getPhoneNumber();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return "";
  }
}
