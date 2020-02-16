package com.eshare.domain.creditlimit;

import com.alibaba.cola.domain.Entity;
import com.alibaba.cola.domain.EntityObject;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import lombok.Data;

import java.util.Date;

/**
 * @Author Evan Leung
 * <p>
 * 额度注册客户对象
 */
@Data
public class RegistrationLimit extends EntityObject {

    /**
     * 申请ID
     */
    private String applicationId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 产品编号
     */
    private Integer quotaLimit;
    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 产品额度过期日期,非必填
     */
    private Date expirationTime;
    /**
     * 额度账户类型,1:一次性授信,2:循环授信
     */
    private Integer accountType;
    /**
     * 客户ID
     */
    private Long customerId;

}
