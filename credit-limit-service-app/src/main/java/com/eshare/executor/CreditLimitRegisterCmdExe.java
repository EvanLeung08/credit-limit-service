package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.gateway.CreditLimitGateway;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.CustomerAddCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Date;

@Command
public class CreditLimitRegisterCmdExe implements CommandExecutorI<Response, CreditLimitRegisterCmd> {

    @Autowired
    private CreditLimitGateway creditLimitGateway;

    @Override
    public Response execute(CreditLimitRegisterCmd cmd) {

        creditLimitGateway.register(cmd.getApplicationId(),
                cmd.getUserId(),
                cmd.getQuotaLimit(),
                cmd.getProductCode(),
                cmd.getExpirationTime(),
                cmd.getCardType());
        return Response.buildSuccess();
    }
}
