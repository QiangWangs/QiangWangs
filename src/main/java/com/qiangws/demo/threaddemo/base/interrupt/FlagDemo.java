package com.qiangws.demo.threaddemo.base.interrupt;

/**
 * @author 王强
 * @create 2022/5/20 14:37
 */
public class FlagDemo {
    //可见性
    static volatile boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(!flag){
                System.out.println(Thread.currentThread().getName()+"在执行");
            }
        }).start();
        Thread.sleep(1);
        flag = true;
    }
}
