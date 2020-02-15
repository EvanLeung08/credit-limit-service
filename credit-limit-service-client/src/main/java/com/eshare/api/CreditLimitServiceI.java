package com.eshare.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CreditLimitRegisterResponse;
import com.eshare.dto.domainmodel.Customer;
import com.eshare.dto.domainmodel.CustomerLimitResponse;
import com.eshare.dto.domainmodel.ProductLimitResponse;

/**
 * @Author Evan Leung
 * <p>
 * 额度服务接口
 */
public interface CreditLimitServiceI {

    public SingleResponse<CreditLimitRegisterResponse> registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd);

    public SingleResponse<CustomerLimitResponse> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd);

    public SingleResponse<ProductLimitResponse> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd);

}
