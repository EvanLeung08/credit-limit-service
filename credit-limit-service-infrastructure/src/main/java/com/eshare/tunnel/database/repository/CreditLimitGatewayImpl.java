package com.eshare.tunnel.database.repository;

import com.alibaba.cola.exception.BizException;
import com.eshare.common.BizCode;
import com.eshare.domain.creditlimit.CustomerLimit;
import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.domain.gateway.CreditLimitGateway;
import com.eshare.domain.gateway.CustomerInfoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;

@Component
public class CreditLimitGatewayImpl implements CreditLimitGateway {

    private final ProductLimitRepository productLimitRepository;
    private final CustomerLimitRepository customerLimitRepository;
    private final CustomerInfoGateway customerInfoGateway;

    @Autowired
    public CreditLimitGatewayImpl(ProductLimitRepository productLimitRepository, CustomerLimitRepository customerLimitRepository, CustomerInfoGateway customerInfoGateway) {
        this.productLimitRepository = productLimitRepository;
        this.customerLimitRepository = customerLimitRepository;
        this.customerInfoGateway = customerInfoGateway;
    }

    @Override
    public ProductLimit register(String applicationId, Long userId, Integer quota, String productCode, @Nullable Date expirationTime, Integer cardType) {
        Long customerId = customerInfoGateway.getCustomerId(userId);
        CustomerLimit customerLimit = customerLimitRepository.find(customerId);
        if (customerLimit == null) {
            customerLimit = customerLimitRepository.init(customerId);
        }
        int cardQuota = productLimitRepository.sumQuota(customerId);
        if (customerLimit.getQuotaLimit() < cardQuota + quota) {
            throw new BizException(BizCode.BIZ_ONE);
        }
        // 2. 保存额度
        ProductLimit productLimit = productLimitRepository.save(userId, customerId, quota, productCode, expirationTime, cardType);
        return productLimit;
    }
}
