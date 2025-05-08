package com.qiangws.demo.jvm;

import java.lang.management.MemoryMXBean;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : test
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/4/2
 * @description :  TODO
 */
public class test {

    public static int i=1;
    public static void main(String[] args) {
        final long max= Runtime.getRuntime().maxMemory();//jvm的视图使用的最大内存
        final long total = Runtime.getRuntime().totalMemory();//jvm初始化内存
        final int cpu = Runtime.getRuntime().availableProcessors();//cpu核数
        //Runtime 类提供了与 Java 应用程序的运行时环境相关的方法。
        //Runtime.getRuntime().totalMemory() ：获取 JVM 的总内存量。
        //Runtime.getRuntime().freeMemory() ：获取 JVM 的空闲内存量。
        //Runtime.getRuntime().maxMemory() ：获取 JVM 尝试使用的最大内存量。
        //堆使用百分比可以通过计算 (totalMemory - freeMemory) / maxMemory 得到。
        
        /*-Xms10m  -Xmx10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -Xss2m
         * 表示配置jvm初始化内存为10M,最大内存为10M,打印GC详细文件,生成错误文件
         * */
        System.out.println("虚拟机获得最大内存"+(max/1024/1024)+"m");
        System.out.println("初始最大内存"+(total/1024/1024)+"m");
        System.out.println("本机核数:"+cpu);

        //java.lang.management 包提供了管理和监视 Java 虚拟机的类和接口。

        //MemoryMXBean 可以获取 Java 虚拟机的内存状态信息。

        //通过 ManagementFactory.getMemoryMXBean() 获取 MemoryMXBean 实例。然后可以使用 MemoryMXBean.getHeapMemoryUsage() 获取堆的内存使用情况。
        
        String s = new String();
        while(true){
            final byte[] bytes = new byte[1024 * 1024*1024];
        }
    } 
}
