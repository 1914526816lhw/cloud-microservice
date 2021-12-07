package com.atguigu.springcloud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName AccountDaoImpl
 * @Description
 * @date 2021年12月07日 17:20
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void decreaseAccount(Long userId, BigDecimal money) {
        String sql = "update t_account set residue = residue - ?,used = used + ? where user_id = ?";
        jdbcTemplate.update(sql, new Object[]{money, money, userId});
    }
}
