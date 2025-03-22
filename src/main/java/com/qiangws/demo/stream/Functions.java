package com.qiangws.demo.stream;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Function
 * @packageName : com.qiangws.demo.stream
 * @createTime : 2025/3/14
 * @description :  TODO
 */
public class Functions {


    public static void main(String[] args) {
        Functions ft = new Functions();
        //使用lambda表达式实现apply方法，返回入参+10。形式上如同传递了一个方法作为参数
        int res = ft.compute(1, v -> v + 10);
        System.out.println(res);//11

        //调用compose
        //先+8，然后将得到的值*3
        System.out.println(ft.compute(2, v -> v * 3, v -> v + 8));//30   2*3 =6  2+8=10

        //调用andThen
        //先*3，然后将得到的值+8
        System.out.println(ft.andThen(2, v -> v * 3, v -> v + 8));//14


        BigDecimal groupProfitAmount = coldDataCalculationStrategy(new BigDecimal(0), i -> {
            return  (BigDecimal)i;
        });
    }

    public int compute(int a, Function<Integer, Integer> function) {
        //使用者在使用本方法时，需要去编写自己的apply，
        //传递的funtion是一个行为方法，而不是一个值
        return function.apply(a);     //相加
    }

    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //将function2先接收入参a，调用apply后，将返回值作为新的入参，传入function1，调用apply返回最后结果
        System.out.println("compute-------------------------------------------------------");
        System.out.println(function1.compose(function2).apply(0));        //3 * (0+8) = 24
        System.out.println(function1.compose(function2).apply(1));        //3 * (1+8) = 27
        System.out.println(function1.compose(function2).apply(2));        //3 * (2+8) = 30
        System.out.println(a);                                               //2
        System.out.println(function1.apply(a));                              //3 * 2  = 6
        System.out.println(function2.apply(a));                              //8 +  2 = 10
        return function1.compose(function2).apply(a);                        //3 * (2+8) = 30
    }

    public int andThen(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        //将function2先接收入参a，调用apply后，将返回值作为新的入参，传入function1，调用apply返回最后结果       
        System.out.println("andThen-------------------------------------------------------");
        System.out.println(function1.andThen(function2).apply(0));        //(0*3) + 8 = 8
        System.out.println(function1.andThen(function2).apply(1));        //(1*3) + 8 = 11
        System.out.println(function1.andThen(function2).apply(2));        //(2*3) + 8 = 14    
        return function1.andThen(function2).apply(a);
    }

    public static <T, R> R coldDataCalculationStrategy(T obj, Function<T, R> function) {
        return function.apply(obj);
    }
}
