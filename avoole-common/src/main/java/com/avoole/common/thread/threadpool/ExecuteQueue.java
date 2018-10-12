package com.avoole.common.thread.threadpool;

import java.util.Queue;

class ExecuteQueue<T>
{
    Queue<T> queue;
    boolean enable;

    ExecuteQueue(Queue<T> queue, boolean enable)
    {
        this.queue = queue;
        this.enable = enable;
    }
}