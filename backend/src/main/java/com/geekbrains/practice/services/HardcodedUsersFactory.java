package com.geekbrains.practice.services;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class HardcodedUsersFactory {
  public static User createFirstUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    firstChatMessages.add("[Egor Klepikov] : Hello!");
    firstChatMessages.add("[Sergey Gavrilov] : Hello!");
    firstChatMessages.add("[Egor Klepikov] : How are you?");
    firstChatMessages.add("[Sergey Gavrilov] : I'm fine, thanks!");
    chats.add(new Chat("+ 7 923 120 22 73", null, firstChatMessages, null));
    return new User("+ 7 913 021 22 37", "Egor Klepikov", chats);
  }

  public static User createSecondUser() {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    firstChatMessages.add("[Sergey Gavrilov] : Hello!");
    firstChatMessages.add("[Egor Klepikov] : Hello!");
    firstChatMessages.add("[Egor Klepikov] : How are you?");
    firstChatMessages.add("[Sergey Gavrilov] : I'm fine, thanks!");
    chats.add(new Chat("+ 7 913 021 22 37", null, firstChatMessages, null));
    return new User("+ 7 923 120 22 73", "Sergey Gavrilov", chats);
  }
}
