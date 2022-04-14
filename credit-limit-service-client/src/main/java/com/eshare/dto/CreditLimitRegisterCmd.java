package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author Evan Leung
 * <p>
 * 额度注册命令
 */
@Data
public class CreditLimitRegisterCmd extends Command {
    @NotNull
    private RegistrationLimitCO registrationLimitCO;
}
