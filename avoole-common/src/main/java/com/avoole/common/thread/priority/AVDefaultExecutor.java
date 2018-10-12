package com.avoole.common.thread.priority;

import com.avoole.common.Applog;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AVDefaultExecutor implements AVExecutor
{
    private static final String TAG = "AVDefaultExecutor";
    private AVExecutorService executorService;
    private Future future;

    public static AVExecutor getInstance()
    {
        AVDefaultExecutor executor = new AVDefaultExecutor();
        executor.executorService = AVDefaultExecutorService.getInstance();
        return executor;
    }

    public AVExecutor submitHighPriority(Runnable runnable)
    {
        Applog.v("AVDefaultExecutor", "----high priority running:" + runnable.getClass().getName());
        this.future = this.executorService.getPriorityExecutorService().submit(runnable);
        return this;
    }

    public AVExecutor submitNormalPriority(Runnable runnable)
    {
        Applog.v("AVDefaultExecutor", "----begin normal priority running:" + runnable);
        return submit(runnable, 5000);
    }

    public AVExecutor submitLowPriority(Runnable runnable)
    {
        Applog.v("AVDefaultExecutor", "----begin low priority running:" + runnable.getClass().getName());
        return submit(runnable, 20000);
    }

    public AVExecutor submit(Runnable runnable, int delayTime) {
        this.future = null;
        this.executorService.getPriorityExecutorService().schedule(runnable, delayTime, TimeUnit.MILLISECONDS);
        return this;
    }

    public Future getFuture() {
        return this.future;
    }

    public void cancel(boolean mayInterruptIfRunning) {
        Applog.v("AVDefaultExecutor", "cancel request");
        if (this.future != null)
            this.future.cancel(mayInterruptIfRunning);
    }

    public void cancel()
    {
        cancel(true);
    }

    public AVExecutor submitHttp(Runnable runnable)
    {
        Applog.v("AVDefaultExecutor", "----http running:" + runnable.getClass().getName());
        this.future = this.executorService.getHttpExecutorService().submit(runnable);
        return this;
    }

    public AVExecutor submitSequence(Runnable runnable)
    {
        Applog.v("AVDefaultExecutor", "----http running:" + runnable.getClass().getName());
        this.future = this.executorService.getSequenceExecutorService().submit(runnable);
        return this;
    }

    public boolean isCancelled()
    {
        if (this.future == null) {
            return false;
        }
        return this.future.isCancelled();
    }

    public void executeLocal(Runnable runnable)
    {
        Applog.v("AVDefaultExecutor", "----Local running:" + runnable.getClass().getName());
        new Thread(runnable).start();
    }

    public void executeProfile(Runnable runnable)
    {
        if (this.executorService != null)
            this.executorService.getMayBlockProfileExecutor().execute(runnable);
        else
            new Thread(runnable).start();
    }

    public void executeHttp(Runnable runnable)
    {
        if (this.executorService != null)
            this.executorService.getHttpExecutorService().execute(runnable);
        else
            new Thread(runnable).start();
    }
}