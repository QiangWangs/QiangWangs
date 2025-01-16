package com.qiangws.demo.threaddemo.threadpool;

import java.util.concurrent.*;

/**
 * @author 王强
 * @create 2021/7/27 14:33
 */
public class RejectDemo{
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2,3,10, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(3),
                new ExecJavaTemplate());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行任务");
            }));
        }
    }
}

class ExecJavaTemplate implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("拒绝");
    }
}