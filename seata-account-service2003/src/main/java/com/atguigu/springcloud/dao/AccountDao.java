package com.atguigu.springcloud.dao;


import java.math.BigDecimal;

public interface AccountDao {

    /**
     * 扣减账户余额
     */
    void decreaseAccount(Long userId, BigDecimal money);
}
 
