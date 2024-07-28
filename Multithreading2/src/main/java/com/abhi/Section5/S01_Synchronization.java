package com.abhi.Section5;

public class S01_Synchronization {

    public static int counter =0;

    public static synchronized void incrementCounter() {
        counter++;
    }

    public static void main(String[] args) {
        process();
        counter=0;
        synchronized_process();
    }

    public static void process(){

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        });

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter++;
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

        System.out.println("value of counter without synchronization = " + counter);
    }
    public static void synchronized_process(){

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                incrementCounter();
            }
        });

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                incrementCounter();
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

        System.out.println("value of counter with synchronization = " + counter);
    }
}
