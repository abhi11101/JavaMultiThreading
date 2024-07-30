package com.abhi.Section13;

import java.util.concurrent.RecursiveTask;

public class S03_SimpleRecursiveTask extends RecursiveTask<Double> {

    private double num;

    public S03_SimpleRecursiveTask(double num) {
        this.num = num;
    }

    @Override
    protected Double compute() {

        if (num>100){
            System.out.println("Parallel execution to split the task.. " + num);

            S03_SimpleRecursiveTask split = new S03_SimpleRecursiveTask(num/2);
            S03_SimpleRecursiveTask split2 = new S03_SimpleRecursiveTask(num/2);

            split.fork();
            split2.fork();

            double subSolution = 0;
            subSolution += split.join();
            subSolution += split2.join();

            return subSolution;
        }else {
            System.out.println("Task is small " + num);
            return num*2;
        }
    }
}
