package com.qiangws.demo.threaddemo.hashmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 王强
 * @create 2021/7/11 21:38
 */
public class HashDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap(0);
        map.put("girl", 18);
        map.put("woman", 48);
        map.get("girl");
        map.size();

        Hashtable a = new Hashtable();
        a.put("mic",68);

        ConcurrentHashMap chm = new ConcurrentHashMap(5);
        chm.put("name", "huihui");
        chm.put("name1", "huihui1");
        chm.put("name2", "huihui2");
        chm.put("name3", "huihui3");
        chm.put("name4", "huihui4");
        chm.put("name5", "huihui5");
        chm.get("name");
        chm.size();


    }
}
