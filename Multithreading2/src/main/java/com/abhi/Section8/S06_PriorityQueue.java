package com.abhi.Section8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class S06_PriorityQueue {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new PriorityBlockingQueue<String>();

        pq_FirstWorker firstWorker = new pq_FirstWorker(queue);
        pq_SecondWorker secondWorker = new pq_SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}

class pq_FirstWorker implements Runnable{

    private BlockingQueue<String> queue;

    public pq_FirstWorker(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            queue.put("B");
            queue.put("H");
            queue.put("W");
            Thread.sleep(2000);
            queue.put("E");
            Thread.sleep(1000);
            queue.put("F");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class pq_SecondWorker implements Runnable{

    private BlockingQueue<String> queue;

    public pq_SecondWorker(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
           Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(5000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}