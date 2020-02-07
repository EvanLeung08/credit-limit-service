package com.eshare.executor;

import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.CustomerAddCmd;

public class CreditLimitRegisterCmdExe implements CommandExecutorI<Response, CreditLimitRegisterCmd> {
    @Override
    public Response execute(CreditLimitRegisterCmd cmd) {

        return null;
    }
}
