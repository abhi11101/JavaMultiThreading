package com.abhi.Section8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S01_CollectionSynchronization {

    private static List<Integer> numbers = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{

            for(int i=0;i<1000;i++){
                numbers.add(i);
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0;i<1000;i++){
                numbers.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("List size: " + numbers.size());
    }

}
