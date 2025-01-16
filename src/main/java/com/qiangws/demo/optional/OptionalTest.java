package com.qiangws.demo.optional;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : OptionalTest
 * @packageName : com.qiangws.demo.optional
 * @createTime : 2023/3/21
 * @description :  TODO
 */
public class OptionalTest {


    public static void main(String[] args) {
        optional();
    }

    private static void optional(){
        /*Map map = new HashMap();
        map.put("key","value");
        Map map1 = new HashMap();
        Optional.ofNullable("").ifPresent(v->{
            map.put("keys","values");
        });

        System.out.println("----------------");

        map.forEach((key,value)->{
            System.out.println("key:"+key+",value:"+value);
        });*/

        dataHandler();

    }

    //最大线程数控制
    private static int MAX_THREADS= 5;
    //跑批分页大小
    private static int EXPIRED_PAGE_SIZE = 30;

 

    private static void dataHandler() {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 10001; i++){
            list.add(i);
        }
        //处理数据数量
        int listSize = list.size();
        //线程数
        int runSize;
        if (listSize % EXPIRED_PAGE_SIZE == 0) {
            runSize = (listSize / EXPIRED_PAGE_SIZE);
        } else {
            runSize = (listSize / EXPIRED_PAGE_SIZE) + 1;
        }
        //定时任务线程池
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(runSize);
        CountDownLatch countDownLatch = new CountDownLatch(runSize);
        //最大并发线程数控制
        final Semaphore semaphore = new Semaphore(MAX_THREADS);
        List handleList = null;
        for (int i = 0; i < runSize; i++) {
            if ((i + 1) == runSize) {
                int startIndex = i * EXPIRED_PAGE_SIZE;
                int endIndex = list.size();
                handleList = list.subList(startIndex, endIndex);
            } else {
                int startIndex = i * EXPIRED_PAGE_SIZE;
                int endIndex = (i + 1) * EXPIRED_PAGE_SIZE;
                handleList = list.subList(startIndex, endIndex);
            }
            SyncTask task = new SyncTask(handleList, countDownLatch, semaphore);
            executor.execute(task);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            executor.shutdown();
        }
    }

   static class SyncTask implements Runnable {
        private List<Integer> list;
        private CountDownLatch countDownLatch;
        private Semaphore semaphore;

        public SyncTask(List<Integer> list, CountDownLatch countDownLatch, Semaphore semaphore) {
            this.list = list;
            this.countDownLatch = countDownLatch;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            if (!CollectionUtils.isEmpty(list)) {
                try {
                    semaphore.acquire();
                    list.stream().forEach(fileDto -> {
                        //业务处理
                        System.out.println(Thread.currentThread()+":"+fileDto);
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }
            //线程任务完成
            countDownLatch.countDown();
        }
    }
}
