package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;

import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.domain.gateway.CustomerGateway;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.QuotaFreezeCmd;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 额度冻结执行类
 * @Author Evan Leung
 *
 */
@Command
public class QuotaFreezeCmdExe implements CommandExecutorI<Response, QuotaFreezeCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public QuotaFreezeCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(QuotaFreezeCmd cmd) {
        ProductLimit productLimit = productLimitRepository.findWithNormal(cmd.getQuotaAccount());
        // 冻结额度
        productLimitRepository.freezeAmount(productLimit, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
