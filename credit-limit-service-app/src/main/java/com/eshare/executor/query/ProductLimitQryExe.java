package com.eshare.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.ProductLimitQryCmd;
import com.eshare.dto.domainmodel.ProductLimit;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 **/
@Command
public class ProductLimitQryExe implements QueryExecutorI<SingleResponse<ProductLimit>, ProductLimitQryCmd> {

    @Autowired
    private ProductLimitRepository productLimitRepository;

    @Override
    public SingleResponse<ProductLimit> execute(ProductLimitQryCmd cmd) {
        ProductLimit productLimitResponse = new ProductLimit();
        com.eshare.domain.creditlimit.ProductLimit productLimit = productLimitRepository.find(cmd.getCardId());
        BeanUtils.copyProperties(productLimit, productLimitResponse);
        return SingleResponse.of(productLimitResponse);
    }
}
