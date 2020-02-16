package com.eshare.domain.constant;

/**
 * @Author Evan Leung
 *
 * 废弃状态
 */
public enum AbandonStatusEnum {

    ABANDONED(1),

    NORMAL(0);

    private final Integer value;

    AbandonStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}