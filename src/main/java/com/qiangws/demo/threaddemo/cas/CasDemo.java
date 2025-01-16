package com.qiangws.demo.threaddemo.cas;

import sun.misc.Unsafe;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : UnsafeDemo
 * @packageName : com.qiangws.demo.threaddemo.Unsafe
 * @createTime : 2024/11/1
 * @description :  TODO
 */
public class CasDemo {

    private volatile int state = 0;
    private static Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(CasDemo.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public  void doSomething(){
        if(unsafe.compareAndSwapInt(this,stateOffset,0,1)){
            System.out.println("123456789");
        }
    }

    /**
     * cas(CompareAndSwap) 比较并替换。  内存地址偏量值 预期值 变更值       navive方法
     * 是用于保证再多线程环境下，保证对共享变量的修改的原子性。
     * 典型的Read-Write情况下,读写操作是发生在不同线程,不是实时可见的
     *  CompareAndSwap 底层实现中，在多核CPU环境中,会增加一个Lock指令对缓存或总线加锁,来保证比较并替换这两个指令的原子性
     *   CAS主要用到并发场景中
     *  1. JUC里面的Atomic的原子实现,比如AtomicInteger AtomicLong
     *  2. 实现多线程对共享资源竞争的互斥性质，AQS，ConcurrentHashMap,ConcurrentLinkeQueue
     * @param args
     */
    public static void main(String[] args) {

    }

}
