package com.eshare.controller;

import com.eshare.api.CreditLimitServiceI;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.domainmodel.CreditLimitRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.cola.dto.Response;


/**
 * @Author Evan Leung
 *
 * 额度控制类
 */
@RestController
public class CreditLimitController {

    @Autowired
    private CreditLimitServiceI creditLimitServiceI;
    @PostMapping(value = "/credit-limit/register")
    public Response registerProductCreditLimit(@RequestBody CreditLimitRegisterCmd creditLimitRegisterCmd){
        return creditLimitServiceI.registerProductLimit(creditLimitRegisterCmd);
    }
}
