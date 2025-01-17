package com.abhi.Section6;

public class S01_Volatile {

    public static volatile int count = 0;

    public static void incrementCounter(){

        for (int i=0;i<10000;i++){
            count++;
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            incrementCounter();
        });

        Thread t2 = new Thread(()->{
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

        System.out.println(count);
    }
}
