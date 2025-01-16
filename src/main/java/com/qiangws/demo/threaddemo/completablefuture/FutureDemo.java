package com.qiangws.demo.threaddemo.completablefuture;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author 王强
 * @create 2022/5/18 15:08
 */
public class FutureDemo {

    public static ThreadLocal<SimpleDateFormat> local = ThreadLocal.withInitial(()->
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );
    static class AddDemo implements Callable<Integer>{
        private int x;
        private int y;

        public AddDemo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Integer call() throws Exception {
            SimpleDateFormat dateFormat = local.get();
            System.out.println("执行call方法："+dateFormat.format(new Date()));
            TimeUnit.SECONDS.sleep(2);
            return x+y;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleDateFormat dateFormat = local.get();
        AddDemo addDemo = new AddDemo(1,2);
        FutureTask<Integer> integerFutureTask = new FutureTask<>(addDemo);
        new Thread(integerFutureTask).start();
        System.out.println(integerFutureTask.get());
        System.out.println("main执行结束："+dateFormat.format(new Date()));
    }
}
