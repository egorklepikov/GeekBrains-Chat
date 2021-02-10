package com.geekbrains.practice.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsExecutionService {
  private static ThreadsExecutionService threadsManager;
  private final ExecutorService executorService;

  private ThreadsExecutionService() {
    executorService = Executors.newFixedThreadPool(12);
  }

  public static ThreadsExecutionService getInstance() {
    if (threadsManager == null) {
      threadsManager = new ThreadsExecutionService();
    }
    return threadsManager;
  }

  public void addTaskToExecutionsQueue(Runnable runnable) {
    executorService.submit(runnable);
  }

  public void shutdown() {
    executorService.shutdown();
  }

  public boolean isTerminated() {
    return executorService.isTerminated();
  }

  public boolean isShutdown() {
    return executorService.isShutdown();
  }
}
