package com.qiangws.demo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @program: code-summary
 * @ClassName reference
 * @description:除了基础的数据类型外都是引用类型，那么java根据其生命周期的长短又将
 * 引用类型分为强引用、软引用、弱引用、虚引用
 * @author: QiangWs
 * @create: 2022-12-23 21:40
 * @Version 1.0
 **/
public class reference {

    /**
     * 当JVM的内存空间不足时，宁愿抛出OutOfMemoryError使得程序异常终止
     * 也不愿意回收具有强引用的存活着的对象！记住是存活着，不可能是你new一个对象就永远不会被GC回收。
     * 如果将引用赋值为null时，你的对象就表明不是存活着，这样就会可以被GC回收了
     * 当内存不足的时候，jvm开始垃圾回收，对于强引用的对象，就算出现OOM也不会回收该对象的。 因此，强引用是造成java内存泄露的主要原因之一。
     */
    public static void test1() {
        Object obj=new Object();//这样定义就是一个强引用
        Object obj2=obj;//也是一个强引用
        obj=null;
        System.gc();//不会被垃圾回收
        System.out.println(obj2);


    }

    /**
     * 软引用是通过SoftReference类实现的。当JVM认为内存空间不足时，就会去试图回收软引用指向的对象
     * 对于只有软引用的对象来说， 当系统内存充足时，不会被回收； 当系统内存不足时，会被回收；
     */
    public static void test2() {
        Object obj=new Object();
        SoftReference wrf=new SoftReference(obj);
        obj=null;
        System.out.println("未发生GC之前"+wrf.get());
        System.gc();
        System.out.println("内存充足，发生GC之后"+wrf.get());
    }

    /**
     * 弱引用是通过WeakReference类实现的，它的生命周期比软引用还要短，也是通过get()方法获取对象。在GC的时候，不管内存空间足不足都会回收
     * 这个对象，同样也可以配合ReferenceQueue使用，也同样适用于内存敏感的缓存。ThreadLocal中的key就用到了弱引用。
     */
    public static void test3() {
        Object obj=new Object();
        WeakReference wrf=new WeakReference(obj);
        obj=null;
        System.out.println("未发生GC之前"+wrf.get());
        System.gc();
        System.out.println("内存充足，发生GC之后"+wrf.get());
    }

    /**
     * 通过PhantomReference类实现的。任何时候可能被GC回收，就像没有引用一样。无法通过虚引用访问对象的任何属性或者函数。
     * 那就要问了要它有什么用？虚引用仅仅只是提供了一种确保对象被finalize以后来做某些事情的机制。
     * 比如说这个对象被回收之后发一个系统通知啊啥的。
     * 虚引用是必须配合ReferenceQueue 使用的，具体使用方法和上面提到软引用的一样。主要用来跟踪对象被垃圾回收的活动。
     */
    public static void test4() {
        Object obj=new Object();
        PhantomReference wrf=new PhantomReference(obj,new ReferenceQueue());
        obj=null;
        System.out.println("未发生GC之前"+wrf.get());
        System.gc();
        System.out.println("内存充足，发生GC之后"+wrf.get());
    }

    public static void main(String[] args) {
        //强引用
        //test1();
        //软引用
        //test2();
        //弱引用
        //test3();
        //虚引用
        //test4();
    }
}
