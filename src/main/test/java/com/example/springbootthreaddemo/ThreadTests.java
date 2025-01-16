package com.example.springbootthreaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: code-summary
 * @ClassName ThreadTests
 * @description:
 * @author: QiangWs
 * @create: 2022-10-27 17:05
 * @Version 1.0
 **/
public class ThreadTests {
    /**
     * jps 查看进程id
     * jstack 查看线程id
     * javap -v 查看二进制字符
     * 启动:
     * start(): 是通过jvm层面先去找到(jvm跨平台也是通过这个实现的)os对应的Thread进行创建,
     * 然后通过调度算法对当前线程分配到cpu中然后再调度run进行运行调用
     * run() : 只是在代码层面,进行了普通方法的调用
     * 终止:
     * stop():强制中断,对程序不友好,如果当前有线程任务在执行,调用stop是不会等当前任务执行完后终止,而是强制中断
     * interrupt():友好中断
     *
     * -XX:PreBlockSpin   设置自旋次数
     *-Djava.compiler=NONE 禁止JIT自动优化
     *
     * monitor监视器每个对象都有,依赖于系统Mutexlock(互斥锁)实现的,当线程阻塞后进入内核态事,
     * 就会造成系统在用户态和内核态之间的切换,进而影响性能
     * synchronized
     * monitorenter:表示获取一个对象的监视器
     * monitorexit:表示释放监视器montor的所有权,允许其他线程来获取
     *
     * 乐观锁:cas比较预期数据和原始数据是否一致,如果一致则修改,否则修改失败
     *
     * 解决线程可见性问题 valatile 休眠 print打印(活性失败,做了一个深度优化) 都可以结束
     *  * volatitle 可以禁止JIT自动优化导致的问题
     *  * -Djava.compiler=NONE 禁止JIT自动优化
     *  print:
     *      1.IO操作:IO操作远远低于cpu执行效率,所以IO会使得线程进入到阻塞状态(锁,IO,等待阻塞)，
     *      这其实没有证明线程进入到阻塞状态后再次执行会同步内存到数据,但是这种情况会破坏jit到深度优化.new File("111");
     *      2.synchronized操作:锁释放之后会把缓存中的内容与内存中的内容进行同步
     *  sleep:
     *      会导致时间片主动释放,释放后线程进入到等待状态,下次获得时间片之后,缓存数据会和内存同步
     *
     * CPU>内存IO>设备IO   木桶原理
     *
     * 为了提高性能cpu提出了高速缓存(可见性问题:L1,L2缓存是线程独立的,当多个线程执行一个共享数据L3,都同时在本cpu都高速缓存中的时候,最新的数据对其他cpu是不可见的)
     * 为了解决可见性引出了->总线锁((数据总线or地址总线)将各个cpu连接到内存(重量级锁,悲观锁,当只有一个cpu处理数据时其他cpu都是阻塞的->不可取引出了缓存锁))和缓存锁(引入了缓存一致性协议来保证数据一致性,MSI,MESI,MOSI)
     *  (缓存锁的条件:CPU的架构是否支持,当前数据是否存在于缓存行,当这两个条件都满足都时候会优先触发缓存锁,如果不满足就触发总线锁(相对于总线锁,降低了锁的力度))
     *  MESI协议:
     *      M(Modifiy):被修改,表示共享数据只能缓存在当前CPU缓存中,并且是被修改状态,也就是缓存的数据与主内存的数据不一致
     *      E(Exclusive):独享的,表示缓存的独占状态,数据只缓存在当前CPU缓存中,并且没有被修改
     *      S(Shared):共享的,表示数据可以被多个CPU缓存,并且各个缓存中的数据和主内存数据一致
     *      I(Invalid):无效的,表示缓存已经失效,不管读或者写都要从主内存和别的缓存中读取数据
     *  带来的问题:各CPU是相互通知工作的,比如现在核1想要修改这个变量,首先核1会向所有拥有相同缓存的其他和发送一个请求,告诉其他核中的缓存是I(Invalid)无效的,
     *  其他核收到这个信息将自己核中的缓存状态设置为无效之后,返回一个设置完成的消息,核1才内改状态为M进行修改,这样cpu1是不是就一直阻塞没事干
     *  ->所以设计者又引入来写缓存(Store Buffer)跟无效化队列(Invalidate Queue)(带来了指令重排问题)
     *  ->为了解决指令重排问题,又引进了内存屏障来解决针对StoreBuffer的写屏障,InvalidateQueue的读屏障
     *  内存屏障:
     *      写屏障(Store Memory Barrier):强制将Store Buffer中的内容写入到缓存中或者将改指令之后到写操作写入到store buffer直到之前到内容被刷入到缓存中,也被称为smp_wmb
     *      读屏障(Load Memory Barrier):强制将Invalidate Queue中到内容处理完毕
     *      全屏障(Fence Memory Barrier):、
     *
     *  JMM层面可见性:
     *  
     *      jmm(Java Memory Model):内存模型与线程规范,java内存模型围绕着并发过程中如何处理可见性,原子性,有序性这三个特性而建立的模型
     *      内存模型主要是描述程序中各个变量之间的关系,以及在实际计算机系统中将变量存储到内存和从内存中取出变量到底层细节
     *      主内存(Main Memory)：JMM规定所有的变量存储在主内存中,每个线程还有自己的工作内存(Working Memory)
     *
     *
     *  Happerns-Before 可见性模型
     *      程序顺序模型：
     *          (as-if-serial:表示不管怎么重排序,单线程的程序结果不能改变)
     *          处理器不能对存在依赖关系的操作进行重排序,因为重排序会改变程序对执行结果
     *          对于没有依赖关系的指令,即便是重排序,也不会改变单线程下的执行结果
     *      传递性规则
     *      volatitle变量规则
     *      监视器锁规则
     *      start规则
     *      join模型
     *
     * @param args
     */
    public static void main(String[] args) {
        //Thread
        new Thread(()->{
            System.out.println(123);
        }).start();

        //Runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(234);
            }
        }).start();

        //Callable/Future
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(345);
                return 345;
            }
        });
        executorService.shutdown();
    }
}
