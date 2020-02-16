package com.eshare.domain.constant;

/**
 * @Author Evan Leung
 * @description 业务来源模块-目前只有额度金额变动的模块需要进行流水记录，
 * 这些模块有CREDIT(注册-新建)、WITHDRAW(提现-扣减)、REPAYMENT(还款-恢复)、QUOTA_CHANGE(提额-提升)、
 * WITHDRAW_QUOTA_FROZEN(提现额度金额冻结-冻结金额)、WITHDRAW_QUOTA_AMOUNT_UNFREEZE(提现额度金额解冻-解冻金额)
 */
public enum BusinessSourceEnum {

    /**
     * 无
     */
    NONE,

    /**
     * 开卡模块 - 创建产品额度
     */
    OPEN_CARD,

    /**
     * 提现模块 - 扣减额度
     */
    WITHDRAW,

    /**
     * 提额模块 - 提升额度
     */
    QUOTA_CHANGE,

    /**
     * 还款模块 - 恢复额度
     */
    REPAYMENT,

    /**
     * 提现额度金额冻结 - 用于提现申请时候但是尚未到达下一个状态
     */
    WITHDRAW_QUOTA_AMOUNT_FREEZE,

    /**
     * 提现额度金额解冻 - 用于提现申请时候的取消等场景
     */
    WITHDRAW_QUOTA_AMOUNT_UNFREEZE,

    /**
     * 产品额度系统冻结
     */
    PRODUCT_QUOTA_STATUS_SYSTEM_FREEZE,

    /**
     * 产品额度状态人工冻结
     */
    PRODUCT_QUOTA_STATUS_MANUAL_FREEZE,

    /**
     * 产品额度状态解冻
     */
    PRODUCT_QUOTA_STATUS_UNFREEZE,
    /**
     * 产品额度状态强制解冻
     */
    PRODUCT_QUOTA_STATUS_FORCE_UNFREEZE,

    /**
     * 禁用产品额度
     */
    PRODUCT_QUOTA_STATUS_INACTIVATE,

    /**
     * 启用产品额度
     */
    PRODUCT_QUOTA_STATUS_ACTIVATE,

    /**
     * 废弃产品额度
     */
    ABANDON_PRODUCT_QUOTA;


}