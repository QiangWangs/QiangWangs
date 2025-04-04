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
 * thenCombine / thenAcceptBoth / runAfterBoth
 */
public class CombineAcceptAfterDemo01 {
    static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(()->new SimpleDateFormat("mm:ss"));
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->{//带入参数带返回值
            System.out.println(Thread.currentThread().getName()+":任务1开始->"+local.get().format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务1结束->"+local.get().format(new Date()));
            return 11;
        });
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+":任务2开始->"+local.get().format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务2结束->"+local.get().format(new Date()));
            return 22;
        });

        //cf1和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,且有返回值
        CompletableFuture<Integer> cf3=cf1.thenCombine(cf2,(a,b)->{
            System.out.println(Thread.currentThread().getName()+":任务3开始->"+local.get().format(new Date()));
            System.out.println("任务3的参数a="+a+",b="+b);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName()+":任务3结束->"+local.get().format(new Date()));
            return a+b;
        });

        //cf1和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture cf4=cf1.thenAcceptBoth(cf2,(a,b)->{
            System.out.println(Thread.currentThread().getName()+":任务4开始->"+local.get().format(new Date()));
            System.out.println("任务4入参a="+a+",b="+b);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName()+":任务4结束->"+local.get().format(new Date()));
        });

        //cf4和cf3都执行完成后，执行cf5，无入参，无返回值
        CompletableFuture cf5=cf4.runAfterBoth(cf3,()->{
            System.out.println(Thread.currentThread().getName()+":任务5开始->"+local.get().format(new Date()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("无入参和返回值");
            System.out.println(Thread.currentThread().getName()+":任务5结束->"+local.get().format(new Date()));
        });
        //等待子任务执行完成
        System.out.println("任务1返回值："+cf1.get()+"->"+local.get().format(new Date()));
        System.out.println("任务2返回值："+cf2.get()+"->"+local.get().format(new Date()));
    }
}
