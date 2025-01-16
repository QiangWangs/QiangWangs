package com.qiangws.demo.threaddemo.base.deadlock.demo01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王强
 * @create 2021/7/9 20:23
 */
public class Allocator {

    private List<Object> list = new ArrayList<Object>();

    synchronized boolean apply(Object from, Object to) {
        if (list.contains(from) || list.contains(to)) {
            return false;
        }
        list.add(from);
        list.add(to);
        return true;

    }

    synchronized void free(Object from, Object to) {
        list.remove(from);
        list.remove(to);
    }
}
