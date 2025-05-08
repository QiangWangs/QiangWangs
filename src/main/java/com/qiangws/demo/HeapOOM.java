package com.qiangws.demo;

import java.util.ArrayList;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : HeapOOM
 * @packageName : com.qiangws.demo
 * @createTime : 2025/4/2
 * @description :  TODO 内存溢出排查当OutOfMemoryError发生时生成dump文件,-XX:HeapDumpPath指定生成后的文件存储路径  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/test/dump/ -Xms8m -Xmx8m
 */
public class HeapOOM {

    // 创建1M的文件
    byte [] buffer = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {
        ArrayList<HeapOOM> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new HeapOOM());
                count++;
            }
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("count:" + count);
        }
    } 
}
