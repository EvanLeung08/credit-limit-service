package com.eshare.repository;

import com.eshare.common.utils.IdGenerator;
import com.eshare.domain.constant.CreditLimitTypeEnum;
import com.eshare.tunnel.database.CustomerLimitTunnel;
import com.eshare.tunnel.database.dataobject.CustomerLimitDO;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerLimitRepository {

    private final CustomerLimitTunnel customerLimitTunnel;
    private final IdGenerator idGenerator;
    private final static Integer MAX_QUOTA = 500000;

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
    public CustomerLimitDO find(Long customerId) {
        CustomerLimitDO cond = new CustomerLimitDO();
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
    public CustomerLimitDO find(Long customerId, String quotaType) {
        CustomerLimitDO cond = new CustomerLimitDO();
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
    public CustomerLimitDO init(Long customerId) {
        CustomerLimitDO customerLimitDO = new CustomerLimitDO();
        customerLimitDO.setId(idGenerator.generateId());
        customerLimitDO.setSerialNumber(idGenerator.generateId() + "");
        customerLimitDO.setCustomerId(customerId);
        customerLimitDO.setQuotaLimit(MAX_QUOTA);
        customerLimitDO.setQuotaBalance(MAX_QUOTA);
        customerLimitDO.setQuotaBase(MAX_QUOTA);
        customerLimitDO.setQuotaType(CreditLimitTypeEnum.GUIDE.name());
        customerLimitTunnel.insertSelective(customerLimitDO);
        return customerLimitDO;
    }
}
