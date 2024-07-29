package com.abhi.Section10;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class S02_UsingBlockingQueue {
    public static void main(String[] args) {

        int numberOfBooks = 3;
        int numberOfStudents = 5;
        BlockingQueue<Book> library = new ArrayBlockingQueue<>(numberOfBooks);

        // Initialize the library with books
        for (int i = 0; i < numberOfBooks; i++) {
            library.add(new Book(i));
        }

        for (int i = 0; i < numberOfStudents; i++) {
            new Thread(new Student(library,i+1)).start();
        }
    }

}

class Book{

    private int id;

    public Book(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Student implements Runnable{

    private BlockingQueue<Book> library;
    private int id;

    public Student(BlockingQueue<Book> library, int id) {
        this.library = library;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Student " + id + " is waiting for library");

        try {

            while (true){

                Book book = library.take();
                System.out.println("Student " + id + " has acquired the book");
                readBook();

                library.put(book);
                System.out.println("Student " + id + " has released the book");
                doOtherThings();
            }

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
