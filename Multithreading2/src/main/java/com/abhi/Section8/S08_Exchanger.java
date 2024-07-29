package com.abhi.Section8;

import java.util.concurrent.Exchanger;

public class S08_Exchanger {

    public static void main(String[] args) {

        Exchanger<Integer> exchanger = new Exchanger<>();

        e_FirstThread firstThread  = new e_FirstThread(exchanger);
        e_SecondThread secondThread  = new e_SecondThread(exchanger);

        new Thread(firstThread).start();
        new Thread(secondThread).start();

    }

}

class e_FirstThread implements Runnable{

    private int counter;
    private Exchanger<Integer> exchanger;

    public e_FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true){
            counter++;
            System.out.println("First thread incremented the counter " + counter);
            try {
                counter = exchanger.exchange(counter);
                System.out.println("First thread get the counter " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class e_SecondThread implements Runnable{

    private int counter;
    private Exchanger<Integer> exchanger;

    public e_SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true){

            counter--;
            System.out.println("Second thread decremented the counter " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("Second thread get the counter " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


