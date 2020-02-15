package com.eshare.repository;

import com.eshare.common.utils.IdGenerator;
import com.eshare.domain.constant.CreditLimitTypeEnum;
import com.eshare.tunnel.database.CustomerLimitTunnel;
import com.eshare.domain.creditlimit.CustomerLimit;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerLimitRepository {

    private final CustomerLimitTunnel customerLimitTunnel;
    private final IdGenerator idGenerator;
    private final static Integer MAX_QUOTA = 5000000;

    @Autowired
    public CustomerLimitRepository(CustomerLimitTunnel customerLimitTunnel, IdGenerator idGenerator) {
        this.customerLimitTunnel = customerLimitTunnel;
        this.idGenerator = idGenerator;
    }

    /**
     * 查询客户额度
     *
     * @param customerId
     * @return
     */
    public CustomerLimit find(Long customerId) {
        Preconditions.checkNotNull(customerId, "customerId can not null");
        CustomerLimit cond = new CustomerLimit();
        cond.setCustomerId(customerId);
        return customerLimitTunnel.selectByModelSelective(cond, true);
    }

    /**
     * 查询客户额度
     *
     * @param customerId
     * @param quotaType
     * @return
     */
    public CustomerLimit find(Long customerId, String quotaType) {
        Preconditions.checkNotNull(customerId, "customerId can not null");
        Preconditions.checkNotNull(quotaType, "quotaType can not null");

        CustomerLimit cond = new CustomerLimit();
        cond.setCustomerId(customerId);
        cond.setQuotaType(quotaType);
        return customerLimitTunnel.selectByModelSelective(cond, true);
    }

    /**
     * 初始化客户额度
     *
     * @param customerId
     * @return
     */
    public CustomerLimit init(Long customerId) {
        CustomerLimit customerLimit = new CustomerLimit();
        customerLimit.setId(idGenerator.generateId());
        customerLimit.setSerialNumber(idGenerator.generateId() + "");
        customerLimit.setCustomerId(customerId);
        customerLimit.setQuotaLimit(MAX_QUOTA);
        customerLimit.setQuotaBalance(MAX_QUOTA);
        customerLimit.setQuotaBase(MAX_QUOTA);
        customerLimit.setQuotaType(CreditLimitTypeEnum.GUIDE.name());
        customerLimitTunnel.insertSelective(customerLimit);
        return customerLimit;
    }
}
