package com.eshare.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditLimitServiceI;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.ProductLimitQryCmd;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
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
    public SingleResponse<ProductLimit> registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return (SingleResponse<ProductLimit>) commandBus.send(creditLimitRegisterCmd);
    }

    @Override
    public SingleResponse<CustomerLimit> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd) {
        return (SingleResponse<CustomerLimit>) commandBus.send(customerLimitQryCmd);
    }

    @Override
    public SingleResponse<ProductLimit> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd) {
        return (SingleResponse<ProductLimit>) commandBus.send(productLimitQryCmd);
    }
}
