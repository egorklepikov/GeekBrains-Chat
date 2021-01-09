package com.geekbrains.practice.ui;

import com.geekbrains.practice.model.Chat;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatsLoader {
  private static ChatsLoader chatsLoader;
  private final CopyOnWriteArrayList<Chat> chats;
  private int selectedChatIndex;

  private ChatsLoader() {
    chats = new CopyOnWriteArrayList<>();
  }

  public static ChatsLoader getInstance() {
    if (chatsLoader == null) {
      chatsLoader = new ChatsLoader();
    }
    return chatsLoader;
  }

  public void loadChats() {
    //TODO some network staff
  }

  public CopyOnWriteArrayList<Chat> getChats() {
    return chats;
  }

  public Chat addNewChat() {
    Chat chat = new Chat(
      "TEST",
      null,
      new ArrayList<>(),
      new FXMLLoader(getClass().getResource("chat_fragment.fxml"))
    );
    chats.add(chat);
    return chat;
  }

  public void setSelectedChatIndex(int selectedChatIndex) {
    this.selectedChatIndex = selectedChatIndex;
  }

  public Chat getSelectedChat() {
    return chats.get(selectedChatIndex);
  }
}
