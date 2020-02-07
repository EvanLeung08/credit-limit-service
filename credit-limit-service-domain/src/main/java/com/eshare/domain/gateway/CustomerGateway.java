package com.eshare.domain.gateway;

import com.eshare.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
