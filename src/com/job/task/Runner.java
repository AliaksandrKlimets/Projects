package com.job.task;

import java.util.Date;
import java.util.Random;

public class Runner implements Runnable {
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


// This variant of method run remember message in case of fail processing and try
// to process it until success

//    public void run() {
//        FrequencyChecker checker = Frequency.getInstance();
//        Message message = null;
//        Message bufferMessage = null;
//        while (true) {
//            if (bufferMessage == null) {
//                message = waitForMessage();
//            } else message = bufferMessage;
//            if (checker.isAllowed()) {
//                process(message);
//                bufferMessage = null;
//            } else {
//                System.out.println("Oops, limit is over " + Thread.currentThread().getName() + " cannot send message. Try again. ");
//                bufferMessage = message;
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

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
