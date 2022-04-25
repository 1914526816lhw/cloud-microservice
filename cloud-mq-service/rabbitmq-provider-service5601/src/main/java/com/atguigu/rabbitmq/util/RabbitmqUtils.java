package com.atguigu.rabbitmq.util;

/**
 * @author 李宏伟
 * @version 1.0
 * @ClassName RabbitmqUtils
 * @Description
 * @date 2022年04月25日 11:27
 */

public class RabbitmqUtils {

    //队列 queue
    public class RabbitmqQueues {
        public static final String TEST_QUEUE = "TEST-QUEUE";
    }

    //交换机 exchange
    public class RabbitmqExchanges{
        public static final String TEST_EXCHANGE = "TEST-EXCHANGE";
    }

    //路由键 routingKey
    public class RabbitmqRoutingKeys {
        public static final String TEST_ROUTE_KEY = "TEST-ROUTE-KEY";
    }
}
