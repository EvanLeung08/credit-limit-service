package com.eshare.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.clientobject.CustomerLimitCO;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 **/
@Command
public class CustomerLimitQryExe implements QueryExecutorI<SingleResponse<CustomerLimitCO>, CustomerLimitQryCmd> {
    @Autowired
    private CustomerLimitRepository customerLimitRepository;
    @Autowired
    private ProductLimitRepository productLimitRepository;


    @Override
    public SingleResponse<CustomerLimitCO> execute(CustomerLimitQryCmd cmd) {
        CustomerLimitCO customerLimitCOResponse = new CustomerLimitCO();
        com.eshare.tunnel.database.dataobject.CustomerLimitDO customerLimitDO = customerLimitRepository.find(cmd.getCustomerId(), cmd.getQuotaType());
        if (customerLimitDO == null) {
            customerLimitDO = customerLimitRepository.init(cmd.getCustomerId());
        }
        int totalCardLimit = productLimitRepository.sumQuota(cmd.getCustomerId());
        customerLimitDO.setQuotaBalance(customerLimitDO.getQuotaBalance() - totalCardLimit);

        //Convert domain object to dto
        BeanUtils.copyProperties(customerLimitDO, customerLimitCOResponse);
        return SingleResponse.of(customerLimitCOResponse);
    }
}
