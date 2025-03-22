package com.qiangws.demo.stream;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Demo
 * @packageName : com.qiangws.demo.stream
 * @createTime : 2025/3/14
 * @description :  TODO
 * 函数式接口
 * 只有函数式接口，才可以转换为lambda表达式
 * 有且只有一个抽象方法的接口被成为函数式接口！
 * 函数式接口可以显式的被@FunctionalInterface所表示，当被标识的接口不满足规定时，编译器会提示报错
 */
public class Demo {

    public static void main(String[] args) {

        // 1.传统方式 需要new接口的实现类来完成对接口的调用
        ICar car1 = new IcarImpl();
        car1.drive();
        // 2.匿名内部类使用
        ICar car2 = new ICar() {
            @Override
            public void drive() {
                System.out.println("Drive BMW");
            }
        };
        car2.drive();
        // 3.无参无返回Lambda表达式
        ICar car3 = () -> {System.out.println("Drive Audi");};
        car3.drive();
        // 4.无参无返回且只有一行实现时可以去掉{}让Lambda更简洁
        ICar car4 = () -> System.out.println("Drive Ferrari");
        car4.drive();

        // 去查看编译后的class文件 大家可以发现 使用传统方式或匿名内部类都会生成额外的class文件，而Lambda不会

        // 1.有参无返回
        IEat eat1 = (String thing) -> System.out.println("eat " + thing);
        eat1.eat("apple");
        // 参数数据类型可以省略
        IEat eat2 = (thing) -> System.out.println("eat " + thing);
        eat2.eat("banana");
        // 2.多个参数
        ISpeak speak1 = (who, content) -> System.out.println(who + " talk " + content);
        speak1.talk("John", "hello word");
        // 3.返回值
        IRun run1 = () -> {
            return 10;
        };
        run1.run();
        // 4.返回值简写
        IRun run2 = () -> 10;
        run2.run();


        // 全写
        IAddition addition1 = (final int a, final int b) -> a + b;
        System.out.println(addition1.add(1, 2));
        // 简写
        IAddition addition2 = (a, b) -> a+b;
        System.out.println(addition2.add(2, 3));
    }
}
interface ICar {

    void drive();
}

class IcarImpl implements ICar {

    @Override
    public void drive() {
        System.out.println("Drive Benz");
    }
}
 
interface IEat {

    void eat(String thing);
}

interface ISpeak {

    void talk(String who, String content);
}

interface IRun {

    int run();
}

interface IAddition {

    int add(final int a, final int b);
}