package com.geekbrains.practice.model;

import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class User {
  private String phoneNumber;
  private String userName;
  private CopyOnWriteArrayList<Chat> chats;
  private int selectedChatIndex;

  public User(String phoneNumber, String userName, CopyOnWriteArrayList<Chat> chats) {
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.chats = chats;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public CopyOnWriteArrayList<Chat> getChats() {
    return chats;
  }

  public void addChats(CopyOnWriteArrayList<Chat> chats) {
    this.chats = chats;
  }

  public void addChat(Chat chat) {
    this.chats.add(chat);
  }

  public void setSelectedChatIndex(int selectedChatIndex) {
    this.selectedChatIndex = selectedChatIndex;
  }

  public Chat getSelectedChat() {
    return chats.get(selectedChatIndex);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(userName, user.userName) && Objects.equals(chats, user.chats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumber, userName, chats);
  }
}
