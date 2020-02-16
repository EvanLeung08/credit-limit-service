package com.eshare.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 **/
@Command
public class CustomerLimitQryExe implements QueryExecutorI<SingleResponse<CustomerLimit>, CustomerLimitQryCmd> {
    @Autowired
    private CustomerLimitRepository customerLimitRepository;
    @Autowired
    private ProductLimitRepository productLimitRepository;


    @Override
    public SingleResponse<CustomerLimit> execute(CustomerLimitQryCmd cmd) {
        CustomerLimit customerLimitResponse = new CustomerLimit();
        com.eshare.domain.creditlimit.CustomerLimit customerLimit = customerLimitRepository.find(cmd.getCustomerId(), cmd.getQuotaType());
        if (customerLimit == null) {
            customerLimit = customerLimitRepository.init(cmd.getCustomerId());
        }
        int totalCardLimit = productLimitRepository.sumQuota(cmd.getCustomerId());
        customerLimit.setQuotaBalance(customerLimit.getQuotaBalance() - totalCardLimit);

        //Convert domain object to dto
        BeanUtils.copyProperties(customerLimit, customerLimitResponse);
        return SingleResponse.of(customerLimitResponse);
    }
}
