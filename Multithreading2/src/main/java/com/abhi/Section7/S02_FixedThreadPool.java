package com.abhi.Section7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class S02_FixedThreadPool {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 100; i++) {

            int finalI = i;
            executorService.execute(()->{

                System.out.println("Task with id " + finalI + " is in work - thread id: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });

        }

        executorService.shutdown();
    }
}
