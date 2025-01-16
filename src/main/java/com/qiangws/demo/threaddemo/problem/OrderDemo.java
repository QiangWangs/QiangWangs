package com.qiangws.demo.threaddemo.problem;

/**
 * @author 王强
 * @create 2022/3/7 11:03
 */
public class OrderDemo {
    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        for(;;){
            new Thread(()->{
                add();
            }).start();
            new Thread(()->{
                dec();
            }).start();
            System.out.println(i);
        }
    }

    public static void add(){
        i++;
    }

    public static void dec(){
        i--;
    }
}


