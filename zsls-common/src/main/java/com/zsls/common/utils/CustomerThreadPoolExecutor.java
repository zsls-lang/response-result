package com.zsls.common.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomerThreadPoolExecutor {

    /**
     * default value
     */
    private static int corePoolSite = 20;
    private static int maxPoolSite = 50;
    private static int queueCapacity = 99999;
    private static Long keepAliveTime = 1L;

    public static volatile ThreadPoolExecutor threadPoolExecutorInstance = null;

    private CustomerThreadPoolExecutor() {
    }

    public static void initialize(int corePoolSite, int maxPoolSite, int queueCapacity, long keepAliveTime) {
        CustomerThreadPoolExecutor.corePoolSite = corePoolSite;
        CustomerThreadPoolExecutor.maxPoolSite = maxPoolSite;
        CustomerThreadPoolExecutor.queueCapacity = queueCapacity;
        CustomerThreadPoolExecutor.keepAliveTime = keepAliveTime;
    }

    /**
     * 双重检索
     */
    public static ThreadPoolExecutor getThreadPoolExecutorInstance() {
        if (threadPoolExecutorInstance == null || threadPoolExecutorInstance.isShutdown()) {
            synchronized (CustomerThreadPoolExecutor.class) {
                if (threadPoolExecutorInstance == null || threadPoolExecutorInstance.isShutdown()) {
                    System.out.println("The thread pool instance is empty, so need to create.");
                    threadPoolExecutorInstance = new ThreadPoolExecutor(
                            corePoolSite,
                            maxPoolSite,
                            keepAliveTime,
                            TimeUnit.SECONDS,
                            new ArrayBlockingQueue<>(queueCapacity),
                            new CustomerThreadFactory("ThreadPool"),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                    System.out.println("The thread pool instance info: " + threadPoolExecutorInstance);
                }
            }
        }
        return threadPoolExecutorInstance;
    }
}