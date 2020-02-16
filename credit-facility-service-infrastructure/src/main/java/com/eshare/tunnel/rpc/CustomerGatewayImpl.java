package com.eshare.tunnel.rpc;

import com.eshare.domain.gateway.CustomerGateway;
import org.springframework.stereotype.Component;

@Component
public class CustomerGatewayImpl implements CustomerGateway {
    @Override
    public Long getCustomerId(Long userId) {
        return 111111l;
    }
}
