package com.qiangws.demo;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Test
 * @packageName : com.qiangws.demo
 * @createTime : 2023/8/24
 * @description :  TODO
 */
@Log4j2
public class Test {

    public static void test(){
        for (int i=1;i<100;i++) {
            test1(i);
//            try{
//                Thread.sleep(1000);
//            }catch (Exception e){
//                throw new RuntimeException(e);
//            }
        }
    }

    public static void test1(Integer i){
        log.info("i:{}", i);
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {



//        int interval = 500;
//        Long idMaximum = 499L;
//        for (int startIdx = 0, endIdx = interval; endIdx <= idMaximum+interval ; startIdx = endIdx, endIdx += interval) {
//            System.out.println(startIdx + " " + endIdx);
//        }
//        int a = 3;
//        int b = 300;
//
//        // 1. 直接取整
//        int floor = a / b;  // 3
//
//        // 2. 向上取整
//        int ceil = (int) Math.ceil((double) a / b);  // 4
//
//        // 3. 四舍五入
//        int round = (int) Math.round((double) a / b);  // 3
//
//        // 4. 处理负数的余数
//        int negativeA = -10;
//        int quotient = negativeA / b;  // -3
//        int remainder = negativeA % b; // -1
//
//        System.out.println("向下取整: " + floor);
//        System.out.println("向上取整: " + ceil);
//        System.out.println("四舍五入: " + round);
//        System.out.println("负数的商/余数: " + quotient + ", " + remainder);

//        test();
//        //String创建对象的问题
//
//        String  a = new String("123") + new String("456");
//        // String b = new String(“123456”);
//        System.out.println(a.intern() == a);//true
        
        // 为什么当第二行放开的时候，结果又变为false
        //intern()的含义是如果字符串常量池中已经包含一个字符串等于此String对象的字符串，则返回常量池中的这个String对应的对象，否则将其添加到常量池并返回常量池中的引用。
        //如果你第二行注释，代表常量池没有123456，所以会添加一个123456到常量池，并把引用给到a
        //如果第二行放开，那么常量池就提前有了一个123456，跟原来的a的指向是不一样的
        
        //boolean bool = false;
        


//        final Semaphore semaphore = new Semaphore(3);
//
//        // 生产者线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        semaphore.acquire();
//                        System.out.println("生产者 " + Thread.currentThread().getName() + " 生产了 " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        semaphore.release();
//                    }
//                }
//            }
//        }).start();
//
//        // 消费者线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    try {
//                        semaphore.acquire();
//                        System.out.println("消费者 " + Thread.currentThread().getName() + " 消费了 " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } finally {
//                        semaphore.release();
//                    }
//                }
//            }
//        }).start();
    }


     
}
