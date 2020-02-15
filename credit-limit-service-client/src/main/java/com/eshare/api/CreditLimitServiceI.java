package com.eshare.api;

import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;

/**
 * @Author Evan Leung
 * <p>
 * 额度服务接口
 */
public interface CreditLimitServiceI {

    public SingleResponse<ProductLimit> registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd);

    public SingleResponse<CustomerLimit> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd);

    public SingleResponse<ProductLimit> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd);

}
