package com.qiangws.demo.threaddemo.base.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo02 implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { //中断标记默认是false
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("1231");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().isInterrupted());
                //可以不做处理，
                //继续中断 ->
                Thread.currentThread().interrupt(); //再次中断
                //抛出异常。。
            }
             Thread.interrupted();  //再次复位
            System.out.println(Thread.currentThread().isInterrupted());
        }
        System.out.println("processor End");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new InterruptDemo02());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        //  Thread.sleep(1000);
        t1.interrupt(); //有作用 true
    }
}
