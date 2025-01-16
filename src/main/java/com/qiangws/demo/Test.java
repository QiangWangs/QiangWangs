package com.qiangws.demo;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Test
 * @packageName : com.qiangws.demo
 * @createTime : 2023/8/24
 * @description :  TODO
 */
@Log4j2
public class Test {

    public static void test(){
        for (int i=1;i<100;i++) {
            test1(i);
//            try{
//                Thread.sleep(1000);
//            }catch (Exception e){
//                throw new RuntimeException(e);
//            }
        }
    }

    public static void test1(Integer i){
        log.info("i:{}", i);
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        test();
//        final Semaphore semaphore = new Semaphore(3);
//
//        // 生产者线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        semaphore.acquire();
//                        System.out.println("生产者 " + Thread.currentThread().getName() + " 生产了 " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        semaphore.release();
//                    }
//                }
//            }
//        }).start();
//
//        // 消费者线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        semaphore.acquire();
//                        System.out.println("消费者 " + Thread.currentThread().getName() + " 消费了 " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        semaphore.release();
//                    }
//                }
//            }
//        }).start();
    }
}
