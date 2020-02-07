package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import com.eshare.dto.domainmodel.Customer;
import lombok.Data;

@Data
public class CustomerAddCmd extends Command{

    private Customer customer;

}
