package com.atguigu.springcloud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName StorageDaoImpl
 * @Description
 * @date 2021年12月07日 17:12
 */
@Repository
public class StorageDaoImpl implements StorageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void decreaseStorage(Long productId, Integer count) {
        String sql = "update t_storage set used = used + ?, residue = residue - ? where product_id = ?";
        jdbcTemplate.update(sql, new Object[]{count, count, productId});
    }
}
