package com.eshare;

/**
 * 额度状态更新命令类型
 *
 * @Author Evan Leung
 **/
public enum QuotaStatusUpdateCmdTypeEnum {
    /**
     * 系统冻结
     */
    SYS_FREEZE(1),

    /**
     * 人工冻结
     */
    MANUAL_FREEZE(2),
    /**
     * 解冻
     */
    UNFREEZE(3),
    /**
     * 强制解冻
     */
    FORCED_UNFREEZE(4),
    /**
     * 启动
     */
    ACTIVE(5),

    /**
     * 失效
     */
    INACTIVE(6),
    /**
     * 废弃
     */
    ABANDON(7);

    private Integer value;

    QuotaStatusUpdateCmdTypeEnum(Integer value) {
        this.value = value;
    }

    public static QuotaStatusUpdateCmdTypeEnum fromValue(Integer value) {
        for (QuotaStatusUpdateCmdTypeEnum quatoUpdateCommandTypeEnum : QuotaStatusUpdateCmdTypeEnum.values()) {
            if (quatoUpdateCommandTypeEnum.getValue().equals(value)) {
                return quatoUpdateCommandTypeEnum;
            }
        }
        throw new IllegalArgumentException(String.format("QuotaUpdateCmdTypeEnum not found for value %s", value));
    }

    public Integer getValue() {
        return value;
    }
}
