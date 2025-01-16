package com.qiangws.demo.threaddemo;

/**
 * @program: code-summary
 * @ClassName Counter
 * @description:
 * @author: QiangWs
 * @create: 2023-01-19 22:10
 * @Version 1.0
 **/
public class Counter {
    private static int count = 0;
    public static void add(int value) {
        count = count + value;
    }

    public static int getCount(){
        return count;
    }

    public static void main(String[] args) {

        for (int i=0;i<100;i++){
            new Thread(()-> {
                Counter.add(1);
            }).start();
        }
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Counter.getCount());
    }
}
