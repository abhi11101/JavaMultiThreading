package com.abhi.Section13;

import java.util.concurrent.ForkJoinPool;

public class S04_MainClass {

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        for (int i = 1; i <= 20; i++) {
            S04_Fibonacci obj = new S04_Fibonacci(i);

            System.out.println(forkJoinPool.invoke(obj));
        }
    }
}
