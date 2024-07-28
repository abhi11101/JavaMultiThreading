package com.abhi.Section4;

public class S04_ThreadWaitJoin {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runner 1: " + i);
            }
        });

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runner 2: " + i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Thread ended");

    }
}


