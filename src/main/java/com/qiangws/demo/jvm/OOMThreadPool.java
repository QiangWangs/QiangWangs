package com.qiangws.demo.jvm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : OOMThreadPool
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/3/29
 * @description :  TODO
 */
public class OOMThreadPool {
    private final Byte[] toLeak;

    public OOMThreadPool() {
        toLeak = new Byte[1024 * 1024];
    }

    static final Thread [] t = new Thread[1];

    static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 5,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(9), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            t[0] = new Thread(r);
            t[0].setDaemon(false);
            t[0].setPriority(Thread.NORM_PRIORITY);
            t[0].setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    e.printStackTrace();
                    System.out.println(t.getName()+" 的状态: "+ t.getState());
                    System.out.println("这里是没有捕获的处理 ===> "+t.getId()+" ===> "+ e.getLocalizedMessage());
                }
            });
            return t[0];
        }
    }, new ThreadPoolExecutor.DiscardOldestPolicy()){
        @Override
        public void afterExecute(Runnable r, Throwable t){
            System.out.println(Thread.currentThread().getName() + " 任务执行完成,但是线程不会结束");
            if(null != t){
                System.out.println(Thread.currentThread().getName() + "任务异常了");
                t.printStackTrace();
            }
        }
    };

    // 为快速发生oom 设置堆大小： VM args: -Xms10m -Xmx10m

    /**
     * 线程池中，thread-0 线程抛出OOM 后线程的task任务结束，thread-0 线程并没有结束，而是可以接收新的任务。
     * 当然，main线程依旧会循环打印"我还行....."。
     * 啰嗦一下：在线程池中，thread-0 线程当前的task任务结束了，但是线程没有结束，还是可以执行新任务的。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        List<OOMThreadPool> list  =new LinkedList<>();
        Runnable target = ()->{
            System.out.println(Thread.currentThread().getName() + "开始了");
            try{
                while (true){
                   list.add(new OOMThreadPool());
                }
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
        };

        threadPool.submit(target);

        while (true){
            System.out.println(Thread.currentThread().getName() + "我还行");
            System.out.println(t[0].getName()+ " 的状态: " + t[0].getState());
            Thread.sleep(1000L);
        }
    }





}
