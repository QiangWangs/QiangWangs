package com.qiangws.demo.stream;

import java.util.function.Consumer;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Consumers
 * @packageName : com.qiangws.demo.stream
 * @createTime : 2025/3/14
 * @description :  TODO
 */
public class Consumers {

    public static void main(String[] args) {
        //Consumer接口中accept方法的作用是接收指定参数类型，无返回值，重点在于内部消费
        Consumer<String> consumer = s -> System.out.println("hello " + s);
        consumer.accept("mike");// hello mike

        //默认方法andThen作用是连续消费，从本Consumer开始，从外到内，针对同一入参。
        Consumer<String> consumer1 = s -> System.out.println("hello " + s);
        Consumer<String> consumer2 = s -> System.out.println("nice to meet you " + s);
        consumer1.andThen(consumer2).accept("mike");
        //hello mike
        //nice to meet you mike
    }
}
