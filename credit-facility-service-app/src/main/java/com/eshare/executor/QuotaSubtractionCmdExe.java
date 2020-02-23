package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import com.eshare.dto.QuotaSubtractionCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 额度扣减执行类
 * @Author Evan Leung
 *
 */
@Command
public class QuotaSubtractionCmdExe implements CommandExecutorI<Response, QuotaSubtractionCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public QuotaSubtractionCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(QuotaSubtractionCmd cmd) {
        ProductLimitDO productLimitDO = productLimitRepository.findWithNormal(cmd.getAccountId());
        // 扣减产品额度
        productLimitRepository.subtract(productLimitDO, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
