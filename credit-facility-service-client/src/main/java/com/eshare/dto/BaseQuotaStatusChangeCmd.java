package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 额度状态变更
 * @Author Evan Leung
 **/
@Data
public class BaseQuotaStatusChangeCmd extends Command {


    private Long accountId;
    /**
     * 外部订单ID
     */
    @NotBlank
    private String applicationId;
    /**
     * 命令类型
     */
    @Max(1)
    @Min(1)
    private Integer commandType;
}
