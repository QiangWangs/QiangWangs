package com.qiangws.demo.threaddemo.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : test
 * @packageName : com.qiangws.demo.threaddemo.completablefuture
 * @createTime : 2023/6/29
 * @description :  TODO
 */
public class test {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + "main thread ");
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " cf1 do something....");
            //int a = 1/0;
            return 1;
        });
       /* CompletableFuture<Integer> cf2 = cf1.whenCompleteAsync((result, e) -> {
            System.out.println("上个任务结果：" + result);
            System.out.println("上个任务抛出异常：" + e);
            System.out.println(Thread.currentThread() + " cf2 do something....");
            return 1;
        });
        //等待任务1执行完成
        System.out.println("cf2结果->" + cf2.get());*/

    }
}
