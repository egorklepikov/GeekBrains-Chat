package com.geekbrains.practice.services;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class HardcodedUsersFactory {
  public static User createFirstUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 923 120 22 73", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 1", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 2", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 3", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 4", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 5", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 6", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 7", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 8", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 9", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 10", null, firstChatMessages, null));
    chats.add(new Chat("+ 7 923 120 22 11", null, firstChatMessages, null));
    return new User("+ 7 913 021 22 37", "Egor Klepikov", chats);
  }

  public static User createSecondUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 73", "Sergey Gavrilov", chats);
  }

  public static User createThirdUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 1", "Test1", chats);
  }

  public static User createFourUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 2", "Test2", chats);
  }

  public static User createFiveUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 3", "Test3", chats);
  }

  public static User createSixUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 4", "Test4", chats);
  }

  public static User createSevenUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 5", "Test5", chats);
  }

  public static User createEightUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 6", "Test6", chats);
  }

  public static User createNineUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 7", "Test7", chats);
  }

  public static User createTenUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 8", "Test8", chats);
  }

  public static User createElevenUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 9", "Test9", chats);
  }

  public static User createTwelveUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 10", "Test10", chats);
  }

  public static User createThirteenUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 11", "Test11", chats);
  }
}
