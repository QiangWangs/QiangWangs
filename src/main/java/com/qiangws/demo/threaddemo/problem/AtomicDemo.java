package com.qiangws.demo.threaddemo.problem;

/**
 * @author 王强
 * @create 2021/7/20 14:54
 * 原子性  加锁
 * javap -v AtomicDemo.class
 * cpu中的一个数据结构
 * 线程切换速度比较快可能导致寄存器(count)数量++ 没有写入到内存,另一个线程执行的时候又拿到另当前寄存器count的数量进行++并写入到内存
 */
public class AtomicDemo {
    public static int count=0; //100
    public static void add(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) {
        for(int i=0;i<1000;i++){
            new Thread(()-> AtomicDemo.add()).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //运行结果是一个小于1000 的数
        System.out.println("运行结果："+count);
    }
}
