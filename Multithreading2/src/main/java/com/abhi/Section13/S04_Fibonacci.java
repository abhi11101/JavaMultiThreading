package com.abhi.Section13;

import java.util.concurrent.RecursiveTask;

public class S04_Fibonacci extends RecursiveTask<Double > {

    private double num;

    public S04_Fibonacci(double num) {
        this.num = num;
    }

    @Override
    protected Double compute() {
        if (num<=1){
            //System.out.println("Task is smaller " + num);
            return num;
        }
        else{
//            System.out.println("Parallel tasks " + num);

            double sol = 0;

            S04_Fibonacci f1 = new S04_Fibonacci(num-1);
            S04_Fibonacci f2 = new S04_Fibonacci(num-2);

            f1.fork();
            f2.fork();

            sol+= f1.join();
            sol+= f2.join();

            return sol;

        }
    }
}
