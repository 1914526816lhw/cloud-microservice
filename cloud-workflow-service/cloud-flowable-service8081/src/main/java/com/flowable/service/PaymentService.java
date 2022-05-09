package com.flowable.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lihw
 * @className PaymentService
 * @date 2022-05-09 17:47
 * @description
 */
@Service
public class PaymentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JSONObject queryPayment() {
        String sql = "select * from payment";
        return jdbcTemplate.queryForList(sql, JSONObject.class).get(0);
    }
}
