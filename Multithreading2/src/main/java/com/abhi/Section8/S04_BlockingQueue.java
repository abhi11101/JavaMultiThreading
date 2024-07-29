package com.abhi.Section8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class S04_BlockingQueue {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        bq_producer producer = new bq_producer(queue);
        bq_consumer consumer = new bq_consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}

class bq_producer implements Runnable {

    private BlockingQueue<Integer> queue;

    public bq_producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        int count = 0;

        while (true){
            try {
                queue.put(count);
                System.out.println("Produced " + count);
                count++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class bq_consumer implements Runnable {

    private BlockingQueue<Integer> queue;

    public bq_consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (true){

            try {
                int value = queue.take();
                System.out.println("Removing " + value);
                Thread.sleep(300);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
