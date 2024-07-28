package com.abhi.Section6;

import java.util.concurrent.Semaphore;

public class S04_semaphores {
    private static int count = 0;

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(()-> {

            System.out.println("Starting " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " trying to acquire semaphore");

            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " acquired semaphore");

            for (int i = 0; i < 5; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() +" Count is " + count);
            }

            semaphore.release();

        });

        Thread t2 = new Thread(()-> {

            System.out.println("Starting " + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + " trying to acquire semaphore");

            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " acquired semaphore");

            for (int i = 0; i < 5; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() +" Count is " + count);
            }

            semaphore.release();

        });

        t1.setName("A");
        t2.setName("B");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main count is " + count);
    }
}