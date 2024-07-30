package com.abhi.Section13;

import java.util.concurrent.ForkJoinPool;

public class S03_MainClass {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        S03_SimpleRecursiveTask obj1 = new S03_SimpleRecursiveTask(40000);
        System.out.println(forkJoinPool.invoke(obj1));

    }
}
