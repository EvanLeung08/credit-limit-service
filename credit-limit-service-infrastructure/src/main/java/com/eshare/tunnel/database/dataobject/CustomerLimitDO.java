package com.eshare.tunnel.database.dataobject;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.util.Date;

/**
 * @Author Evan Leung
 *
 * 客户额度数据对象
 */
@Data
@Entity
public class CustomerLimitDO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date editTime;
    /**
     * 软删除标识 1:删除,0:正常
     */
    private Integer deleted;
    /**
     * 版本号
     */
    private Long version;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 客户id
     */
    private Long customerId;
    /**
     * 总额度
     */
    private Integer quotaLimit;
    /**
     * 剩余额度
     */
    private Integer quotaBalance;
    /**
     * 占用额度
     */
    private Integer quotaOccupancy;
    /**
     * 冻结额度
     */
    private Integer quotaFrozen;
    /**
     * 基础额度
     */
    private Integer quotaBase;
    /**
     * 更变额度
     */
    private Integer quotaChange;
    /**
     * 额度类型
     */
    private String quotaType;
    /**
     * 冻结状态,1:正常,2:系统冻结,3:人工冻结
     */
    private Integer frozenStatus;
    /**
     * 冻结日期
     */
    private Date frozenTime;
    /**
     * 过期状态,1:正常,0:过期
     */
    private Integer expireStatus;
    /**
     * 冻结日期
     */
    private Date expirationTime;
    /**
     * 是否可用 1:可用,0:不可用
     */
    private Integer activeStatus;
    /**
     * 禁用日期
     */
    private Date inactiveTime;
    /**
     * 父节点id
     */
    private Long parentId;
}
