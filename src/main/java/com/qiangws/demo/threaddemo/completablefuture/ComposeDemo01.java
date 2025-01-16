package com.qiangws.demo.threaddemo.completablefuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author 王强
 * @create 2022/5/18 17:29
 * thenCompose
 */
public class ComposeDemo01 {
    static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(()->new SimpleDateFormat("mm:ss"));
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+":任务1开始->"+local.get().format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务1结束->"+local.get().format(new Date()));
            return 11;
        });
        CompletableFuture<Integer> cf2 = cf1.thenCompose((param)->{
            System.out.println(Thread.currentThread().getName()+":任务2开始->"+local.get().format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务2结束->"+local.get().format(new Date()));

            return CompletableFuture.supplyAsync(()->{
                System.out.println(Thread.currentThread().getName()+":任务3开始->"+local.get().format(new Date()));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName()+":任务3结束->"+local.get().format(new Date()));
                return 22;
            });
        });


        //等待子任务执行完成
        System.out.println("任务1返回值："+cf1.get()+"->"+local.get().format(new Date()));
        System.out.println("任务2返回值："+cf2.get()+"->"+local.get().format(new Date()));
    }
}
