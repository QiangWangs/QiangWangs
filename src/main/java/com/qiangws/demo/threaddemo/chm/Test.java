package com.qiangws.demo.threaddemo.chm;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(5);
        concurrentHashMap.put("key", "value");
        concurrentHashMap.put("key1", "value");
        concurrentHashMap.put("key2", "value");
        concurrentHashMap.put("key3", "value");
        concurrentHashMap.put("key4", "value");
        concurrentHashMap.put("key5", "value");
        System.out.println(concurrentHashMap.get("key"));
        System.out.println(concurrentHashMap.size());

        HashMap map = new HashMap(5);
//        HashMap map1 = new HashMap(5,0.55f);
        map.put("key", "123");
        System.out.println(map.get("key"));
        System.out.println(map.size());

        ReentrantLock reentrantLock = new ReentrantLock();
        Task task = new  Task(reentrantLock);
        /*new Thread(task,"Thread1").start();
        new Thread(task,"Thread2").start();*/

    }
}

class Task implements Runnable{
    //重入锁
    ReentrantLock lock;

    public Task(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (true){
                System.out.println(Thread.currentThread().getName());
            }
        }finally {
            lock.unlock();
        }
    }
}
