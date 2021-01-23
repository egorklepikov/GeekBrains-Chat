package com.geekbrains.practice.listeners;

import com.geekbrains.practice.core.ClientsHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessagesListener extends Thread implements IListener {
  private final Socket socket;

  public MessagesListener(Socket socket) {
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
    messagesReader.setDaemon(true);
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
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(senderName + "|" + senderPhoneNumber + "|" + message);
        dataOutputStream.flush();
        dataOutputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    messageSenderThread.setDaemon(true);
    messageSenderThread.start();
  }

  private class MessagesReader extends Thread {
    private ObjectInputStream objectInputStream;

    public MessagesReader() {
      try {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

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
