package com.eshare.domain.gateway;

import com.eshare.domain.constant.AbandonStatusEnum;
import com.eshare.domain.constant.ActiveStatusEnum;
import com.eshare.domain.constant.FrozenStatusEnum;
import com.eshare.domain.creditlimit.ProductLimit;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @Author Evan Leung
 *
 * 额度网关
 *
 * @decription 用于从基础设施层获取数据
 */
public interface CreditLimitGateway {
    /**
     * 注册额度
     *
     * @param applicationId
     * @param userId
     * @param quota          额度金额
     * @param productCode
     * @param expirationTime 过期时间
     * @param cardType
     * @return
     */
    public ProductLimit register(
            String applicationId,
            Long userId,
            Integer quota,
            String productCode,
            @Nullable Date expirationTime,
            Integer cardType);
}
