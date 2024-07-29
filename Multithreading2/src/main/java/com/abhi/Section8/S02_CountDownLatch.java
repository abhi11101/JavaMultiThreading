package com.abhi.Section8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class S02_CountDownLatch {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new cdl_worker(i,latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All task done");
        executorService.shutdown();

    }

}

class cdl_worker implements Runnable{

    private int id;
    private CountDownLatch latch;

    public cdl_worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }

    public void doWork(){

        try {
            System.out.println("Worker "+ id  + " is working");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
