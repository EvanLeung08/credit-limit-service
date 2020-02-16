package com.eshare;

/**
 * 额度更新命令类型
 * @Author Evan Leung
 **/
public enum QuotaUpdateCmdTypeEnum {
    /**
     * 变更额度
     */
    CHANGE_QUOTA(1),

    /**
     * 冻结额度
     */
    FREEZE_QUOTA(2),

    UNFREEZE_QUOTA(3),

    SUBTRACT_QUOTA(4),

    RECOVER_QUOTA(5);

    private Integer value;

    QuotaUpdateCmdTypeEnum(Integer value) {
        this.value = value;
    }

    public static QuotaUpdateCmdTypeEnum fromValue(Integer value) {
        for (QuotaUpdateCmdTypeEnum quotaUpdateCmdTypeEnum : QuotaUpdateCmdTypeEnum.values()) {
            if (quotaUpdateCmdTypeEnum.getValue().equals(value)) {
                return quotaUpdateCmdTypeEnum;
            }
        }
        throw new IllegalArgumentException(String.format("QuotaUpdateCmdTypeEnum not found for value %s", value));
    }

    public Integer getValue() {
        return value;
    }
}
