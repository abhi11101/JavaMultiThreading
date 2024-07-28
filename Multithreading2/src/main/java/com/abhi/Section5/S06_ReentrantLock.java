package com.abhi.Section5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class S06_ReentrantLock {

    private static int counter=0;
    private static Lock lock = new ReentrantLock();

    private static void incrementCounter() {
        lock.lock();
        try {
            for (int i=1;i<=10000;i++){
                counter++;
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            incrementCounter();
        });

        Thread t2 = new Thread(() -> {
            incrementCounter();
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter);
    }
}
