package com.job.task;

import java.util.Date;
import java.util.Random;

public class Runner implements Runnable {
    @Override
    public void run() {
        FrequencyChecker checker = Frequency.getInstance();
        while (true) {
            Message message = waitForMessage();
            if (checker.isAllowed()) {
                process(message);
            } else
                System.out.println("Oops, limit is over " + Thread.currentThread().getName() + " cannot send message");
        }
    }

    private Message waitForMessage() {
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Message(Thread.currentThread().getName() + " Message " +
                new Random().nextInt(200) + " " + new Date());
    }

    private void process(Message msg) {
        System.out.println(msg.getContent());
    }
}
