package com.eshare.dto.clientobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Author Evan Leung
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductLimitCO {

    /**
     * 用户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)//解决前端精度丢失问题
    private Long userId;
    /**
     * 客户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)//解决前端精度丢失问题
    private Long customerId;
    /**
     * 额度账户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)//解决前端精度丢失问题
    private Long accountId;
    /**
     * 是否删除,1:删除,0:正常
     */
    private Integer deleted;
    /**
     * 产品类型,这个值依据产品中心的productCode
     */
    private String productCode;
    /**
     * 额度模式,1:一次性使用额度,2:循环授信额度。对应卡的cardType
     */
    private Integer quotaMode;
    /**
     * 总额度
     */
    private Integer quotaLimit;
    /**
     * 剩余额度
     */
    private Integer quotaBalance;
    /**
     * 占用额度
     */
    private Integer quotaOccupancy;
    /**
     * 冻结额度
     */
    private Integer quotaFrozen;
    /**
     * 基础额度
     */
    private Integer quotaBase;
    /**
     * 更变额度
     */
    private Integer quotaChange;
    /**
     * 冻结状态,1:正常,2:系统冻结,3:人工冻结
     */
    private Integer frozenStatus;
    /**
     * 过期状态,1:正常,0:过期
     */
    private Integer expireStatus;
    /**
     * 可用状态,1:正常,0:禁用
     */
    private Integer activeStatus;
    /**
     * 废弃状态,1:废弃,0:正常
     */
    private Integer abandoned;
}
