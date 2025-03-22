package com.qiangws.demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @program: code-summary
 * @ClassName UnsafeDemo
 * @description:
 * @author: QiangWs
 * @create: 2022-12-05 15:35
 * @Version 1.0
 **/
public class UnsafeDemo {

    public static void main(String[] args) {
        System.out.println(reflectGetUnsafe());
    }
    //通过反射获取Unsafe实例
    /*Unsafe 是位于sun.misc包下的一个类，它可以让我们直接访问系统内存资源、自主管理内存资源等，
    这些方法在提升Java运行效率、增强Java语言底层资源操作能力方面起到了很大的作用。但由于Unsafe类使Java语言拥有了
    类似C语言指针一样操作内存空间的能力，这无疑也增加了程序发生相关指针问题的风险。

    内存操作
        //分配内存, 相当于C++的malloc函数
        public native long allocateMemory(long bytes);
        //扩充内存
        public native long reallocateMemory(long address, long bytes);
        //释放内存
        public native void freeMemory(long address);
        //在给定的内存块中设置值
        public native void setMemory(Object o, long offset, long bytes,\byte value);
        //内存拷贝
        public native void copyMemory(Object srcBase, long srcOffset,Object destBase, long destOffset, long bytes);
    CAS相关
        public final native boolean compareAndSwapObject(Object var1, longvar2, Object var4, Object var5);
        public final native boolean compareAndSwapInt(Object var1, longvar2, int var4, int var5);
        public final native boolean compareAndSwapLong(Object var1, longvar2, long var4, long var6);
    线程调度
        //取消阻塞线程
        public native void unpark(Object thread);
        //阻塞线程
        public native void park(boolean isAbsolute, long time);
        //获得对象锁（可重入锁）
        @Deprecated
        public native void monitorEnter(Object o);
        //释放对象锁
        @Deprecated
        public native void monitorExit(Object o);
        //尝试获取对象锁
        @Deprecated
        public native boolean tryMonitorEnter(Object o);
    内存屏障
        //内存屏障，禁止load操作重排序。屏障前的load操作不能被重排序到屏
        障后，屏障后的load操作不能被重排序到屏障前
        public native void loadFence();
        //内存屏障，禁止store操作重排序。屏障前的store操作不能被重排序到屏障后，
        屏障后的store操作不能被重排序到屏障前
        public native void storeFence();
        //内存屏障，禁止load、store操作重排序
        public native void fullFence();


        //获取给定地址值，忽略修饰限定符的访问限制。与此类似操作还有: getInt，getDouble，getLong，getChar等
        public native Object getObject(Object o, long offset);
        //为给定地址设置值，忽略修饰限定符的访问限制，与此类似操作还有: putInt,putDouble，putLong，putChar等
        public native void putObject(Object o, long offset, Object x);
        //获取给定地址的byte类型的值（当且仅当该内存地址为allocateMemory分配时，此方法结果为确定的）
        public native byte getByte(long address);
        //为给定地址设置byte类型的值（当且仅当该内存地址为allocateMemory分配时，此方法结果才是确定的）
        public native void putByte(long address, byte x);
    */
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field =
                    Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

