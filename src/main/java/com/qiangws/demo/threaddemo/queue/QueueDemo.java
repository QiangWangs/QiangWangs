package com.qiangws.demo.threaddemo.queue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author 王强
 * @create 2022/5/11 11:53
 */
public class QueueDemo {

    public static void main(String[] args) {

        MyQueue queue = new MyQueue(5);
        queue.add("张三");
        queue.add("2");
        queue.add("1");
        queue.add("3");
        queue.add("31");
        queue.add("311");
        queue.add("3111");
        queue.add("31111");


        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }

//        MyBlockQueue q = new MyBlockQueue(10);
//        new Thread(()->{
//            while (true){
//                q.add(Math.random());
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();
//        new Thread(()->{
//            while (true){
//                System.out.println(q.poll());
//            }
//
//        }).start();
    }

}
