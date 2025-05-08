package com.qiangws.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : OOMObject
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/3/29
 * @description :  TODO  线程OOM，JVM不一定退出
 */
public class OOMObject {

    private final Byte[] toLeak;

    public OOMObject() {
        toLeak = new Byte[1024 * 1024];
    }

    //hread-0 线程抛出OOM 后线程结束后，main线程依旧会循环打印"我还行....."。
    //线程中发生OOM异常，和发生其他异常一样，只是那个线程终止了，但是不影响其他线程，thread-0 线程线程OOM，也不会导致JVM退出。
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            List<OOMObject> list = new ArrayList<>();
            while (true) {
                list.add(new OOMObject());
            }
        });
        //设置未捕获异常处理器：实现未捕获异常处理器的 uncaughtException 方法
        //thread.setUncaughtExceptionHandler：这个方法用于为指定的线程设置一个未捕获异常处理器。当该线程抛出未捕获的异常时，这个处理器会被调用。
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
                System.out.println("这里是没有捕获的处理 ==> " +t.getId() + " ==> "+ e.getLocalizedMessage());
            }
        });

        thread.start();
        while (true){
            System.out.println(Thread.currentThread().getName()+" 我还行 。。。");
            Thread.sleep(1000L);
            System.out.println(thread.getName() + " 的状态 "+ thread.getState());
        }
    }
}
