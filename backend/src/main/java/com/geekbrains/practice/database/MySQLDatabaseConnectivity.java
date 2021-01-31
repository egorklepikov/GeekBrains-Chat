package com.geekbrains.practice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnectivity implements IDatabaseConnectivity {
  private Connection connection;

  @Override
  public void connect() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection(MySQLProperties.DB_URL, MySQLProperties.LOGIN, MySQLProperties.PASSWORD);
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
