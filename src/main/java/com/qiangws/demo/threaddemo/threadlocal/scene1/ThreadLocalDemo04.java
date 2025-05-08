package com.qiangws.demo.threaddemo.threadlocal.scene1;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author 王强
 * @create 2022/1/19 14:14
 * 官方条件装配
 */
public class ThreadLocalDemo04 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));
    public static void main(String[] args) throws InterruptedException {


//        String ii = "1,2,3,5";
//
//String a = "1,2,3,4,56,,,,7,8,,8,";
//
//        System.out.println(ii.concat(a));
//        String s = a.replaceAll("\\,+", ",");
//        System.out.println(s);
//
//        List<String> as =new ArrayList<>();
//        as.add("1");as.add("s1");
//
//        List<String> list2 =
//                as.stream().map(data -> {
//                    ArrayList<String> b = new ArrayList<String>();
//                    b.add("a"); b.add("a"); b.add("a");
//                    return b;
//                }).collect(Collectors.toList())
//                        .stream().flatMap(Collection::stream).collect(Collectors.toList());
//        System.out.println(list2);
//
//        String av =(String)null;

        for (int i = 0; i < 1000; i++) {
            int s = i;
            threadPool.submit(()->{
                Date date = new Date(1000*s);
                SimpleDateFormat dateFormat = dateFormatThreadLocal.get();
                String data = dateFormat.format(date);
                System.out.println(data);
            });
        }
        threadPool.shutdown();
    }
}
