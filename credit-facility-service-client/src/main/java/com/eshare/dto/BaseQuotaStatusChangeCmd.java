package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 额度状态变更
 * @Author Evan Leung
 **/
@Data
public class BaseQuotaStatusChangeCmd extends Command {

    @NotNull
    private Long quotaAccount;
    /**
     * 外部订单ID
     */
    @NotBlank
    private String applicationId;
}
