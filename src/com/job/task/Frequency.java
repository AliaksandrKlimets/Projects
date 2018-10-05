package com.job.task;

import java.util.Date;

/**
 * Singleton class with lazy initialisation.
 */

public class Frequency implements FrequencyChecker {
    private int maxMessagesCount;
    private long time;
    private long messagesCount = 0;
    private static volatile Frequency instance;

    public static Frequency getInstance() {
        if(instance == null){
            synchronized (Frequency.class){
                if(instance == null) {
                    instance = new Frequency();
                }
            }
        }
        return instance;
    }

    private Frequency() {
        maxMessagesCount = 30;
        this.time = new Date().getTime();
    }

    /**
     * This method calculate the value of the messages per minute and compare it to maxMessagesCount value.
     * @return true if this value less than maxMessagesCount value, otherwise returns false.
     */

    @Override
    synchronized public boolean isAllowed() {
        double messagesPerMinute = (double) (messagesCount + 1) / ((new Date().getTime() - time) / (double) 60000);
        if (messagesPerMinute > maxMessagesCount) {
            return false;
        } else {
            ++messagesCount;
            return true;
        }
    }
}
