package com.abhi.Section7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class S04_CallableAndFuture {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 1 ; i <= 5; i++) {
            int finalI = i;
            Future<String> future = executorService.submit(() -> "Id "+ finalI);

            futureList.add(future);
        }

       for (Future<String> future : futureList) {
           try {
               System.out.println(future.get());
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } catch (ExecutionException e) {
               throw new RuntimeException(e);
           }
       }
       executorService.shutdown();
    }
}

