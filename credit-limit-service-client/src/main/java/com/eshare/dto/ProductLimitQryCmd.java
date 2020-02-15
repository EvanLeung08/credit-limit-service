package com.eshare.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;


/**
 * @Author Evan Leung
 * 产品额度查询指令
 **/
@Data
public class ProductLimitQryCmd extends Query {

    /**
     * 卡号
     */
    private Long cardId;
}
