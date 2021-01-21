package com.geekbrains.practice.services;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class UserCheckerAuthService extends Thread implements IAuthService {
  private final Socket socket;
  private PrintWriter printWriter;
  private Scanner scanner;

  public UserCheckerAuthService(Socket socket) {
    this.socket = socket;
    try {
      scanner = new Scanner(new InputStreamReader(socket.getInputStream()));
      printWriter = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void startListening() {
    start();
  }

  @Override
  public void run() {
    while (!socket.isClosed()) {
      String phoneNumber;
      while((phoneNumber = scanner.nextLine()) != null) {
        System.out.println(phoneNumber);
        if (phoneNumber.equals("")) {
          printWriter.println("true");
        } else {
          printWriter.println("false");
        }
      }
    }
  }
}
