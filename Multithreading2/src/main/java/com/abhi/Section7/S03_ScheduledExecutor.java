package com.abhi.Section7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class S03_ScheduledExecutor {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(
                ()->{

                    System.out.println("Uploading and downloading data....");

            },
                1000,2000, TimeUnit.MILLISECONDS
        );

    }


}
