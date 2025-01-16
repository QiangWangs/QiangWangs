package com.qiangws.demo.threaddemo.completablefuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author 王强
 * @create 2022/5/24 20:44
 * exceptionally
 */
public class ExceptionallyDemo01 {
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
            if(true){
                throw new RuntimeException("任务1抛异常了");
            }else{
                System.out.println(Thread.currentThread().getName()+":任务1结束->"+local.get().format(new Date()));
                return 11;
            }
        },pool);

        //cf1执行异常时，将抛出的异常作为入参传递给回调方法
        CompletableFuture<Integer> cf2 = cf1.exceptionally((x)->{
            System.out.println(Thread.currentThread().getName()+":任务2开始->"+local.get().format(new Date()));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务2结束->"+local.get().format(new Date()));
            return 22;
        });
        //cf1执行正常时，如果执行异常则不调用此逻辑
        CompletableFuture cf3 = cf1.thenAccept((x)->{
            System.out.println(Thread.currentThread().getName()+":任务3开始->"+local.get().format(new Date()));
            System.out.println("入参x="+x);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":任务3结束->"+local.get().format(new Date()));
        });
        //等待子任务执行完成
        System.out.println("任务1返回值："+cf1.get()+"->"+local.get().format(new Date()));
        System.out.println("任务3返回值："+cf3.get()+"->"+local.get().format(new Date()));
    }
}

