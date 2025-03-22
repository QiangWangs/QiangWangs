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


    /**
     * ‌创建Optional对象‌：
     *
     * Optional.of(T value)：创建一个包含给定值的Optional对象。如果值为null，则抛出NullPointerException。
     * Optional.empty()：创建一个空的Optional对象。
     * Optional.ofNullable(T value)：创建一个可能包含给定值的Optional对象。如果值为null，则创建一个空的Optional对象。
     * ‌获取值‌：
     *
     * get()：如果值存在，则返回该值，否则抛出NoSuchElementException。
     * orElse(T other)：如果值存在，则返回该值，否则返回一个默认值。
     * orElseGet(Supplier<? extends T> other)：如果值存在，则返回该值，否则通过调用提供的Supplier函数获取默认值。
     * orElseThrow(Supplier<? extends X> exceptionSupplier)：如果值存在，则返回该值，否则抛出一个由提供的Supplier生成的异常。
     * ‌判断值是否存在‌：
     *
     * isPresent()：如果值存在，则返回true，否则返回false。
     * ifPresent(Consumer<? super T> consumer)：如果值存在，则执行给定的操作。
     * ‌转换和映射‌：
     *
     * map(Function<? super T, ? extends U> mapper)：如果值存在，则对其应用给定的函数，并返回一个新的Optional对象，该对象包含应用函数后的结果。如果原始Optional对象为空，则返回一个新的空Optional对象。
     * flatMap(Function<? super T, Optional<U>> mapper)：与map类似，但映射函数必须返回一个Optional对象。然后，结果将是一个Optional的Optional，但会被“扁平化”为一个Optional。
     * ‌过滤‌：
     *
     * filter(Predicate<? super T> predicate)：如果值存在并且满足给定的谓词条件，则返回一个包含该值的Optional对象；否则返回一个空的Optional对象。
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个Optional对象
        Optional<String> optionalString = Optional.ofNullable("Hello, Optional!");

        System.out.println(optionalString.orElseGet(()->"Default"));
        // 判断值是否存在
        if (optionalString.isPresent()) {
            System.out.println(optionalString.get());
        }

        // 使用orElse获取值
        String valueOrDefault = optionalString.orElse("Default value");
        System.out.println(valueOrDefault);

        // 使用map进行转换
        Optional<Integer> optionalLength = optionalString.map(String::length);
        optionalLength.ifPresent(System.out::println);

        // 使用filter进行过滤
        Optional<String> filteredOptional = optionalString.filter(s -> s.contains("Optional"));
        filteredOptional.ifPresent(System.out::println);

        //optional();
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
