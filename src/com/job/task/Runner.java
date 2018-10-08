package com.job.task;

import java.util.Date;
import java.util.Random;

public class Runner implements Runnable {

    private static long messageNumber = 0;

    @Override
    public void run() {
        FrequencyChecker checker = Frequency.getInstance();
        Message message;
        while (true) {
            message = waitForMessage();
            if (checker.isAllowed()) {
                process(message);
            } else {
                System.out.println("Oops, limit is over " + Thread.currentThread().getName() + " cannot send message. Try again.");
            }
        }
    }

    private Message waitForMessage() {
        try {
            Thread.sleep(new Random().nextInt(9000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Message(Thread.currentThread().getName() + " Message " +
                messageNumber++ + " " + new Date());
    }

    private void process(Message msg) {
        System.out.println(msg.getContent());
    }
}
