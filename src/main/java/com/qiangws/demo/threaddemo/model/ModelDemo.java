package com.qiangws.demo.threaddemo.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ModelDemo {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingQueue(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread consumer1 = new Thread(consumer, "Consumer");
        Thread producer1 = new Thread(producer, "Producer");
        producer1.start();
        consumer1.start();
    }
}
