package com.qiangws.demo.threaddemo.countdownlatch;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *@author
 *
 *@create 2021/7/27 20:07
 *
 */
public class CountDownLatchDemo {
    /**
     * 倒计时器CountDownLatch
     * 为了能够理解 CountDownLatch，举一个很通俗的例子，运动员进行跑步比赛时，假设有 6 个运动员参与比赛，裁判员在终点会为这 6 个运动员分别计时，
     * 可以想象每当一个运动员到达终点的时候，对于裁判员来说就少了一个计时任务。直到所有运动员都到达终点了，裁判员的任务也才完成。这 6 个运动员可以类比成 6 个线程，
     * 当线程调用 CountDownLatch.countDown 方法时就会对计数器的值减一，直到计数器的值为 0 的时候，裁判员（调用 await 方法的线程）继续往下执行。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < latch.getCount(); i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                        Thread.sleep((long) (Math.random() * 100));
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
                        latch.countDown();//当前线程调用此方法，则计数减一
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }

        try {
            System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
            latch.await();//阻塞当前线程，直到计数器的值为0
            System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}