package com.eshare.domain.constant;

/**
 * @Author Evan Leung
 *
 * 卡类型
 */
public enum CardTypeEnum {

    /**
     * 一次性授信
     */
    DISPOSABLE_CREDIT(1),

    /**
     * 循环授信
     */
    CYCLIC_CREDIT(2);

    private Integer value;

    CardTypeEnum(Integer value) {
        this.value = value;
    }

    public static CardTypeEnum fromValue(Integer value) {
        for (CardTypeEnum cardTypeEnum : CardTypeEnum.values()) {
            if (cardTypeEnum.getValue().equals(value)) {
                return cardTypeEnum;
            }
        }
        throw new IllegalArgumentException(String.format("CardTypeEnum not found for value %s", value));
    }

    public Integer getValue() {
        return value;
    }
}
