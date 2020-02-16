package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.constant.FrozenStatusEnum;
import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.dto.ProductQuotaManualFreezeCmd;
import com.eshare.dto.ProductQuotaSysFreezeCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度人工冻结执行类
 * @Author Evan Leung
 *
 */
@Command
public class ProductQuotaManualFreezeCmdExe implements CommandExecutorI<Response, ProductQuotaManualFreezeCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public ProductQuotaManualFreezeCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(ProductQuotaManualFreezeCmd cmd) {
        ProductLimit productLimit = productLimitRepository.find(cmd.getAccountId());
        // 系统冻结
        productLimitRepository.freeze(productLimit, FrozenStatusEnum.MANUAL_FROZEN);
        return Response.buildSuccess();
    }
}
