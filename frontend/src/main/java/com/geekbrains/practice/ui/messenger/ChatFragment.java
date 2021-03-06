package com.geekbrains.practice.ui.messenger;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.network.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment {
  @FXML
  private Label lastMessage;
  @FXML
  private Label chatName;
  @FXML
  private AnchorPane chatPane;

  private Chat chat;

  private ChatController chatController;
  private int fragmentIndex;

  public void setChatController(ChatController chatController) {
    this.chatController = chatController;
  }

  public void setFragmentIndex(int fragmentIndex) {
    this.fragmentIndex = fragmentIndex;
  }

  public AnchorPane getChatPane() {
    return chatPane;
  }

  @FXML
  public void onChatSelected() {
    switchPanesVisibility();
    resetChatsStyle();
    try {
      addMessages();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    chatPane.setStyle("-fx-background-color: #009587");
    lastMessage.setStyle("-fx-text-fill: #ffffff");
    UserController.getInstance().setSelectedChatIndex(fragmentIndex);
  }

  private void addMessages() throws IllegalArgumentException {
    chatController.getMessagesArea().setText("");
    if (chat == null || chat.getMessages() == null) {
      throw new IllegalArgumentException();
    }
    List<String> lastMessages;
    if (chat.getMessages().size() <= 100) {
      lastMessages = chat.getMessages();
    } else {
      lastMessages = chat.getMessages().subList(chat.getMessages().size() - 100, chat.getMessages().size());
    }
    for (String message : lastMessages) {
      chatController.getMessagesArea().appendText(message + "\n");
    }
  }

  private void switchPanesVisibility() {
    chatController.getBottomPane().setVisible(true);
    chatController.getNotificationLabelPane().setVisible(false);
  }

  private void resetChatsStyle() {
    for (Chat chat : UserController.getInstance().getChats()) {
      if (chat.getFxmlLoader().getController() instanceof ChatFragment) {
        ChatFragment chatFragment = chat.getFxmlLoader().getController();
        chatFragment.getChatPane().setStyle("chat_fragment.css");
        chatFragment.getLastMessage().setStyle("chat_fragment.css");
      }
    }
  }

  public void initialize() {
    chat = UserController.getInstance().getChats().get(fragmentIndex);
    lastMessage.setText(chat.getLastMessage());
    chatName.setText(chat.getChatName());
  }

  public void setLastMessage(String lastMessage) {
    this.lastMessage.setText(lastMessage);
  }

  public Label getLastMessage() {
    return lastMessage;
  }
}
