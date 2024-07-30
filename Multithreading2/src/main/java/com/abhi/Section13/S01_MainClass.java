package com.abhi.Section13;

import java.util.concurrent.ForkJoinPool;

public class S01_MainClass {

    public static void main(String[] args) {

//        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        S01_SimpleRecursiveAction simpleRecursiveAction = new S01_SimpleRecursiveAction(700);
        simpleRecursiveAction.invoke();

    }

}
