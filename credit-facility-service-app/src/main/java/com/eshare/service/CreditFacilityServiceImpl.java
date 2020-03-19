package com.eshare.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.eshare.QuotaStatusUpdateCmdTypeEnum;
import com.eshare.QuotaUpdateCmdTypeEnum;
import com.eshare.api.CreditFacilityServiceI;
import com.eshare.dto.*;
import com.eshare.dto.clientobject.CustomerLimitCO;
import com.eshare.dto.clientobject.ProductLimitCO;
import org.springframework.beans.BeanUtils;
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
    public SingleResponse<ProductLimitCO> registerAccount(CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return (SingleResponse<ProductLimitCO>) commandBus.send(creditLimitRegisterCmd);
    }



    @Override
    public Response dispatchQuotaChangeCmd(BaseQuotaAmountUpdateCmd cmd) {
        QuotaUpdateCmdTypeEnum quotaUpdateCmdTypeEnum = QuotaUpdateCmdTypeEnum.fromValue(cmd.getCommandType());
        switch (quotaUpdateCmdTypeEnum) {
            case CHANGE_QUOTA:
                QuotaChangeCmd quotaChangeCmd = new QuotaChangeCmd();
                BeanUtils.copyProperties(cmd, quotaChangeCmd);
                return commandBus.send(quotaChangeCmd);
            case FREEZE_QUOTA:
                QuotaFreezeCmd quotaFreezeCmd = new QuotaFreezeCmd();
                BeanUtils.copyProperties(cmd, quotaFreezeCmd);
                return commandBus.send(quotaFreezeCmd);
            case UNFREEZE_QUOTA:
                QuotaUnfreezeCmd quotaUnfreezeCmd = new QuotaUnfreezeCmd();
                BeanUtils.copyProperties(cmd, quotaUnfreezeCmd);
                return commandBus.send(quotaUnfreezeCmd);
            case SUBTRACT_QUOTA:
                QuotaSubtractionCmd quotaSubtractionCmd = new QuotaSubtractionCmd();
                BeanUtils.copyProperties(cmd, quotaSubtractionCmd);
                return commandBus.send(quotaSubtractionCmd);

            case RECOVER_QUOTA:
                QuotaRecoveryCmd quotaRecoveryCmd = new QuotaRecoveryCmd();
                BeanUtils.copyProperties(cmd, quotaRecoveryCmd);
                return commandBus.send(quotaRecoveryCmd);
            default:
                throw new BizException("Command Type Not Found, uniqueIdentity=" + cmd.getBizScenario().getUniqueIdentity());
        }
    }

    @Override
    public Response dispatchQuotaStatusChangeCmd(BaseQuotaStatusChangeCmd cmd) {
        QuotaStatusUpdateCmdTypeEnum quotaStatusUpdateCmdTypeEnum = QuotaStatusUpdateCmdTypeEnum.fromValue(cmd.getCommandType());
        switch (quotaStatusUpdateCmdTypeEnum) {
            case SYS_FREEZE:
                ProductQuotaSysFreezeCmd poductQuotaSysFreezeCmd = new ProductQuotaSysFreezeCmd();
                BeanUtils.copyProperties(cmd, poductQuotaSysFreezeCmd);
                return commandBus.send(poductQuotaSysFreezeCmd);
            case MANUAL_FREEZE:
                ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd = new ProductQuotaManualFreezeCmd();
                BeanUtils.copyProperties(cmd, productQuotaManualFreezeCmd);
                return commandBus.send(productQuotaManualFreezeCmd);
            case UNFREEZE:
                ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd = new ProductQuotaUnfreezeCmd();
                BeanUtils.copyProperties(cmd, productQuotaUnfreezeCmd);
                return commandBus.send(productQuotaUnfreezeCmd);
            case FORCED_UNFREEZE:
                ProductQuotaForcedUnfreezeCmd productQuotaForcedUnfreezeCmd = new ProductQuotaForcedUnfreezeCmd();
                BeanUtils.copyProperties(cmd, productQuotaForcedUnfreezeCmd);
                return commandBus.send(productQuotaForcedUnfreezeCmd);

            case ACTIVE:
                ProductQuotaActivateCmd productQuotaActivateCmd = new ProductQuotaActivateCmd();
                BeanUtils.copyProperties(cmd, productQuotaActivateCmd);
                return commandBus.send(productQuotaActivateCmd);
            case INACTIVE:
                ProductQuotaInactiveCmd productQuotaInactiveCmd = new ProductQuotaInactiveCmd();
                BeanUtils.copyProperties(cmd, productQuotaInactiveCmd);
                return commandBus.send(productQuotaInactiveCmd);
            case ABANDON:
                ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd = new ProductQuotaAbandonmentCmd();
                BeanUtils.copyProperties(cmd, productQuotaAbandonmentCmd);
                return commandBus.send(productQuotaAbandonmentCmd);
            default:
                throw new BizException("Command Type Not Found, uniqueIdentity=" + cmd.getBizScenario().getUniqueIdentity());
        }
    }

    @Override
    public SingleResponse<CustomerLimitCO> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd) {
        return (SingleResponse<CustomerLimitCO>) commandBus.send(customerLimitQryCmd);
    }

    @Override
    public SingleResponse<ProductLimitCO> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd) {
        return (SingleResponse<ProductLimitCO>) commandBus.send(productLimitQryCmd);
    }
}
