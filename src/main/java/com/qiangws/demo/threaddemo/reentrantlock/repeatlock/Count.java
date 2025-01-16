package com.qiangws.demo.threaddemo.reentrantlock.repeatlock;

/**
 * @author 王强
 * @create 2021/7/13 18:55
 */
public class Count {
    // 重入锁
    RepeatLock lock = new RepeatLock();

    public void print() throws InterruptedException {
        lock.lock();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"调用doAdd之前");
//        doAdd();
        System.out.println(Thread.currentThread().getName()+"调用doAdd之后");
        lock.unlock();
    }

    public void doAdd() throws InterruptedException {
        lock.lock();
        //do something
        System.out.println(Thread.currentThread().getName());
        lock.unlock();
    }

    public static void main(String[] args) {
        Count cc = new Count();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    cc.print();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
