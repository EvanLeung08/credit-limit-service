package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.constant.FrozenStatusEnum;
import com.eshare.dto.ProductQuotaForcedUnfreezeCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度强制解冻执行类
 *
 * @Author Evan Leung
 */
@Command
public class ProductQuotaForcedUnfreezeCmdExe implements CommandExecutorI<Response, ProductQuotaForcedUnfreezeCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public ProductQuotaForcedUnfreezeCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(ProductQuotaForcedUnfreezeCmd cmd) {
        productLimitRepository.freezeStatus(cmd.getAccountId(), FrozenStatusEnum.NORMAL);
        return Response.buildSuccess();
    }
}
