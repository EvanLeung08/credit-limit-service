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


    @ApiOperation("开通额度账户接口")
    @PostMapping(value = "/credit-facility/account")
    public SingleResponse<ProductLimit> registerAccount(@RequestBody CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return creditFacilityServiceI.registerProductLimit(creditLimitRegisterCmd);
    }

    @ApiOperation("添加额度接口")
    @PostMapping(value = "/credit-facility/quota")
    public SingleResponse<ProductLimit> registerProductCreditLimit(@RequestBody CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return creditFacilityServiceI.registerProductLimit(creditLimitRegisterCmd);
    }

    @ApiOperation("变更额度接口")
    @PutMapping(value = "/credit-facility/quota")
    public Response changeQuota(@RequestBody QuotaChangeCmd quotaChangeCmd) {
        return creditFacilityServiceI.changeQuota(quotaChangeCmd);
    }

    @ApiOperation("冻结额度数值接口")
    @PutMapping(value = "/credit-facility/frozen-amount")
    public Response freezeQuota(@RequestBody QuotaFreezeCmd quotaFreezeCmd) {
        return creditFacilityServiceI.freezeQuota(quotaFreezeCmd);
    }

    @ApiOperation("解冻额度数值接口")
    @PutMapping(value = "/credit-facility/unfrozen-amount")
    public Response unfreezeQuota(@RequestBody QuotaUnfreezeCmd quotaUnfreezeCmd) {
        return creditFacilityServiceI.unfreezeQuota(quotaUnfreezeCmd);
    }

    @ApiOperation("扣减额度数值接口")
    @PutMapping(value = "/credit-facility/subtracted-amount")
    public Response subtractQuota(@RequestBody QuotaSubtractionCmd quotaSubtractionCmd) {
        return creditFacilityServiceI.subtractQuota(quotaSubtractionCmd);
    }

    @ApiOperation("恢复额度数值接口")
    @PutMapping(value = "/credit-facility/recovery-amount")
    public Response recoverQuota(@RequestBody QuotaRecoveryCmd quotaRecoveryCmd) {
        return creditFacilityServiceI.recoverQuota(quotaRecoveryCmd);
    }
    @ApiOperation("更新系统冻结状态接口")
    @PutMapping(value = "/credit-facility/sys-frozen-status")
    public Response freezeProductQuotaBySys(@RequestBody ProductQuotaSysFreezeCmd productQuotaSysFreezeCmd) {
        return creditFacilityServiceI.freezeProductQuotaBySys(productQuotaSysFreezeCmd);
    }
    @ApiOperation("更新人工冻结状态接口")
    @PutMapping(value = "/credit-facility/manual-frozen-status")
    public Response freezeProductQuotaByMan(@RequestBody ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd) {
        return creditFacilityServiceI.freezeProductQuotaByMan(productQuotaManualFreezeCmd);
    }

    @ApiOperation("更新产品冻结状态接口")
    @PutMapping(value = "/credit-facility/product-unfrozen-status")
    public Response unfreezeProductQuota(@RequestBody ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd) {
        return creditFacilityServiceI.unfreezeProductQuota(productQuotaUnfreezeCmd);
    }

    @ApiOperation("强制解除额度冻结状态接口")
    @PutMapping(value = "/credit-facility/forced-unfrozen-status")
    public Response forceUnfreezeProductQuota(@RequestBody ProductQuotaForcedUnfreezeCmd productQuotaUnfreezeCmd) {
        return creditFacilityServiceI.forceUnfreezeProductQuota(productQuotaUnfreezeCmd);
    }

    @ApiOperation("更新额度启动状态接口")
    @PutMapping(value = "/credit-facility/enable-status")
    public Response inactivateProductQuota(@RequestBody ProductQuotaInactiveCmd productQuotaInactiveCmd) {
        return creditFacilityServiceI.inactivateProductQuota(productQuotaInactiveCmd);
    }

    @ApiOperation("更新额度失效状态接口")
    @PutMapping(value = "/credit-facility/disable-status")
    public Response activateProductQuota(@RequestBody ProductQuotaActivateCmd productQuotaActivateCmd) {
        return creditFacilityServiceI.activateProductQuota(productQuotaActivateCmd);
    }

    @ApiOperation("更新额度废弃状态接口")
    @PutMapping(value = "/credit-facility/abandoned-status")
    public Response abandonProductQuota(@RequestBody ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd) {
        return creditFacilityServiceI.abandonProductQuota(productQuotaAbandonmentCmd);
    }

    @ApiOperation("查询客户额度接口")
    @GetMapping(value = "/credit-facility/get-customer-quota")
    public SingleResponse<CustomerLimit> fetchCustomerLimit(@RequestParam(value = "customerLimitQryCmd") CustomerLimitQryCmd customerLimitQryCmd) {

        return creditFacilityServiceI.fetchCustomerLimit(customerLimitQryCmd);
    }
    @ApiOperation("查询产品额度接口")
    @GetMapping(value = "/credit-facility/get-product-quota")
    public SingleResponse<ProductLimit> fetchProductLimit(@RequestParam(value = "productLimitQryCmd") ProductLimitQryCmd productLimitQryCmd) {

        return creditFacilityServiceI.fetchProductLimit(productLimitQryCmd);
    }
}
