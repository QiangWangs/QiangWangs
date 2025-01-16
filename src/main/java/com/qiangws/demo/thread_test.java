package com.qiangws.demo;


/**
 * @program: code-summary
 * @ClassName thread_test
 * @description:
 * @author: QiangWs
 * @create: 2022-12-23 21:20
 * @Version 1.0
 **/
public class thread_test {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 10;
        }
    };

    public static void test1(){
        int i1 = threadLocal.get().intValue();
        threadLocal.set(i1+10);
        System.out.println(threadLocal.get().intValue());
        threadLocal.remove();
    }

    public static void main(String[] args) throws Exception {


        Thread[] thread = new Thread[5];
        for (int i=0;i<5;i++){
            thread[i] = new Thread(()->{
                test1();
            });
            thread[i].start();
        }

        test2();
    }


    static Object o = new Object();

    public static void test2() throws Exception {
        //java.lang.Thread中有一个方法叫holdsLock()，它返回true如果当且仅当当前线程拥有某个具体对象的锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("child thread: holdLock: " +
                            Thread.holdsLock(o));
                }
            }
        }).start();
        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);

    }
}
