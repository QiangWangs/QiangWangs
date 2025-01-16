package com.qiangws.demo.threaddemo.completablefuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.SimpleFormatter;

/**
 * @author 王强
 * @create 2022/5/20 16:30
 */
public class ThreadPoolSubmitDemo {
    static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(()->new SimpleDateFormat("HH:mm:ss"));
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建异步执行任务:
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Future<Double> cf = executorService.submit(()->{
            System.out.println(Thread.currentThread().getName()+"开始执行："+local.get().format(new Date()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if(false){
                throw new RuntimeException("test");
            }else{
                System.out.println(Thread.currentThread().getName()+"结束执行："+local.get().format(new Date()));
                return 1.2;
            }
        });
        System.out.println("main线程开始执行："+local.get().format(new Date()));
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
//        System.out.println("线程池返回值："+cf.get());
        System.out.println("main线程结束执行："+local.get().format(new Date()));
    }
}
