package com.eshare.tunnel.database.repository;

import com.alibaba.cola.exception.BizException;
import com.eshare.common.BizCode;
import com.eshare.domain.constant.*;
import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.tunnel.database.ProductLimitTunnel;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
     * @param cardId 卡id 和流水通过cardId一对一关联
     * @return
     */
    public ProductLimit find(Long cardId) {
        cardId = Preconditions.checkNotNull(cardId, "quotaLimit can not null");
        ProductLimit productLimit = new ProductLimit();
        productLimit.setCardId(cardId);
        ProductLimit result = productLimitTunnel.selectByModelSelective(productLimit, true);
        if (result == null) {
            throw new BizException(cardId + "");
        }
        return result;
    }

    /**
     * 查找可用的卡
     *
     * @param cardId
     * @return
     */
    public ProductLimit findWithNormal(Long cardId) {
        ProductLimit quota = find(cardId);

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
     * @param userId         userId
     * @param quota          额度
     * @param productCode    产品code
     * @param expirationTime 过期时间
     * @param cardType       卡类型 1:一次性 2:循环授信
     * @return 入库的数据
     */
    public ProductLimit save(Long userId,
                             Long customerId,
                             Integer quota,
                             String productCode,
                             Date expirationTime,
                             Integer cardType) {
        Preconditions.checkNotNull(userId, "userId can not null");
        Preconditions.checkNotNull(quota, "quotaLimit can not null");
        Preconditions.checkNotNull(productCode, "productCode can not null");
        Preconditions.checkNotNull(customerId, "customerId can not null");
        CardTypeEnum.fromValue(cardType);
        if (Objects.isNull(expirationTime)) {
            expirationTime = Constant.MAX_EXPIRATION_DATA;
        }

        ProductLimit productLimit = new ProductLimit();
        productLimit.setId(idGenerator.generateId());
        productLimit.setCardId(idGenerator.generateId());
        productLimit.setSerialNumber(idGenerator.generateId() + "");

        productLimit.setUserId(userId);
        productLimit.setCustomerId(customerId);
        productLimit.setQuotaLimit(quota);
        productLimit.setQuotaBalance(quota);
        productLimit.setQuotaBase(quota);
        productLimit.setProductCode(productCode);
        productLimit.setQuotaMode(cardType);
        productLimit.setExpirationTime(expirationTime);
        productLimitTunnel.insertSelective(productLimit);
        return productLimit;
    }

    /**
     * 冻结额度
     *
     * @param quota  产品额度
     * @param amount 冻结的额度
     */
    public void freezeAmount(ProductLimit quota, Integer amount) {
        Preconditions.checkNotNull(quota.getCardId(), "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(quota.getCardId());
        cond.setVersion(quota.getVersion());
        ProductLimit model = new ProductLimit();
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
    public void unfreezeAmount(ProductLimit quota, Integer amount) {

        Preconditions.checkNotNull(quota.getCardId(), "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(quota.getCardId());
        cond.setVersion(quota.getVersion());

        ProductLimit model = new ProductLimit();
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
    public void subtract(ProductLimit quota, Integer amount) {
        Preconditions.checkNotNull(quota.getCardId(), "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(quota.getCardId());
        cond.setVersion(quota.getVersion());

        ProductLimit model = new ProductLimit();
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
        if (quota.getQuotaMode().equals(CardTypeEnum.DISPOSABLE_CREDIT.getValue())) {
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
    public void recover(ProductLimit quota, Integer amount) {
        Preconditions.checkNotNull(quota.getCardId(), "cardId can not null");
        ProductLimit cond = new ProductLimit();
        cond.setCardId(quota.getCardId());
        cond.setVersion(quota.getVersion());

        ProductLimit model = new ProductLimit();
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
    public void changeQuota(ProductLimit quota, Integer amount) {
        Preconditions.checkNotNull(quota.getCardId(), "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(quota.getCardId());
        cond.setVersion(quota.getVersion());

        ProductLimit model = new ProductLimit();
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
     * @param productLimit
     * @param frozenStatusEnum
     */
    public void freeze(ProductLimit productLimit, FrozenStatusEnum frozenStatusEnum) {
        switch (FrozenStatusEnum.fromValue(productLimit.getFrozenStatus())) {
            case SYSTEM_FROZEN: frozenStatusEnum = FrozenStatusEnum.ALL_FROZEN; break;
            case MANUAL_FROZEN: frozenStatusEnum = FrozenStatusEnum.ALL_FROZEN; break;
            case NORMAL: break;
            default: return;
        }
        this.freezeStatus(productLimit.getCardId(), frozenStatusEnum);
    }

    public void freezeStatus(Long cardId, FrozenStatusEnum frozenStatusEnum) {
        Preconditions.checkNotNull(cardId, "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(cardId);

        ProductLimit model = new ProductLimit();
        model.setFrozenStatus(frozenStatusEnum.getValue());
        model.setFrozenTime(new Date());
        productLimitTunnel.updateByModelSelective(cond, true, model);
    }
    /**
     * 更改是否可用状态
     *
     * @param cardId
     * @param active
     */
    public void changeActiveStatus(Long cardId, ActiveStatusEnum active) {
        Preconditions.checkNotNull(cardId, "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(cardId);

        ProductLimit model = new ProductLimit();
        model.setActiveStatus(active.getValue());
        model.setInactiveTime(new Date());
        productLimitTunnel.updateByModelSelective(cond, true, model);
    }

    /**
     * 更改是否废弃状态
     *
     * @param cardId
     * @param abandonStatusEnum
     */
    public void abandonStatus(Long cardId, AbandonStatusEnum abandonStatusEnum) {
        Preconditions.checkNotNull(cardId, "cardId can not null");

        ProductLimit cond = new ProductLimit();
        cond.setCardId(cardId);

        ProductLimit model = new ProductLimit();
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
        Preconditions.checkNotNull(customerId, "customerId can not null");
        Integer sum = productLimitTunnel.sumQuota(customerId);
        return sum == null ? 0 : sum;
    }
}
