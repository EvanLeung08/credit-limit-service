package com.eshare.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 主额度类型
 *
 * @Author Evan Leung
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum MainCreditLimitType {

    OWN_PRODUCT("OWN-PRODUCT", "自营产品"),
    THIRD_PARTY_PRODUCT("3RD-PRODUCT", "第三方产品");


    private String creditLimitCode;
    private String creditLimitName;


    public static MainCreditLimitType of(String metricCode) {
        if (StringUtils.isBlank(metricCode)) {
            return null;
        }
        for (MainCreditLimitType mainCreditLimitType : MainCreditLimitType.values()) {
            if (metricCode.equals(mainCreditLimitType.creditLimitCode)) {
                return mainCreditLimitType;
            }
        }
        return null;
    }
}
