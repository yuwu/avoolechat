package com.avoole.common.thread.threadpool;

import java.util.ArrayList;
import java.util.Queue;

class ListQueue<E>
{
    private ArrayList<ExecuteQueue<E>> linkQueues = new ArrayList();

    public boolean isElementEmpty()
    {
        int total = 0;
        for (ExecuteQueue e : this.linkQueues) {
            if ((e != null) && (e.enable)) {
                total += e.queue.size();
            }
        }

        return total <= 0;
    }

    E pollElement()
    {
        E result = null;
        if (isElementEmpty()) {
            return result;
        }

        for (ExecuteQueue e : this.linkQueues) {
            if ((e != null) && (e.enable))
            {
                Queue<E> q = e.queue;
                if ((q != null) && (!q.isEmpty()))
                {
                    result = q.poll();
                }
            }
        }
        return result;
    }

    ExecuteQueue<E> get(int index) {
        if (index > this.linkQueues.size() - 1) {
            return null;
        }

        return (ExecuteQueue)this.linkQueues.get(index);
    }

    void addQueue(ExecuteQueue<E> e) {
        this.linkQueues.add(e);
    }

    int size() {
        return this.linkQueues.size();
    }

    void clear() {
        this.linkQueues.clear();
    }
}