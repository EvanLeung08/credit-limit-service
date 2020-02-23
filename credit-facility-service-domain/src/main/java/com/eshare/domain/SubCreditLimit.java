package com.eshare.domain;

import lombok.Data;

/**
 * 子信用额度 - 产品级别信用额度
 *
 * @Author Evan Leung
 **/
@Data
public class SubCreditLimit extends CreditLimit {

    protected SubCreditLimitType subCreditLimitType;

    protected MainCreditLimit parent;
}
