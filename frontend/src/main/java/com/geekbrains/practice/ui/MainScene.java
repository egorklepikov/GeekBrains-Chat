package com.geekbrains.practice.ui;

import com.geekbrains.practice.network.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainScene extends Application {
  private static Stage primaryStage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader;
    if (UserController.getInstance().isUserRegistered()) {
      UserController.getInstance().loadUser();
      fxmlLoader = new FXMLLoader(getClass().getResource("main_scene.fxml"));
    } else {
      fxmlLoader = new FXMLLoader(getClass().getResource("register_scene.fxml"));
    }
    Parent root = fxmlLoader.load();
    primaryStage.initStyle(StageStyle.UNDECORATED);
    primaryStage.setResizable(false);
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
    setPrimaryStage(primaryStage);
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void setPrimaryStage(Stage primaryStage) {
    MainScene.primaryStage = primaryStage;
  }
}
