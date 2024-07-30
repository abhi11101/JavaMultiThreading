package com.abhi.Section13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class S02_SplitArray {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        S02_work obj = new S02_work(list);
        long startTime = System.nanoTime();
        obj.invoke();
        long endTime = System.nanoTime();
        System.out.println("time taken " + (endTime-startTime));

    }
}

class S02_work extends RecursiveAction{

    private List<Integer> list;

    public S02_work(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected void compute() {

        if (list.size()>2){

            System.out.println("Doing parallel split.." + list);

            S02_work split1 = new S02_work(list.subList(0, list.size()/2));
            S02_work split2 = new S02_work(list.subList((list.size()/2), list.size()));

            invokeAll(split1, split2);

        }else {
            System.out.println("Can't be broken down: " + list);
        }

    }
}