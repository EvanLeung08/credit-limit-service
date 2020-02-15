package com.eshare.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditLimitServiceI;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.ProductLimitQryCmd;
import com.eshare.dto.domainmodel.CreditLimitRegisterResponse;
import com.eshare.dto.domainmodel.CustomerLimitResponse;
import com.eshare.dto.domainmodel.ProductLimitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Evan Leung
 * <p>
 * 额度服务类
 */
@Service
public class CreditLimitServiceImpl implements CreditLimitServiceI {

    @Autowired
    private CommandBusI commandBus;

    @Override
    public SingleResponse<CreditLimitRegisterResponse> registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return (SingleResponse<CreditLimitRegisterResponse>) commandBus.send(creditLimitRegisterCmd);
    }

    @Override
    public SingleResponse<CustomerLimitResponse> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd) {
        return (SingleResponse<CustomerLimitResponse>) commandBus.send(customerLimitQryCmd);
    }

    @Override
    public SingleResponse<ProductLimitResponse> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd) {
        return (SingleResponse<ProductLimitResponse>) commandBus.send(productLimitQryCmd);
    }
}
