package com.eshare.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author Evan Leung
 * 客户额度查询指令
 **/
@Data
public class CustomerLimitQryCmd extends Query {
    /**
     * 客户ID
     */
    @NotNull
    private Long customerId;
    /**
     * 额度类型
     */
    @NotNull
    private String quotaType;
}
