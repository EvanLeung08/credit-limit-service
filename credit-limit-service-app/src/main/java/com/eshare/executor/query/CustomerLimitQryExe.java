package com.eshare.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.domain.creditlimit.CustomerLimit;
import com.eshare.domain.gateway.CustomerInfoGateway;
import com.eshare.dto.CustomerLimitQryCmd;
import com.eshare.dto.CustomerListByNameQry;
import com.eshare.dto.domainmodel.Customer;
import com.eshare.dto.domainmodel.CustomerLimitResponse;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 **/
@Command
public class CustomerLimitQryExe implements QueryExecutorI<SingleResponse<CustomerLimitResponse>, CustomerLimitQryCmd> {
    @Autowired
    private CustomerLimitRepository customerLimitRepository;
    @Autowired
    private ProductLimitRepository productLimitRepository;


    @Override
    public SingleResponse<CustomerLimitResponse> execute(CustomerLimitQryCmd cmd) {
        CustomerLimitResponse customerLimitResponse = new CustomerLimitResponse();
        CustomerLimit customerLimit = customerLimitRepository.find(cmd.getCustomerLimitCO().getCustomerId(), cmd.getCustomerLimitCO().getQuotaType());
        if (customerLimit == null) {
            customerLimit = customerLimitRepository.init(cmd.getCustomerLimitCO().getCustomerId());
        }
        int totalCardLimit = productLimitRepository.sumQuota(cmd.getCustomerLimitCO().getCustomerId());
        customerLimit.setQuotaBalance(customerLimit.getQuotaBalance() - totalCardLimit);

        //Convert domain object to dto
        BeanUtils.copyProperties(customerLimit, customerLimitResponse);
        return SingleResponse.of(customerLimitResponse);
    }
}
