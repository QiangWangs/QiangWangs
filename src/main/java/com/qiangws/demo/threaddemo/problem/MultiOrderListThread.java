package com.qiangws.demo.threaddemo.problem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auth Gp-TianMing
 * @description
 * @data 2021/9/15
 */


public class MultiOrderListThread {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(50);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50,
                50,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(200),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        while(true){
//            Thread.sleep(10);
            for(int i=0;i<10000;i++){
                executor.execute(new OrderInfo());
            }
        }

    }
}

class OrderInfo implements Runnable{
    BigDecimal price = new BigDecimal(0.0);
    String name = "TianMing";
    int age = 18;
    Date createDate = new Date();
    @Override
    public void run() {

    }
}