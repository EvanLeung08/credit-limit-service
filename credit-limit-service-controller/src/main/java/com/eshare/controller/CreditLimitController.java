package com.eshare.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditLimitServiceI;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Evan Leung
 * <p>
 * 额度控制类
 */
@RestController
public class CreditLimitController {

    @Autowired
    private CreditLimitServiceI creditLimitServiceI;

    @PostMapping(value = "/credit-limit/register")
    public SingleResponse<ProductLimit> registerProductCreditLimit(@RequestBody CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return creditLimitServiceI.registerProductLimit(creditLimitRegisterCmd);
    }

    @PostMapping(value = "/credit-limit/freezeQutoa")
    public Response freezeQuota(@RequestBody QuotaFreezeCmd quotaFreezeCmd) {
        return creditLimitServiceI.freezeQuota(quotaFreezeCmd);
    }

    @PostMapping(value = "/credit-limit/unfreezeQuota")
    public Response unfreezeQuota(@RequestBody QuotaUnfreezeCmd quotaUnfreezeCmd) {
        return creditLimitServiceI.unfreezeQuota(quotaUnfreezeCmd);
    }

    @PostMapping(value = "/credit-limit/subtractQuota")
    public Response subtractQuota(@RequestBody QuotaSubtractionCmd quotaSubtractionCmd) {
        return creditLimitServiceI.subtractQuota(quotaSubtractionCmd);
    }

    @PostMapping(value = "/credit-limit/recoverQuota")
    public Response recoverQuota(@RequestBody QuotaRecoveryCmd quotaRecoveryCmd) {
        return creditLimitServiceI.recoverQuota(quotaRecoveryCmd);
    }

    @PostMapping(value = "/credit-limit/freezeProductQuotaBySys")
    public Response freezeProductQuotaBySys(@RequestBody ProductQuotaSysFreezeCmd productQuotaSysFreezeCmd) {
        return creditLimitServiceI.freezeProductQuotaBySys(productQuotaSysFreezeCmd);
    }

    @PostMapping(value = "/credit-limit/freezeProductQuotaByMan")
    public Response freezeProductQuotaByMan(@RequestBody ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd) {
        return creditLimitServiceI.freezeProductQuotaByMan(productQuotaManualFreezeCmd);
    }

    @PostMapping(value = "/credit-limit/unfreezeProductQuota")
    public Response unfreezeProductQuota(@RequestBody ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd) {
        return creditLimitServiceI.unfreezeProductQuota(productQuotaUnfreezeCmd);
    }

    @PostMapping(value = "/credit-limit/forceUnfreezeProductQuota")
    public Response forceUnfreezeProductQuota(@RequestBody ProductQuotaForcedUnfreezeCmd productQuotaUnfreezeCmd) {
        return creditLimitServiceI.forceUnfreezeProductQuota(productQuotaUnfreezeCmd);
    }

    @PostMapping(value = "/credit-limit/inactivateProductQuota")
    public Response inactivateProductQuota(@RequestBody ProductQuotaInactiveCmd productQuotaInactiveCmd) {
        return creditLimitServiceI.inactivateProductQuota(productQuotaInactiveCmd);
    }

    @PostMapping(value = "/credit-limit/activateProductQuota")
    public Response activateProductQuota(@RequestBody ProductQuotaActivateCmd productQuotaActivateCmd) {
        return creditLimitServiceI.activateProductQuota(productQuotaActivateCmd);
    }

    @PostMapping(value = "/credit-limit/abandonProductQuota")
    public Response abandonProductQuota(@RequestBody ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd) {
        return creditLimitServiceI.abandonProductQuota(productQuotaAbandonmentCmd);
    }

    @PostMapping(value = "/credit-limit/changeQuota")
    public Response changeQuota(@RequestBody QuotaChangeCmd quotaChangeCmd) {
        return creditLimitServiceI.changeQuota(quotaChangeCmd);
    }

    @PostMapping(value = "/credit-limit/fetCustomerLimit")
    public SingleResponse<CustomerLimit> fetchCustomerLimit(@RequestBody CustomerLimitQryCmd customerLimitQryCmd) {

        return creditLimitServiceI.fetchCustomerLimit(customerLimitQryCmd);
    }

    @PostMapping(value = "/credit-limit/fetProductLimit")
    public SingleResponse<ProductLimit> fetchProductLimit(@RequestBody ProductLimitQryCmd productLimitQryCmd) {

        return creditLimitServiceI.fetchProductLimit(productLimitQryCmd);
    }
}
