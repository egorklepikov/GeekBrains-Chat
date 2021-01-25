package com.geekbrains.practice.network;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
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
  }

  public String getMessage() {
    return networkHandler.getMessage();
  }
}
