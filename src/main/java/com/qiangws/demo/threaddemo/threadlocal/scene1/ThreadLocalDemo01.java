package com.qiangws.demo.threaddemo.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 王强
 * @create 2022/1/19 14:14
 */
public class ThreadLocalDemo01 {
    //这里不会出现线程不安全的问题
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int s = i;
            new Thread(()->{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                Date date = new Date(1000*s);
                String data = simpleDateFormat.format(date);
                System.out.println(data);
            }).start();
            Thread.sleep(100);
        }
    }
}
