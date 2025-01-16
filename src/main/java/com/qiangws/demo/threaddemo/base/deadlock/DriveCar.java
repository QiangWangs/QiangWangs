package com.qiangws.demo.threaddemo.base.deadlock;

/**
 * @author 王强
 * @create 2021/7/16 14:55
 */
public class DriveCar {
    public static void main(String[] args) {
        String key = "key";
        String car = "car";
        Thread wife = new Thread(new Wife(key, car), "wife");
        Thread husband = new Thread(new Husband(key, car), "husband");
        wife.start();
        husband.start();
    }
}
