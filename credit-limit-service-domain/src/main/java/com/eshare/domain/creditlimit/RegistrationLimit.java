package com.eshare.domain.creditlimit;

import com.alibaba.cola.domain.Entity;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import lombok.Data;

import java.util.Date;

/**
 * @Author Evan Leung
 * <p>
 * 额度注册客户对象
 */
@Data
@Entity
public class RegistrationLimit extends RegistrationLimitCO {
    private Long customerId;

}
