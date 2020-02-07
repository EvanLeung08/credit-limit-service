package com.eshare.controller;

import com.eshare.dto.domainmodel.CreditLimitRegisterResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class CreditLimitController {

    @PostMapping(value = "/credit-limit/register")
    public Response<CreditLimitRegisterResponse> registerProductCreditLimit(){
        return null;
    }
}
