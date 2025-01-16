package com.qiangws.demo.threaddemo.base.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * interrupt中断,如果sleep，wait也会马上中断 通过调用底层unpark唤醒线程,捕获interruptedException
 */
public class InterruptDemo01 implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {//获取中断标记，默认是false
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("正常执行");
                throw new InterruptedException();
            } catch (InterruptedException e) {
                //有一个默认操作，复位
                //复位 false
//                Thread.currentThread().interrupt();//true
                //e.printStackTrace();
                System.out.println(Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupted(); //再次复位
                System.out.println(Thread.currentThread().isInterrupted());//false
                Thread.currentThread().interrupt();   //中断
                System.out.println(Thread.currentThread().isInterrupted());//true
            }
            System.out.println("线程循环");
        }
        System.out.println("结束");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptDemo01());
        thread.start();
        System.out.println(thread.isInterrupted());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
