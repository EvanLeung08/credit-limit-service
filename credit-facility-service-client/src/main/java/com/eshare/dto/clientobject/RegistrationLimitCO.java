package com.eshare.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author Evan Leung
 *
 * 额度注册客户对象
 */
@Data
public class RegistrationLimitCO extends ClientObject {

    /**
     * 申请ID
     */
    @NotBlank
    private String applicationId;
    /**
     * 用户ID
     */
    @NotNull
    private Long userId;
    /**
     * 产品编号
     */
    @Max(5000000)
    @Min(10000)
    private Integer quotaLimit;
    /**
     * 产品编号
     */
    @Length(min = 1, max = 20)
    private String productCode;
    /**
     * 产品额度过期日期,非必填
     */
    private Date expirationTime;
    /**
     * 卡类型,1:一次性授信,2:循环授信
     */
    @Max(2)
    @Min(1)
    private Integer accountType;

}
