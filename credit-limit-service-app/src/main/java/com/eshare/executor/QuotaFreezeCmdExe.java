package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;

import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import com.eshare.dto.QuotaFreezeCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 额度冻结执行类
 *
 * @Author Evan Leung
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
        ProductLimitDO productLimitDO = productLimitRepository.findWithNormal(cmd.getAccountId());
        // 冻结额度
        productLimitRepository.freezeAmount(productLimitDO, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
