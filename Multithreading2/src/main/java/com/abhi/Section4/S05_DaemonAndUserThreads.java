package com.abhi.Section4;

public class S05_DaemonAndUserThreads {

    public static void main(String[] args) {

        Thread deamonThread = new Thread(new DaemonThread());
        Thread userThread = new Thread(new UserThread());

        deamonThread.setDaemon(true);
        deamonThread.start();
        userThread.start();

    }
}

class DaemonThread implements Runnable {


    @Override
    public void run() {
        System.out.println("Starting DaemonThread");
        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("DaemonThread exiting");
        }
    }
}

class UserThread implements Runnable {


    @Override
    public void run() {
        System.out.println("Starting UserThread");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("UserThread exiting");
    }
}