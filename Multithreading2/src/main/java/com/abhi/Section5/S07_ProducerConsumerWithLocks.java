package com.abhi.Section5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class S07_ProducerConsumerWithLocks {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producing data");
        condition.await();
        System.out.println("Again producing data");
        lock.unlock();
    }

    public void consume() throws InterruptedException {

        Thread.sleep(2000);
        lock.lock();
        System.out.println("Consuming data");
        Thread.sleep(3000);
        condition.signal();
        lock.unlock();

    }


    public static void main(String[] args) {

        S07_ProducerConsumerWithLocks process = new S07_ProducerConsumerWithLocks();

        Thread t1 = new Thread(() -> {
            try {
                process.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        Thread t2 = new Thread(() -> {
            try {
                process.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
    }
}
