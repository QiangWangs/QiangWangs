package com.qiangws.demo.threaddemo.reentrantlock.repeatlock;

/**
 * @author 王强
 * @create 2021/7/13 18:52
 * 可重入锁
 */
public class RepeatLock {
    boolean isLocked = false;//标志位，表示该锁是否已经被线程占用
    Thread lockedBy = null;//线程，用来存储获取到该锁的线程
    int lockedCount = 0;  //被锁了几次

    public synchronized void lock()
            throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
