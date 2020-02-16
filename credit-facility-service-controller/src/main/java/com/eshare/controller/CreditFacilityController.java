package com.eshare.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditFacilityServiceI;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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



    @ApiOperation("注册账户额度接口")
    @PostMapping(value = "/credit-facility/account")
    public SingleResponse<ProductLimit> registerProductCreditLimit(@RequestBody CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return creditFacilityServiceI.registerProductLimit(creditLimitRegisterCmd);
    }

    @ApiOperation("变更账户额度接口")
    @PutMapping(value = "/credit-facility/account-quota")
    public Response changeQuota(@RequestBody QuotaChangeCmd quotaChangeCmd) {
        return creditFacilityServiceI.changeQuota(quotaChangeCmd);
    }

    @ApiOperation("冻结账户额度数值接口")
    @PutMapping(value = "/credit-facility/account-frozen-quota")
    public Response freezeQuota(@RequestBody QuotaFreezeCmd quotaFreezeCmd) {
        return creditFacilityServiceI.freezeQuota(quotaFreezeCmd);
    }

    @ApiOperation("解冻账户额度数值接口")
    @PutMapping(value = "/credit-facility/account-unfrozen-quota")
    public Response unfreezeQuota(@RequestBody QuotaUnfreezeCmd quotaUnfreezeCmd) {
        return creditFacilityServiceI.unfreezeQuota(quotaUnfreezeCmd);
    }

    @ApiOperation("扣减账户额度数值接口")
    @PutMapping(value = "/credit-facility/account-subtracted-quota")
    public Response subtractQuota(@RequestBody QuotaSubtractionCmd quotaSubtractionCmd) {
        return creditFacilityServiceI.subtractQuota(quotaSubtractionCmd);
    }

    @ApiOperation("恢复账户额度数值接口")
    @PutMapping(value = "/credit-facility/account-recovery-quota")
    public Response recoverQuota(@RequestBody QuotaRecoveryCmd quotaRecoveryCmd) {
        return creditFacilityServiceI.recoverQuota(quotaRecoveryCmd);
    }
    @ApiOperation("系统冻结账户状态接口")
    @PutMapping(value = "/credit-facility/account-sys-frozen-status")
    public Response freezeProductQuotaBySys(@RequestBody ProductQuotaSysFreezeCmd productQuotaSysFreezeCmd) {
        return creditFacilityServiceI.freezeProductQuotaBySys(productQuotaSysFreezeCmd);
    }
    @ApiOperation("人工冻结账户状态接口")
    @PutMapping(value = "/credit-facility/account-man-frozen-status")
    public Response freezeProductQuotaByMan(@RequestBody ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd) {
        return creditFacilityServiceI.freezeProductQuotaByMan(productQuotaManualFreezeCmd);
    }

    @ApiOperation("解除账户冻结状态接口")
    @PutMapping(value = "/credit-facility/account-unfrozen-status")
    public Response unfreezeProductQuota(@RequestBody ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd) {
        return creditFacilityServiceI.unfreezeProductQuota(productQuotaUnfreezeCmd);
    }

   @ApiOperation("强制解除额度账户冻结接口")
    @PutMapping(value = "/credit-facility/account-forced-unfrozen-status")
    public Response forceUnfreezeProductQuota(@RequestBody ProductQuotaForcedUnfreezeCmd productQuotaUnfreezeCmd) {
        return creditFacilityServiceI.forceUnfreezeProductQuota(productQuotaUnfreezeCmd);
    }

    @ApiOperation("启用额度账户接口")
    @PutMapping(value = "/credit-facility/account-enable-status")
    public Response inactivateProductQuota(@RequestBody ProductQuotaInactiveCmd productQuotaInactiveCmd) {
        return creditFacilityServiceI.inactivateProductQuota(productQuotaInactiveCmd);
    }

    @ApiOperation("失效额度账户接口")
    @PutMapping(value = "/credit-facility/account-disable-status")
    public Response activateProductQuota(@RequestBody ProductQuotaActivateCmd productQuotaActivateCmd) {
        return creditFacilityServiceI.activateProductQuota(productQuotaActivateCmd);
    }

    @ApiOperation("废弃额度账户接口")
    @PutMapping(value = "/credit-facility/account-abandoned-status")
    public Response abandonProductQuota(@RequestBody ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd) {
        return creditFacilityServiceI.abandonProductQuota(productQuotaAbandonmentCmd);
    }

    @ApiOperation("查询客户额度接口")
    @GetMapping(value = "/credit-facility/customer-quota")
    public SingleResponse<CustomerLimit> fetchCustomerLimit(@RequestParam(value = "customerLimitQryCmd") CustomerLimitQryCmd customerLimitQryCmd) {

        return creditFacilityServiceI.fetchCustomerLimit(customerLimitQryCmd);
    }
    @ApiOperation("查询客户账户额度接口")
    @GetMapping(value = "/credit-facility/customer-account-quota")
    public SingleResponse<ProductLimit> fetchProductLimit(@RequestParam(value = "productLimitQryCmd") ProductLimitQryCmd productLimitQryCmd) {

        return creditFacilityServiceI.fetchProductLimit(productLimitQryCmd);
    }
}
