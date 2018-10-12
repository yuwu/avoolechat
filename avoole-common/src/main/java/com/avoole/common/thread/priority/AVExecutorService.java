package com.avoole.common.thread.priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public interface AVExecutorService {

    public ExecutorService getHttpExecutorService();

    public ExecutorService getSequenceExecutorService();

    public ThreadPoolExecutor getMayBlockProfileExecutor();

    public ScheduledExecutorService getPriorityExecutorService();

    public void destory();
}
