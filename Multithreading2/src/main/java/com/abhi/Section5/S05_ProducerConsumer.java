package com.abhi.Section5;

import java.util.ArrayList;
import java.util.List;

public class S05_ProducerConsumer {

    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private static final int LOWER_LIMIT = 0;
    private final Object lock = new Object();
    private int value=1;

    public void producer() throws InterruptedException {
        synchronized (lock){
            while (true){
                if (list.size() == UPPER_LIMIT){
                    System.out.println("Waiting for removing items..");
                    value=1;
                    lock.wait();
                }
                else {
                    System.out.println("adding item: " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {

        synchronized (lock){
            while (true){

                if (list.size() == LOWER_LIMIT){
                    System.out.println("Waiting for adding items..");
                    lock.wait();
                }
                else {
                    System.out.println("Removing item: " + list.get(list.size()-1));
                    list.remove(list.size()-1);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }

    }

    public static void main(String[] args) {

        S05_ProducerConsumer process = new S05_ProducerConsumer();

        Thread produce = new Thread(()->{
            try {
                process.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consume = new Thread(()->{
            try {
                process.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        produce.setName("producer thread");
        consume.setName("consumer thread");
        produce.start();
        consume.start();


    }
}
