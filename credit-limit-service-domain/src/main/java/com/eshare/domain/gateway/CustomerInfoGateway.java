package com.eshare.domain.gateway;

//the customer info is in antoher distributed Service
public interface CustomerInfoGateway {
    /**
     * 从远程数据中心获取客户ID
     * @param userId 用户ID
     * @return 客户ID
     */
    public Long getCustomerId(Long userId);
}
