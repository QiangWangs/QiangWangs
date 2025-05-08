package com.qiangws.demo.jvm;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : JvmDemo
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/3/27
 * @description :  TODO
 */
public class JvmDemo {
    /**
     * 内存设置：
     * -Xms：设置JVM的初始堆内存。
     * -Xmx：设置JVM的最大堆内存。
     * -XX:NewRatio和-XX:OldRatio：控制新生代和老年代的内存比例。
     * -XX:MaxPermSize（JDK 8及之前）和-XX:MaxMetaspaceSize（JDK 8及之后）：设置永久代和元数据区的大小。
     * 垃圾回收器设置：
     * -XX:+UseG1GC：启用G1垃圾回收器。
     * -XX:MaxGCPauseMillis：设置G1 GC的目标最大停顿时间。
     * -XX:ParallelGCThreads：设置并行垃圾回收线程数。
     * JIT编译器优化：
     * -XX:+TieredCompilation：启用分层编译。
     * -XX:CompileThreshold：设置JIT编译的阈值。
     * 线程和锁：
     * -XX:ThreadStackSize：设置每个线程的栈大小。
     * -XX:+UseBiasedLocking：启用偏向锁。
     * 性能监控和调试：
     * -XX:+PrintGCDetails：输出详细的GC日志。
     * -XX:+PrintConcurrentLocks：输出应用程序锁的信息。
     * -XX:+HeapDumpOnOutOfMemoryError：在内存溢出时生成堆转储文件。
     * 系统属性：
     * -Djava.awt.headless=true：在无图形界面的环境中运行Java应用。
     * -Djava.net.preferIPv4Stack=true：优先使用IPv4网络堆栈。
     * 五、配置说明和适用性
     * 内存设置：假设服务器有至少32GB的可用内存，可以配置-Xms4g（初始堆内存4GB）和-Xmx32g（最大堆内存32GB）。
     * 新生代和老年代：设置-XX:NewRatio=1和-XX:OldRatio=2，使新生代与老年代的堆内存比例为1:2。
     * 垃圾回收器：使用G1 GC并设置目标最大GC停顿时间为100毫秒，-XX:MaxGCPauseMillis=100。
     * JIT编译器：启用分层编译并设置编译阈值为10000次，-XX:+TieredCompilation和-XX:CompileThreshold=10000。
     * 线程和锁：设置线程栈大小为256KB，-XX:ThreadStackSize=256k，并启用偏向锁，-XX:+UseBiasedLocking。
     * 性能监控和调试：输出详细的GC日志和应用程序锁信息，-XX:+PrintGCDetails和-XX:+PrintConcurrentLocks。
     *
     * 系统属性：
     * 系统属性通常在运行时通过System.setProperty方法设置。
     * 这些属性在Java应用运行期间可以被访问和修改。
     * JVM启动参数：
     * JVM启动参数在JVM启动时设定，如内存大小和垃圾回收策略。
     * 常见的启动参数包括-Xms（设置初始堆大小）、-Xmx（设置最大堆大小）、-XX:+UseG1GC（启用G1垃圾回收器）等。
     * 虽然部分JVM启动参数在运行时无法更改，但通过设置合适的初始参数和监控内存状况，依然可以达到优化目的。此外，通过动态调整应用程序的内存使用（如对象的创建和释放），可以间接实现性能优化。
     *
     * 二、动态设置JVM参数的方法
     * 使用System.setProperty方法：
     * System.setProperty方法用于设置系统属性。
     * System.getProperty方法用于获取指定的系统属性。
     * 使用Runtime类获取JVM信息：
     * Runtime.getRuntime().maxMemory()方法返回JVM可以使用的最大内存。
     */
    public static void main(String[] args) {
        // 设置JVM系统属性
        System.setProperty("my.custom.property", "Hello, JVM!");

        // 获取已设置的JVM属性
        String customProperty = System.getProperty("my.custom.property");

        // 打印结果
        System.out.println("Custom Property: " + customProperty);

        // 读取当前JVM最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("Max Memory: " + maxMemory / (1024 * 1024) + "MB");

        // 示例：动态调整内存参数（虽然直接调整堆大小不可行，但可以通过监控和优化内存使用实现）
        // 以下代码仅为示例，不会直接改变堆大小
        System.out.println("This is an example of dynamically adjusting memory usage.");
        // 实际应用中，可以通过监控内存使用，优化对象创建和释放

        // 其他可能的动态设置示例
        // 启用或禁用断言
        System.setProperty("java.assertions", "true");
        boolean assertionsEnabled = Boolean.parseBoolean(System.getProperty("java.assertions"));
        System.out.println("Assertions Enabled: " + assertionsEnabled);

        // 设置系统属性以优化安全性和网络性能
        System.setProperty("java.net.preferIPv4Stack", "true");
        String ipv4Stack = System.getProperty("java.net.preferIPv4Stack");
        System.out.println("IPv4 Stack Preferred: " + ipv4Stack);

        // 启用偏向锁
        // 注意：偏向锁是通过JVM启动参数设置的，动态设置无效，但可以作为配置参考
        // -XX:+UseBiasedLocking
        // 这里仅展示如何获取和打印JVM是否启用了偏向锁（假设已启用）
        String biasedLocking = System.getProperty("sun.misc.Unsafe.useBiasedLocking");
        // 注意：实际上没有直接的系统属性可以查询偏向锁状态，这里仅为示例
        System.out.println("Biased Locking (example check, not actual): " + (biasedLocking != null && biasedLocking.equals("true")));
    }
}
