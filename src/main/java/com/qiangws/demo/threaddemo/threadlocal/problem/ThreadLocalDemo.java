package com.qiangws.demo.threaddemo.threadlocal.problem;

public class ThreadLocalDemo {
    private static ThreadLocal<User> sThreadLocal = new ThreadLocal<>();

    public static void main(String args[]) {
        User user = new User("", 310);
        user.setName("这是在主线程中");
        sThreadLocal.set(user);
        System.out.println("线程名字：" + Thread.currentThread().getName() + "---" + sThreadLocal.get());
        //线程a
        new Thread(new Runnable() {
            @Override
            public void run() {
                user.setName("这是在线程a中");
                user.setAge(311);
                sThreadLocal.set(user);
                System.out.println("线程名字：" + Thread.currentThread().getName() + "---" + sThreadLocal.get());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //引用对象内容变更
                System.out.println("线程名字：" + Thread.currentThread().getName() + "---" + sThreadLocal.get());
            }
        }, "线程a").start();
        //线程b
        new Thread(new Runnable() {
            @Override
            public void run() {
                user.setName("这是在线程b中");
                user.setAge(312);
                sThreadLocal.set(user);
                System.out.println("线程名字：" + Thread.currentThread().getName() + "---" + sThreadLocal.get());
            }
        }, "线程b").start();

        //线程c
        new Thread(() -> {
            user.setName("这是在线程c中");
//            user.setAge(313);
            sThreadLocal.set(user);
            System.out.println("线程名字：" + Thread.currentThread().getName() + "---" + sThreadLocal.get());
        }, "线程c").start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}

