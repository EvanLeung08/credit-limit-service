package com.eshare.domain.gateway;


/**
 * @Author Evan Leung
 *
 * 客户数据信息网关
 *
 * @decription 用于从基础设施层获取数据
 */
public interface CustomerGateway {
    /**
     * 从远程数据中心获取客户ID
     * @param userId 用户ID
     * @return 客户ID
     */
    public Long getCustomerId(Long userId);
}
