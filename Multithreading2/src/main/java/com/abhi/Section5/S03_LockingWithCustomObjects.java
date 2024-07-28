package com.abhi.Section5;

public class S03_LockingWithCustomObjects {

    public static int counter1 =0;
    public static int counter2 =0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void incrementCounter1() {
        synchronized (lock1){
            counter1++;
        }
    }

    public static void incrementCounter2() {
        synchronized (lock2){
            counter2++;
        }
    }

    public static void main(String[] args) {
        synchronized_process();
    }

    public static void synchronized_process(){

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                incrementCounter1();
            }
        });

        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                incrementCounter2();
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

        System.out.println("value of counter1 = " + counter1);
        System.out.println("value of counter2 = " + counter2);
    }
}
