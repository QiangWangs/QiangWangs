package com.qiangws.demo.threaddemo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //固定线程数量的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        //提供可以定时执行任务的线程的池子
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        //动态的创建线程数
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //线程池的线程数有且只有一个
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //最大程度的满足任务执行需求，向操作系统申请足够的线程(JDK1.8新增的)
        ExecutorService workStealingPool = Executors.newWorkStealingPool();

        ThreadPoolExecutor a = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        a.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 100; i++) {
//            fixedThreadPool.execute(new MyTask());
//            workStealingPool.execute(new MyTask());
            fixedThreadPool.submit(new MyTask());

        }
        fixedThreadPool.shutdown();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}