package com.qiangws.demo.threaddemo.model;

/*
 *@author 王强
 *@create 2021/7/27 22:00
 */
public class MyTask implements Runnable{
    @Override
    public void run() {
        System.out.println("我是一个任务");
    }
}
