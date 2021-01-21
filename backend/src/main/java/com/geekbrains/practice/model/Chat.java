package com.geekbrains.practice.model;

import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Chat {
  private final String chatName;
  private final Image chatIcon;
  private final ArrayList<String> messages;
  private final FXMLLoader fxmlLoader;

  public Chat(String chatName, Image chatIcon, ArrayList<String> messages, FXMLLoader fxmlLoader) {
    this.chatName = chatName;
    this.chatIcon = chatIcon;
    this.messages = messages;
    this.fxmlLoader = fxmlLoader;
  }

  public String getChatName() {
    return chatName;
  }

  public ArrayList<String> getMessages() {
    return messages;
  }

  public String getLastMessage() {
    if (messages == null || messages.isEmpty()) {
      return "";
    }
    return messages.get(messages.size() - 1);
  }

  public FXMLLoader getFxmlLoader() {
    return fxmlLoader;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Chat chat = (Chat) o;
    return Objects.equals(chatName, chat.chatName) && Objects.equals(chatIcon, chat.chatIcon) && Objects.equals(messages, chat.messages) && Objects.equals(fxmlLoader, chat.fxmlLoader);
  }

  @Override
  public int hashCode() {
    return Objects.hash(chatName, chatIcon, messages, fxmlLoader);
  }
}
