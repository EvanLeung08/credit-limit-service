package com.eshare.tunnel.rpc;

import com.eshare.domain.gateway.CustomerInfoGateway;
import org.springframework.stereotype.Component;

@Component
public class CustomerInfoGatewayImpl implements CustomerInfoGateway {
    @Override
    public Long getCustomerId(Long userId) {
        return 111111l;
    }
}
