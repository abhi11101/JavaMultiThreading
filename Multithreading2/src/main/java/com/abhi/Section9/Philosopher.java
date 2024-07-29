package com.abhi.Section9;

import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{

    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                pickUpForks();
                eat();
                putDownForks();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void pickUpForks() {
        leftFork.lock();
        System.out.println("Philosopher " + id + " picked up left fork.");
        rightFork.lock();
        System.out.println("Philosopher " + id + " picked up right fork.");
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void putDownForks() {
        leftFork.unlock();
        System.out.println("Philosopher " + id + " put down left fork.");
        rightFork.unlock();
        System.out.println("Philosopher " + id + " put down right fork.");
    }

}
