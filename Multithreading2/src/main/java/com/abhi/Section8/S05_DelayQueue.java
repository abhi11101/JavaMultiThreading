package com.abhi.Section8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class S05_DelayQueue {

    public static void main(String[] args) {

        BlockingQueue<DelayWorker> queue = new DelayQueue<>();

        try {
            queue.put(new DelayWorker(2000, "This is the first message"));
            queue.put(new DelayWorker(3000, "This is the second message"));
            queue.put(new DelayWorker(4500, "This is the third message"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (!queue.isEmpty()){
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class DelayWorker implements Delayed{

    private long duration;
    private String message;

    public DelayWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // this is the method that can compare objects

        if (duration< ((DelayWorker)o).getDuration()){
            return -1;
        }
        if (duration> ((DelayWorker)o).getDuration()){
            return 1;
        }
        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "message='" + message + '\'' +
                '}';
    }
}