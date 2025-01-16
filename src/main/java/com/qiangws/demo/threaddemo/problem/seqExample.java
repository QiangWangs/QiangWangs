package com.qiangws.demo.threaddemo.problem;

/**
 * @program: code-summary
 * @ClassName seqExample
 * @description: 有序性 cpu里面的执行指令会发生重排,在单线程的时候，是不会影响最终结果，但是最多线程的情况下就会导致问题。
 * @author: QiangWs
 * @create: 2022-12-07 17:22
 * @Version 1.0
 **/
public class seqExample {
    private static volatile int a=0,b=0,x=0,y=0;

    public static void main(String[] args) throws InterruptedException {
        int i=0;
        for (;;){
            i++;
            //多线程下 cpu重排序有可能导致结果问题  x=0;y=0;最后执行
            //volatile 可以解决
            x=0;y=0;
            a=0;b=0;
            Thread t1=new Thread(()->{
                a=1;
                x=b;
            });
            Thread t2=new Thread(()->{
                b=1;
                y=a;
            });
            /**
             可能的结果：
             * 1和1
             * 0和1
             * 1和0
             ----
             * 0和0*/
             t1.start();
             t2.start();
             t1.join();
             t2.join();
             String result="第"+i+"次("+x+","+y+")";
             if(x==0&&y==0){
                System.out.println(result);
                break;
             }else{
             }
        }
    }
}
