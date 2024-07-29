package com.abhi.Section8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class S03_CyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new BarrierAction());

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executorService.execute(new cb_worker(cyclicBarrier,i));
        }
        System.out.println("Main thread finished");
        executorService.shutdown();
    }
}

class cb_worker implements Runnable{

    private CyclicBarrier cyclicBarrier;
    private int id;

    public cb_worker(CyclicBarrier cyclicBarrier, int id) {
        this.cyclicBarrier = cyclicBarrier;
        this.id = id;
    }

    @Override
    public void run() {
        try{
            System.out.println(String.format("Worker %d started with phase 1",id));
            Thread.sleep(1000);

            System.out.println(String.format("Worker %d is waiting at the barrier",id));
            cyclicBarrier.await();

            System.out.println(String.format("Worker %d started with phase 2",id));
            Thread.sleep(1000);

            System.out.println(String.format("Worker %d is waiting at the barrier",id));
            cyclicBarrier.await();

            System.out.println("All tasks have finished of worker " + id);

        }catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

    }

    private void doWork(){

        System.out.println("Worker " + id + " begin");

    }
}

class BarrierAction implements Runnable{

    @Override
    public void run() {
        System.out.println("All threads have reached barrier. Proceeding...");
    }
}