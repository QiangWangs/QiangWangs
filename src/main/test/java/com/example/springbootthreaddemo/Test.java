package com.example.springbootthreaddemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: code-summary
 * @ClassName Test
 * @description:
 * @author: QiangWs
 * @create: 2022-11-14 11:15
 * @Version 1.0
 **/
public class Test {
    //0x61c88647 斐波那契数列
    private static final int HASH_INCREMENT = 0x61c88647;
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(1);
        list.add(0,2);
        System.out.println(list);

        System.out.println(HASH_INCREMENT);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println((int)(-20<<3));
        System.out.println((int)(20<<3));
        int i = 4;
        i |= i>>>1;
        i |= i>>>4;
        i |= i>>>8;
        i |= i>>>16;
        System.out.println(i);

        magicHash(16);
        magicHash(32);
    }
    private static void magicHash(int size){
        int hashCode=0;
        for(int i=0;i<size;i++){
            hashCode=i*HASH_INCREMENT+HASH_INCREMENT;
            System.out.print((hashCode&(size-1))+" ");
        }
        System.out.println(""); }
}
