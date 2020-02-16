package com.eshare.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auothor Evan Leung
 * 客户额度请求对象
 */
@Data
public class CustomerLimitCO  extends ClientObject {

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
