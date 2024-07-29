package com.abhi.Section8;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class S09_CopyonWriteArray {

    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();

        // Add elements to the list
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");

        // Start a thread that iterates over the list
        new Thread(() -> {
            for (String element : list) {
                System.out.println("Read Thread: " + element);
                try {
                    Thread.sleep(100); // Simulate some processing time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        // Start a thread that modifies the list
        new Thread(() -> {
            try {
                Thread.sleep(50); // Ensure some reads happen before modification
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Write Thread: Adding Element4");
            list.add("Element4");
            System.out.println("Write Thread: Removing Element2");
            list.remove("Element2");
        }).start();

    }
}
