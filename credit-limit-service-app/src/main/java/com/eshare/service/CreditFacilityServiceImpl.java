package com.eshare.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.api.CreditFacilityServiceI;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Evan Leung
 * <p>
 * 额度服务类
 */
@Service
public class CreditFacilityServiceImpl implements CreditFacilityServiceI {

    @Autowired
    private CommandBusI commandBus;

    @Override
    public SingleResponse<ProductLimit> registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return (SingleResponse<ProductLimit>) commandBus.send(creditLimitRegisterCmd);
    }

    @Override
    public Response freezeQuota(QuotaFreezeCmd quotaFreezeCmd) {
        return commandBus.send(quotaFreezeCmd);
    }

    @Override
    public Response unfreezeQuota(QuotaUnfreezeCmd quotaUnfreezeCmd) {
        return commandBus.send(quotaUnfreezeCmd);
    }

    @Override
    public Response subtractQuota(QuotaSubtractionCmd quotaSubtractionCmd) {
        return commandBus.send(quotaSubtractionCmd);
    }

    @Override
    public Response recoverQuota(QuotaRecoveryCmd quotaRecoveryCmd) {
        return commandBus.send(quotaRecoveryCmd);
    }

    @Override
    public Response freezeProductQuotaBySys(ProductQuotaSysFreezeCmd productQuotaSysFreezeCmd) {
        return commandBus.send(productQuotaSysFreezeCmd);
    }

    @Override
    public Response freezeProductQuotaByMan(ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd) {
        return commandBus.send(productQuotaManualFreezeCmd);
    }

    @Override
    public Response unfreezeProductQuota(ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd) {
        return commandBus.send(productQuotaUnfreezeCmd);
    }

    @Override
    public Response forceUnfreezeProductQuota(ProductQuotaForcedUnfreezeCmd productQuotaUnfreezeCmd) {
        return commandBus.send(productQuotaUnfreezeCmd);
    }

    @Override
    public Response inactivateProductQuota(ProductQuotaInactiveCmd productQuotaInactiveCmd) {
        return commandBus.send(productQuotaInactiveCmd);
    }

    @Override
    public Response activateProductQuota(ProductQuotaActivateCmd productQuotaActivateCmd) {
        return commandBus.send(productQuotaActivateCmd);
    }

    @Override
    public Response abandonProductQuota(ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd) {
        return commandBus.send(productQuotaAbandonmentCmd);
    }

    @Override
    public Response changeQuota(QuotaChangeCmd qotaChangeCmd) {
        return commandBus.send(qotaChangeCmd);
    }

    @Override
    public SingleResponse<CustomerLimit> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd) {
        return (SingleResponse<CustomerLimit>) commandBus.send(customerLimitQryCmd);
    }

    @Override
    public SingleResponse<ProductLimit> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd) {
        return (SingleResponse<ProductLimit>) commandBus.send(productLimitQryCmd);
    }
}
