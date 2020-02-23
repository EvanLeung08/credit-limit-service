package com.eshare.domain;

import lombok.Data;

import java.util.List;

/**
 * 主额度，有多个子额度构成
 *
 * @Author Evan Leung
 **/
@Data
public class MainCreditLimit extends CreditLimit {

    protected MainCreditLimitType mainCreditLimitType;

    protected List<SubCreditLimit> subCreditLimitList;

}
