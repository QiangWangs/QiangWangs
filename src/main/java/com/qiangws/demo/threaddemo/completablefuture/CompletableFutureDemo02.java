package com.qiangws.demo.threaddemo.completablefuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 */
public class CompletableFutureDemo02 {
    static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(()->new SimpleDateFormat("mm:ss"));
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+":任务1开始->"+local.get().format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务1结束->"+local.get().format(new Date()));
            return 11;
        },pool);

        //cf关联的异步任务的返回值作为方法入参，传入到thenAccept的方法中
        //thenAccept这里实际创建了一个新的CompletableFuture实例
        CompletableFuture cf2 = cf.thenAccept((x)->{
            System.out.println(Thread.currentThread().getName()+":任务2开始->"+local.get().format(new Date()));
            x+=5;
            System.out.println(Thread.currentThread().getName()+":任务2结束->"+local.get().format(new Date()));
        });
        //等待子任务执行完成
        System.out.println("任务1返回值："+cf.get()+"->"+local.get().format(new Date()));
        System.out.println("任务2返回值："+cf2.get()+"->"+local.get().format(new Date()));
    }
}
