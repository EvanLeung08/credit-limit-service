package com.eshare.controller;

import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditLimitServiceI;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.ProductLimitQryCmd;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Evan Leung
 * <p>
 * 额度控制类
 */
@RestController
public class CreditLimitController {

    @Autowired
    private CreditLimitServiceI creditLimitServiceI;

    @PostMapping(value = "/credit-limit/register")
    public SingleResponse<ProductLimit> registerProductCreditLimit(@RequestBody CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return creditLimitServiceI.registerProductLimit(creditLimitRegisterCmd);
    }

    @PostMapping(value = "/credit-limit/fetCustomerLimit")
    public SingleResponse<CustomerLimit> fetchCustomerLimit(@RequestBody CustomerLimitQryCmd customerLimitQryCmd) {

        return creditLimitServiceI.fetchCustomerLimit(customerLimitQryCmd);
    }

    @PostMapping(value = "/credit-limit/fetProductLimit")
    public SingleResponse<ProductLimit> fetchProductLimit(@RequestBody ProductLimitQryCmd productLimitQryCmd) {

        return creditLimitServiceI.fetchProductLimit(productLimitQryCmd);
    }
}
