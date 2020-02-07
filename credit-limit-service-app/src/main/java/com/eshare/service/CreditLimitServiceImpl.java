package com.eshare.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.Response;
import com.eshare.api.CreditLimitServiceI;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.domainmodel.CreditLimitRegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditLimitServiceImpl implements CreditLimitServiceI {

    @Autowired
    private CommandBusI commandBus;

    @Override
    public Response registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return  commandBus.send(creditLimitRegisterCmd);
    }
}
