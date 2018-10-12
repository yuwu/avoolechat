package com.avoole.common.thread.priority;

import com.avoole.common.Applog;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AVDefaultExecutorService implements AVExecutorService
{
    private static volatile AVDefaultExecutorService executor;
    private ScheduledExecutorService priorityExecutorService;
    private ThreadPoolExecutor httpExecutorService;
    private ThreadPoolExecutor sequenceExecutorService;
    private LinkedBlockingQueue<Runnable> httpBlockingQueue;
    private LinkedBlockingQueue<Runnable> sequenceBlockingQueue;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 4 + 1;
    private static final int KEEP_ALIVE = 1;
    private BlockingQueue<Runnable> mayBlockThreadPoolWorkQueue;
    public ThreadPoolExecutor mayBlockThreadPoolExecutor;
    private LinkedBlockingQueue<Runnable> mayBlockProfileWorkQueue;
    private ThreadPoolExecutor mayBlockProfileExecutor;

    public static AVDefaultExecutorService getInstance()
    {
        if (executor == null) {
            synchronized (AVDefaultExecutorService.class) {
                if (executor == null) {
                    executor = new AVDefaultExecutorService();
                    executor.init();
                }
            }
        }
        return executor;
    }

    public void init() {
        this.priorityExecutorService = new ScheduledThreadPoolExecutor(2, new ThreadFactory()
        {
            public Thread newThread(Runnable r)
            {
                return new Thread(r, "priority-thread");
            }
        })
        {
            public Future<?> submit(final Runnable task) {
                Runnable wrappedTask = new Runnable()
                {
                    public void run() {
                        try {
                            task.run();
                        } catch (Throwable e) {
                            Applog.e("priorityExecutorService", "taskclass name:" + task.getClass().getSimpleName(), e);
                        }
                    }
                };
                return super.submit(wrappedTask);
            }
        };
        this.httpBlockingQueue = new LinkedBlockingQueue();
        this.httpExecutorService = new ThreadPoolExecutor(6, 30, 30L, TimeUnit.SECONDS, this.httpBlockingQueue, new ThreadFactory()
        {
            public Thread newThread(Runnable r)
            {
                return new Thread(r, "http-thread");
            }

        })
        {
            public Future<?> submit(final Runnable task)
            {
                Runnable wrappedTask = new Runnable()
                {
                    public void run() {
                        try {
                            task.run();
                        } catch (Throwable e) {
                            Applog.e("httpExecutorService", "taskclass name:" + task.getClass().getSimpleName(), e);
                        }
                    }
                };
                return super.submit(wrappedTask);
            }
        };
        this.sequenceBlockingQueue = new LinkedBlockingQueue();
        this.sequenceExecutorService = new ThreadPoolExecutor(1, 1, 30L, TimeUnit.SECONDS, this.sequenceBlockingQueue, new ThreadFactory()
        {
            public Thread newThread(Runnable r)
            {
                return new Thread(r, "sequence-thread");
            }

        })
        {
            public Future<?> submit(final Runnable task)
            {
                Runnable wrappedTask = new Runnable()
                {
                    public void run() {
                        try {
                            task.run();
                        } catch (Throwable e) {
                            Applog.e("sequenceExecutorService", "taskclass name:" + task.getClass().getSimpleName(), e);
                        }
                    }
                };
                return super.submit(wrappedTask);
            }
        };
        ThreadFactory sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            public Thread newThread(Runnable r) {
                return new Thread(r, "mayBlockAsyncTask #" + this.mCount.getAndIncrement());
            }
        };
        this.mayBlockThreadPoolWorkQueue = new LinkedBlockingQueue();

        this.mayBlockThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, this.mayBlockThreadPoolWorkQueue, sThreadFactory);

        this.mayBlockProfileWorkQueue = new LinkedBlockingQueue();

        this.mayBlockProfileExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, this.mayBlockProfileWorkQueue, sThreadFactory);
    }

    public void destory()
    {
        if (this.priorityExecutorService != null) {
            this.priorityExecutorService.shutdown();
        }

        if (this.httpBlockingQueue != null) {
            this.httpBlockingQueue.clear();
        }

        if (this.httpExecutorService != null) {
            this.httpExecutorService.shutdown();
        }

        if (this.mayBlockThreadPoolWorkQueue != null) {
            this.mayBlockThreadPoolWorkQueue.clear();
        }

        if (this.mayBlockThreadPoolExecutor != null) {
            this.mayBlockThreadPoolExecutor.shutdown();
        }
        if (this.mayBlockProfileWorkQueue != null) {
            this.mayBlockProfileWorkQueue.clear();
        }

        if (this.mayBlockProfileExecutor != null) {
            this.mayBlockProfileExecutor.shutdown();
        }
        if (this.sequenceBlockingQueue != null) {
            this.sequenceBlockingQueue.clear();
        }
        if (this.sequenceExecutorService != null)
            this.sequenceExecutorService.shutdown();
    }

    public ThreadPoolExecutor getHttpExecutorService()
    {
        return this.httpExecutorService;
    }
    public ThreadPoolExecutor getSequenceExecutorService() {
        return this.sequenceExecutorService;
    }

    public ScheduledExecutorService getPriorityExecutorService() {
        return this.priorityExecutorService;
    }

    public ThreadPoolExecutor getMayBlockThreadPoolExecutor() {
        return this.mayBlockThreadPoolExecutor;
    }
    public ThreadPoolExecutor getMayBlockProfileExecutor() {
        return this.mayBlockProfileExecutor;
    }
}
