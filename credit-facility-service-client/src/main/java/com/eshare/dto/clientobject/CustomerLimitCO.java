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
public class CustomerLimitCO {

    /**
     * 客户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)//解决前端精度丢失问题
    private Long customerId;
    /**
     * 是否删除,1:删除,0:正常
     */
    private Integer deleted;
    /**
     * 额度类型
     */
    private String quotaType;
    /**
     * 总额度
     */
    private Integer quotaLimit;
    /**
     * 剩余额度
     */
    private Integer quotaBalance;
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

}
