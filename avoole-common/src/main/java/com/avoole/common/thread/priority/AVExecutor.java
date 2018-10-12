package com.avoole.common.thread.priority;

import com.avoole.common.thread.threadpool.ExecutedTask;

public interface AVExecutor extends ExecutedTask
{
    public AVExecutor submitHighPriority(Runnable runnable);

    public AVExecutor submitNormalPriority(Runnable runnable);

    public AVExecutor submitLowPriority(Runnable runnable);

    public AVExecutor submitHttp(Runnable runnable);

    public AVExecutor submitSequence(Runnable runnable);

    public AVExecutor submit(Runnable runnable, int delayTime);

    public void executeHttp(Runnable runnable);

    public void executeLocal(Runnable runnable);

    public void executeProfile(Runnable runnable);
}
