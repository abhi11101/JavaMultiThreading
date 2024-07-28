package com.abhi.Section5;

public class S02_SynchronizedBlock {

    public static int counter1 =0;
    public static int counter2 =0;

    public static void incrementCounter1() {
        synchronized (S02_SynchronizedBlock.class){
            counter1++;
        }
    }

    public static void incrementCounter2() {
        synchronized (S02_SynchronizedBlock.class){
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
