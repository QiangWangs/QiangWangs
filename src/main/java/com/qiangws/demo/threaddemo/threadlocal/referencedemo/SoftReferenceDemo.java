package com.qiangws.demo.threaddemo.threadlocal.referencedemo;

import java.lang.ref.SoftReference;

/** 软
 * @author 王强
 * @create 2022/6/7 16:22
 */
public class SoftReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object b = new Object();
        SoftReference sr = new SoftReference(b);//此时sr是软引用
        b = null;//这一步就是断开强引用链接
        //软引用的特点就是当内存满了就会回收软引用关联的对象，如果内存没满，则不会回收
        System.gc();
        Thread.sleep(1000);
        System.out.println(sr.get());
    }
}
