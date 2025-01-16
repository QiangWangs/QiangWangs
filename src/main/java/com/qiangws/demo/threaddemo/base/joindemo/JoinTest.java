package com.qiangws.demo.threaddemo.base.joindemo;

/**
 * @author 王强
 * @create 2021/9/2 21:17
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("我是主线程");
        Thread thread = new Thread(()->{
            System.out.println("我是分线程");
        });
        thread.start();
        thread.join();
        System.out.println("主线程结束");

    }
}
