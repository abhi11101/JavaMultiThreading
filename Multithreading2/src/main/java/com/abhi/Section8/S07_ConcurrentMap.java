package com.abhi.Section8;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class S07_ConcurrentMap {

    public static void main(String[] args) {

        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();

        cm_FirstWorker firstWorker = new cm_FirstWorker(map);
        cm_SecondWorker secondWorker = new cm_SecondWorker(map);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
    }
}

class cm_FirstWorker implements Runnable{

    private ConcurrentMap<String, Integer> concurrentMap;

    public cm_FirstWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {

        try {
            concurrentMap.put("A",11);
            Thread.sleep(1000);
            concurrentMap.put("V",12);
            concurrentMap.put("G",43);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class cm_SecondWorker implements Runnable{

    private ConcurrentMap<String, Integer> concurrentMap;

    public cm_SecondWorker(ConcurrentMap<String, Integer> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(concurrentMap.get("A"));
            Thread.sleep(1000);
            System.out.println(concurrentMap.get("G"));
            Thread.sleep(1000);
            System.out.println(concurrentMap.get("V"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}