package com.qiangws.demo.threaddemo.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 王强
 * @create 2022/5/18 17:29
 * CompletableFuture四种构建方式
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
            System.out.println("异步执行任务，并提供返回值");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 11;
        });
        System.out.println(cf.get());

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println("异步执行任务，并提供返回值,且指定线程池:"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 22;
        }, Executors.newFixedThreadPool(5));

        System.out.println(cf1.get());
        CompletableFuture cf2 = CompletableFuture.runAsync(()->{
            System.out.println("异步执行任务，无返回值:"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //没有返回值，但是会等待cf2执行完
        System.out.println(cf2.get());
        CompletableFuture cf3 = CompletableFuture.runAsync(()->{
            System.out.println("异步执行任务，无返回值,且指定线程池:"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },Executors.newFixedThreadPool(5));
        //没有返回值，但是会等待cf3执行完
        System.out.println(cf3.get());

    }
}
