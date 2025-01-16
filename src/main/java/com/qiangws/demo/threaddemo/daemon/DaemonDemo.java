package com.qiangws.demo.threaddemo.daemon;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : DaemonDemo
 * @packageName : com.qiangws.demo.threaddemo.daemon
 * @createTime : 2024/10/31
 * @description :  TODO
 */
public class DaemonDemo {

    /**
     * 守护线程就是一种后台服务线程
     * 守护线程的生命周期依赖于用户线程
     * jvm垃圾回收线程就是一个典型的守护线程,它存在的意义是不断的处理用户线程运行过程中产生的内存垃圾。 一但用户线程全部结束了，那么垃圾回收器就没有存在的意义了
     *
     * 由于守护线程的特性，所以它适合用在一些后台的通用服务场景里面。
     * 但是守护线程不能用在线程池或者一些 IO 任务的场景里面，因为一旦 JVM 退出之后，
     * 守护线程也会直接退出。
     * 就会可能导致任务没有执行完或者资源没有正确释放的问题。
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+" close");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        thread.start();
        thread2.setDaemon(true);
        thread2.start();

    }
}
