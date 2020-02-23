package com.eshare.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Author Evan Leung
 **/
@AllArgsConstructor
@Getter
public enum SubCreditLimitType {


    EASY_INSTALLMENT(MainCreditLimitType.OWN_PRODUCT, "EASY-INSTALLMENT","自营轻松分期产品");


    //额度归属业务类型
    private MainCreditLimitType mainCreditLimitType;

    //产品额度编码
    private String creditLimitSubTypeCode;

    //产品类型名称
    private String creditLimitSubTypeName;


}
