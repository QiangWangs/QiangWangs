package com.qiangws.demo.jvm;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : ObjectLayout
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/3/22
 * @description :  TODO   计算出来一个对象的内存占用
 */

import org.openjdk.jol.info.ClassLayout;

public class ObjectTest {

    public static void printObjectLayout(Object obj) throws IllegalAccessException {
        String printable = ClassLayout.parseInstance(obj).toPrintable();
        System.out.println("printable = " + printable);
    }

    public static void main(String[] args) throws IllegalAccessException {
        MyOrder order = new MyOrder();
        printObjectLayout(order);
    }
}

// 定义一个名为MyOrder的类
class MyOrder {
    // 定义一个byte类型的变量state，表示订单状态
    byte state;
    // 定义一个long类型的变量orderId，表示订单ID
    long orderId;
    // 定义一个long类型的变量userId，表示用户ID
    long userId;
    // 定义一个long类型的变量createMillis，表示订单创建时间
    long createMillis;
}
