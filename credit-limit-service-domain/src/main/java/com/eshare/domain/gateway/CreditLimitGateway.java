package com.eshare.domain.gateway;

import com.eshare.domain.constant.AbandonStatusEnum;
import com.eshare.domain.constant.ActiveStatusEnum;
import com.eshare.domain.constant.FrozenStatusEnum;
import com.eshare.domain.creditlimit.ProductLimit;

import java.util.Date;

public interface CreditLimitGateway {
    /**
     * 查询额度
     *
     * @param cardId
     * @return
     */
    public ProductLimit find(Long cardId);

    /**
     * 查询可用卡
     *
     * @param cardId
     * @return
     */
    public ProductLimit findAvailableCard(Long cardId);

    /**
     * 保存产品额度
     *
     * @param userId         userId
     * @param limitAmount    额度
     * @param productCode    产品code
     * @param expirationTime 过期时间
     * @param cardType       卡类型 1:一次性 2:循环授信
     * @return ProductLimit 额度数据
     */
    public ProductLimit save(Long userId,
                             Long customerId,
                             Integer limitAmount,
                             String productCode,
                             Date expirationTime,
                             Integer cardType);

    /**
     * 冻结产品额度
     *
     * @param productLimit 产品额度
     * @param amount       冻结的额度
     */
    public void freezeProductLimit(ProductLimit productLimit, Integer amount);

    /**
     * 解冻产品额度
     *
     * @param productLimit  产品额度
     * @param amount 解冻的额度
     */
    public void unfreeProductLimit(ProductLimit productLimit, Integer amount);
    /**
     * 扣减产品额度
     *
     * @param productLimit  产品额度
     * @param amount 扣减的额度
     */
    public void subtractProductLimit(ProductLimit productLimit, Integer amount);

    /**
     * 恢复产品额度
     * @param productLimit 产品额度
     * @param amount 恢复金额
     */
    public void recoverProductLimit(ProductLimit productLimit, Integer amount);

    /**
     * 变更产品额度
     * @param productLimit 产品额度
     * @param amount 金额
     */
    public void updateProductLimit(ProductLimit productLimit, Integer amount);

    /**
     * 更新冻结状态
     * @param productLimit 产品额度
     * @param frozenStatusEnum 金额
     */
    public void updateFrozenStatus(ProductLimit productLimit, FrozenStatusEnum frozenStatusEnum);

    /**
     * 变更激活状态
     * @param cardId 卡ID
     * @param active 激活状态
     */
    public void updateActiveStatus(Long cardId, ActiveStatusEnum active);

    /**
     * 变更是否废弃状态
     * @param cardId 卡ID
     * @param abandonStatusEnum 废弃状态
     */
    public void updateAandonStatus(Long cardId, AbandonStatusEnum abandonStatusEnum );

    /**
     * 计算客户的总额度
     * @param customId 客户ID
     * @return
     */
    public int countCustomerLimitAmount(Long customId);
}
