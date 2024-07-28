package com.abhi.Section4;

public class S02_Runnable {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable_Runner1());
        Thread thread2 = new Thread(new Runnable_Runner2());

        thread1.start();
        thread2.start();

    }
}


class Runnable_Runner1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner 1: " + i);
        }
    }
}

class Runnable_Runner2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runner 2: " + i);
        }
    }
}