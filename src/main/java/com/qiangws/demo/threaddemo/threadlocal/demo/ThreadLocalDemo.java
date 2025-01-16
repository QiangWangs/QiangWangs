package com.qiangws.demo.threaddemo.threadlocal.demo;

/**
 * @author 王强
 * @create 2021/7/9 22:19
 */

public class ThreadLocalDemo {
    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                num += 5;
                System.out.println(Thread.currentThread().getName() + "的num=" + num);
            });
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
           // threads[i].join();
        }
    }
}

