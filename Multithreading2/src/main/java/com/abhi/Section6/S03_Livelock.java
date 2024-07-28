package com.abhi.Section6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class S03_Livelock {

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        S03_Livelock obj = new S03_Livelock();

        new Thread(obj::worker1,"worker 1").start();
        new Thread(obj::worker2,"worker 2").start();

    }

    private void worker1(){

        while (true){
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker 1 acquires the lock1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Worker1 tries to get lock2");
            if (lock2.tryLock()){
                System.out.println("Worker1 gets lock2");
                lock2.unlock();
            }else {
                System.out.println("worker1 cannot get lock2");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();

    }

    private void worker2(){
        while (true){
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker 2 acquires the lock2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Worker2 tries to get lock1");
            if (lock1.tryLock()){
                System.out.println("Worker2 tries to gets lock1");
                lock1.unlock();
            }else {
                System.out.println("worker2 cannot get lock1");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }
}
