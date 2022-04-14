package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import com.eshare.dto.QuotaChangeCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度变更(提额/降额)执行类
 *
 * @Author Evan Leung
 */
@Command
public class QuotaChangeCmdExe implements CommandExecutorI<Response, QuotaChangeCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public QuotaChangeCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(QuotaChangeCmd cmd) {
        ProductLimitDO productLimitDO = productLimitRepository.findWithNormal(cmd.getAccountId());
        productLimitRepository.changeQuota(productLimitDO, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
