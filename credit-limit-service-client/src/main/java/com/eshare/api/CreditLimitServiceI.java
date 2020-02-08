package com.eshare.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.CustomerAddCmd;
import com.eshare.dto.CustomerListByNameQry;
import com.eshare.dto.domainmodel.Customer;

/**
 * @Author Evan Leung
 *
 * 额度服务接口
 */
public interface CreditLimitServiceI {

    public Response registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd);

}
