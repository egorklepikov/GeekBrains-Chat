package com.geekbrains.practice.core;

import com.geekbrains.practice.model.Client;

import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientsHandler {
  private static ClientsHandler instance;
  private final CopyOnWriteArrayList<Client> clients;

  private ClientsHandler() {
    clients = new CopyOnWriteArrayList<>();
  }

  public static ClientsHandler getInstance() {
    if (instance == null) {
      instance = new ClientsHandler();
    }
    return instance;
  }

  public CopyOnWriteArrayList<Client> getClients() {
    return clients;
  }

  public void addClient(Socket socket) {
    clients.add(new Client(socket));
  }
}
