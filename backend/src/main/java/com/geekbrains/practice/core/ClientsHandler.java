package com.geekbrains.practice.core;

import com.geekbrains.practice.model.Client;
import com.geekbrains.practice.model.User;

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

  public void sendMessage(String senderName, String senderPhoneNumber, String message, String readerName, String readerPhoneNumber) {
    for (Client client : clients) {
      User currentUser = client.getUser();
      if (currentUser.getPhoneNumber().equals(readerPhoneNumber)) {
        client.getMessagesListener().sendMessage(senderName, senderPhoneNumber, message);
        return;
      }
    }
  }
}
