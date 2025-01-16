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
 * whenComplete
 */
public class WhenCompleteDemo01 {
    static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(()->new SimpleDateFormat("mm:ss"));
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+":任务1开始->"+local.get().format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            if(true){
                throw new RuntimeException("任务1抛异常了");
            }else{
                System.out.println(Thread.currentThread().getName()+":任务1结束->"+local.get().format(new Date()));
                return 11;
            }
        });

        //cf1执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<Integer> cf2 = cf1.whenComplete((a,b)->{
            System.out.println(Thread.currentThread().getName()+":任务2开始->"+local.get().format(new Date()));

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            if(b!=null){
                System.out.println("打印任务1的异常");
                b.printStackTrace();
            }else{
                System.out.println("任务1的结果为："+a);
            }
            System.out.println(Thread.currentThread().getName()+":任务2结束->"+local.get().format(new Date()));
        });

        //等待子任务执行完成
        System.out.println("任务2返回值："+cf2.get()+"->"+local.get().format(new Date()));
    }
}

