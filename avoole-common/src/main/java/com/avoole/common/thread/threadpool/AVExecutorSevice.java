package com.avoole.common.thread.threadpool;

public interface AVExecutorSevice {
    public ExecutedTask submit(Runnable paramRunnable, PriorityLevel paramPriorityLevel);

    public ExecutedTask submit(Runnable paramRunnable);

    public void execute(Runnable paramRunnable, PriorityLevel paramPriorityLevel);

    public void execute(Runnable paramRunnable);

    public void setExecuteStrategy(int paramInt);

    public void shutdown();
}
