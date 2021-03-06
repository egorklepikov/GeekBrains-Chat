package com.geekbrains.practice.ui.register;

import com.geekbrains.practice.network.UserController;
import com.geekbrains.practice.ui.MainScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
  @FXML
  private Label userNotFound;
  @FXML
  private Button continueButton;
  @FXML
  private GridPane mainPane;
  @FXML
  private TextField userName;
  @FXML
  private TextField phoneNumberField;
  @FXML
  private ImageView closeButton;

  private double xOffset;
  private double yOffset;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    closeButton.setImage(new Image("/assets/close_button.jpg"));
    continueButton.setDisable(true);
  }

  @FXML
  public void onMouseClickCloseButtonListener() {
    System.exit(0);
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
  public void onMousePressedTopPanel(MouseEvent mouseEvent) {
    xOffset = mainPane.getScene().getWindow().getX() - mouseEvent.getScreenX();
    yOffset = mainPane.getScene().getWindow().getY() - mouseEvent.getScreenY();
  }

  @FXML
  public void onMouseDraggedTopPanel(MouseEvent mouseEvent) {
    mainPane.getScene().getWindow().setX(mouseEvent.getScreenX() + xOffset);
    mainPane.getScene().getWindow().setY(mouseEvent.getScreenY() + yOffset);
  }

  @FXML
  public void onActionContinueButton() throws IOException {
    boolean isUserLoaded = UserController.getInstance().loadUser(phoneNumberField.getText(), userName.getText());
    if (!isUserLoaded) {
      userNotFound.setVisible(true);
      return;
    }
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/geekbrains/practice/ui/main_scene.fxml"));
    Parent root = fxmlLoader.load();
    MainScene.getPrimaryStage().hide();
    MainScene.getPrimaryStage().setScene(new Scene(root));
    MainScene.getPrimaryStage().show();
  }

  @FXML
  public void onKeyPressedPhoneNumber() {
    checkContinueButtonAccessibility();
  }

  @FXML
  public void onKeyPressedUserName() {
    checkContinueButtonAccessibility();
  }

  private void checkContinueButtonAccessibility() {
    continueButton.setDisable(userName.getText().isEmpty() || phoneNumberField.getText().isEmpty());
  }
}
