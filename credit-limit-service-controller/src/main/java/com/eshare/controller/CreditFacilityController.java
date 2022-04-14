package com.eshare.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditFacilityServiceI;
import com.eshare.dto.*;
import com.eshare.dto.clientobject.CustomerLimitCO;
import com.eshare.dto.clientobject.ProductLimitCO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author Evan Leung
 * <p>
 * 额度控制类
 */
@Api(tags = "信贷额度对外服务接口")
@RestController
public class CreditFacilityController {

    @Autowired
    private CreditFacilityServiceI creditFacilityServiceI;


    @ApiOperation("注册额度账户接口")
    @PostMapping(value = "/account")
    public SingleResponse<ProductLimitCO> registerAccount(@RequestBody(required = true) CreditLimitRegisterCmd cmd) {
        return creditFacilityServiceI.registerAccount(cmd);
    }

    @ApiOperation("更新账户额度接口")
    @PutMapping(value = "/accounts/{accountId}/quota")
    public Response changeQuota(@PathVariable(required = true) Long accountId, @RequestBody(required = true) BaseQuotaAmountUpdateCmd cmd) {
        cmd.setAccountId(accountId);
        return creditFacilityServiceI.dispatchQuotaChangeCmd(cmd);
    }

    @ApiOperation("更新额度账户状态接口")
    @PutMapping(value = "/accounts/{accountId}/status")
    public Response changeAccountStatus(@PathVariable(required = true) Long accountId, @RequestBody(required = true) BaseQuotaStatusChangeCmd cmd) {
        cmd.setAccountId(accountId);
        return creditFacilityServiceI.dispatchQuotaStatusChangeCmd(cmd);
    }

    @ApiOperation("查询客户额度接口")
    @GetMapping(value = "/customers/{customerId}/quota")
    public SingleResponse<CustomerLimitCO> fetchCustomerLimit(@PathVariable(required = true) Long customerId, @RequestParam(required = true) String quotaType) {
        CustomerLimitQryCmd customerLimitQryCmd = new CustomerLimitQryCmd();
        customerLimitQryCmd.setCustomerId(customerId);
        customerLimitQryCmd.setQuotaType(quotaType);
        return creditFacilityServiceI.fetchCustomerLimit(customerLimitQryCmd);
    }

    @ApiOperation("查询账户额度接口")
    @GetMapping(value = "/accounts/{accountId}/quota")
    public SingleResponse<ProductLimitCO> fetchProductLimit(@PathVariable(required = true) Long accountId) {
        ProductLimitQryCmd productLimitQryCmd = new ProductLimitQryCmd();
        productLimitQryCmd.setAccountId(accountId);
        return creditFacilityServiceI.fetchProductLimit(productLimitQryCmd);
    }
}
