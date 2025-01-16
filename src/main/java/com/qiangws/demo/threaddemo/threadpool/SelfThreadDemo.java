package com.qiangws.demo.threaddemo.threadpool;

/**
 * @author 王强
 * @create 2021/7/27 14:20
 */
public class SelfThreadDemo implements Runnable{
    final Thread t;

    public SelfThreadDemo() {
        t = new Thread(this);
    }

    @Override
    public void run() {
        System.out.println("我是一个任务，我有一个成员变量是线程，所以我自己运行了我自己");
    }

    public static void main(String[] args) {
        SelfThreadDemo demo = new SelfThreadDemo();
        Thread t = demo.t;
        t.start();
    }
}
