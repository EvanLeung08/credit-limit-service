package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Evan Leung
 **/
@Data
public class BaseQuotaAmountUpdateCmd extends Command {

    /**
     * 申请ID号
     */
    @NotBlank
    private String applicationId;
    /**
     * 命令类型 1.变更额度 2.冻结额度 3.解冻额度 4.恢复额度
     */
    @Min(1)
    @Max(1)
    private Integer commandType;
    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 操作金额
     */
    @Min(100)
    @Max(5000000)
    private Integer operationAmount;
    /**
     * 操作日期,非必填
     */
    private Date operationTime;
}
