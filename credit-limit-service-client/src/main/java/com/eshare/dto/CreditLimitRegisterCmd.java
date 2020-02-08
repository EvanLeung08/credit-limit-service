package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import lombok.Data;

/**
 * @Author Evan Leung
 * <p>
 * 额度注册命令
 */
@Data
public class CreditLimitRegisterCmd extends Command {
    private RegistrationLimitCO registrationLimitCO;
}
