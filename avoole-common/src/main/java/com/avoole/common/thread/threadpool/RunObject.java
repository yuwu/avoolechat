package com.avoole.common.thread.threadpool;

import java.util.concurrent.Future;

class RunObject implements ExecutedTask
{
    Runnable runnable;
    private boolean cancelled;
    Future<?> future;
    private long createDate;
    boolean withoutResult;

    RunObject(Runnable runnable, boolean withoutResult)
    {
        this.runnable = runnable;
        this.createDate = System.currentTimeMillis();
        this.withoutResult = withoutResult;
    }

    public long getCreateDate()
    {
        return this.createDate;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void cancel()
    {
        this.cancelled = true;
        if (this.future != null)
            this.future.cancel(false);
    }
}