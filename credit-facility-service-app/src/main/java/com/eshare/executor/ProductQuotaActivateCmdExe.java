package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.constant.ActiveStatusEnum;
import com.eshare.dto.ProductQuotaActivateCmd;
import com.eshare.dto.ProductQuotaInactiveCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度激活执行类
 *
 * @Author Evan Leung
 */
@Command
public class ProductQuotaActivateCmdExe implements CommandExecutorI<Response, ProductQuotaActivateCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public ProductQuotaActivateCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(ProductQuotaActivateCmd cmd) {
        productLimitRepository.changeActiveStatus(cmd.getAccountId(), ActiveStatusEnum.ACTIVE);
        return Response.buildSuccess();
    }
}
