package com.atguigu.test;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author lihw
 * @className TestSnowFlake
 * @date 2022-06-17 10:32
 * @description
 */

public class TestSnowFlake {

    public static void main(String[] args) {

        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long distributionId  = snowflake.nextId();
        System.out.println("distributionId = " + distributionId);
    }
}
