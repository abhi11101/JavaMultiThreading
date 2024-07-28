package com.abhi.Section6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class S02_Deadlock {

    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        S02_Deadlock obj = new S02_Deadlock();

        new Thread(obj::worker1,"worker 1").start();
        new Thread(obj::worker2,"worker 2").start();
    }

    public void worker1(){
        lock1.lock();
        System.out.println("worker1 acquires lock 1...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock2.lock();
        System.out.println("worker1 acquires lock 2...");

        lock1.unlock();
        lock2.unlock();

    }

    public void worker2(){
        lock2.lock();
        System.out.println("worker2 acquires lock 2...");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lock1.lock();
        System.out.println("worker2 acquires lock 1...");

        lock2.unlock();
        lock1.unlock();
    }

}
