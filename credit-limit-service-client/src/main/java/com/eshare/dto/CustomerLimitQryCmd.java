package com.eshare.dto;

import com.alibaba.cola.dto.Query;
import com.eshare.dto.clientobject.CustomerLimitCO;
import lombok.Data;

/**
 * @Author Evan Leung
 * 客户额度查询指令
 **/
@Data
public class CustomerLimitQryCmd extends Query {

    private CustomerLimitCO customerLimitCO;
}
