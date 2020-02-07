package com.eshare.domain.constant;

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