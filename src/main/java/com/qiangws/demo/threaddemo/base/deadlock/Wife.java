package com.qiangws.demo.threaddemo.base.deadlock;

public class Wife implements Runnable {
    private String key;
    private String car;

    public Wife(String key, String car) {
        this.key = key;
        this.car = car;
    }

    @Override
    public void run() {

        synchronized (key) {
            System.out.println(Thread.currentThread().getName() + "获取钥匙之后的执行操作");
            try {
                Thread.sleep(1000);
                System.out.println("拿到钥匙的先给拿到车子的人");
                key.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            synchronized (car) {
                System.out.println(Thread.currentThread().getName() + "获取车子之后执行操作");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("wife开了一次车子");
    }
}
