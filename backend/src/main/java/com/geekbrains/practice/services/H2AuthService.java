package com.geekbrains.practice.services;

import com.geekbrains.practice.database.DatabaseInitializer;
import com.geekbrains.practice.model.Chat;
import com.geekbrains.practice.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class H2AuthService implements IAuthService {

  public H2AuthService() {
  }

  @Override
  public boolean isUserExist(String phoneNumber, String userName) {
    return false;
  }

  @Override
  public User getUserByPhoneAndName(String phoneNumber, String userName) {
    String query = "SELECT * FROM USER WHERE PHONE_NUMBER=?;";
    try {
      PreparedStatement preparedStatement = DatabaseInitializer.getInstance().getConnection().prepareStatement(query);
      preparedStatement.setString(1, phoneNumber);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String number = resultSet.getString("PHONE_NUMBER");
        String name = resultSet.getString("NAME");
        int selectedChatIndex = resultSet.getInt("SELECTED_CHAT_INDEX");
        return createUser(number, name, selectedChatIndex);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return null;
  }

  private User createUser(String number, String name, int selectedChatIndex) {
    CopyOnWriteArrayList<Chat> chats = new CopyOnWriteArrayList<>();
    ArrayList<String> firstChatMessages = new ArrayList<>();
    firstChatMessages.add("TEST");
    //Chats hardcoded due to a complex logic. Do not have enought time to implement it...
    chats.add(new Chat("+ 7 913 120 22 73", null, firstChatMessages, null));
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
    return new User(number, name, chats);
  }
}
