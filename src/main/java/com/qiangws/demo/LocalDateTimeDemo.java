package com.qiangws.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : LocalDateTimeDemo
 * @packageName : com.qiangws.demo
 * @createTime : 2025/4/9
 * @description :  TODO
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        LocalDateTime currentDate = LocalDateTime.now();

        LocalDateTime sixMonthsAgo = LocalDateTime.now().plusMonths(-6);

        System.out.println(currentDate);                                // 2025-04-07
        System.out.println(sixMonthsAgo);                               // 2024-10-07

        LocalDateTime newDate = LocalDateTime.of(2019, 8, 23, 0, 0, 0); //2019-08-23

        // is this date older than 6 months?
        if (newDate.isBefore(sixMonthsAgo)) {           //    <
            System.out.println("[isBefore] date: " + newDate + " is older than 6 months!");
        }

        long between = ChronoUnit.MONTHS.between(currentDate, newDate);
        System.out.println(between);
        if (between <= -6) {
            System.out.println("[ChronoUnit] date: " + newDate + " is older than 6 months!");
        }

        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        System.out.println(dateTime);
    }
}
