package com.qiangws.demo;

import java.util.concurrent.*;

/**
 * @author 王强
 * @create 2021/7/27 14:33
 */
public class RejectDemo{
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(14,28,10, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        for (int i = 0; i < 400000; i++) {
            int s= i;
            executorService.execute(new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "执行任务:"+s);
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