package com.abhi.ThreadCreation.UsingThreadClass;

public class DragonBallMain {

    public static void main(String[] args) {
        System.out.println("Main thread started");

        GokuThread gokuThread = new GokuThread();
        gokuThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main thread ended");
    }

}
