package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.eshare.domain.constant.FrozenStatusEnum;
import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import com.eshare.dto.ProductQuotaUnfreezeCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 产品额度解冻执行类
 *
 * @Author Evan Leung
 */
@Command
public class ProductQuotaUnfreezeCmdExe implements CommandExecutorI<Response, ProductQuotaUnfreezeCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public ProductQuotaUnfreezeCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(ProductQuotaUnfreezeCmd cmd) {
        ProductLimitDO productLimitDO = productLimitRepository.find(cmd.getAccountId());
        FrozenStatusEnum currentStatus = FrozenStatusEnum.fromValue(productLimitDO.getFrozenStatus());
        FrozenStatusEnum frozenStatus;
        switch (currentStatus) {
            case MANUAL_FROZEN:
                frozenStatus = FrozenStatusEnum.NORMAL;
                break;
            case SYSTEM_FROZEN:
                frozenStatus = FrozenStatusEnum.NORMAL;
                break;
            case ALL_FROZEN:
                frozenStatus = FrozenStatusEnum.SYSTEM_FROZEN;
                break;
            default:
                throw new BizException("Failed to unfreezeQuota,accountId=" + cmd.getAccountId());
        }

        productLimitRepository.freezeStatus(cmd.getAccountId(), frozenStatus);
        return Response.buildSuccess();
    }
}
