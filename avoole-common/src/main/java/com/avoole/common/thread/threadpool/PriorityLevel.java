package com.avoole.common.thread.threadpool;

public enum PriorityLevel
{
    HIGH(7),

    NORMAL(5),

    LOW(3);

    private int level;

    private PriorityLevel(int p) {
        this.level = p;
    }

    public int getLevel() {
        return this.level;
    }
}