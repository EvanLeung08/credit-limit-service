package com.eshare.domain.constant;

/**
 * @Author Evan Leung
 * 冻结状态
 */
public enum FrozenStatusEnum {

    /**
     * 正常
     */
    NORMAL(1),

    /**
     * 系统冻结
     */
    SYSTEM_FROZEN(2),

    /**
     * 人工冻结
     */
    MANUAL_FROZEN(3),

    /**
     * 完全冻结，由系统和人工叠加，如果解冻结一次，会优先解冻人工，进入系统冻结
     */
    ALL_FROZEN(4);

    private final Integer value;

    FrozenStatusEnum(Integer value) {
        this.value = value;
    }

    public static FrozenStatusEnum fromValue(Integer value) {
        for (FrozenStatusEnum frozenStatus : FrozenStatusEnum.values()) {
            if (value.equals(frozenStatus.getValue())) {
                return frozenStatus;
            }
        }
        throw new IllegalArgumentException(String.format("FrozenStatusEnum not found for value %s", value));
    }

    public Integer getValue() {
        return value;
    }
}