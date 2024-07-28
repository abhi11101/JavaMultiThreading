package com.abhi.Section4;

public class S03_ThreadClass {

    public static void main(String[] args) {

        Thread runner1 = new ThreadClass_Runner1();
        ThreadClass_Runner2 runner2 = new ThreadClass_Runner2();

        runner1.start();
        runner2.start();
    }
}

class ThreadClass_Runner1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner 1: " + i);
        }
    }
}

class ThreadClass_Runner2 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner 2: " + i);
        }
    }
}
