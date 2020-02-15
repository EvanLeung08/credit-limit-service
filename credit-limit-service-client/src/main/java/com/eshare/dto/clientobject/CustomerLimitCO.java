package com.eshare.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;

/**
 * @Auothor Evan Leung
 * 客户额度请求对象
 */
@Data
public class CustomerLimitCO  extends ClientObject {

    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 额度类型
     */
    private String quotaType;
}
