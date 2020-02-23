package com.eshare.domain;

import java.io.Serializable;

public interface Measurable extends Serializable {

    /**
     * 计算可用额度
     * @return
     */
    public double calculateBalance();

    /**
     * 计算已经用额度
     * @return
     */
    public double calculateOccupancy();
}
