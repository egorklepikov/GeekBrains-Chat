package com.geekbrains.practice.ui;

import com.geekbrains.practice.model.Chat;
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
  private ScrollPane scrollVBoxPane;
  @FXML
  private ImageView newChatButton;
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
    newChatButton.setImage(new Image("/assets/new_chat.jpg"));
    bottomPane.setVisible(false);
    scrollVBoxPane.setFitToWidth(true);
    scrollVBoxPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollVBoxPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    Platform.runLater(() -> messagesArea.requestFocus());
    ChatsLoader.getInstance().loadChats();
    addChatsToVBox();
  }

  @FXML
  public void sendMessageWithField(KeyEvent keyEvent) {
    if (keyEvent.getCode() == KeyCode.ENTER) {
      messagesArea.appendText(inputMessageField.getText() + "\n");
      ChatsLoader.getInstance().getSelectedChat().getMessages().add(inputMessageField.getText());
      ChatFragment chatFragment = ChatsLoader.getInstance().getSelectedChat().getFxmlLoader().getController();
      chatFragment.setLastMessage(inputMessageField.getText());
      inputMessageField.clear();
      inputMessageField.requestFocus();
      //TODO some network staff
    } else if (keyEvent.getCode() == KeyCode.ESCAPE) {
      inputMessageField.clear();
      inputMessageField.requestFocus();
    }
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
    messagesArea.appendText(inputMessageField.getText() + "\n");
    ChatsLoader.getInstance().getSelectedChat().getMessages().add(inputMessageField.getText());
    ChatFragment chatFragment = ChatsLoader.getInstance().getSelectedChat().getFxmlLoader().getController();
    chatFragment.setLastMessage(inputMessageField.getText());
    inputMessageField.clear();
    inputMessageField.requestFocus();
    //TODO some network staff
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
    for (Chat chat : ChatsLoader.getInstance().getChats()) {
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

  public void clickedMouseNewChatListener() {
    addChatFragment(
      ChatsLoader.getInstance().addNewChat(),
      ChatsLoader.getInstance().getChats().size() - 1
    );
    //TODO some network staff
  }

  public void enterMouseNewChatListener() {
    newChatButton.setScaleX(1.1);
    newChatButton.setScaleY(1.1);
  }

  public void exitMouseNewChatListener() {
    newChatButton.setScaleX(1);
    newChatButton.setScaleY(1);
  }
}
