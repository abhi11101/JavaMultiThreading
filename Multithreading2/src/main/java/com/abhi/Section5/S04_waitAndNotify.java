package com.abhi.Section5;

public class S04_waitAndNotify {

    public static void main(String[] args) {

        S04_waitAndNotify process = new S04_waitAndNotify();

        Thread t1 = new Thread(()->{
            process.produce();
        });

        Thread t2 = new Thread(()->{
            process.consume();
        });

        t1.start();
        t2.start();

    }

    public void produce(){
        synchronized (this){
            System.out.println("Running the produce method");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Again in the produce method");
        }
    }

    public void consume(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this){
            System.out.println("Running the consume method");
            notify();
            System.out.println("Waiting for 5 seconds after notify");
            for (int i=1;i<=5;i++){
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
