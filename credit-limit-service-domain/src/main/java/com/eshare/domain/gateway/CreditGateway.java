package com.eshare.domain.gateway;

import com.eshare.domain.customer.Customer;
import com.eshare.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
