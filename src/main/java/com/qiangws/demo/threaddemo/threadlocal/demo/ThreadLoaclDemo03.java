package com.qiangws.demo.threaddemo.threadlocal.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王强
 * @create 2021/11/11 15:03
 */
public class ThreadLoaclDemo03 {
    static ThreadLocal<Map> local = new ThreadLocal<Map>();
    static ThreadLocal<Integer> locals =new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    static Map map = new HashMap();
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            map.put("wenwen",12);
            local.set(map);
            locals.set(1);
            System.out.println(Thread.currentThread().getName()+local.get()+" - "+locals.get());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+local.get()+" - "+locals.get());
        },"0");
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put("wenwen",18);
            local.set(map);locals.set(2);
            System.out.println(Thread.currentThread().getName()+(Map)local.get()+" - "+locals.get());
        },"1");
        thread.start();
        thread1.start();
    }
}
