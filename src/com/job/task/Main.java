package com.job.task;

public class Main {

    public static void main(String[] args) {
        new Thread(new Runner(),"Thread1").start();
        new Thread(new Runner(),"Thread2").start();
        new Thread(new Runner(),"Thread3").start();
    }
}
