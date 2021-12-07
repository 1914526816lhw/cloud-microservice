package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName OrderDaoImpl
 * @Description
 * @date 2021年12月07日 16:38
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createOrder(Order order) {
        String sql = "insert into t_order (user_id,product_id,count,money,state) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, ps -> {
            ps.setLong(1, order.getUserId());
            ps.setLong(2, order.getProductId());
            ps.setInt(3, order.getCount());
            ps.setBigDecimal(4, order.getMoney());
            ps.setInt(5, order.getState());
        });
    }

    @Override
    public void updateOrderState(Long userId, Integer state) {
        String sql = "update t_order set state = 1 where user_id= ? and state = ? ";
        jdbcTemplate.update(sql, new Object[]{userId, state});
    }
}
