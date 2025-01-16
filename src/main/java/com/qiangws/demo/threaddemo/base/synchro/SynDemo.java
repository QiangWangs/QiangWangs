package com.qiangws.demo.threaddemo.base.synchro;

/**
 * @author 王强
 * @create 2021/7/20 15:26
 * 本质: 共享资源 互斥
 * 针对加锁的修饰类型 代表锁的颗粒度不同
 * 实例方法:对象实例
 * 静态方法:类对象
 * 代码块:指定对象
 *
 * jvm为了提高锁获取和释放的效率->偏向锁 轻量级
 * 1.6前(阻塞或者唤醒一个java线程想要系统切换cpu来完成,耗费处理器时间) 后引入了锁升级的概念
 */
public class SynDemo {

    //方法同步(作用域是同一个对象） 锁实例
    //synchronized修饰方法，该方法会被加上一个ACC_SYNCHRONIZED同步标识，表明在执行该方法时，必须先拿到该方法的锁，否则相关线程会被阻塞。
    //其执行流程是：线程进入synchronized修饰的方法时会先上锁（假设没有其他线程访问），方法执行完成后会自动解锁，之后下一个线程才能进入这个方法里，不解锁的话，
    // 其他线程是无法访问改方法的。
    //原理
    //它的原理是在方法的flags中增加ACC_SYNCHRONIZED标记，有ACC_SYNCHRONIZED标记的方法在被调用时，调用指令会先去检查方法的ACC_SYNCHRONIZED访问标志是否设置，
    // 如果设置了，执行线程先要持有同步锁，然后才能执行方法，否则相关线程会被阻塞。
    public synchronized void test1(){
        try {
            System.out.println(Thread.currentThread().getName()+"开始运行");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"结束运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //方法静态方法 锁类
    public static synchronized void test2(){
        try {
            System.out.println(Thread.currentThread().getName()+"开始运行");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"结束运行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test3(){
        System.out.println("sada");
        synchronized(SynDemo.class){//锁类
            try {
                System.out.println(Thread.currentThread().getName()+"开始运行");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+"结束运行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        SynDemo demo = new SynDemo();
        //SynDemo demo1 = new SynDemo();
        /*new Thread(()->{
            demo.test1();
        },"Thread1").start();
        new Thread(()->{
            demo1.test1();
        },"Thread2").start();*/

        //SynDemo demo1 = new SynDemo();
//        SynDemo demo2 = new SynDemo();
       /* new Thread(()->{
            demo.test3();
        },"Thread1").start();
        new Thread(()->{
            demo1.test3();
        },"Thread2").start();*/
//        new Thread(()->{
//            demo2.test2();
//        },"Thread3").start();
    }
}

