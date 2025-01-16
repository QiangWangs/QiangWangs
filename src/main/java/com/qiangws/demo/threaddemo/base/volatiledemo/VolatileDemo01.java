package com.qiangws.demo.threaddemo.base.volatiledemo;

/**
 * 线程可见性问题
 */
public class VolatileDemo01 {
    public static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
                System.out.println("rs:" + i);
            }
            //JIT 深度优化,活性失败
            System.out.println("rs:" + i);
            //1.println底层加了synchronized锁,释放锁的时候会把缓存中的数据写入到主内存中
            //2.IO要比CPU执行效率慢,所以CPU执行完成后IO可能在执行,(缓存失效)
        });

        thread.start();
        Thread.sleep(1);
        int a=0;
        //变量线程不可见
        stop = true;
    }
}
