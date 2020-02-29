package com.eshare.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.ProductLimitQryCmd;
import com.eshare.dto.clientobject.ProductLimitCO;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 **/
@Command
public class ProductLimitQryExe implements QueryExecutorI<SingleResponse<ProductLimitCO>, ProductLimitQryCmd> {

    @Autowired
    private ProductLimitRepository productLimitRepository;

    @Override
    public SingleResponse<ProductLimitCO> execute(ProductLimitQryCmd cmd) {
        ProductLimitCO productLimitCOResponse = new ProductLimitCO();
        com.eshare.tunnel.database.dataobject.ProductLimitDO productLimitDO = productLimitRepository.find(cmd.getAccountId());
        BeanUtils.copyProperties(productLimitDO, productLimitCOResponse);
        return SingleResponse.of(productLimitCOResponse);
    }
}
