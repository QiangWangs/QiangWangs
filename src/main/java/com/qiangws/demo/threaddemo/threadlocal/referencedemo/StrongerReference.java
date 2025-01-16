package com.qiangws.demo.threaddemo.threadlocal.referencedemo;

/** 强
 * @author 王强
 * @create 2022/6/7 16:21
 */
public class StrongerReference {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();//通过new关键字创建的对象，然后引用指向该对象，则就是强引用
        Object obj2 = obj1;//此时obj1和obj2都是强引用
        //强引用的特点就是宁愿抛内存溢出的异常也不回收强引用关联的对象。
        obj1 = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(obj2);
    }
}
