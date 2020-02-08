package com.eshare.dto.domainmodel;

import lombok.Data;

@Data
public class CreditLimitRegisterResponse {

    /**
     *
     用户ID
     */
    private Long userId;
    /**
     *
     客户ID
     */
    private Long customerId;
    /**
     *
     卡ID
     */
    private Long cardId;
    /**
     *
     产品类型,这个值依据产品中心的productCode
     */
    private String productCode;
    /**
     *
     总额度
     */
    private Integer quotaLimit;
    /**
     *
    剩余额度
     */
    private Integer quotaBalance;
}