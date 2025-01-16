package com.qiangws.demo.threaddemo.threadlocal.demo;

/**
 * @author 王强
 * @create 2021/7/9 22:19
 */
public class ThreadLocalDemo02 {
    static ThreadLocal<Integer> girl = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    static ThreadLocal<Integer> woman = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                int num = girl.get();
                girl.set(num += 5);
                woman.set(10);
                girl.get();
                System.out.println(Thread.currentThread().getName() + "的num=" + girl.get());
            });
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
        girl.remove();
    }
}

