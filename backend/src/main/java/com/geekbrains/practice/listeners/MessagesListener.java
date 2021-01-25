package com.geekbrains.practice.listeners;

import com.geekbrains.practice.core.ClientsHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessagesListener extends Thread implements IListener {
  private final Socket socket;
  private final ObjectInputStream objectInputStream;
  private final ObjectOutputStream objectOutputStream;

  public MessagesListener(Socket socket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
    this.objectOutputStream = objectOutputStream;
    this.objectInputStream = objectInputStream;
    this.socket = socket;
  }

  @Override
  public void startListening() {
    start();
  }

  @Override
  public void run() {
    MessagesReader messagesReader = new MessagesReader();
    messagesReader.start();
    try {
      messagesReader.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void sendMessage(String senderName, String senderPhoneNumber, String message) {
    Thread messageSenderThread = new Thread(() -> {
      try {
        //Формат сообщения для клиента. [Имя отправителя|Номер отправителя|Контент]
        objectOutputStream.writeUTF(senderName + "|" + senderPhoneNumber + "|" + message);
        objectOutputStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    messageSenderThread.setDaemon(true);
    messageSenderThread.start();
  }

  private class MessagesReader extends Thread {
    @Override
    public void run() {
      while (!socket.isClosed()) {
        //Формат сообщения с клиента. [Имя отправителя|Номер отправителя|Контент|Имя получателя|Номер получателя]
        try {
          String[] messageContent = objectInputStream.readUTF().split("\\|");
          String senderName = messageContent[0];
          String senderPhoneNumber = messageContent[1];
          String message = messageContent[2];
          String readerName = messageContent[3];
          String readerPhoneNumber = messageContent[4];
          ClientsHandler.getInstance().sendMessage(
            senderName,
            senderPhoneNumber,
            message,
            readerName,
            readerPhoneNumber
          );
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
