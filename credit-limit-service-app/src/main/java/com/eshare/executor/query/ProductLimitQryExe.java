package com.eshare.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.ProductLimitQryCmd;
import com.eshare.dto.domainmodel.CustomerLimitResponse;
import com.eshare.dto.domainmodel.ProductLimitResponse;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 **/
@Command
public class ProductLimitQryExe implements QueryExecutorI<SingleResponse<ProductLimitResponse>, ProductLimitQryCmd> {

    @Autowired
    private ProductLimitRepository productLimitRepository;

    @Override
    public SingleResponse<ProductLimitResponse> execute(ProductLimitQryCmd cmd) {
        ProductLimitResponse productLimitResponse = new ProductLimitResponse();
        ProductLimit productLimit = productLimitRepository.find(cmd.getCardId());
        BeanUtils.copyProperties(productLimit, productLimitResponse);
        return SingleResponse.of(productLimitResponse);
    }
}
