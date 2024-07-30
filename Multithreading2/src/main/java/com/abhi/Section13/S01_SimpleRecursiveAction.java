package com.abhi.Section13;

import java.util.concurrent.RecursiveAction;

public class S01_SimpleRecursiveAction extends RecursiveAction {

    protected int simulatedWork;

    public S01_SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {

        if (simulatedWork >100){
            System.out.println("Parallel execution and split the task " + simulatedWork);

            S01_SimpleRecursiveAction obj1 = new S01_SimpleRecursiveAction(simulatedWork/2);
            S01_SimpleRecursiveAction obj2 = new S01_SimpleRecursiveAction(simulatedWork/2);

           invokeAll(obj1, obj2);
        }else {
            System.out.println("The task is smaller sequential is fine " + simulatedWork);
        }

    }
}
