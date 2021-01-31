package com.geekbrains.practice.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2InMemoryPreparation {
  private Statement statement;

  public H2InMemoryPreparation(Connection connection) {
    try {
      statement = connection.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void createTable() {
    try {
      statement.execute("CREATE TABLE USER(" +
        "PHONE_NUMBER VARCHAR (20), " +
        "NAME VARCHAR (20)," +
        "SELECTED_CHAT_INDEX INT);");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    System.out.println("Table USER was created.");
  }

  public void addUsers() {
    try {
      statement.executeUpdate("INSERT INTO USER VALUES('+7 913 021 22 37', 'Egor Klepikov', 1)");
      statement.executeUpdate("INSERT INTO USER VALUES('+7 913 120 22 73', 'Sergey', 1)");
      System.out.println("Users were added");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
