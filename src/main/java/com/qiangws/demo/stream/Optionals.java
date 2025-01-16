package com.qiangws.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : QiangWs
 * @version : V1.0.0
 * @className : Optional
 * @packageName : com.qiangws.demo.stream
 * @createTime : 2024/6/17
 * @description :  TODO
 */
public class Optionals {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.forEach(data->{
            System.out.println(data);
        });
//        Streams.Author author = null;
//        for(int i=1;i<10;i++){
//        Optional.ofNullable(author);
//          try{
//              optional();
//          }catch (Exception e){
//              System.out.println(e.getMessage());
//              //throw e;
//          }
//        //optional();
//
//        System.out.println(123123);     }
    }

    /**
     * Optional就好像是包装类，可以把我们的具体数据封装Optional对象内部。然后我们去使用Optional中封装好的方法操作封装进去的数据就可以非常优雅的避免空指针异常
     */
    public static  <T> T optional() throws NullPointerException {
//        List<Streams.Author> author = Streams.getAuthors();
//        Optional< List<Streams.Author>> authorOptional = Optional.ofNullable(author);
        throw new NullPointerException("支付宝补充资料失败");
    }



}
