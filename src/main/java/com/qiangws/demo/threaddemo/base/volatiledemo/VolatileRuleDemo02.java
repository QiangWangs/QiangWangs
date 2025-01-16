package com.qiangws.demo.threaddemo.base.volatiledemo;

public class VolatileRuleDemo02 {
    public static void main(String[] args) {
        final int x[]={10};
        Thread t1 = new Thread(()->{
            // 主线程调用 t1.start() 之前
            // 所有对共享变量的修改，此处皆可见
            // 此例中，x==10
            System.out.println(x[0]);
        });
        // 此处对共享变量 x修改
        x[0] = 11;
        // 主线程启动子线程
        t1.start();
    }
}
