package com.example.springbootthreaddemo;

import lombok.Data;

/**
 * @Description TODO
 * @Author xulingli
 * @Date 2022/11/8 9:31
 * @Version 1.0
 */

@Data
public class LsMerchantAudit {
    /**
     * 商户编号
     */
    private String merchantNo;
    /**
     * 审核状态
     */
    private Integer state;
    /**
     * 审核描述
     */
    private String checkDesc;

    /**
     * 易伙伴商户号
     */
    private String downstreamMerchantNo;
}
