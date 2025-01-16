package com.qiangws.demo.threaddemo.model;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {//避免消费者线程执行完run方法被jvm回收
            try {
//                MyTask task = (MyTask)queue.take();
//                task.run();//
                System.out.println("消费数据："+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
