package com.geekbrains.practice.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInitializer {
  private static DatabaseInitializer databaseInitializer;
  private IDatabaseConnectivity iDatabaseConnectivity;

  private DatabaseInitializer() { }

  public void initialize() {
    iDatabaseConnectivity = new H2DatabaseConnectivity();
    try {
      iDatabaseConnectivity.connect();
      H2InMemoryPreparation h2InMemoryPreparation = new H2InMemoryPreparation(iDatabaseConnectivity.getConnection());
      h2InMemoryPreparation.createTable();
      h2InMemoryPreparation.addUsers();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  public static DatabaseInitializer getInstance() {
    if (databaseInitializer == null) {
      databaseInitializer = new DatabaseInitializer();
    }
    return databaseInitializer;
  }

  public Connection getConnection () {
    return iDatabaseConnectivity.getConnection();
  }
}
