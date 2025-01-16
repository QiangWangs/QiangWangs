package com.qiangws.demo.threaddemo.base.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();//调用start方法并不代表线程立马执行。
        Thread.sleep(200);
//        Task task = new Task();//创建的任务实例
//        Thread thread = new Thread(task);//任务得要人去执行
//        thread.start();

//        myThread.stop();

//        Thread thread = new Thread(new Task());
//        thread.start();
////
//        int i = 0;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        executorService.submit(new MyCallable());
//
//        FutureTask task = new FutureTask(new MyCallable());
//        task.run();
    }
}

//第一种创建方式
class MyThread extends Thread {
    @Override
    public void run() {
        while(true){
            System.out.println("第一种线程：");
        }
    }
}

//第二种创建方式
class Task implements Runnable {//就相当于洗衣服这件事
    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("第二种线程：" + i);
        }
    }
}

//第三种创建方式
class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        for (int i = 1; i <= 50; i++) {
            System.out.println("第三种线程：" + i);
        }
        return "第三种线程执行完毕";
    }
}
