package com.eshare.repository;

import com.eshare.domain.customer.Customer;
import com.eshare.domain.gateway.CustomerGateway;

import com.eshare.tunnel.database.CustomerTunnel;
import com.eshare.tunnel.database.dataobject.CustomerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepository implements CustomerGateway {
    @Autowired
    private CustomerTunnel customerTunnel;

    public Customer getByById(String customerId){
      CustomerDO customerDO = customerTunnel.getById(customerId);
      //Convert to Customer
      return null;
    }
}
