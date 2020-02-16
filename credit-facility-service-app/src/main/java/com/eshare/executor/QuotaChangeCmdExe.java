package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.constant.AbandonStatusEnum;
import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.dto.ProductQuotaAbandonmentCmd;
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
        ProductLimit productLimit = productLimitRepository.findWithNormal(cmd.getCardId());
        productLimitRepository.changeQuota(productLimit, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
