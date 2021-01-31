package com.geekbrains.practice.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseConnectivity {
  void connect() throws ClassNotFoundException, SQLException;
  void disconnect() throws SQLException;
  boolean isConnected();
  Connection getConnection();
}
