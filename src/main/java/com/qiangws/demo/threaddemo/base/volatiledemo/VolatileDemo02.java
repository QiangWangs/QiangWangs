package com.qiangws.demo.threaddemo.base.volatiledemo;

/**
 * 解决线程可见性问题 valatile 休眠 print打印(活性失败,做了一个深度优化) 都可以结束
 * volatitle 可以禁止JIT自动优化导致的问题
 * -Djava.compiler=NONE 禁止JIT自动优化
 */
public class VolatileDemo02 {
    //    public static boolean stop = false;
    public volatile static boolean stop = false;//3、加个volatile也可以结束

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
//               try {
//                   Thread.sleep(5);
//               } catch (InterruptedException e) {
//                   e.printStackTrace();
//               }//2、睡眠一下也可以结束
//               System.out.println("rs:"+i);//1、打印放在循环里面也可以结束
            }
            System.out.println("rs:" + i);
        });
        thread.start();
        Thread.sleep(1000);
        stop = true;
    }
}
