package com.abhi.ThreadCreation;

public class ThreadMain {

    public static void main(String[] args) {

        Thread thread = new Thread(()-> {
            System.out.println("Inside thread " + Thread.currentThread().getName());
        });

        thread.setName("New_Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Before starting " + thread.getName());
        thread.start();
        System.out.println("Priority "+thread.getPriority());
        System.out.println("After starting " + thread.getName());

        threadException();
    }

    public static void threadException() {

        Thread thread = new Thread(()->{

            throw new RuntimeException("Intentional Exception");
        });

        thread.setName("Misbehaving thread");

        thread.setUncaughtExceptionHandler((t, e)->{

            System.out.println("Error happened in thread " + thread.getName() + " the error is " + e.getMessage());
        });
        thread.start();
    }
}
