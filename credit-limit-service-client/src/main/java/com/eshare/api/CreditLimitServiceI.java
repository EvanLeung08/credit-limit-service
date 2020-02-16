package com.eshare.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;

/**
 * @Author Evan Leung
 * <p>
 * 额度服务接口
 */
public interface CreditLimitServiceI {

    public SingleResponse<ProductLimit> registerProductLimit(CreditLimitRegisterCmd creditLimitRegisterCmd);

    public Response freezeQuota(QuotaFreezeCmd quotaFreezeCmd);

    public Response unfreezeQuota(QuotaUnfreezeCmd quotaUnfreezeCmd);

    public Response subtractQuota(QuotaSubtractionCmd quotaSubtractionCmd);

    public Response recoverQuota(QuotaRecoveryCmd quotaRecoveryCmd);

    public Response freezeProductQuotaBySys(ProductQuotaSysFreezeCmd productQuotaSysFreezeCmd);

    public Response freezeProductQuotaByMan(ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd);

    public Response unfreezeProductQuota(ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd);

    public Response forceUnfreezeProductQuota(ProductQuotaForcedUnfreezeCmd productQuotaUnfreezeCmd);

    public Response inactivateProductQuota(ProductQuotaInactiveCmd productQuotaInactiveCmd);

    public Response activateProductQuota(ProductQuotaActivateCmd productQuotaActivateCmd);

    public Response abandonProductQuota(ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd);

    public Response changeQuota(QuotaChangeCmd qotaChangeCmd);

    public SingleResponse<CustomerLimit> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd);

    public SingleResponse<ProductLimit> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd);

}
