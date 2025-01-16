package com.qiangws.demo.threaddemo.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王强
 * @create 2021/7/13 17:46unsaft
 */
public class AtomicDemo {
    private static int count = 0;
    static Lock lock = new ReentrantLock();

    public static void inc() {
        lock.lock();
        try {
            Thread.sleep(1000000000);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void dec() {
        lock.lock();
        try {
            Thread.sleep(1);
            count--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                AtomicDemo.inc();
            },"jt").start();
        new Thread(() -> {
            AtomicDemo.inc();
        },"jtshuai").start();
//        }
        Thread.sleep(4000);
        System.out.println(count);
    }
}
