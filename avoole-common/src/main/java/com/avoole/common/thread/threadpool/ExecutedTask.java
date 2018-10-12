package com.avoole.common.thread.threadpool;

public abstract interface ExecutedTask
{
    public abstract void cancel();

    public abstract boolean isCancelled();
}