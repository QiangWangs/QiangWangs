package com.qiangws.demo.threaddemo.problem;

/**
 * @program: code-summary
 * @ClassName VolatileExample
 * @description: 可见性 线程外改的变量，对于线程不可见
 * @author: QiangWs
 * @create: 2022-12-07 17:46
 * @Version 1.0
 **/
public class VolatileExample {
    public static boolean stop=false;//volatile可以解决可见性问题
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            int i=0;
            while(!stop){ //load
                i++;//。。。。
            }
            System.out.println(i);
        });
        t1.start();
        System.out.println("begin start thread");
        Thread.sleep(10);
        stop=true; //store
        //storeload()
    }
}
