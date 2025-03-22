package com.qiangws.demo.jvm;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : StaticObjTest
 * @packageName : com.qiangws.demo
 * @createTime : 2025/3/21
 * @description :  TODO
 */
public class StaticObjTest {

    static class Test{
        // 三个变量引用对应的对象实体都是在堆空间。
        // 静态变量
        // 一个java.lang.Class类型的对象实例引用了此变量         方法区
        static ObjectHolder staticObj = new ObjectHolder();
        // 实例变量                                          堆区
        ObjectHolder instanceObj = new ObjectHolder();
        void foo() {
            // 局部变量                                      方法栈帧的局部变量表中
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }
    private static class ObjectHolder{
    }
    public static void main(String[] args) {
        Test test = new StaticObjTest.Test();
        test.foo();
    }
}
