package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import com.eshare.dto.QuotaRecoveryCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 额度恢复执行类
 * @Author Evan Leung
 *
 */
@Command
public class QuotaRecoveryCmdExe implements CommandExecutorI<Response, QuotaRecoveryCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public QuotaRecoveryCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(QuotaRecoveryCmd cmd) {
        ProductLimitDO productLimitDO = productLimitRepository.find(cmd.getAccountId());
        // 扣减产品额度
        productLimitRepository.recover(productLimitDO, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
