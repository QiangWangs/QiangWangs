package com.qiangws.demo.stream;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Lambda
 * @packageName : com.qiangws.demo.stream
 * @createTime : 2024/6/17
 * @description :  TODO
 */
public class Lambda {

    @FunctionalInterface
    interface LambdaInterface{
        int have(int a, int b);
    }

    @FunctionalInterface
    interface LambdaInterfaceMethod{
        String method(String str);
    }
 
    private String value = "Outer scope value";

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Runnable runnable=()->{
           atomicInteger.set(5);
           System.out.println(atomicInteger.get()+"=");
        };
        //runnable.run();
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        atomicInteger.set(10);
        System.out.println(atomicInteger.get());
    }

    /**
     *   使用匿名内部类   语法糖。
     */
    private void have(){

        LambdaInterface lambdaInterface = new LambdaInterface(){
            @Override
            public int have(int a, int b) {
                return a + b;
            }
        };
        System.out.println(lambdaInterface.have(1, 21));
        LambdaInterface lambdaInterface1 = (a, b) -> a + b;
        System.out.println(lambdaInterface1.have(1, 21));
    }

    /**
     * lambda表达式只能实现函数式接口。即一个接口中，要求实现类必须实现的抽象方法，有且只有一个，这样的接口就是函数式接口。JDK的函数式接口都加上了@FunctionalInterface 注解进行标识。但是无论是否加上该注解，只要接口中只有一个抽象方法，都是函数式接口。
     * 可以通过父类进行继承，有且只有一个抽象方法需要实现，default 这种不是必须实现的，Object类中的方法也可以不进行重写，这种才属于函数式接口。
     * Lambda表达式的语法
     * 参数部分︰方法的参数列表，要求和实现的接口中的方法参数部分一致，包括参数的数量和类型。
     * 方法体部分∶方法的实现部分，如果接口中定义的方法有返回值，则在实现的时候，注意返回值的返回。
     * ->:分隔参数部分和方法体部分。
     * @return
     */
    public String scopeExperiment() {
        LambdaInterfaceMethod usage = new LambdaInterfaceMethod() {
            String value = "Inner class value";
            @Override
            public String method(String str) {
                return this.value;
            }
        };
        String result = usage.method("");

        LambdaInterfaceMethod usageLambda = (parameter) -> {
            String value = parameter;
            return this.value;
        };
        String resultLambda = usageLambda.method("Lambda value");
        return "Results: result = " + result +
                ", resultLambda = " + resultLambda;

    }

    /**
     * 函数引用:引用一个已经存在的方法，使其替代lambda表达式完成接口的实现
     * 静态函数引用
     */
    public void staticFunctionReference(){
        LambdaInterface lambdaInterface = Lambda::statusHave;
        System.out.println(lambdaInterface.have(1, 2));
    }

    /**
     * 函数引用:引用一个已经存在的方法，使其替代lambda表达式完成接口的实现
     * 非静态函数引用
     */
    public void nonStaticFunctionReference(){
        LambdaInterface lambdaInterface = new Lambda()::nonStaticHave;
        System.out.println(lambdaInterface.have(1, 2));
    }

    public static int statusHave(Integer a, Integer b){
        return a + b;
    }

    public int nonStaticHave(Integer a, Integer b){
        return a + b;
    }

    /**
     * 引用构造方法
     * lambda表达式会自动根据 方法的参数类型 匹配Person类中对应的构造方法
     * New Student() New Student(name) New Student(name,age)
     * Student::new()    Student::new()     Student::new()
     */
}
