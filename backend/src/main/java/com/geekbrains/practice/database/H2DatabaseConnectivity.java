package com.geekbrains.practice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DatabaseConnectivity implements IDatabaseConnectivity {
  private Connection connection;

  @Override
  public void connect() throws ClassNotFoundException, SQLException {
    Class.forName ("org.h2.Driver");
    connection = DriverManager.getConnection(H2Properties.DB_URL, H2Properties.LOGIN, H2Properties.PASSWORD);
    System.out.println("Connection to H2 db succesfull!");
  }

  @Override
  public void disconnect() throws SQLException {
    connection.close();
  }

  @Override
  public boolean isConnected() {
    try {
      return !connection.isClosed();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return false;
  }

  @Override
  public Connection getConnection() {
    return connection;
  }
}
