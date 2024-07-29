package com.abhi.Section10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class S01_UsingSemaphores {

    public static void main(String[] args) {

        int numberOfBooks = 3;
        int numberOfStudents = 5;
        Semaphore semaphore = new Semaphore(numberOfBooks);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfStudents);

        for (int i = 0; i < numberOfStudents; i++) {

//            new Thread(new sem_Student(semaphore,i+1)).start();
            executorService.execute(new sem_Student(semaphore,i+1));
        }
        executorService.shutdown();

    }

}


class sem_Student implements Runnable{

    private int id;
    private Semaphore library;

    public sem_Student(Semaphore library, int id) {
        this.library = library;
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Student " + id + " is waiting for library");

        try {
            library.acquire();
            System.out.println("Student " + id + " has acquired the book");

            readBook();

            library.release();
            System.out.println("Student " + id + " has released the book");

            doOtherThings();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void readBook() throws InterruptedException {

        System.out.println("Student " + id + " is reading a book.");
        Thread.sleep(1000);
    }

    private void doOtherThings() throws InterruptedException {
        System.out.println("Student " + id + " is doing other things.");
        Thread.sleep(1000);
    }
}
