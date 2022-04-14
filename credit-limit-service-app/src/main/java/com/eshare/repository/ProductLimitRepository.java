package com.eshare.repository;

import com.alibaba.cola.exception.BizException;
import com.eshare.common.BizCode;
import com.eshare.domain.constant.*;
import com.eshare.tunnel.database.ProductLimitTunnel;
import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import com.eshare.common.utils.IdGenerator;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.Objects;

@Repository
public class ProductLimitRepository {

    private final ProductLimitTunnel productLimitTunnel;
    private final IdGenerator idGenerator;

    @Autowired
    public ProductLimitRepository(ProductLimitTunnel productLimitTunnel,
                                  IdGenerator idGenerator) {
        this.productLimitTunnel = productLimitTunnel;
        this.idGenerator = idGenerator;
    }

    /**
     * 查询额度
     *
     * @param accountId 卡id 和流水通过cardId一对一关联
     * @return
     */
    public ProductLimitDO find(Long accountId) {
        ProductLimitDO productLimitDO = new ProductLimitDO();
        productLimitDO.setAccountId(accountId);
        ProductLimitDO result = productLimitTunnel.selectByModelSelective(productLimitDO, true);
        if (result == null) {
            throw new BizException(accountId + "");
        }
        return result;
    }

    /**
     * 查找可用的卡
     *
     * @param accountId
     * @return
     */
    public ProductLimitDO findWithNormal(Long accountId) {
        ProductLimitDO quota = find(accountId);

        if (AbandonStatusEnum.ABANDONED.getValue().equals(quota.getAbandoned())) {
            throw new BizException("");
        }
        if (ActiveStatusEnum.INACTIVE.getValue().equals(quota.getActiveStatus())) {
            throw new BizException("");
        }
        long now = System.currentTimeMillis();
        if (now > quota.getExpirationTime().getTime()) {
            throw new BizException("");
        }
        if (!FrozenStatusEnum.NORMAL.getValue().equals(quota.getFrozenStatus())) {
            throw new BizException("");
        }
        return quota;
    }

    /**
     * 保存产品额度
     *
     * @param productLimitDO 注册额度
     * @return 入库的数据
     */
    public ProductLimitDO save(ProductLimitDO productLimitDO) {
        if (Objects.isNull(productLimitDO.getExpirationTime())) {
            productLimitDO.setExpirationTime(Constant.MAX_EXPIRATION_DATA);
        }

        productLimitDO.setId(idGenerator.generateId());
        productLimitDO.setAccountId(idGenerator.generateId());
        productLimitDO.setSerialNumber(idGenerator.generateId() + "");

        productLimitTunnel.insertSelective(productLimitDO);
        return productLimitDO;
    }


    /**
     * 冻结额度
     *
     * @param quota  产品额度
     * @param amount 冻结的额度
     */
    public void freezeAmount(ProductLimitDO quota, Integer amount) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(quota.getAccountId());
        cond.setVersion(quota.getVersion());
        ProductLimitDO model = new ProductLimitDO();
        Integer quotaBalance = quota.getQuotaBalance() - amount;
        // 判断剩余额度
        if (quotaBalance < 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
        // 增加冻结额度
        model.setQuotaFrozen(quota.getQuotaFrozen() + amount);
        // 扣减剩余额度
        model.setQuotaBalance(quotaBalance);
        // 版本号+1
        model.setVersion(quota.getVersion() + 1);
        // 更新失败抛出
        if (productLimitTunnel.updateByModelSelective(cond, true, model) <= 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
    }


    /**
     * 冻结额度
     *
     * @param quota  产品额度
     * @param amount 解冻的额度
     */
    public void unfreezeAmount(ProductLimitDO quota, Integer amount) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(quota.getAccountId());
        cond.setVersion(quota.getVersion());

        ProductLimitDO model = new ProductLimitDO();
        Integer quotaFrozen = quota.getQuotaFrozen() - amount;
        // 判断剩余额度
        if (quotaFrozen < 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
        // 减少冻结额度
        model.setQuotaFrozen(quotaFrozen);
        // 增加剩余额度
        model.setQuotaBalance(quota.getQuotaBalance() + amount);
        // 版本号+1
        model.setVersion(quota.getVersion() + 1);
        // 更新失败抛出
        if (productLimitTunnel.updateByModelSelective(cond, true, model) <= 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
    }

    /**
     * 扣减额度
     *
     * @param quota  产品额度
     * @param amount 扣减的额度
     */
    public void subtract(ProductLimitDO quota, Integer amount) {
        Preconditions.checkNotNull(quota.getAccountId(), "accountId can not null");

        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(quota.getAccountId());
        cond.setVersion(quota.getVersion());

        ProductLimitDO model = new ProductLimitDO();
        Integer quotaFrozen = quota.getQuotaFrozen() - amount;
        // 判断剩余额度
        if (quotaFrozen < 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
        // 减少冻结额度，从预减额度（冻结的金额）中将金额减去
        model.setQuotaFrozen(quotaFrozen);
        // 已用金额增加
        model.setQuotaOccupancy(quota.getQuotaOccupancy() + amount);
        // 版本号+1
        model.setVersion(quota.getVersion() + 1);
        if (quota.getQuotaMode().equals(AccountTypeEnum.DISPOSABLE_CREDIT.getValue())) {
            model.setAbandoned(AbandonStatusEnum.ABANDONED.getValue());
        }
        // 更新失败抛出
        if (productLimitTunnel.updateByModelSelective(cond, true, model) <= 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
    }

    /**
     * 恢复额度
     *
     * @param quota  产品额度
     * @param amount 恢复金额
     */
    public void recover(ProductLimitDO quota, Integer amount) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(quota.getAccountId());
        cond.setVersion(quota.getVersion());

        ProductLimitDO model = new ProductLimitDO();
        model.setQuotaBalance(quota.getQuotaBalance() + amount);
        Integer occupancy = quota.getQuotaOccupancy() - amount;
        model.setQuotaOccupancy(occupancy < 0 ? 0 : occupancy);
        model.setVersion(quota.getVersion() + 1);

        // 更新失败抛出
        if (productLimitTunnel.updateByModelSelective(cond, true, model) <= 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }
    }

    /**
     * 提额、降额
     *
     * @param quota  产品额度
     * @param amount 更新后的额度
     */
    public void changeQuota(ProductLimitDO quota, Integer amount) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(quota.getAccountId());
        cond.setVersion(quota.getVersion());

        ProductLimitDO model = new ProductLimitDO();
        // 由于额度总额更改，重新计算剩余额度，有可能为负值
        model.setQuotaBalance(amount - quota.getQuotaFrozen() - quota.getQuotaOccupancy());
        model.setQuotaChange(amount);
        model.setQuotaLimit(amount);
        model.setVersion(quota.getVersion() + 1);
        // 更新失败抛出
        if (productLimitTunnel.updateByModelSelective(cond, true, model) <= 0) {
            throw new BizException(BizCode.BIZ_ONE);
        }

    }

    /**
     * 更改冻结状态
     *
     * @param productLimitDO
     * @param frozenStatusEnum
     */
    public void freeze(ProductLimitDO productLimitDO, FrozenStatusEnum frozenStatusEnum) {
        switch (FrozenStatusEnum.fromValue(productLimitDO.getFrozenStatus())) {
            case SYSTEM_FROZEN:
                frozenStatusEnum = FrozenStatusEnum.ALL_FROZEN;
                break;
            case MANUAL_FROZEN:
                frozenStatusEnum = FrozenStatusEnum.ALL_FROZEN;
                break;
            case NORMAL:
                break;
            default:
                return;
        }
        this.freezeStatus(productLimitDO.getAccountId(), frozenStatusEnum);
    }

    public void freezeStatus(Long accountId, FrozenStatusEnum frozenStatusEnum) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(accountId);

        ProductLimitDO model = new ProductLimitDO();
        model.setFrozenStatus(frozenStatusEnum.getValue());
        model.setFrozenTime(new Date());
        productLimitTunnel.updateByModelSelective(cond, true, model);
    }

    /**
     * 更改是否可用状态
     *
     * @param accountId
     * @param active
     */
    public void changeActiveStatus(Long accountId, ActiveStatusEnum active) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(accountId);

        ProductLimitDO model = new ProductLimitDO();
        model.setActiveStatus(active.getValue());
        model.setInactiveTime(new Date());
        productLimitTunnel.updateByModelSelective(cond, true, model);
    }

    /**
     * 更改是否废弃状态
     *
     * @param accountId
     * @param abandonStatusEnum
     */
    public void abandonStatus(Long accountId, AbandonStatusEnum abandonStatusEnum) {
        ProductLimitDO cond = new ProductLimitDO();
        cond.setAccountId(accountId);

        ProductLimitDO model = new ProductLimitDO();
        model.setAbandoned(abandonStatusEnum.getValue());
        productLimitTunnel.updateByModelSelective(cond, true, model);
    }

    /**
     * 计算客户所有卡的总额度
     *
     * @param customerId
     * @return
     */
    public int sumQuota(Long customerId) {
        Integer sum = productLimitTunnel.sumQuota(customerId);
        return sum == null ? 0 : sum;
    }
}
