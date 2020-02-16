package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.constant.ActiveStatusEnum;
import com.eshare.domain.constant.FrozenStatusEnum;
import com.eshare.dto.ProductQuotaForcedUnfreezeCmd;
import com.eshare.dto.ProductQuotaInactiveCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度禁用执行类
 *
 * @Author Evan Leung
 */
@Command
public class ProductQuotaInactiveCmdExe implements CommandExecutorI<Response, ProductQuotaInactiveCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public ProductQuotaInactiveCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(ProductQuotaInactiveCmd cmd) {
        productLimitRepository.changeActiveStatus(cmd.getAccountId(), ActiveStatusEnum.INACTIVE);
        return Response.buildSuccess();
    }
}
