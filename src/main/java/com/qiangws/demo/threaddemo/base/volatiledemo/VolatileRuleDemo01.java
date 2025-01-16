package com.qiangws.demo.threaddemo.base.volatiledemo;

public class VolatileRuleDemo01 {
    int a = 0;
    volatile boolean flag = false;

    public void write() {
        flag = true;
        a = 1;
    }

    public void read() {
        if (flag) {
            int i = a;
            System.out.println(i);//活性失败,jit深度优化
        }
    }

    public static void main(String[] args) {
        VolatileRuleDemo01 demo = new VolatileRuleDemo01();
        Thread read = new Thread(() -> {
            demo.read();
        });
        Thread write = new Thread(() -> {
            demo.write();
        });

        write.start();
        read.start();

    }
}
