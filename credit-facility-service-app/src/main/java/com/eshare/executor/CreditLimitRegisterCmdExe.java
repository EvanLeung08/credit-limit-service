package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.eshare.common.BizCode;
import com.eshare.domain.gateway.CustomerGateway;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import com.eshare.tunnel.database.dataobject.CustomerLimitDO;
import com.eshare.dto.domainmodel.ProductLimit;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 * <p>
 * 客户注册命令执行类
 */
@Command
public class CreditLimitRegisterCmdExe implements CommandExecutorI<SingleResponse<ProductLimit>, CreditLimitRegisterCmd> {

    private final ProductLimitRepository productLimitRepository;
    private final CustomerLimitRepository customerLimitRepository;
    private final CustomerGateway customerGateway;

    @Autowired
    public CreditLimitRegisterCmdExe(ProductLimitRepository productLimitRepository, CustomerLimitRepository customerLimitRepository, CustomerGateway customerGateway) {
        this.productLimitRepository = productLimitRepository;
        this.customerLimitRepository = customerLimitRepository;
        this.customerGateway = customerGateway;
    }

    @Override
    public SingleResponse<ProductLimit> execute(CreditLimitRegisterCmd cmd) {
        ProductLimit productLimitRsp = new ProductLimit();
        RegistrationLimitCO registrationLimitCO = cmd.getRegistrationLimitCO();
        //RegistrationLimit registrationLimit = new RegistrationLimit();
        ProductLimitDO productLimitDO = new ProductLimitDO();
        BeanUtils.copyProperties(registrationLimitCO, productLimitDO);
        Long customerId = customerGateway.getCustomerId(registrationLimitCO.getUserId());
        productLimitDO.setCustomerId(customerId);
        CustomerLimitDO customerLimitDO = customerLimitRepository.find(customerId);
        if (customerLimitDO == null) {
            customerLimitDO = customerLimitRepository.init(customerId);
        }
        int cardQuota = productLimitRepository.sumQuota(customerId);
        if (customerLimitDO.getQuotaLimit() < cardQuota + registrationLimitCO.getQuotaLimit()) {
            throw new BizException(BizCode.BIZ_ONE);
        }

        // 2. 保存额度
        productLimitDO = productLimitRepository.save(productLimitDO);
        //3. 转换领域对象到dto
        BeanUtils.copyProperties(productLimitDO, productLimitRsp);
        return SingleResponse.of(productLimitRsp);
    }
}
