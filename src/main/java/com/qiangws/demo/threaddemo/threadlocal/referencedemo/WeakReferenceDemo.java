package com.qiangws.demo.threaddemo.threadlocal.referencedemo;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/** 弱
 * @author 王强
 * @create 2022/6/7 16:22
 */
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object b = new Object();
        WeakReference sr = new WeakReference(b);
        b = null;
        //弱引用的特点就是不管你内存充不充足，只要触发垃圾回收，就会回收对象
        System.gc();
        Thread.sleep(1000);
        System.out.println(sr.get());
    }
}
