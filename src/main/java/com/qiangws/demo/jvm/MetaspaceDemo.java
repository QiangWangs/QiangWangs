package com.qiangws.demo.jvm;

import lombok.extern.log4j.Log4j2;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : MetaspaceDemo
 * @packageName : com.qiangws.demo.jvm
 * @createTime : 2025/3/10
 * @description :  TODO
 */
@Log4j2
public class MetaspaceDemo {

    static class OOM{}

    /**
     * 元空间会产生内存溢出么？在什么情况下会产生内存溢出？
     * 错误的主要原因, 是加载到内存中的 class 数量太多或者体积太大, 解决方案：增加 Metaspace 的大小
     * 元空间与永久代最大区别在于，Metaspace并不在虚拟机内存中而是使用本地内存也就是在JDK8中
     * Matespace 默认配置: -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     * 增加 Metaspace 的大小:   -XX:MaxMetaspaceSize=512m
     * 查看元空间大小: java -XX:+PrintFlagsInitial
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;//模拟计数多少次以后发生异常
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            log.error("==================================多少次后发生异常：{}",i);
            e.printStackTrace();
        }
    }
}
