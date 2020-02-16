package com.eshare.domain.constant;

/**
 * @Author Evan Leung
 *
 * 账户类型
 */
public enum AccountTypeEnum {

    /**
     * 一次性授信
     */
    DISPOSABLE_CREDIT(1),

    /**
     * 循环授信
     */
    CYCLIC_CREDIT(2);

    private Integer value;

    AccountTypeEnum(Integer value) {
        this.value = value;
    }

    public static AccountTypeEnum fromValue(Integer value) {
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            if (accountTypeEnum.getValue().equals(value)) {
                return accountTypeEnum;
            }
        }
        throw new IllegalArgumentException(String.format("AccountTypeEnum not found for value %s", value));
    }

    public Integer getValue() {
        return value;
    }
}
