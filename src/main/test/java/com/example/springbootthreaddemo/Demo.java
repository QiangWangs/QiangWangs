package com.example.springbootthreaddemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.util.Objects;

/**
 * @program: code-summary
 * @ClassName Demo
 * @description:
 * @author: QiangWs
 * @create: 2022-10-28 15:06
 * @Version 1.0
 **/
public class Demo {

    /*public static void main(String[] args) {
        Integer a = 0;
        System.out.println(a instanceof Integer);//编译不通过 a必须是引用类型，不能是基本类型
        System.out.println(a instanceof Object);//编译不通过
        int i=1;
        synchronized (Demo.class){
            i=2;
        }
    }*/


    public static void main(String[] args) throws Exception {

        if(!Objects.equals("1","1")) {
            System.out.println(123);
        }
        /*Integer type = 4;
        String mercUnionpayOutcome= "营业证明文件号码:同一身份证注册小微商户的收单机构不得超过5个";
        //商户报备银联状态(0-审核通过，1-审核驳回)
        Integer mercUnionpayState = 1;
        //修改-特殊补录功能           修改类型为4
        //'营业证明文件号码:同一身份证注册小微商户的收单机构不得超过5个','营业证明文件号码:同一收单机构同一身份证注册小微商户不能超过2个'
        if(null != mercUnionpayState && mercUnionpayState == 1 && (mercUnionpayOutcome.indexOf("同一身份证注册小微商户的收单机构不得超过5个") != -1 ||
                mercUnionpayOutcome.indexOf("同一收单机构同一身份证注册小微商户不能超过2个") != -1) && type == 4){
            System.out.println(1);
        }else{
            System.out.println(0);
        }*/
        /*LsMerchantAudits a = new LsMerchantAudits();
        a.setState(3);
        a.setMerchantNo("1");
        LsMerchantAudit lsMerchantAudit =  JSONObject.parseObject(String.valueOf(a), LsMerchantAudit.class);
        System.out.println(lsMerchantAudit.getState());*/
      /*  String addressDetails = "香洲区前山街道珠海嘉瑞时尚酒店";
        if(addressDetails != null && addressDetails.length() > 0) {
            addressDetails = addressDetails.replace("广东省", "").replaceAll("珠海市", "").replaceAll("香洲区（横琴新区）", "");
            ;
            System.out.println(addressDetails);
        }
        System.out.println(addressDetails);*/
    }
}
