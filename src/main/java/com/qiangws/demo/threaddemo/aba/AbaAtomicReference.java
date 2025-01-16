package com.qiangws.demo.threaddemo.aba;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: code-summary
 * @ClassName AbaAtomicReference
 * @description:CAS的ABA 问题
 * 上面提到，CAS 保证了比较和交换的原子性。但是从读取到开始比较这段期间，其他核心仍然是可以修改这个值的。
 * 如果核心将 A 修改为 B，CAS 可以判断出来。但是如果核心将 A 修改为 B 再修改回 A。那么 CAS 会认为这个值并没有被改变，
 * 从而继续操作。这是和实际情况不符的。解决方案是加一个版本号。
 * @author: QiangWs
 * @create: 2022-12-20 11:32
 * @Version 1.0
 **/
public class AbaAtomicReference {

    public static void test1(){
        //ABA问题
        System.out.println("==========ABA问题：");
        AtomicReference<String> reference = new AtomicReference<>("A");
        new Thread(() -> {
            //获取期望值
            String expect = reference.get();
            //打印期望值
            System.out.println(Thread.currentThread().getName() + "---- expect: " + expect);
            try {
                //干点别的事情
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印实际值
            System.out.println(Thread.currentThread().getName() + "---- actual: " + reference.get());
            //进行CAS操作
            boolean result = reference.compareAndSet("A", "X");
            //打印操作结果
            System.out.println(Thread.currentThread().getName() + "---- result: " + result + " ==》 final reference = " + reference.get());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进行ABA操作
            System.out.print(Thread.currentThread().getName() + "---- change: " + reference.get());
            reference.compareAndSet("A", "B");
            System.out.print(" -- > B");
            reference.compareAndSet("B", "A");
            System.out.println(" -- > A");
        }).start();
    }

    public static void test2(){
        //带版本戳的原子引用类型
        //为了解决上述的ABA问题，Java提供了两种带版本戳的原子引用类型：
        //AtomicStampedReference：带版本戳的原子引用类型，版本戳为int类型。
        //AtomicMarkableReference：带版本戳的原子引用类型，版本戳为boolean类型。
        //AtomicStampedReference提供的方法如下：
        //
        //1.AtomicStampedReference<>(V initialRef, int initialStamp)
        //带版本戳的原子引用类型没有无参的构造函数。
        //带版本戳的原子引用类型只有这个构造函数，要求必须设置初始的引用对象以及版本戳。
        //2.getReference()与getStamp()
        //getReference()：获取引用对象。
        //getStamp()：获取版本戳。
        //3.set(V newReference, int newStamp)
        //重新设置引用对象以及版本戳。
        //4.attemptStamp(V expectedReference, int newStamp)
        //如果引用对象为期望值，则重新设置新的版本戳。
        //5.compareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp)
        //如果引用对象为期望值，并且版本戳正确，则赋新值并修改版本戳。
        //6.get(int[] stampHolder)
        //获取引用当前值以及版本戳
        //注意参数为长度至少为1的数组类型
        //其中：引用值为通过return返回，版本戳存放在stampHolder[0]中
        //参数使用数组类型的原因：需要将版本戳存放在参数中，而基本数据类型无法进行引用传递，但是数组可以。

        //AtomicStampedReference的方法汇总：
        System.out.println("\n=========AtomicStampedReference的方法汇总：");
//构造方法：AtomicStampedReference<>(V initialRef, int initialStamp)
        System.out.println("构造方法：AtomicStampedReference<>(V initialRef, int initialStamp)");
        AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("David", 1);

//getStamp和getReference：获取版本戳和引用对象
        System.out.println("\ngetReference():获取引用对象的值----" + stampedReference.getReference());
        System.out.println("getStamp():获取引用对象的值的版本戳----" + stampedReference.getStamp());

//set(V newReference, int newStamp):无条件的重设引用和版本戳的值
        stampedReference.set("Joke", 0);
        System.out.println("\nset(V newReference, int newStamp):无条件的重设引用和版本戳的值---[reference:"
                + stampedReference.getReference() + ",stamp:" + stampedReference.getStamp() + "]");

//attemptStamp(V expectedReference, int newStamp)
        stampedReference.attemptStamp("Joke", 11);
        System.out.println("\nattemptStamp(V expectedReference, int newStamp):如果引用为期望值，则重设版本戳---[reference:"
                + stampedReference.getReference() + ",stamp:" + stampedReference.getStamp() + "]");

//compareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp)
        System.out.println("\ncompareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp):" +
                "\n如果引用为期望值且版本戳正确，则赋新值并修改版本戳:");
        System.out.println("第一次：" + stampedReference.compareAndSet("Joke", "Tom", 11, 12));
        System.out.println("第二次：" + stampedReference.compareAndSet("Tom", "Grey", 11, 12));
        System.out.println("weakCompareAndSet不再赘述");

//get(int[] stampHolder):通过版本戳获取引用当前值
//参数为数组类型是因为基本类型无法传递引用，需要使用数组类型
        int[] stampHolder = new int[10];
        String aRef = stampedReference.get(stampHolder);
        System.out.println("\nget(int[] stampHolder):获取引用和版本戳,stampHolder[0]持有版本戳---[reference=" + aRef + ",stamp=" + stampHolder[0] + "].");
    }


    public static void test3(){
        //通过版本戳解决ABA问题
        System.out.println("\n==========通过版本戳解决ABA问题：");
        AtomicStampedReference<String> stampedRef = new AtomicStampedReference<>("A", 1);
        new Thread(() -> {
            //获取期望值
            String expect = stampedRef.getReference();
            //获取期望版本戳
            Integer stamp = stampedRef.getStamp();
            //打印期望值和期望版本戳
            System.out.println(Thread.currentThread().getName() + "---- expect: " + expect + "-" + stamp);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //打印实际值和实际版本戳
            System.out.println(Thread.currentThread().getName() + "---- actual: " + stampedRef.getReference() + "-" + stampedRef.getStamp());
            //进行CAS操作（带版本戳）
            boolean result = stampedRef.compareAndSet("A", "X", stamp, stamp + 1);
            //打印操作结果
            System.out.println(Thread.currentThread().getName() + "---- result: " + result + " ==》 final reference = " + stampedRef.getReference() + "-" + stampedRef.getStamp());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进行ABA操作（带版本戳）
            System.out.print(Thread.currentThread().getName() + "---- change: " + stampedRef.getReference() + "-" + stampedRef.getStamp());
            stampedRef.compareAndSet("A", "B", stampedRef.getStamp(), stampedRef.getStamp() + 1);
            System.out.print(" -- > B" + "-" + stampedRef.getStamp());
            stampedRef.compareAndSet("B", "A", stampedRef.getStamp(), stampedRef.getStamp() + 1);
            System.out.println(" -- > A" + "-" + stampedRef.getStamp());
        }).start();
    }
    public static void main(String[] args) {
        //test1();
        //AtomicStampedReference
        test2();
        //AtomicStampedReference 模拟解决ABA问题：
        test3();
        //AtomicMarkableReference
        //关于AtomicMarkableReference的原理其实是与AtomicStampedReference类似的。
        //因为其版本戳只是boolean类型，所以导致版本状态只有两个：true或者false。
        //所以，我更倾向于称呼AtomicMarkableReference为带标记的原子引用类型。
        //版本戳 = true，表示此引用被标记。
        //版本戳 = false，表示此引用未被标记。
    }
}
