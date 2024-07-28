package com.abhi.Section7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class S01_SingleThreadExecutor {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {

            executorService.execute(new Task(i));
        }
        executorService.shutdown();
    }


}

class Task implements Runnable {

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("task with id: " + id + " is in work -thread id: " + Thread.currentThread().getName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}