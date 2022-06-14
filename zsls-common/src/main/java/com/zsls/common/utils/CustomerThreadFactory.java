package com.zsls.common.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;


public class CustomerThreadFactory implements ThreadFactory {

    private String namePrefix;

    private final LongAdder nextId = new LongAdder();
    
    /**
     *
     * 线程池名字 + 线程名字前缀
     */
    public CustomerThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "From CustomerThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }
    
    @Override
    public Thread newThread(Runnable task) {
        // 线程id
        nextId.increment();
        String name = namePrefix + nextId.intValue();
        Thread thread = new Thread(null, task, name);
        return thread;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }
}
