package com.qiangws.demo.tuple;

import com.alibaba.fastjson.JSONObject;
import com.othelle.jtuples.Tuple;
import com.othelle.jtuples.Tuple2;
import com.othelle.jtuples.Tuples;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Tuple
 * @packageName : com.qiangws.demo.tuple
 * @createTime : 2025/3/8
 * @description :  TODO
 */
public class TupleTest {

    @Data
    static class User{
        private String month;
        private Integer num;

        private Integer year;

        private String yearMonth;
    }
    /**
     * Unit<A> (1 element)
     * Pair<A,B> (2 elements)
     * Triplet<A,B,C> (3 elements)
     * Quartet<A,B,C,D> (4 elements)
     * Quintet<A,B,C,D,E> (5 elements)
     * Sextet<A,B,C,D,E,F> (6 elements)
     * Septet<A,B,C,D,E,F,G> (7 elements)
     * Octet<A,B,C,D,E,F,G,H> (8 elements)
     * Ennead<A,B,C,D,E,F,G,H,I> (9 elements)
     * Decade<A,B,C,D,E,F,G,H,I,J> (10 elements)
     * @param args
     */
    public static void main(String[] args) {
        String jsonString = "[{\"month\":\"01\",\"num\":1,\"year\":\"2025\",\"yearMonth\":\"2025-01\"},{\"month\":\"02\",\"num\":1,\"year\":\"2025\",\"yearMonth\":\"2025-02\"}]";

        List<User> orderCountDetailMap = JSONObject.parseArray(jsonString, User.class);
        System.out.println(JSONObject.toJSONString(orderCountDetailMap));

        Map<String, User> collect = orderCountDetailMap.stream().collect(Collectors.toMap(User::getMonth, Function.identity()));
        System.out.println(JSONObject.toJSONString(collect));


//        orderCountDetailMap.stream().forEach(data->{
//            data.setMonth("111222");
//
//        });
//        System.out.println(JSONObject.toJSONString(orderCountDetailMap));
//
        User remove = collect.remove("2025-01");
        System.out.println(JSONObject.toJSONString(collect.values().stream()
                .collect(Collectors.toList())));

//        BigDecimal a = new BigDecimal(1);
//
//        BigDecimal b = new BigDecimal(2);
//
//        Long c = 1L;
//
//        System.out.println(a.add(new BigDecimal(c)));
//
//        StringBuilder keyBuilder = new StringBuilder();
//        System.out.println(keyBuilder.length());
//        List<Tuple2<Long,Long>> items = new ArrayList<>();
//        System.out.println(items.toString());
//        items.add(Tuples.tuple(1L,1L));
//        items.add(Tuples.tuple(2L,2L));
//        long amount = items.stream().map(item -> {
//            System.out.println(item._1()+"  "+item._2());
//            if (2 == item._1()) {
//                return -item._2();
//            }
//            return item._2();
//        }).reduce(0L, Long::sum);
//
//        System.out.println(amount);




//还有下面这段
//        var gradeGroupUsers = dutyPlanGradesService.list(new LambdaQueryWrapper<DutyPlanGrades>()
//                        .eq(DutyPlanGrades::getOrgUuid, orgUuid)
//                        .eq(DutyPlanGrades::getTermUuid, termUuid))
//                .stream().map(x -> Tuples.tuple(x.getDutyGroupId(), x.getUserUuid()))
//                .collect(Collectors.toList());

    }
}
