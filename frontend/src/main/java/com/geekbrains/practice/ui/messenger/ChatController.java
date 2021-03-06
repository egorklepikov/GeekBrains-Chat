package com.geekbrains.practice.ui.messenger;

import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.network.UserController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
  @FXML
  private TextField newConversationTextField;
  @FXML
  private ScrollPane scrollVBoxPane;
  @FXML
  private AnchorPane notificationLabelPane;
  @FXML
  private VBox chats;
  @FXML
  private AnchorPane bottomPane;
  @FXML
  private GridPane mainPane;
  @FXML
  private ImageView closeButton;
  @FXML
  private ImageView sendButton;
  @FXML
  private TextArea messagesArea;
  @FXML
  private TextField inputMessageField;

  private double xOffset;
  private double yOffset;

  @FXML
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    sendButton.setImage(new Image("/assets/send_message.png"));
    closeButton.setImage(new Image("/assets/close_button.jpg"));
    bottomPane.setVisible(false);
    scrollVBoxPane.setFitToWidth(true);
    scrollVBoxPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollVBoxPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    Platform.runLater(() -> messagesArea.requestFocus());
    addChatsToVBox();
    new ChatsListener().start();
  }

  @FXML
  public void sendMessageWithField(KeyEvent keyEvent) {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      sendMessage();
      updateTextArea();
    } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
      inputMessageField.clear();
      inputMessageField.requestFocus();
    }
  }

  private void updateTextArea() {
    StringBuilder message = new StringBuilder();
    message.
      append("[").
      append(UserController.getInstance().getUser().getUserName()).
      append("] : ").
      append(inputMessageField.getText()).
      append("\n"
    );
    messagesArea.appendText(message.toString());
    UserController.getInstance().getSelectedChat().getMessages().add(message.toString());
    ChatFragment chatFragment = UserController.getInstance().getSelectedChat().getFxmlLoader().getController();
    chatFragment.setLastMessage(message.toString());
    inputMessageField.clear();
    inputMessageField.requestFocus();
  }

  @FXML
  public void enterMouseListener() {
    sendButton.setScaleX(1.1);
    sendButton.setScaleY(1.1);
  }

  @FXML
  public void exitMouseListener() {
    sendButton.setScaleX(1.0);
    sendButton.setScaleY(1.0);
  }

  @FXML
  public void clickedMouseListener() {
    updateTextArea();
    sendMessage();
  }

  private void sendMessage() {
    StringBuilder message = new StringBuilder();
    message.
      append("[").
      append(UserController.getInstance().getUser().getUserName()).
      append("] : ").
      append(inputMessageField.getText()).
      append("\n"
      );
    UserController.getInstance().sendMessage(
      UserController.getInstance().getUser().getUserName(),
      UserController.getInstance().getUser().getPhoneNumber(),
      message.toString(),
      "",
      UserController.getInstance().getSelectedChat().getChatName()
    );
  }

  @FXML
  public void onMouseEnteredExit() {
    closeButton.setScaleX(1.1);
    closeButton.setScaleY(1.1);
  }

  @FXML
  public void onMouseExitedExit() {
    closeButton.setScaleX(1.0);
    closeButton.setScaleY(1.0);
  }

  @FXML
  public void onMouseClickedExit() {
    System.exit(0);
  }

  @FXML
  public void onMousePressedTopPanel(MouseEvent mouseEvent) {
    xOffset = mainPane.getScene().getWindow().getX() - mouseEvent.getScreenX();
    yOffset = mainPane.getScene().getWindow().getY() - mouseEvent.getScreenY();
  }

  @FXML
  public void onMouseDraggedTopPanel(MouseEvent mouseEvent) {
    mainPane.getScene().getWindow().setX(mouseEvent.getScreenX() + xOffset);
    mainPane.getScene().getWindow().setY(mouseEvent.getScreenY() + yOffset);
  }

  public AnchorPane getBottomPane() {
    return bottomPane;
  }

  public AnchorPane getNotificationLabelPane() {
    return notificationLabelPane;
  }

  public TextArea getMessagesArea() {
    return messagesArea;
  }

  private void addChatsToVBox() {
    int fragmentIndex = 0;
    chats.getChildren().clear();
    for (Chat chat : UserController.getInstance().getChats()) {
      fragmentIndex = addChatFragment(chat, fragmentIndex);
    }
  }

  private int addChatFragment(Chat chat, int fragmentIndex) {
    try {
      Node node = chat.getFxmlLoader().load();
      if (chat.getFxmlLoader().getController() instanceof ChatFragment) {
        ChatFragment chatFragment = chat.getFxmlLoader().getController();
        chatFragment.setChatController(ChatController.this);
        chatFragment.setFragmentIndex(fragmentIndex);
        chatFragment.initialize();
        fragmentIndex++;
      }
      chats.getChildren().add(node);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return fragmentIndex;
  }

  public void onNewConversationKeyPressed(KeyEvent keyEvent) {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      addChatFragment(
        UserController.getInstance().addNewChat(newConversationTextField.getText()),
        UserController.getInstance().getChats().size() - 1
      );
      //TODO some network staff
      newConversationTextField.setText("");
      messagesArea.requestFocus();
    }
  }

  private class ChatsListener extends Thread {
    @Override
    public void run() {
      while (true) {
        String[] message = UserController.getInstance().getMessage().split("\\|");
        String senderPhoneNumber = message[1];
        String messageContent = message[2];
        System.out.println(messageContent);
        for (Chat chat : UserController.getInstance().getChats()) {
          if (chat.getChatName().equals(senderPhoneNumber)) {
            chat.getMessages().add(messageContent);
            if (UserController.getInstance().getSelectedChat() != null) {
              messagesArea.appendText(messageContent);
            }
          }
        }
      }
    }
  }
}
