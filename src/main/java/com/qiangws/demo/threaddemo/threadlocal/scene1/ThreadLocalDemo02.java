package com.qiangws.demo.threaddemo.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 王强
 * @create 2022/1/19 14:14
 */
public class ThreadLocalDemo02 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int s = i;
            threadPool.submit(()->{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                Date date = new Date(1000*s);
                String data = simpleDateFormat.format(date);
                System.out.println(data);
            });
        }
        threadPool.shutdown();
    }

}
