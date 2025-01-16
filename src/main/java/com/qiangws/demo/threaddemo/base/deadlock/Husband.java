package com.qiangws.demo.threaddemo.base.deadlock;

public class Husband implements Runnable {
    private String key;
    private String car;

    public Husband(String key, String car) {
        this.key = key;
        this.car = car;
    }

    @Override
    public void run() {

        synchronized (car) {
            System.out.println(Thread.currentThread().getName() + "获取车子之后的执行操作");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            synchronized (key) {
                System.out.println(Thread.currentThread().getName() + "获取钥匙之后执行操作");
                try {
                    Thread.sleep(1000);
                    key.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("husband开了一次车子");
    }
}
