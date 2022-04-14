package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.constant.AbandonStatusEnum;
import com.eshare.domain.constant.ActiveStatusEnum;
import com.eshare.dto.ProductQuotaAbandonmentCmd;
import com.eshare.dto.ProductQuotaActivateCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度废弃执行类
 *
 * @Author Evan Leung
 */
@Command
public class ProductQuotaAbandonmentCmdExe implements CommandExecutorI<Response, ProductQuotaAbandonmentCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public ProductQuotaAbandonmentCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(ProductQuotaAbandonmentCmd cmd) {
        productLimitRepository.abandonStatus(cmd.getAccountId(), AbandonStatusEnum.ABANDONED);
        return Response.buildSuccess();
    }
}
