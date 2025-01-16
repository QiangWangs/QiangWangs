package com.qiangws.demo.threaddemo.model;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println("生产消息：" + i);
            try {
//                MyTask myTask = new MyTask();
//                queue.put(myTask);
                queue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
