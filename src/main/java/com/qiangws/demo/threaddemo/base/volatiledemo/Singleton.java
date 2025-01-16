package com.qiangws.demo.threaddemo.base.volatiledemo;
public class Singleton {
    /*
    如果不加volatile的话，不同的虚拟机可能会出现指令重排问题
    例如线程A在执行的时候，先通过
     */
    private static volatile Singleton instance;

    private Singleton() {

    }

    /*
    这种写法是可以保证线程安全的，假设有两个线程同时到达 synchronized 语句块，
    那么实例化代码只会由其中先抢到锁的线程执行一次，而后抢到锁的线程会在第二个 if 判断中发现 singleton 不为 null，
    所以跳过创建实例的语句。再后面的其他线程再来调用 getInstance 方法时，只需判断第一次的 if (singleton == null)，
    然后会跳过整个 if 块，直接 return 实例化后的对象。这种写法的优点是不仅线程安全，而且延迟加载、效率也更高。
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singleton();
                    //开辟空间
                    //实例化对象
                    //引用开辟空间地址
                }
            }
        }
        return instance;
    }
}
