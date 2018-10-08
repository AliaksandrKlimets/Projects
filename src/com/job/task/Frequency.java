package com.job.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Singleton class with lazy initialisation.
 */

public class Frequency implements FrequencyChecker {
    private long maxMessagesCount;
   // private List<InnerDate> dateList;
    private List<Date> dateList;
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

    public void setMaxMessagesCount(int maxMessagesCount) {
        this.maxMessagesCount = maxMessagesCount;
    }

    private Frequency() {
        maxMessagesCount = 30;
        this.dateList = new ArrayList<>();
    }

    /**
     * This method calculate the value of the messages per minute and compare it to maxMessagesCount value.
     * @return true if this value less than maxMessagesCount value, otherwise returns false.
     */

    @Override
    synchronized public boolean isAllowed() {
        long messages = dateList.stream().filter(p ->( new Date().getTime()- p.getTime())<60000).count();
        System.out.println(messages);
        if (messages >= maxMessagesCount) {
            dateList = dateList.stream().filter(p ->( new Date().getTime()- p.getTime())<60000).collect(Collectors.toList());
            return false;
        } else {
            dateList.add(new Date());
           // dateList = dateList.stream().filter(p ->( new Date().getTime()- p.getDate().getTime())>60000).collect(Collectors.toList());
            return true;
        }
    }
}
